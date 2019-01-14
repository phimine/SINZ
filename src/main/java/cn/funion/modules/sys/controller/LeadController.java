package cn.funion.modules.sys.controller;

import cn.funion.common.utils.R;
import cn.funion.sync.LeadCollection;
import cn.funion.sync.dto.Member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/sinz/enquiry")
public class LeadController {
    private static final Logger _logger = LoggerFactory.getLogger(LeadController.class);

    @Autowired
    private LeadCollection leadCollection;

    @CrossOrigin
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public R enquiry(Member member){
        _logger.info("upload member info to SD:{}",member.toLeadJson().toString());
        leadCollection.upload(member);
        return R.ok();
    }
}
