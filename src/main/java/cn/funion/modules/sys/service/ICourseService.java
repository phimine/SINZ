package cn.funion.modules.sys.service;

import cn.funion.modules.sys.entity.Course;
import cn.funion.modules.sys.entity.wapperDto.CourseDto;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
public interface ICourseService extends IService<Course> {

	boolean syncCourseFromHotCourse();

	Course queryCourseInfo(String courseId, String institutionId);

	Page<CourseDto> queryCourse(Map params);

}
