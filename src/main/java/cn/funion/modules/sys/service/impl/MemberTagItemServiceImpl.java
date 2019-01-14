package cn.funion.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.funion.common.utils.PageUtils;
import cn.funion.common.utils.Query;

import cn.funion.modules.sys.dao.MemberTagItemDao;
import cn.funion.modules.sys.entity.MemberTagItemEntity;
import cn.funion.modules.sys.service.MemberTagItemService;


@Service("memberTagItemService")
public class MemberTagItemServiceImpl extends ServiceImpl<MemberTagItemDao, MemberTagItemEntity> implements MemberTagItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MemberTagItemEntity> page = this.selectPage(
                new Query<MemberTagItemEntity>(params).getPage(),
                new EntityWrapper<MemberTagItemEntity>()
        );

        return new PageUtils(page);
    }

}
