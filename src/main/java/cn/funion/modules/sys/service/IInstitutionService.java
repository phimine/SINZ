package cn.funion.modules.sys.service;

import cn.funion.modules.sys.entity.Institution;
import cn.funion.modules.sys.entity.wapperDto.AgentDto;
import cn.funion.modules.sys.entity.wapperDto.InstitutionDto;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.text.ParseException;
import java.util.Map;

/**
 * <p>
 * 机构 服务类
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
public interface IInstitutionService extends IService<Institution> {

    void syncInstitutionFromHotCourse() throws ParseException;

    Page<InstitutionDto> pageInstitutions(Map params);


    InstitutionDto campusOfInstitution(int institutionId);

    InstitutionDto institutionDetail(int institutionId);

    Page<AgentDto> pageAgent(Map params);
}
