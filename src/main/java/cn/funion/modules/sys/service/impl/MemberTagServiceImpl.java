package cn.funion.modules.sys.service.impl;

import cn.funion.modules.sys.dao.MemberDao;
import cn.funion.modules.sys.dao.MemberTagItemDao;
import cn.funion.modules.sys.entity.MemberEntity;
import cn.funion.modules.sys.entity.MemberTagItemEntity;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.funion.common.utils.PageUtils;
import cn.funion.common.utils.Query;

import cn.funion.modules.sys.dao.MemberTagDao;
import cn.funion.modules.sys.entity.MemberTagEntity;
import cn.funion.modules.sys.service.MemberTagService;


@Service("memberTagService")
public class MemberTagServiceImpl extends ServiceImpl<MemberTagDao, MemberTagEntity> implements MemberTagService {

    @Autowired
    private MemberTagItemDao memberTagItemDao;

    @Autowired
    private MemberDao memberDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<MemberTagEntity> ew = new EntityWrapper<MemberTagEntity>();
        if(params.get("key")!=null&&!params.get("key").equals("")){
            ew.like("name", params.get("key").toString());
        }
        Page<MemberTagEntity> page = this.selectPage(
                new Query<MemberTagEntity>(params).getPage(),
                ew
        );
        List<MemberTagEntity> list = page.getRecords();
        for(MemberTagEntity entity: list){
            Integer num = getMemberNum(entity.getId());
            entity.setNum(num);
        }
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public boolean insert(MemberTagEntity memberTagEntity){
        memberTagEntity.setCreatedTime(new Date());
        baseMapper.insert(memberTagEntity);
        Long tagId = memberTagEntity.getId();
        String[] itemIds = memberTagEntity.getItemIds();
        for(String itemId: itemIds){
            MemberTagItemEntity memberTagItemEntity = new MemberTagItemEntity();
            String[] itemIdArray = itemId.split("###");
            memberTagItemEntity.setFieldName(itemIdArray[0]);
            memberTagItemEntity.setFieldValue(itemIdArray[1]);
            memberTagItemEntity.setTagId(tagId);
            memberTagItemDao.insert(memberTagItemEntity);
        }
        return true;
    }

    @Override
    public MemberTagEntity selectById(Long id){
        MemberTagEntity memberTagEntity = baseMapper.selectById(id);
        Wrapper<MemberTagItemEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("tag_id", id);
        List<MemberTagItemEntity> itemIdList = memberTagItemDao.selectList(wrapper);
        String[] itemIds = new String[itemIdList.size()];
        int i = 0;
        for(MemberTagItemEntity entity: itemIdList){
            itemIds[i++] = entity.getFieldName() + "###" + entity.getFieldValue();
        }
        memberTagEntity.setItemIds(itemIds);
        return memberTagEntity;
    }

    @Override
    public boolean updateById(MemberTagEntity entity){
        baseMapper.updateById(entity);
        Long tagId = entity.getId();
        Wrapper<MemberTagItemEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("tag_id", tagId);
        memberTagItemDao.delete(wrapper);

        String[] itemIds = entity.getItemIds();
        for(String itemId: itemIds){
            MemberTagItemEntity memberTagItemEntity = new MemberTagItemEntity();
            String[] itemIdArray = itemId.split("###");
            memberTagItemEntity.setFieldName(itemIdArray[0]);
            memberTagItemEntity.setFieldValue(itemIdArray[1]);
            memberTagItemEntity.setTagId(tagId);
            memberTagItemDao.insert(memberTagItemEntity);
        }

        return true;
    }

    @Override
    public boolean deleteBatchIds(Long[] ids){
        baseMapper.deleteBatchIds(Arrays.asList(ids));
        Wrapper<MemberTagItemEntity> wrapper = new EntityWrapper<>();
        wrapper.in("tag_id", Arrays.asList(ids));
        memberTagItemDao.delete(wrapper);
        return true;
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

    private Integer getMemberNum(Long tagId){
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
        Integer num =  memberDao.selectCount(wrapper1);
        if(num == null){
            num = 0;
        }
        return num;
    }
}
