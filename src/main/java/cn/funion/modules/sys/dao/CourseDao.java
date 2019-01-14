package cn.funion.modules.sys.dao;

import cn.funion.modules.sys.entity.Course;
import cn.funion.modules.sys.entity.wapperDto.CourseDto;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Mapper
public interface CourseDao extends BaseMapper<Course> {
    public List<CourseDto> pageCourse(Map params);

    public int countCousrse(Map params);

}
