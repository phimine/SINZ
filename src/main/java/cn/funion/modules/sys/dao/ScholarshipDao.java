package cn.funion.modules.sys.dao;

import cn.funion.modules.sys.entity.Scholarship;
import cn.funion.modules.sys.entity.wapperDto.ScholarshipDto;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 奖学金 Mapper 接口
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Mapper
public interface ScholarshipDao extends BaseMapper<Scholarship> {

    int countScholarship(Map params);

    List<ScholarshipDto> pageScholarship(Map params);
}
