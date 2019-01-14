package cn.funion.modules.sys.dao;

import cn.funion.modules.sys.entity.Opportunity;
import cn.funion.modules.sys.entity.wapperDto.OpportunityDto;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 模式 Mapper 接口
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Mapper
public interface OpportunityDao extends BaseMapper<Opportunity> {

    List<OpportunityDto> studyModeOfInstitution(int institutionId);
}
