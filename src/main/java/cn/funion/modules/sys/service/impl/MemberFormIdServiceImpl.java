package cn.funion.modules.sys.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.funion.common.utils.PageUtils;
import cn.funion.common.utils.Query;
import cn.funion.modules.sys.dao.MemberFormIdDao;
import cn.funion.modules.sys.entity.MemberFormIdEntity;
import cn.funion.modules.sys.service.MemberFormIdService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


@Service("memberFormIdService")
public class MemberFormIdServiceImpl extends ServiceImpl<MemberFormIdDao, MemberFormIdEntity> implements MemberFormIdService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MemberFormIdEntity> page = this.selectPage(
                new Query<MemberFormIdEntity>(params).getPage(),
                new EntityWrapper<MemberFormIdEntity>()
        );

        return new PageUtils(page);
    }

}
