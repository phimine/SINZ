package cn.funion.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import cn.funion.common.utils.R;
import cn.funion.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户Token
 * 
 * @author 4union
 * @email dev@4union.cn
 * @date 2017-03-23 15:22:07
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
