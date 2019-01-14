package cn.funion.modules.sys.dao;

import cn.funion.sync.LeadCollection;
import cn.funion.sync.dto.Member;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeadCollectionDao extends BaseMapper<LeadCollection> {
    void save(Member member);
}
