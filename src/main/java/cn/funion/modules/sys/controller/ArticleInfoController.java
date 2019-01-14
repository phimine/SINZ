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

import cn.funion.modules.sys.entity.ArticleInfoEntity;
import cn.funion.modules.sys.service.ArticleInfoService;
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
@RequestMapping("sys/articleinfo")
public class ArticleInfoController {
    @Autowired
    private ArticleInfoService articleInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:articleinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = articleInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:articleinfo:info")
    public R info(@PathVariable("id") Long id){
			ArticleInfoEntity articleInfo = articleInfoService.selectById(id);

        return R.ok().put("articleInfo", articleInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:articleinfo:save")
    public R save(@RequestBody ArticleInfoEntity articleInfo){
			articleInfoService.insert(articleInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:articleinfo:update")
    public R update(@RequestBody ArticleInfoEntity articleInfo){
			articleInfoService.updateById(articleInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:articleinfo:delete")
    public R delete(@RequestBody Long[] ids){
			articleInfoService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
