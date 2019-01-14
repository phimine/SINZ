package cn.funion.modules.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.funion.common.utils.PageUtils;
import cn.funion.common.utils.R;
import cn.funion.modules.sys.entity.ImsMcMappingFansEntity;
import cn.funion.modules.sys.entity.MemberEntity;
import cn.funion.modules.sys.service.MemberService;

import com.baomidou.mybatisplus.toolkit.StringUtils;



/**
 * 
 *
 * @author aixc
 * @email dev@4union.cn
 * @date 2018-11-28 20:13:53
 */
@RestController
@RequestMapping("sys/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:member:list")
    public R list(@RequestParam Map<String, Object> params){
    	
        PageUtils page = memberService.queryPage(params);
        if(page.getList()!=null){
        	 List<MemberEntity> records = (List<MemberEntity>) page.getList();
             if(CollectionUtils.isNotEmpty(records)){
             	List<String> unionids = new ArrayList<>();
             	for (MemberEntity memberEntity : records) {
             		if(StringUtils.isNotEmpty(memberEntity.getWeChatUnionId())){
             			unionids.add(memberEntity.getWeChatUnionId());
             		}
     			}
             	if(unionids.size()>0){
             		Map<String, ImsMcMappingFansEntity> sinzMemberMap = memberService.getSinzMemberMap(unionids);
             		if(sinzMemberMap!=null){
             			for (MemberEntity memberEntity : records) {
             				if(StringUtils.isNotEmpty(memberEntity.getWeChatUnionId())){
             					convertData(memberEntity, sinzMemberMap.get(memberEntity.getWeChatUnionId()));
             				}
             			}
             		}
             	}
             }
        }
        return R.ok().put("page", page);
    }
    
    private void convertData(MemberEntity member,ImsMcMappingFansEntity imsMember){
    	if(imsMember!=null&&member!=null){
    		if(StringUtils.isNotEmpty(imsMember.getNickname())){
    			member.setWxNickname(imsMember.getNickname());
    		}
    		member.setWxFollowedStatus(imsMember.getFollow());
    		if(imsMember.getFollowtime()!=null){
    			long follow = imsMember.getFollow();
    			member.setWxFollowedDate(new Date(follow*1000l));
    		}
    	}
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberId}")
    @RequiresPermissions("sys:member:info")
    public R info(@PathVariable("memberId") Long memberId){
			MemberEntity member = memberService.selectById(memberId);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:member:save")
    public R save(@RequestBody MemberEntity member){
			memberService.insert(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:member:update")
    public R update(@RequestBody MemberEntity member){
			memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:member:delete")
    public R delete(@RequestBody Long[] memberIds){
			memberService.deleteBatchIds(Arrays.asList(memberIds));

        return R.ok();
    }

}
