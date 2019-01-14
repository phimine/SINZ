package cn.funion.modules.sys.service.impl;


import cn.funion.common.utils.JsonHelper;
import cn.funion.modules.sys.dao.CourseDao;
import cn.funion.modules.sys.dao.InstitutionDao;
import cn.funion.modules.sys.entity.Course;
import cn.funion.modules.sys.entity.Institution;
import cn.funion.modules.sys.entity.wapperDto.CourseDto;
import cn.funion.modules.sys.service.ICourseService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseDao, Course> implements ICourseService {
	
	private static final Logger _logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private InstitutionDao institutionDao;

	@Override
	@Scheduled(cron="0 */10 * * * ?")
	public boolean syncCourseFromHotCourse() {
		JSONObject result = HotCourse.syncHotCourse(1,HotCourse.COURSE_URL);
		if(StringUtils.isEmpty(result.get("hateoas"))){
			_logger.error("同步course信息失败");
			return false;
			}
		//总页数
		int totalPageNum = Integer.parseInt((String)((JSONObject)result.get("hateoas")).get("total_pages"));
		_logger.info("pageNum sync: {}",totalPageNum);
		//课程内容
		saveOrUpdateCourse(result);
		
		if (totalPageNum>1){
			for(int i=2;i<=totalPageNum;i++){
				JSONObject corseResult = HotCourse.syncHotCourse(i,HotCourse.COURSE_URL);
				if(StringUtils.isEmpty(corseResult.get("hateoas"))){
					_logger.error("同步course信息失败");
					return false;
					}
				//总页数
				totalPageNum = Integer.parseInt((String)((JSONObject)corseResult.get("hateoas")).get("total_pages"));
				_logger.info("pageNum sync: {}",totalPageNum);
				saveOrUpdateCourse(corseResult);
			}
		}
		
		return false;
	}

	private void saveOrUpdateCourse(JSONObject result) {
		JSONArray jsonArry = JSONArray.fromObject(result.get("courses"));
//		List<Course> courseList = JsonHelper.str2list(, Course.class);
		for(int i=0;i<jsonArry.size();i++){
			JSONObject jsonCous = (JSONObject) jsonArry.get(i);
			
			Course obj  = JsonHelper.str2obj(jsonCous.toString(), Course.class);
			//查询是否存在
			Course queryCousre = new Course();
			queryCousre.setInstitutionId(obj.getInstitutionId());
			queryCousre.setCourseId(obj.getCourseId());
			Course exist = courseDao.selectOne(queryCousre);
			//存在则判断更新时间是否一致，一致则不更新，不一致更新
			if(exist != null){
				//更新时间不一致则更新
				if(exist.getLastUpdatedDate().compareTo(obj.getLastUpdatedDate()) != 0){
					obj.setSyncDate(new Date());
					obj.setId(exist.getId());
					courseDao.updateById(exist);
				}
			}else{
				obj.setSyncDate(new Date());
				obj.setCreatedDate(new Date());
				try {
					courseDao.insert(obj);
				} catch (Exception e) {
					_logger.warn("insert course exception,course info:{}",obj.toString());
					_logger.error("insert course exception:",e);
				}
			}
		}
	}

	@Override
	public Course queryCourseInfo(String courseId, String institutionId) {
		Course query = new Course();
		query.setCourseId(Integer.parseInt(courseId));
		query.setInstitutionId(Integer.parseInt(institutionId));

		Institution queryIn = new Institution();
		queryIn.setInstitutionId(Integer.parseInt(institutionId));
		Institution institution = institutionDao.selectOne(queryIn);

		Course resultCourse = courseDao.selectOne(query);
		resultCourse.setInstitution(institution);
		return resultCourse;
		
	}

	@Override
	public Page<CourseDto> queryCourse(Map params) {
		//解析查询参数
		_logger.info("queryCourse params:{}",params);
		int count = courseDao.countCousrse(params);
		List<CourseDto> records = new ArrayList<>();
		if(count >0){
			records = courseDao.pageCourse(params);
		}
		Page<CourseDto> result = new Page();
		result.setRecords(records);
		result.setTotal(count);
		return result;
	}


}
