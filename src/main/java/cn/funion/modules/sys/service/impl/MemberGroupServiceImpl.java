package cn.funion.modules.sys.service.impl;

import cn.funion.modules.sys.dao.MemberDao;
import cn.funion.modules.sys.dao.MemberTagDao;
import cn.funion.modules.sys.dao.MemberTagItemDao;
import cn.funion.modules.sys.entity.MemberEntity;
import cn.funion.modules.sys.entity.MemberTagEntity;
import cn.funion.modules.sys.entity.MemberTagItemEntity;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.funion.common.utils.PageUtils;
import cn.funion.common.utils.Query;

import cn.funion.modules.sys.dao.MemberGroupDao;
import cn.funion.modules.sys.entity.MemberGroupEntity;
import cn.funion.modules.sys.service.MemberGroupService;


@Service("memberGroupService")
public class MemberGroupServiceImpl extends ServiceImpl<MemberGroupDao, MemberGroupEntity> implements MemberGroupService {

    @Autowired
    private MemberTagItemDao memberTagItemDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MemberTagDao memberTagDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        EntityWrapper<MemberGroupEntity> ew = new EntityWrapper<MemberGroupEntity>();
        if(params.get("key")!=null&&!params.get("key").equals("")){
            ew.like("name", params.get("key").toString());
        }
        Page<MemberGroupEntity> page = this.selectPage(
                new Query<MemberGroupEntity>(params).getPage(),
                ew
        );
        List<MemberGroupEntity> list = page.getRecords();
        Set<String> tagIds = new HashSet<>();
        for(MemberGroupEntity entity: list){
            String ids = entity.getTagIds();
            if(StringUtils.isNotEmpty(ids)){
                String[] array = ids.split(",");
                for(String id:array){
                    tagIds.add(id);
                }
            }
        }
        Wrapper<MemberTagEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", tagIds);
        List<MemberTagEntity> tagList = memberTagDao.selectList(wrapper);
        Map<Long, String> tagMap = new HashMap<>();
        for(MemberTagEntity entity: tagList){
            tagMap.put(entity.getId(), entity.getName());
        }
        for(MemberGroupEntity entity: list){
          Integer num = getMemberNum(entity);
          entity.setNum(num);
          String tagNames = "";
          String ids = entity.getTagIds();
          if(StringUtils.isNotEmpty(ids)){
              String[] idArray = ids.split(",");
              for(String id:idArray){
                  String name = tagMap.get(Long.parseLong(id));
                  if(StringUtils.isNotEmpty(name)){
                      tagNames += name + ",";
                  }
              }
              if(tagNames.length() > 0){
                  tagNames = tagNames.substring(0, tagNames.length() - 1);
              }
          }
          entity.setTagNames(tagNames);
        }

        return new PageUtils(page);
    }

    @Override
    public boolean insert(MemberGroupEntity entity){
        entity.setCreatedTime(new Date());
        Long[] tagArray = entity.getTagArray();
        if(tagArray != null && tagArray.length > 0){
            String tagIds = "";
            for(Long tagId: tagArray){
                tagIds += tagId + ",";
            }
            entity.setTagIds(tagIds.substring(0, tagIds.length() - 1));
        }
        baseMapper.insert(entity);
        return true;
    }

    @Override
    public MemberGroupEntity selectById(Long id){
        MemberGroupEntity entity = baseMapper.selectById(id);
        if(entity == null){
            return null;
        }
        String tagIds = entity.getTagIds();
        if(StringUtils.isNotEmpty(tagIds)){
            String[] array = tagIds.split(",");
            Long[] tagArray = new Long[array.length];
            for (int i = 0; i < array.length; i++) {
                tagArray[i] = Long.parseLong(array[i]);
            }
            entity.setTagArray(tagArray);
        }
        return entity;
    }

    @Override
    public boolean updateById(MemberGroupEntity entity){
        Long[] tagArray = entity.getTagArray();
        if(tagArray != null && tagArray.length > 0){
            String tagIds = "";
            for(Long tagId: tagArray){
                tagIds += tagId + ",";
            }
            entity.setTagIds(tagIds.substring(0, tagIds.length() - 1));
        }
        baseMapper.updateById(entity);
        return true;
    }

    private Integer getMemberNum(MemberGroupEntity entity){
        String tagIds = entity.getTagIds();
        Set<Long> memberSet = new HashSet<>();
        if(StringUtils.isNotEmpty(tagIds)){
            String[] tagArray = tagIds.split(",");
            for(String tagId: tagArray){
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
                List<MemberEntity> list = memberDao.selectList(wrapper1);
                for (MemberEntity entity1:list){
                    memberSet.add(entity1.getMemberId());
                }
            }
        }
        return memberSet.size();
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

}
