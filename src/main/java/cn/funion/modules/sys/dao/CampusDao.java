package cn.funion.modules.sys.dao;

import cn.funion.modules.sys.entity.Campus;
import cn.funion.modules.sys.entity.wapperDto.CampusDto;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 大学 Mapper 接口
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Mapper
public interface CampusDao extends BaseMapper<Campus> {

    List<CampusDto> campusOfInstitution(int institutionId);
}
