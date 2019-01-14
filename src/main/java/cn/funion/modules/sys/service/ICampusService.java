package cn.funion.modules.sys.service;

import cn.funion.modules.sys.entity.Campus;
import com.baomidou.mybatisplus.service.IService;

import java.text.ParseException;

/**
 * <p>
 * 大学 服务类
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
public interface ICampusService extends IService<Campus> {
    void syncCampusFromHotCourse() throws ParseException;

}
