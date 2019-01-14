package cn.funion.modules.sys.controller;

import cn.funion.common.utils.R;
import cn.funion.modules.sys.entity.Scholarship;
import cn.funion.modules.sys.entity.wapperDto.ScholarshipDto;
import cn.funion.modules.sys.service.IScholarshipService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("v1/sinz/scholarship")
public class ScholarshipController {
    private Logger _logger= LoggerFactory.getLogger(ScholarshipController.class);
    @Autowired
    private IScholarshipService scholarshipService;

    @CrossOrigin
    @RequestMapping(value="/pageScholarship",method = RequestMethod.GET)
    private R pageScholarship(
            @RequestParam(value ="pageSize",required=true,defaultValue = "5") int pageSize,
            @RequestParam(value ="pageNum",required=true,defaultValue = "1") int pageNum,
            @RequestParam(value ="areaOfStudy",required=false) String areaOfStudy,
            @RequestParam(value ="nationality",required=false) String nationality,
            @RequestParam(value ="institutionId",required=false) String institutionId,
            @RequestParam(value ="institutionType",required=false) String institutionType,
            @RequestParam(value ="subjectOfStudy",required=false) String subjectOfStudy,
            @RequestParam(value ="orderType",required=false) String orderType,
            @RequestParam(value ="levelOfStudy",required=false) String levelOfStudy
    ){
        int courrentIndex = (pageNum-1)*pageSize;
        Map params = new HashMap<>();
        params.put("courrentIndex",courrentIndex);
        params.put("pageSize",pageSize);
        params.put("areaOfStudy",areaOfStudy);
        params.put("levelOfStudy",levelOfStudy);
        params.put("nationality",nationality);
        params.put("institutionType",institutionType);
        params.put("subjectOfStudy",subjectOfStudy);
        params.put("orderType",orderType);
        _logger.info("scholarshipOfInstitution params:{}",params);
        Page<ScholarshipDto> scholarshipDtos = scholarshipService.pageScholarship(params);
        //根据参数封装同一个学校的奖学金
        Map<Integer ,List<ScholarshipDto>> institusionScholarship = new HashMap<>();
        for(ScholarshipDto dto:scholarshipDtos.getRecords()){
            List<ScholarshipDto> scholarships = new ArrayList<>();
            if(institusionScholarship.containsKey(dto.getInstitutionId())){
                scholarships = institusionScholarship.get(dto.getInstitutionId());
                scholarships.add(dto);
            }else{
                scholarships.add(dto);
                institusionScholarship.put(dto.getInstitutionId(),scholarships);
            }
        }
        return R.ok().put("total",scholarshipDtos.getTotal()).put("data",institusionScholarship).put("keys", institusionScholarship.keySet());
    }


    @CrossOrigin
    @RequestMapping(value="/getScholarship",method = RequestMethod.GET)
    public R getCourse(@RequestParam(value = "scholarshipId",required=true) String scholarshipId
    ){
        _logger.info("getCourse params scholarshipId: {}",scholarshipId);
        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("scholarship_id",scholarshipId).eq("_status",0);
        Scholarship result = scholarshipService.selectOne(wrapper);
        if(StringUtils.isEmpty(result)){
            return R.error("奖学金信息获取失败，请稍后尝试。");
        }
        return R.ok().put("scholarship", result);
    }

}
