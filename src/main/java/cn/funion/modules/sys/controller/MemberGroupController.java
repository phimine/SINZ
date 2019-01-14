package cn.funion.modules.sys.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.funion.modules.sys.entity.MemberGroupEntity;
import cn.funion.modules.sys.service.MemberGroupService;
import cn.funion.common.utils.PageUtils;
import cn.funion.common.utils.R;



/**
 * 
 *
 * @author aixc
 * @email dev@4union.cn
 * @date 2018-11-28 21:07:11
 */
@RestController
@RequestMapping("sys/membergroup")
public class MemberGroupController {
    @Autowired
    private MemberGroupService memberGroupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:membergroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberGroupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:membergroup:info")
    public R info(@PathVariable("id") Long id){
			MemberGroupEntity memberGroup = memberGroupService.selectById(id);

        return R.ok().put("memberGroup", memberGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:membergroup:save")
    public R save(@RequestBody MemberGroupEntity memberGroup){

			memberGroupService.insert(memberGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:membergroup:update")
    public R update(@RequestBody MemberGroupEntity memberGroup){
			memberGroupService.updateById(memberGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:membergroup:delete")
    public R delete(@RequestBody Long[] ids){
			memberGroupService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


}
