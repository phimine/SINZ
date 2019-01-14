package cn.funion.modules.sys.dao;

import cn.funion.modules.sys.entity.Department;
import cn.funion.modules.sys.entity.wapperDto.DepartmentDto;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 部门
学科 Mapper 接口
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Mapper
public interface DepartmentDao extends BaseMapper<Department> {

    List<DepartmentDto> departmentOfInstitution(int institutionId);
}
