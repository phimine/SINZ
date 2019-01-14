package cn.funion.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;

import cn.funion.common.utils.PageUtils;
import cn.funion.modules.sys.entity.ImsMcMappingFansEntity;
import cn.funion.modules.sys.entity.MemberEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author aixc
 * @email dev@4union.cn
 * @date 2018-11-28 21:07:11
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    public Map<String,ImsMcMappingFansEntity> getSinzMemberMap(List<String> unionIds);
}

