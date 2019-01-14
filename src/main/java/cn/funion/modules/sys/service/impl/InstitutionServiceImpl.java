package cn.funion.modules.sys.service.impl;


import cn.funion.common.utils.DateUtils;
import cn.funion.modules.sys.dao.*;
import cn.funion.modules.sys.entity.*;
import cn.funion.modules.sys.entity.wapperDto.AgentDto;
import cn.funion.modules.sys.entity.wapperDto.CampusDto;
import cn.funion.modules.sys.entity.wapperDto.DepartmentDto;
import cn.funion.modules.sys.entity.wapperDto.InstitutionDto;
import cn.funion.modules.sys.service.IInstitutionService;
import cn.funion.sync.HotCourse;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.*;

/**
 * <p>
 * 机构 服务实现类
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Service
public class InstitutionServiceImpl extends ServiceImpl<InstitutionDao, Institution> implements IInstitutionService {
    private Logger _logger = LoggerFactory.getLogger(InstitutionServiceImpl.class);
    @Autowired
    private InstitutionDao institutionDao;
    @Autowired
    private InstitutionAddressDao institutionAddressDao;
    @Autowired
    private InstitutionExtraInfoDao institutionExtraInfoDao;
    @Autowired
    private InstitutionProfileDao institutionProfileDao;
    @Autowired
    private InstitutionMediaDao institutionMediaDao;

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CampusDao campusDao;
    @Autowired
    private DepartmentDao departmentDao;


    @Override
//    @Transactional(rollbackFor = Exception.class)
//@Scheduled(cron="0 */10 * * * ?")
    public void syncInstitutionFromHotCourse() throws ParseException {
        JSONObject result = HotCourse.syncHotCourse(1,HotCourse.INSTITUTION_URL);
        if(StringUtils.isEmpty(result.get("hateoas"))){
            _logger.error("同步INSTITUTION_URL信息失败");
        }
        //总页数
        int totalPageNum = Integer.parseInt((String)((JSONObject)result.get("hateoas")).get("total_pages"));
        _logger.info("INSTITUTION_URL pageNum sync: {}",totalPageNum);
        //课程内容
        saveOrUpdateInstitution(result);

        if (totalPageNum>1){
            for(int i=2;i<=totalPageNum;i++){
                JSONObject instatutionInForJson = HotCourse.syncHotCourse(i,HotCourse.INSTITUTION_URL);
                if(StringUtils.isEmpty(instatutionInForJson.get("hateoas"))){
                    _logger.error("同步course信息失败");
                    continue;
                }
                _logger.info("当前同的页码： current_page: {}",i);
                saveOrUpdateInstitution(instatutionInForJson);
            }
        }

    }

    @Override
    public Page<InstitutionDto> pageInstitutions(Map params) {
        int total = institutionDao.countInstitutions(params);
        List<InstitutionDto> records = new ArrayList<>();
        if(total >0){
            records = institutionDao.pageInstitutions(params);
            //补充其他信息
            for(InstitutionDto dto:records){
                //查询学科
                //TODO
                EntityWrapper ew = new EntityWrapper();
                ew.eq("institution_id",dto.getInstitutionId()).eq("_del_status",0);
                List<Course> coursesInst = courseDao.selectList(ew);
                List<String> areaOfStudy = new ArrayList<>();
                for(Course course:coursesInst){
                    if(!areaOfStudy.contains(course.getAreaOfStudy()))
                        areaOfStudy.add(course.getAreaOfStudy());
                }
                dto.setAreaOfStudy(areaOfStudy);
            }
        }
        Page<InstitutionDto> result = new Page();
        result.setTotal(total);
        result.setRecords(records);
        return result;
    }

    @Override
    public InstitutionDto campusOfInstitution(int institutionId) {
        //查询institution基本信息
        InstitutionDto institutionDto = institutionDao.queryInstitutionInfoById(institutionId);
        if(institutionDto != null){
            //添加media
            EntityWrapper eq = new EntityWrapper();
            eq.eq("institution_id",institutionId);
            List<InstitutionMedia> mediaList = institutionMediaDao.selectList(eq);
            institutionDto.setInstitutionMedia(mediaList);
            //根据institution信息填充campus
            List<CampusDto> campusOfInstitution = campusDao.campusOfInstitution(institutionId);
            institutionDto.setCampusList(campusOfInstitution);
        }
        return institutionDto;
    }

    @Override
    public InstitutionDto institutionDetail(int institutionId) {
        //查询基础信息
       InstitutionDto institutionDto = institutionDao.queryInstitutionInfoById(institutionId);
       if(institutionDto != null){
           //查询department
           List<DepartmentDto> departments = departmentDao.departmentOfInstitution(institutionId);
           institutionDto.setDepartmentList(departments);
           // 添加campus
           List<CampusDto> campusList = campusDao.campusOfInstitution(institutionId);
           institutionDto.setCampusList(campusList);
           //添加media
           EntityWrapper eq = new EntityWrapper();
           eq.eq("institution_id",institutionId);
           List<InstitutionMedia> mediaList = institutionMediaDao.selectList(eq);
           institutionDto.setInstitutionMedia(mediaList);
       }

        return institutionDto;
    }

    @Override
    public Page<AgentDto> pageAgent(Map params) {

        Page  result = new Page();
      int rowNumb =  institutionDao.countAgent(params);
        result.setTotal(rowNumb);
      if(rowNumb >0){
          List<AgentDto> agentList = institutionDao.pageAgent(params);
          result.setRecords(agentList);
      }
        return result;
    }


    private void saveOrUpdateInstitution(JSONObject result) throws ParseException {
        JSONArray jsonArry = JSONArray.fromObject(result.get("institutions"));
        for(int i=0;i<jsonArry.size();i++){
            JSONObject instationResult = (JSONObject) jsonArry.get(i);
            int currentInstitutionId = Integer.parseInt((String)instationResult.get("institution_id"));
            _logger.info("sync institution_id:{}",currentInstitutionId);
            //查询是否存在
            Institution queryInstitution = new Institution();
            queryInstitution.setInstitutionId(currentInstitutionId);
            Institution exist = institutionDao.selectOne(queryInstitution);
            //存在则判断更新时间是否一致，一致则不更新，不一致更新
            if(exist != null){
                _logger.info("institution exist {}",exist.toString());
                //更新时间不一致则更新
                if(exist.getLastUpdatedDate().before(DateUtils.parseDate((String)instationResult.get("last_updated_date"),"yyyy-MMM-dd HH:mm:ss a", Locale.ENGLISH))){
                    fillInstitutionInfo(exist,instationResult);
                }
            }else{

                    fillInstitutionInfo(null,instationResult);

            }
        }
    }

private boolean fillInstitutionInfo(Institution updateDb,JSONObject instationResult){
        try{
            int institutionId = Integer.parseInt((String) instationResult.get("institution_id"));
            Institution exist = new Institution();
            if(updateDb != null){
                exist = updateDb;
            }else{
                exist.setSyncDate(new Date());
                exist.setInstitutionId(institutionId);
                exist.setDelStatus(0);
                exist.setCreatedDate(new Date());
            }
            //更新institution 内容
            exist.setAnnualTuitionFees((String)instationResult.get("annual_tuition_fees"));
            exist.setCodeOfPracticeSignatory((String)instationResult.get("code_of_practice_signatory"));
            exist.setEmail((String)instationResult.get("email"));
            exist.setInstitutionName((String)instationResult.get("institution_name"));
            exist.setInstitutionType((String)instationResult.get("institution_type"));
            exist.setLastUpdatedDate(DateUtils.parseDate((String)instationResult.get("last_updated_date"),"yyyy-MMM-dd HH:mm:ss a", Locale.ENGLISH));
            exist.setMinistryId((String) instationResult.get("ministry_id"));
            exist.setSilverStripeId((String)instationResult.get("silver_stripe_id"));
            exist.setWebsite((String)instationResult.get("website"));
            exist.setStatus((String)instationResult.get("status"));
            exist.setLicensedImmigrationAgent((String)instationResult.get("licensed_immigration_agent"));
            exist.setSyncDate(new Date());
            if(updateDb != null){
                institutionDao.updateById(exist);
                //删除 institution 中地址、exro/profile/media
                EntityWrapper queryW = new EntityWrapper();
                queryW.eq("institution_id",instationResult.getInt("institution_id"));
                institutionAddressDao.delete(queryW);
                institutionExtraInfoDao.delete(queryW);
                institutionProfileDao.delete(queryW);
                institutionMediaDao.delete(queryW);
            }else{
                institutionDao.insert(exist);
            }

            //重新插入address
            JSONObject addJson = JSONObject.fromObject(instationResult.get("address"));
            if(!StringUtils.isEmpty(addJson)){
                InstitutionAddress address = new InstitutionAddress();
                address.setInstitutionId(institutionId);
                address.setCountry((String) addJson.get("country"));
                address.setLatitude((String) addJson.get("latitude"));
                address.setLongitude((String) addJson.get("longitude"));
                address.setPostcode((String) addJson.get("postcode"));
                address.setPhone((String) addJson.get("phone"));
                address.setRegion((String) addJson.get("region"));
                address.setEmail((String)addJson.get( "email"));
                address.setStatus(0);
                address.setStreetAddress1((String) addJson.get("street_address1"));
                address.setStreetAddress2((String) addJson.get("street_address2"));
                address.setTown((String) addJson.get("town"));
                address.setWebsite((String) addJson.get("website"));
                address.setCreatedDate(new Date());
                institutionAddressDao.insert(address);
            }


            JSONObject extraJson = JSONObject.fromObject(instationResult.get("extra_info"));
            if(!StringUtils.isEmpty(extraJson)){
                InstitutionExtraInfo extraInfo = new InstitutionExtraInfo();
                extraInfo.setInstitutionId(institutionId);
                extraInfo.setAccommodation((String)extraJson.get("accommodation"));
                extraInfo.setDescription((String)extraJson.get("description"));
                extraInfo.setGender((String)extraJson.get("gender"));
                extraInfo.setNumberOfInternationalStudents((String)extraJson.get("number_of_international_students"));
                extraInfo.setNumberOfStudents((String)extraJson.get( "number_of_students"));
                extraInfo.setOnSiteFoodOptions((String)extraJson.get("on_site_food_options"));
                extraInfo.setOnSiteReligiousFacilities((String)extraJson.get( "on_site_religious_facilities"));
                extraInfo.setReligiousClubs((String)extraJson.get("religious_clubs"));
                extraInfo.setStatus(0);
                extraInfo.setCreatedDate(new Date());
                institutionExtraInfoDao.insert(extraInfo);
            }

            JSONObject profileJson = JSONObject.fromObject(instationResult.get("profile"));
            if(!StringUtils.isEmpty(profileJson)){
                InstitutionProfile profile = new InstitutionProfile();
                profile.setInstitutionId(institutionId);
                profile.setProfileSummary((String)profileJson.get( "profile_summary"));
                profile.setProfileUpdatedBy((String)profileJson.get("profile_updated_by"));
                if(!StringUtils.isEmpty((String)profileJson.get( "profile_updated_date"))){
                    profile.setProfileUpdatedDate(DateUtils.parseDate((String)profileJson.get( "profile_updated_date"),"yyyy-MMM-dd HH:mm:ss a", Locale.ENGLISH));
                }
                profile.setStatus(0);
                profile.setCreatedDate(new Date());
                institutionProfileDao.insert(profile);
            }
            JSONArray mediaArr = JSONArray.fromObject(instationResult.get("media"));
            if(!StringUtils.isEmpty(mediaArr) && mediaArr.size()>0) {
                for(int j=0;j<mediaArr.size();j++){
                    JSONObject mediaJson = (JSONObject) mediaArr.get(j);
                    InstitutionMedia media = new InstitutionMedia();
                    media.setInstitutionId(institutionId);
                    media.setMediaPath((String)mediaJson.get("media_path"));
                    media.setMediaType((String)mediaJson.get("media_type"));
                    media.setStatus(0);
                    media.setCreatedDate(new Date());
                    institutionMediaDao.insert(media);
                }

            }
            return  true;
        }catch (Exception e){
            _logger.info("sync inser or update Institution, institutionInfo:{}",instationResult.toString());
            _logger.error("sync inser or update Institution： ",e);
            return false;
        }

}

}
