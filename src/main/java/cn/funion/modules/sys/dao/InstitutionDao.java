package cn.funion.modules.sys.dao;

import cn.funion.modules.sys.entity.Institution;
import cn.funion.modules.sys.entity.wapperDto.AgentDto;
import cn.funion.modules.sys.entity.wapperDto.InstitutionDto;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构 Mapper 接口
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Mapper
public interface InstitutionDao extends BaseMapper<Institution> {


    List<InstitutionDto> pageInstitutions(Map params);

    int countInstitutions(Map params);

    InstitutionDto queryInstitutionInfoById(int institutionId);

    int countAgent(Map params);

    List<AgentDto> pageAgent(Map params);
}
