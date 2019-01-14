package cn.funion.modules.sys.service.impl;

import cn.funion.common.utils.JsonHelper;
import cn.funion.modules.sys.dao.OpportunityDao;
import cn.funion.modules.sys.entity.Opportunity;
import cn.funion.modules.sys.entity.wapperDto.OpportunityDto;
import cn.funion.modules.sys.service.IOpportunityService;
import cn.funion.sync.HotCourse;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class OpportunityServiceImpl extends ServiceImpl<OpportunityDao, Opportunity> implements IOpportunityService {
    private static final Logger _logger = LoggerFactory.getLogger(OpportunityServiceImpl.class);

    @Autowired
    private OpportunityDao opportunityDao;
    @Override
//@Scheduled(cron="0 */10 * * * ?")
    public boolean syncOpportunityFromHotCourse() {
        JSONObject result = HotCourse.syncHotCourse(1,HotCourse.OPPORTUNITY_URL);
        if(StringUtils.isEmpty(result.get("hateoas"))){
            _logger.error("同步Opportunity信息失败");
            return false;
        }
        //总页数
        int totalPageNum = Integer.parseInt((String)((JSONObject)result.get("hateoas")).get("total_pages"));
        _logger.info("pageNum sync: {}",totalPageNum);
        //课程内容
        saveOrUpdateOpportunity(result);

        if (totalPageNum>1){
            for(int i=2;i<=totalPageNum;i++){
                JSONObject corseResult = HotCourse.syncHotCourse(i,HotCourse.OPPORTUNITY_URL);
                if(StringUtils.isEmpty(corseResult.get("hateoas"))){
                    _logger.error("同步Opportunity信息失败");
                    return false;
                }
                //总页数
                totalPageNum = Integer.parseInt((String)((JSONObject)corseResult.get("hateoas")).get("total_pages"));
                _logger.info("pageNum sync: {}",totalPageNum);
                saveOrUpdateOpportunity(corseResult);
            }
        }

        return false;    }

    @Override
    public List<OpportunityDto> studyModeOfInstitution(int institutionId) {
        return opportunityDao.studyModeOfInstitution(institutionId);
    }


    private void saveOrUpdateOpportunity(JSONObject result) {
        JSONArray jsonArry = JSONArray.fromObject(result.get("opportunities"));
//		List<Course> courseList = JsonHelper.str2list(, Course.class);
        for(int i=0;i<jsonArry.size();i++){
            JSONObject jsonCous = (JSONObject) jsonArry.get(i);

            Opportunity obj  = JsonHelper.str2obj(jsonCous.toString(), Opportunity.class);
            //查询是否存在
            Opportunity queryOpportunity = new Opportunity();
            queryOpportunity.setOpportunityId(obj.getOpportunityId());
            Opportunity exist = opportunityDao.selectOne(queryOpportunity);
            //存在则判断更新时间是否一致，一致则不更新，不一致更新
            if(exist != null){
                //更新时间不一致则更新
                if(exist.getLastUpdatedDate().compareTo(obj.getLastUpdatedDate()) != 0){
                    obj.setSyncDate(new Date());
                    obj.setId(exist.getId());
                    obj.setCreatedDate(exist.getCreatedDate());
                    opportunityDao.updateById(obj);
                }
            }else{
                obj.setSyncDate(new Date());
                obj.setCreatedDate(new Date());
                try {
                    opportunityDao.insert(obj);
                } catch (Exception e) {
                    _logger.warn("insert opportunity exception,course info:{}",obj.toString());
                    _logger.error("insert opportunity exception:",e);
                }
            }
        }
    }
}
