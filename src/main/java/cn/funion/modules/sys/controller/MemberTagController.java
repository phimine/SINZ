package cn.funion.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.funion.modules.sys.entity.MemberTagEntity;
import cn.funion.modules.sys.service.MemberTagService;
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
@RequestMapping("sys/membertag")
public class MemberTagController {
    @Autowired
    private MemberTagService memberTagService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:membertag:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberTagService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:membertag:info")
    public R info(@PathVariable("id") Long id){
			MemberTagEntity memberTag = memberTagService.selectById(id);

        return R.ok().put("memberTag", memberTag);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:membertag:save")
    public R save(@RequestBody MemberTagEntity memberTag){
			memberTagService.insert(memberTag);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:membertag:update")
    public R update(@RequestBody MemberTagEntity memberTag){
			memberTagService.updateById(memberTag);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:membertag:delete")
    public R delete(@RequestBody Long[] ids){
			memberTagService.deleteBatchIds(ids);

        return R.ok();
    }

}
