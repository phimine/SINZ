package cn.funion.modules.sys.service.impl;


import cn.funion.common.utils.DateUtils;
import cn.funion.modules.sys.dao.*;
import cn.funion.modules.sys.entity.*;
import cn.funion.modules.sys.service.IDepartmentService;
import cn.funion.sync.HotCourse;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * <p>
 * 部门
学科 服务实现类
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentDao, Department> implements IDepartmentService {

    private static final Logger _logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private DepartmentPofileDao departmentPofileDao;
    @Autowired
    private DepartmentMediaDao departmentMediaDao;
    @Autowired
    private DepartmentExtraInfoDao departmentExtraInfoDao;
    @Autowired
    private DepartmentAddressDao departmentAddressDao;


    @Override
//@Scheduled(cron="0 */10 * * * ?")
    public void syncDepartmentFromHotCourse() throws ParseException {
        JSONObject result = HotCourse.syncHotCourse(1,HotCourse.DEPARTMENT_URL);
        if(StringUtils.isEmpty(result.get("hateoas"))){
            _logger.error("同步DEPARTMENT_URL信息失败");
        }
        //总页数
        int totalPageNum = Integer.parseInt((String)((JSONObject)result.get("hateoas")).get("total_pages"));
        _logger.info("DEPARTMENT_URL pageNum sync: {}",totalPageNum);
        //课程内容
        saveOrUpdateDepartment(result);

        if (totalPageNum>1){
            for(int i=2;i<=totalPageNum;i++){
                JSONObject departmentJson = HotCourse.syncHotCourse(i,HotCourse.DEPARTMENT_URL);
                if(StringUtils.isEmpty(departmentJson.get("hateoas"))){
                    _logger.error("同步DEPARTMENT信息失败");
                    continue;
                }
                _logger.info("当前同的页码： current_page: {}",i);
                saveOrUpdateDepartment(departmentJson);
            }
        }
    }

    private void saveOrUpdateDepartment(JSONObject result) throws ParseException {
        JSONArray jsonArry = JSONArray.fromObject(result.get("departments"));
        for(int i=0;i<jsonArry.size();i++){
            JSONObject departmentResult = (JSONObject) jsonArry.get(i);
            int currentDepartmentId = Integer.parseInt((String)departmentResult.get("department_id"));
            _logger.info("sync department_id:{}",currentDepartmentId);
            //查询是否存在
            Department queryCousre = new Department();
            queryCousre.setDepartmentId(currentDepartmentId);
            Department exist = departmentDao.selectOne(queryCousre);
            //存在则判断更新时间是否一致，一致则不更新，不一致更新
            if(exist != null){
                _logger.info("department exist {}",exist.toString());
                //更新时间不一致则更新
                if(exist.getLastUpdatedDate().before(DateUtils.parseDate((String)departmentResult.get("last_updated_date"),"yyyy-MMM-dd HH:mm:ss a", Locale.ENGLISH))){
                    fillDepartmentInfo(exist,departmentResult);
                }
            }else{

                fillDepartmentInfo(null,departmentResult);

            }
        }
    }

    private boolean fillDepartmentInfo(Department updateDb, JSONObject departmentJson) {
        try {
            int departmentId = Integer.parseInt((String) departmentJson.get("department_id"));
            Department exist = new Department();
            if(updateDb != null){
                exist = updateDb;
            }else{
                exist.setSyncDate(new Date());
                exist.setInstitutionId(departmentId);
                exist.setCreatedDate(new Date());
            }
            //更新institution 内容
            exist.setDepartmentId(departmentId);
            exist.setSilverStripeId((String)departmentJson.get("silver_stripe_id"));
            exist.setDepartmentName((String)departmentJson.get("department_name"));
            exist.setStatus((String)departmentJson.get("status"));
            exist.setInstitutionId(Integer.parseInt((String) departmentJson.get("institution_id")));
            exist.setLastUpdatedDate(DateUtils.parseDate((String)departmentJson.get("last_updated_date"),"yyyy-MMM-dd HH:mm:ss a", Locale.ENGLISH));
            exist.setSyncDate(new Date());
            if(updateDb != null){
                departmentDao.updateById(exist);
                //删除 department 中地址、exro/profile/media
                EntityWrapper queryW = new EntityWrapper();
                queryW.eq("department_id",departmentJson.getInt("department_id"));
                departmentExtraInfoDao.delete(queryW);
                departmentMediaDao.delete(queryW);
                departmentPofileDao.delete(queryW);
                departmentAddressDao.delete(queryW);
            }else{
                departmentDao.insert(exist);
            }

            //重新插入address
            JSONObject addJson = JSONObject.fromObject(departmentJson.get("address"));
            if(!StringUtils.isEmpty(addJson)){
                DepartmentAddress address = new DepartmentAddress();


                address.setDepartmentId(departmentId);
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
                departmentAddressDao.insert(address);
            }


            JSONObject extraJson = JSONObject.fromObject(departmentJson.get("extra_info"));
            if(!StringUtils.isEmpty(extraJson)){
                DepartmentExtraInfo extraInfo = new DepartmentExtraInfo();
                extraInfo.setDepartmentId(departmentId);
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
                if(!StringUtils.isEmpty(extraInfo.getAccommodation()) && !StringUtils.isEmpty(extraInfo.getDescription())
                        && !StringUtils.isEmpty(extraInfo.getGender()) && !StringUtils.isEmpty(extraInfo.getNumberOfInternationalStudents())
                        && !StringUtils.isEmpty(extraInfo.getNumberOfStudents()) && !StringUtils.isEmpty(extraInfo.getOnSiteFoodOptions())
                        && !StringUtils.isEmpty(extraInfo.getOnSiteReligiousFacilities()) && !StringUtils.isEmpty(extraInfo.getReligiousClubs())){
                    departmentExtraInfoDao.insert(extraInfo);
                }
            }

            JSONObject profileJson = JSONObject.fromObject(departmentJson.get("extra_info"));
            if(!StringUtils.isEmpty(profileJson)){
                DepartmentPofile profile = new DepartmentPofile();
                profile.setDepartmentId(departmentId);
                profile.setProfileSummary((String)profileJson.get( "profile_summary"));
                profile.setProfileUpdatedBy((String)profileJson.get("profile_updated_by"));
                if(!StringUtils.isEmpty((String)profileJson.get( "profile_updated_date"))){
                    profile.setProfileUpdatedDate(DateUtils.parseDate((String)profileJson.get( "profile_updated_date"),"yyyy-MMM-dd HH:mm:ss a", Locale.ENGLISH));
                }
                profile.setStatus(0);
                profile.setCreatedDate(new Date());
                if(!StringUtils.isEmpty(profile.getProfileSummary()) && !StringUtils.isEmpty(profile.getProfileUpdatedBy())){
                    departmentPofileDao.insert(profile);
                }

            }
            JSONArray mediaArr = JSONArray.fromObject(departmentJson.get("media"));
            if(!StringUtils.isEmpty(mediaArr) && mediaArr.size()>0) {
                for(int j=0;j<mediaArr.size();j++){
                    JSONObject mediaJson = (JSONObject) mediaArr.get(j);
                    DepartmentMedia media = new DepartmentMedia();
                    media.setDepartmentId(departmentId);
                    media.setMediaPath((String)mediaJson.get("media_path"));
                    media.setMediaType((String)mediaJson.get("media_type"));
                    media.setStatus(0);
                    media.setCreatedDate(new Date());
                    if(!StringUtils.isEmpty(media.getMediaPath()) && !StringUtils.isEmpty(media.getMediaType())){
                        departmentMediaDao.insert(media);
                    }

                }

            }
            return  true;
        }catch (Exception e){
            _logger.info("sync inser or update department, departmentInfo:{}",departmentJson.toString());
            _logger.error("sync inser or update department： ",e);
            return false;
        }
    }
}
