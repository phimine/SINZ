package cn.funion.modules.sys.controller;


import cn.funion.common.utils.R;
import cn.funion.modules.sys.entity.Campus;
import cn.funion.modules.sys.entity.Course;
import cn.funion.modules.sys.entity.Institution;
import cn.funion.modules.sys.entity.InstitutionExtraInfo;
import cn.funion.modules.sys.entity.wapperDto.*;
import cn.funion.modules.sys.service.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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

/**
 * <p>
 * 机构 前端控制器
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@RestController
@RequestMapping("v1/sinz/institution")
public class InstitutionController {
    private static Logger _logger = LoggerFactory.getLogger(InstitutionController.class);

    @Autowired
    private IInstitutionService institutionService;

    @Autowired
    private IInstitutionExtraInfoService institutionExtraInfoService;
    @Autowired
    private ICampusService campusService;

    @Autowired
    private IOpportunityService opportunityService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IScholarshipService scholarshipService;

    @CrossOrigin
    @RequestMapping(value="/getInstitution",method = RequestMethod.GET)
    public R getInstitution(@RequestParam(value = "institutionId",required=true) String institutionId
    ){
        _logger.info("getInstitution params institutionId:{}",institutionId);

        EntityWrapper ew = new EntityWrapper();
        ew.eq("institution_id",Integer.parseInt(institutionId));
        InstitutionExtraInfo extraInfo = institutionExtraInfoService.selectOne(ew);
        Institution result = institutionService.selectOne(ew);
        result.setInstitutionExtraInfo(extraInfo);
        if(StringUtils.isEmpty(result)){
            return R.error("学校信息获取失败，请稍后尝试。");
        }
        return R.ok().put("course", result);
    }

    @CrossOrigin
    @RequestMapping(value="/getAllCampusByInstitution",method = RequestMethod.GET)
    public R getAllCampusByInstitution(@RequestParam(value = "institutionId",required=true) String institutionId
    ){
        _logger.info("getAllCampusByInstitution params institutionId:{}",institutionId);
        EntityWrapper ew = new EntityWrapper();
        ew.eq("institution_id",Integer.parseInt(institutionId));
        List<Campus> campusInst = campusService.selectList(ew);
        if(StringUtils.isEmpty(campusInst)){
            return R.error("学校信息获取失败，请稍后尝试。");
        }
        return R.ok().put("campus", campusInst);
    }

    @CrossOrigin
    @RequestMapping(value="/pageInstitutions",method = RequestMethod.GET)
    public R pageInstitutions(
            @RequestParam(value ="pageSize",required=true,defaultValue = "5") int pageSize,
            @RequestParam(value ="pageNum",required=true,defaultValue = "1") int pageNum,
            @RequestParam(value ="institutionType",required=false) String institutionType,
            @RequestParam(value ="institutionAddRegion",required=false) String institutionAddRegion,
            @RequestParam(value ="studyMode",required=false) String studyMode,
            @RequestParam(value ="orderType",required=false) String orderType,
            @RequestParam(value ="orderDateType",required=false) String orderDateType
    ){

        int courrentIndex = (pageNum-1)*pageSize;
        Map params = new HashMap<>();
        params.put("courrentIndex",courrentIndex);
        params.put("pageSize",pageSize);
        params.put("institutionType",institutionType);
        params.put("institutionAddRegion",institutionAddRegion);
        params.put("studyMode",studyMode);
        params.put("orderType",orderType);
        params.put("orderDateType",orderDateType);
        _logger.info("pageInstitutions params:{}",params);

        Page<InstitutionDto> result = institutionService.pageInstitutions(params);
        return R.ok().put("institutions", result.getRecords()).put("total",result.getTotal());
    }
    @CrossOrigin
    @RequestMapping(value="/campusOfInstitution",method = RequestMethod.GET)
    private R campusOfInstitution(@RequestParam(value ="institutionId",required=false) int institutionId){
        InstitutionDto campusAndInstitution = institutionService.campusOfInstitution(institutionId);
        return R.ok().put("institution",campusAndInstitution);
    }

    @CrossOrigin
    @RequestMapping(value="/institutionDetail",method = RequestMethod.GET)
    public R institutionDetail(@RequestParam(value ="institutionId",required=false) int institutionId
    ){
      InstitutionDto institutionDto =   institutionService.institutionDetail(institutionId);
        return R.ok().put("institution",institutionDto);
    }

    @CrossOrigin
    @RequestMapping(value="/studyLevelAndAreaOfInstitution",method = RequestMethod.GET)
    private R studyLevelAndAreaOfInstitution(@RequestParam(value ="institutionId",required=false) int institutionId){
        EntityWrapper ew = new EntityWrapper();
        ew.eq("institution_id",institutionId);
        List<Course> courseList = courseService.selectList(ew);
        List<String> studyLevels = new ArrayList<>();
        List<String> studyAreas = new ArrayList<>();
        for(Course c:courseList){
            if(!studyLevels.contains(c.getLevelOfStudy())){
                studyLevels.add(c.getLevelOfStudy());
            }
            if(!studyAreas.contains(c.getAreaOfStudy())){
                studyAreas.add(c.getAreaOfStudy());
            }
        }
        return  R.ok().put("studyLevel",studyLevels).put("studyArea",studyAreas);
    }

    @CrossOrigin
    @RequestMapping(value="/studyModeOfInstitution",method = RequestMethod.GET)
    private R studyModeOfInstitution(@RequestParam(value ="institutionId",required=false) int institutionId){
     List<OpportunityDto> opportunityList =  opportunityService.studyModeOfInstitution(institutionId);
     List<String> studyModes = new ArrayList<>();
        for(OpportunityDto opp:opportunityList){
            if(!studyModes.contains(opp.getStudyMode())){
                studyModes.add(opp.getStudyMode());
            }
        }
        return  R.ok().put("studyMode",studyModes);
    }

    @CrossOrigin
    @RequestMapping(value="/campusOfInstitutionForSearch",method = RequestMethod.GET)
    private R campusOfInstitutionForSearch(@RequestParam(value ="institutionId",required=false) int institutionId){
        EntityWrapper ew = new EntityWrapper();
        ew.eq("institution_id",institutionId).eq("_status",0);
        List<Campus> campusList = campusService.selectList(ew);
        return  R.ok().put("campuslist",campusList);
    }
    @CrossOrigin
    @RequestMapping(value="/scholarshipOfInstitution",method = RequestMethod.GET)
    private R scholarshipOfInstitution(
            @RequestParam(value ="pageSize",required=true,defaultValue = "5") int pageSize,
            @RequestParam(value ="pageNum",required=true,defaultValue = "1") int pageNum,
            @RequestParam(value ="institutionId",required=true) int institutionId,
            @RequestParam(value ="areaOfStudy",required=false) String areaOfStudy,
            @RequestParam(value ="nationality",required=false) String nationality,
            @RequestParam(value ="levelOfStudy",required=false) String levelOfStudy
    ){
        int courrentIndex = (pageNum-1)*pageSize;
        Map params = new HashMap<>();
        params.put("courrentIndex",courrentIndex);
        params.put("pageSize",pageSize);
        params.put("institutionId",institutionId);
        params.put("areaOfStudy",areaOfStudy);
        params.put("levelOfStudy",levelOfStudy);
        params.put("nationality",nationality);
        _logger.info("scholarshipOfInstitution params:{}",params);
        Page<ScholarshipDto> scholarshipDtos = scholarshipService.pageScholarship(params);
        return R.ok().put("total",scholarshipDtos.getTotal()).put("data",scholarshipDtos.getRecords());
    }


    @CrossOrigin
    @RequestMapping(value="/courseOfInstitution",method = RequestMethod.GET)
    public R courseOfInstitution(@RequestParam(value ="pageSize",required=true,defaultValue = "5") int pageSize,
                        @RequestParam(value ="pageNum",required=true,defaultValue = "1") int pageNum,
                        @RequestParam(value ="institutionId",required=true) int institutionId,
                        @RequestParam(value ="levlelOfStudy",required=false) String levlelOfStudy,
                        @RequestParam(value ="studyMode",required=false) String studyMode,
                        @RequestParam(value ="campusId",required=false) String campusId,
                        @RequestParam(value ="orderType",required=false) String orderType,
                        @RequestParam(value ="orderDateType",required=false) String orderDateType
    ){
        int courrentIndex = (pageNum-1)*pageSize;
        Map params = new HashMap<>();
        params.put("courrentIndex",courrentIndex);
        params.put("pageSize",pageSize);
        params.put("campusId",campusId);
        params.put("levlelOfStudy",levlelOfStudy);
        params.put("institutionId",institutionId);
        params.put("studyMode",studyMode);
        params.put("orderType",orderType);
        params.put("orderDateType",orderDateType);
        _logger.info("getCourse params:{}",params);
        Page<CourseDto> result = courseService.queryCourse(params);
        return R.ok().put("course", result.getRecords()).put("total",result.getTotal());
    }


    @CrossOrigin
    @RequestMapping(value="/pageAgent",method = RequestMethod.GET)
    public R pageAgent(
            @RequestParam(value ="pageSize",required=true,defaultValue = "5") int pageSize,
            @RequestParam(value ="pageNum",required=true,defaultValue = "1") int pageNum,
            @RequestParam(value ="institutionType",required=false) String institutionType,
            @RequestParam(value ="country",required=false) String country,
            @RequestParam(value ="town",required=false) String town,
            @RequestParam(value ="licensedImmigrationAgent",required=false) String licensedImmigrationAgent,
            @RequestParam(value ="orderType",required=false) String orderType,
            @RequestParam(value ="orderDateType",required=false) String orderDateType
    ){
        int courrentIndex = (pageNum-1)*pageSize;
        Map params = new HashMap<>();
        params.put("courrentIndex",courrentIndex);
        params.put("pageSize",pageSize);
        params.put("institutionType",institutionType);
        params.put("country",country);
        params.put("town",town);
        params.put("licensedImmigrationAgent",licensedImmigrationAgent);
        params.put("orderType",orderType);
        params.put("orderDateType",orderDateType);
        _logger.info("getCourse params:{}",params);
      Page<AgentDto> agentOfInstitution =   institutionService.pageAgent(params);
        return R.ok().put("total",agentOfInstitution.getTotal()).put("agentList",agentOfInstitution.getRecords());
    }
}

