package cn.funion.modules.sys.service;

import cn.funion.modules.sys.entity.Scholarship;
import cn.funion.modules.sys.entity.wapperDto.ScholarshipDto;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 奖学金 服务类
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
public interface IScholarshipService extends IService<Scholarship> {

	boolean syncScholarshipFromHotCourse();



    Page<ScholarshipDto> pageScholarship(Map params);
}
