package cn.funion.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import cn.funion.common.utils.PageUtils;
import cn.funion.modules.sys.entity.MemberTagItemEntity;

import java.util.Map;

/**
 * 
 *
 * @author aixc
 * @email dev@4union.cn
 * @date 2018-11-28 21:07:11
 */
public interface MemberTagItemService extends IService<MemberTagItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

