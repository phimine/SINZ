package cn.funion.modules.sys.service;

import cn.funion.modules.sys.entity.Department;
import com.baomidou.mybatisplus.service.IService;

import java.text.ParseException;

/**
 * <p>
 * 部门
学科 服务类
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
public interface IDepartmentService extends IService<Department> {
    void syncDepartmentFromHotCourse() throws ParseException;

}
