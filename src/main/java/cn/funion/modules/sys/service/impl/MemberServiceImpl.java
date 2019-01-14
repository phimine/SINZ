package cn.funion.modules.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.funion.common.utils.PageUtils;
import cn.funion.common.utils.Query;
import cn.funion.datasources.DataSourceNames;
import cn.funion.datasources.annotation.DataSource;
import cn.funion.modules.sys.dao.ImsMcMappingFansDao;
import cn.funion.modules.sys.dao.MemberDao;
import cn.funion.modules.sys.entity.ImsMcMappingFansEntity;
import cn.funion.modules.sys.entity.MemberEntity;
import cn.funion.modules.sys.service.MemberService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {
	@Autowired
	private ImsMcMappingFansDao imsMcMappingFansDao ;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	
    	 EntityWrapper<MemberEntity> entityWrapper = new EntityWrapper<MemberEntity>();
    	 if(params.get("phone")!=null&&!params.get("phone").equals("")){
    		 entityWrapper.like("member_mobile", params.get("phone").toString());
    	 }
        Page<MemberEntity> page = this.selectPage(
                new Query<MemberEntity>(params).getPage(),
                entityWrapper
        );
        return new PageUtils(page);
    }
    
    @DataSource(name=DataSourceNames.SECOND)
    public List<ImsMcMappingFansEntity> getSinzMemberList(List<String> unionIds){
    	 EntityWrapper<ImsMcMappingFansEntity> wrapper = new EntityWrapper<ImsMcMappingFansEntity>();
    	 wrapper.in("unionid", unionIds);
    	 return imsMcMappingFansDao.selectList(wrapper);
    }
    
    
    @DataSource(name=DataSourceNames.SECOND)
    public Map<String,ImsMcMappingFansEntity> getSinzMemberMap(List<String> unionIds){
    	Map<String,ImsMcMappingFansEntity> result = null	;
    	List<ImsMcMappingFansEntity> sinzMemberList = getSinzMemberList(unionIds);
    	if(CollectionUtils.isNotEmpty(sinzMemberList)){
    		result =new HashMap<String,ImsMcMappingFansEntity>(unionIds.size())	;
    		for (ImsMcMappingFansEntity imsMcMappingFansEntity : sinzMemberList) {
    			result.put(imsMcMappingFansEntity.getUnionid(), imsMcMappingFansEntity);
			}
    	}
    	return result;
    }
    
    
	public ImsMcMappingFansEntity selectSinzMemberByUnionId(String unionid) {
		ImsMcMappingFansEntity entity = new ImsMcMappingFansEntity();
		entity.setUnionid(unionid);
		return imsMcMappingFansDao.selectOne(entity);
	}
    
     


}
