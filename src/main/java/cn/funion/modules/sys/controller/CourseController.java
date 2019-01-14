package cn.funion.modules.sys.controller;


import cn.funion.common.utils.R;
import cn.funion.modules.sys.entity.Course;
import cn.funion.modules.sys.entity.wapperDto.CourseDto;
import cn.funion.modules.sys.service.*;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@RestController
@RequestMapping("v1/sinz/course")
public class CourseController {
	private static final Logger _logger = LoggerFactory.getLogger(CourseController.class);
	@Autowired
	private ICourseService courseService;
	@Autowired
	private IInstitutionService institutionService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private ICampusService campusService;
	@Autowired
	private IOpportunityService opportunityService;
	@Autowired
	private  IScholarshipService scholarshipService;

	@CrossOrigin
	@RequestMapping(value="/getCourse",method = RequestMethod.GET)
	public R getCourse(@RequestParam(value = "courseId",required=true) String courseId,
					   @RequestParam(value = "institutionId",required=true) String institutionId
			){
		_logger.info("getCourse params courseId: {},institutionId:{}",courseId,institutionId);
		Course result = courseService.queryCourseInfo(courseId,institutionId);
		if(StringUtils.isEmpty(result)){
			return R.error("课程信息获取失败，请稍后尝试。");
		}
		return R.ok().put("course", result);
	}

	@CrossOrigin
	@RequestMapping(value="/pageCourse",method = RequestMethod.GET)
	public R pageCourse(@RequestParam(value ="pageSize",required=true,defaultValue = "5") int pageSize,
						@RequestParam(value ="pageNum",required=true,defaultValue = "1") int pageNum,
						@RequestParam(value ="institutionType",required=false) String institutionType,
						@RequestParam(value ="levlelOfStudy",required=false) String levlelOfStudy,
						@RequestParam(value ="institutionAddRegion",required=false) String institutionAddRegion,
						@RequestParam(value ="studyMode",required=false) String studyMode,
						@RequestParam(value ="orderType",required=false) String orderType,
						@RequestParam(value ="orderDateType",required=false) String orderDateType
						){
		int courrentIndex = (pageNum-1)*pageSize;
		Map params = new HashMap<>();
		params.put("courrentIndex",courrentIndex);
		params.put("pageSize",pageSize);
		params.put("institutionType",institutionType);
		params.put("levlelOfStudy",levlelOfStudy);
		params.put("institutionAddRegion",institutionAddRegion);
		params.put("studyMode",studyMode);
		params.put("orderType",orderType);
		params.put("orderDateType",orderDateType);
		_logger.info("getCourse params:{}",params);
		Page<CourseDto> result = courseService.queryCourse(params);
		//封装courseinstitution
		Map<Integer, List<CourseDto>> courseGroupByInstitution = new HashedMap();
		for(CourseDto dto:result.getRecords()){
			List<CourseDto> courseDtolist = new ArrayList<>();
			if(courseGroupByInstitution.containsKey(dto.getInstitutionId())){
				courseDtolist= courseGroupByInstitution.get(dto.getInstitutionId());
				courseDtolist.add(dto);
			}else {
				courseDtolist.add(dto);
				courseGroupByInstitution.put(dto.getInstitutionId(),courseDtolist);
			}
		}
		return R.ok().put("course", courseGroupByInstitution).put("total",result.getTotal()).put("keys",courseGroupByInstitution.keySet());
	}

	@CrossOrigin
	@RequestMapping(value="/syncHT",method = RequestMethod.GET)
	public R syncHT() throws ParseException {
//		courseService.syncCourseFromHotCourse();
//		institutionService.syncInstitutionFromHotCourse();
//		campusService.syncCampusFromHotCourse();
//		departmentService.syncDepartmentFromHotCourse();
//		scholarshipService.syncScholarshipFromHotCourse();
//		opportunityService.syncOpportunityFromHotCourse();
		return R.ok();

	}
}

