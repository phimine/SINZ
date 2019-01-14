package cn.funion.modules.sys.service;

import cn.funion.modules.sys.entity.Opportunity;
import cn.funion.modules.sys.entity.wapperDto.OpportunityDto;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 模式 服务类
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
public interface IOpportunityService extends IService<Opportunity> {

	boolean syncOpportunityFromHotCourse();


    List<OpportunityDto> studyModeOfInstitution(int institutionId);
}
