package cn.funion.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.funion.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 * 
 * @author 4union
 * @email dev@4union.cn
 * @date 2017-03-23 15:22:07
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
	
}
