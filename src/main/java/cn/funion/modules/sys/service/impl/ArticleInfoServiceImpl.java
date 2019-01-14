package cn.funion.modules.sys.service.impl;

import cn.funion.common.utils.DateUtils;
import cn.funion.common.utils.HttpUtils;
import cn.funion.datasources.DataSourceNames;
import cn.funion.datasources.annotation.DataSource;
import cn.funion.modules.sys.dao.*;
import cn.funion.modules.sys.entity.*;
import cn.funion.modules.sys.form.vo.AccessTokenReq;
import com.baomidou.mybatisplus.mapper.Wrapper;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.funion.common.utils.PageUtils;
import cn.funion.common.utils.Query;

import cn.funion.modules.sys.service.ArticleInfoService;


@Service("articleInfoService")
public class ArticleInfoServiceImpl extends ServiceImpl<ArticleInfoDao, ArticleInfoEntity> implements ArticleInfoService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MemberGroupDao memberGroupDao;

    @Autowired
    private MemberTagItemDao memberTagItemDao;

    @Autowired
    private MemberFormIdDao memberFormIdDao;

    @Value("${sinz.wechat.appId}")
    private String APP_ID;

    @Value("${sinz.wechat.secret}")
    private String SECRET;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<ArticleInfoEntity> ew = new EntityWrapper<ArticleInfoEntity>();
        if(params.get("key")!=null&&!params.get("key").equals("")){
            ew.like("article_title", params.get("key").toString());
        }
        Page<ArticleInfoEntity> page = this.selectPage(
                new Query<ArticleInfoEntity>(params).getPage(),
                ew
        );

        return new PageUtils(page);
    }

    @Override
    public boolean insert(ArticleInfoEntity articleInfo){
        articleInfo.setCreatedTime(new Date());
        baseMapper.insert(articleInfo);

        //发送资讯
        String groupIds = articleInfo.getGroupIds();
        if(StringUtils.isNotEmpty(groupIds)){
            sendWechatMsg(articleInfo, groupIds);
        }

        return true;
    }

    private void sendWechatMsg(ArticleInfoEntity articleInfo, String groupIds){

        //去重
        Set<String> memberSet = new HashSet<String>();
        for (String groupId: groupIds.split(";")) {
            MemberGroupEntity group = memberGroupDao.selectById(Long.parseLong(groupId));
            String tagIds = group.getTagIds();
            if(StringUtils.isNotEmpty(tagIds)){
                String[] tagIdArray = tagIds.split(",");
                for(String tagId: tagIdArray){
                    Wrapper<MemberTagItemEntity> wrapper = new EntityWrapper<>();
                    wrapper.eq("tag_id", tagId);
                    List<MemberTagItemEntity> tagItems = memberTagItemDao.selectList(wrapper);
                    Map<String, List<String>> tagMap = wrapperTagItemMap(tagItems);
                    Wrapper<MemberEntity> wrapper1 = new EntityWrapper<>();
                    wrapper1.where("1 = 1");
                    for (Map.Entry<String, List<String>> entry : tagMap.entrySet()) {
                        List<String> valueList = entry.getValue();
                        String key = entry.getKey();
                        wrapper1.in(key, valueList);
                    }
                    List<MemberEntity> memberList =  memberDao.selectList(wrapper1);
                    for (MemberEntity member : memberList) {
                        if(member != null && member.getWeChatOpenId() != null){
                            memberSet.add(member.getWeChatOpenId().toString());
                        }
                    }
                }
            }
        }
        if(memberSet.size() > 0){
            Wrapper<MemberFormIdEntity> wrapper2 = new EntityWrapper<>();
            wrapper2.in(memberSet.size() > 0, "open_id", memberSet.toArray());
            wrapper2.orderBy("_created_time");
            List<MemberFormIdEntity> lst = memberFormIdDao.selectList(wrapper2);
            Map<String, MemberFormIdEntity> data = new HashMap<>();
            for (MemberFormIdEntity memberFormId : lst) {
                String openId = memberFormId.getOpenId();
                MemberFormIdEntity tmp = data.get(openId);
                if(tmp == null){
                    data.put(openId, memberFormId);
                }
            }
            if(data.size() > 0){
                List<Long> succIds = new ArrayList<>();
                //发送资讯
                String accessToken = getAccessToken();
                for (String openId : data.keySet()) {
                    MemberFormIdEntity memberFormId = data.get(openId);
                    boolean result = sendMsg(accessToken, openId, memberFormId.getFormId(), articleInfo);
                    if(result){
                        succIds.add(memberFormId.getId());
                    }
                }
                //删除发送码
                if(succIds.size() > 0){
                    Wrapper<MemberFormIdEntity> wrapper3 = new EntityWrapper<>();
                    wrapper3.in("id", succIds);
                    memberFormIdDao.delete(wrapper3);
                }
            }

        }
    }

    private boolean sendMsg(String accessToken, String openId, String formId, ArticleInfoEntity articleInfo){
        String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + accessToken;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", openId);
        jsonObject.put("template_id", "S1BssmPalYSIsPVDVK5PvZk_OuN0HPk2BO3Rxm0INbo");
        jsonObject.put("page", "pages/loading/loading?_news_url=" + articleInfo.getArticleUrl() + "&_article_id=" +articleInfo.getId());
        jsonObject.put("form_id", formId);
        JSONObject datas = new JSONObject();

        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", "资讯通知");
        datas.put("keyword1", keyword1);

        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", articleInfo.getArticleTitle());
        datas.put("keyword2", keyword2);

        jsonObject.put("data", datas);
        //jsonObject.put("emphasis_keyword", "keyword1.DATA");
        JSONObject result = HttpUtils.postBody(sendUrl, jsonObject,null,null);
        System.out.println("RESULT: " + result);
        String errmsg = result.getString("errmsg");
        return "ok".equalsIgnoreCase(errmsg);
    }

    private Map<String, List<String>> wrapperTagItemMap(List<MemberTagItemEntity> tagItems) {
        //根据TagItem动态封装SQL统计member数量
        Map<String,List<String>>  tagMap = new HashMap<>();
        for(MemberTagItemEntity item:tagItems){
            if(tagMap.containsKey(item.getFieldName())){
                List<String> itemValues = tagMap.get(item.getFieldName());
                itemValues.add(item.getFieldValue());
            }else{
                List<String> itemValues = new ArrayList<>();
                itemValues.add(item.getFieldValue());
                tagMap.put(item.getFieldName(),itemValues);
            }
        }
        return tagMap;
    }

    private String getAccessToken(){
        //获取accessToken
        AccessTokenReq accessTokenReq = new AccessTokenReq();
        //小程序
        accessTokenReq.setAppid(APP_ID);
        accessTokenReq.setSecret(SECRET);
        String accessTokenUrl = accessTokenReq.getRequestUrl();
        JSONObject result = HttpUtils.get(accessTokenUrl);

        String accessToken = result.getString("access_token");
        return accessToken;
    }

}
