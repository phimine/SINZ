package cn.funion.modules.sys.service.impl;

import cn.funion.common.utils.JsonHelper;
import cn.funion.modules.sys.dao.ScholarshipDao;
import cn.funion.modules.sys.entity.Scholarship;
import cn.funion.modules.sys.entity.wapperDto.ScholarshipDto;
import cn.funion.modules.sys.service.IScholarshipService;
import cn.funion.sync.HotCourse;
import com.baomidou.mybatisplus.plugins.Page;
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
import java.util.Map;

@Service
public class ScholarshipServiceImpl extends ServiceImpl<ScholarshipDao, Scholarship> implements IScholarshipService {
    private static final Logger _logger = LoggerFactory.getLogger(ScholarshipServiceImpl.class);

    @Autowired
    private ScholarshipDao scholarshipDao;
    @Override
//@Scheduled(cron="0 */10 * * * ?")
    public boolean syncScholarshipFromHotCourse() {
        JSONObject result = HotCourse.syncHotCourse(1,HotCourse.SCHOLARSHP_URL);
        if(StringUtils.isEmpty(result.get("hateoas"))){
            _logger.error("同步scholarship信息失败");
            return false;
        }
        //总页数
        int totalPageNum = Integer.parseInt((String)((JSONObject)result.get("hateoas")).get("total_pages"));
        _logger.info("pageNum sync: {}",totalPageNum);
        //课程内容
        saveOrUpdateScholarship(result);

        if (totalPageNum>1){
            for(int i=2;i<=totalPageNum;i++){
                JSONObject scholarshipResult = HotCourse.syncHotCourse(i,HotCourse.SCHOLARSHP_URL);
                if(StringUtils.isEmpty(scholarshipResult.get("hateoas"))){
                    _logger.error("同步scholarship信息失败");
                    return false;
                }
                //总页数
                totalPageNum = Integer.parseInt((String)((JSONObject)scholarshipResult.get("hateoas")).get("total_pages"));
                _logger.info("pageNum sync: {}",totalPageNum);
                saveOrUpdateScholarship(scholarshipResult);
            }
        }

        return false;
    }



    @Override
    public Page<ScholarshipDto> pageScholarship(Map params) {
        Page  result = new Page();
        //根据条件查询条数
       int rowNum = scholarshipDao.countScholarship(params);
        result.setTotal(rowNum);
       if(rowNum >0 ){
          List<ScholarshipDto> scholarshipDtos = scholarshipDao.pageScholarship(params);
          result.setRecords(scholarshipDtos);
       }

        return result;
    }



    private void saveOrUpdateScholarship(JSONObject result) {
        JSONArray jsonArry = JSONArray.fromObject(result.get("scholarships"));
//		List<Course> courseList = JsonHelper.str2list(, Course.class);
        for(int i=0;i<jsonArry.size();i++){
            JSONObject jsonCous = (JSONObject) jsonArry.get(i);

            Scholarship obj  = JsonHelper.str2obj(jsonCous.toString(), Scholarship.class);
            //查询是否存在
            Scholarship queryScholarship = new Scholarship();
            queryScholarship.setScholarshipId(obj.getScholarshipId());
            Scholarship exist = scholarshipDao.selectOne(queryScholarship);
            //存在则判断更新时间是否一致，一致则不更新，不一致更新
            if(exist != null){
                //更新时间不一致则更新
                if(exist.getLastUpdatedDate().compareTo(obj.getLastUpdatedDate()) != 0){
                    obj.setSyncDate(new Date());
                    obj.setId(exist.getId());
                    obj.setCreatedDate(exist.getCreatedDate());
                    scholarshipDao.updateById(obj);
                }
            }else{
                obj.setSyncDate(new Date());
                obj.setCreatedDate(new Date());
                try {
                    scholarshipDao.insert(obj);
                } catch (Exception e) {
                    _logger.warn("insert Scholarship exception,course info:{}",obj.toString());
                    _logger.error("insert Scholarship exception:",e);
                }
            }
        }
    }
}
