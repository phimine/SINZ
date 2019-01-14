package cn.funion.modules.sys.service.impl;

import cn.funion.common.utils.DateUtils;
import cn.funion.modules.sys.dao.*;
import cn.funion.modules.sys.entity.*;
import cn.funion.modules.sys.service.ICampusService;
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
 * 大学 服务实现类
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Service
public class CampusServiceImpl extends ServiceImpl<CampusDao, Campus> implements ICampusService {
    private Logger _logger = LoggerFactory.getLogger(CampusServiceImpl.class);
    @Autowired
    private CampusDao campusDao;
    @Autowired
    private CampusAddressDao campusAddressDao;
    @Autowired
    private CampusExtraInfoDao campusExtraInfoDao;
    @Autowired
    private CampusProfileDao campusProfileDao;
    @Autowired
    private CampusMediaDao campusMediaDao;

    @Override
//@Scheduled(cron="0 */10 * * * ?")
    public void syncCampusFromHotCourse() throws ParseException {
        JSONObject result = HotCourse.syncHotCourse(1,HotCourse.CAMPUS_URL);
        if(StringUtils.isEmpty(result.get("hateoas"))){
            _logger.error("同步CAMPUS_URL信息失败");
        }
        //总页数
        int totalPageNum = Integer.parseInt((String)((JSONObject)result.get("hateoas")).get("total_pages"));
        _logger.info("CAMPUS_URL pageNum sync: {}",totalPageNum);
        //课程内容
        saveOrUpdateCampus(result);

        if (totalPageNum>1){
            for(int i=2;i<=totalPageNum;i++){
                JSONObject campusJson = HotCourse.syncHotCourse(i,HotCourse.CAMPUS_URL);
                if(StringUtils.isEmpty(campusJson.get("hateoas"))){
                    _logger.error("同步course信息失败");
                    continue;
                }
                _logger.info("当前同的页码： current_page: {}",i);
                saveOrUpdateCampus(campusJson);
            }
        }
    }

    private void saveOrUpdateCampus(JSONObject result) throws ParseException {
        JSONArray jsonArry = JSONArray.fromObject(result.get("campuses"));
        for(int i=0;i<jsonArry.size();i++){
            JSONObject campusResult = (JSONObject) jsonArry.get(i);
            int currentCampusId = Integer.parseInt((String)campusResult.get("campus_id"));
            _logger.info("sync campus_id:{}",currentCampusId);
            //查询是否存在
            Campus queryCampus = new Campus();
            queryCampus.setCampusId(currentCampusId);
            Campus exist = campusDao.selectOne(queryCampus);
            //存在则判断更新时间是否一致，一致则不更新，不一致更新
            if(exist != null){
                _logger.info("Campus exist {}",exist.toString());
                //更新时间不一致则更新
                if(exist.getLastUpdatedDate().before(DateUtils.parseDate((String)campusResult.get("last_updated_date"),"yyyy-MMM-dd HH:mm:ss a", Locale.ENGLISH))){
                    fillCampusInfo(exist,campusResult);
                }
            }else{

                fillCampusInfo(null,campusResult);

            }
        }
    }

    private boolean fillCampusInfo(Campus updateDb, JSONObject campusResult) {
        try{
            int campusId = Integer.parseInt((String) campusResult.get("campus_id"));
            Campus exist = new Campus();
            if(updateDb != null){
                exist = updateDb;
            }else{
                exist.setSyncDate(new Date());
                exist.setCampusId(campusId);
                exist.setCreatedDate(new Date());
            }
            //更新institution 内容

            exist.setCampusName((String)campusResult.get("campus_name"));
            exist.setLastUpdatedDate(DateUtils.parseDate((String)campusResult.get("last_updated_date"),"yyyy-MMM-dd HH:mm:ss a", Locale.ENGLISH));
            exist.setMinistryId((String) campusResult.get("ministry_id"));
            exist.setSilverStripeId((String)campusResult.get("silver_stripe_id"));
            exist.setStatus((String)campusResult.get("status"));
            exist.setInstitutionId(Integer.parseInt((String) campusResult.get("institution_id")));
            exist.setSyncDate(new Date());
            if(updateDb != null){
                campusDao.updateById(exist);
                //删除 institution 中地址、exro/profile/media
                EntityWrapper queryW = new EntityWrapper();
                queryW.eq("campus_id",campusResult.getInt("campus_id"));
                campusAddressDao.delete(queryW);
                campusExtraInfoDao.delete(queryW);
                campusProfileDao.delete(queryW);
                campusMediaDao.delete(queryW);
            }else{
                campusDao.insert(exist);
            }

            //重新插入address
            JSONObject addJson = JSONObject.fromObject(campusResult.get("address"));
            if(!StringUtils.isEmpty(addJson)){
                CampusAddress address = new CampusAddress();
                address.setCampusId(campusId);
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
                campusAddressDao.insert(address);
            }


            JSONObject extraJson = JSONObject.fromObject(campusResult.get("extra_info"));
            if(!StringUtils.isEmpty(extraJson)){
                CampusExtraInfo extraInfo = new CampusExtraInfo();
                extraInfo.setCampusId(campusId);
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
                    campusExtraInfoDao.insert(extraInfo);
                }
            }

            JSONObject profileJson = JSONObject.fromObject(campusResult.get("profile"));
            if(!StringUtils.isEmpty(profileJson)){
                CampusProfile profile = new CampusProfile();
                profile.setCampusId(campusId);
                profile.setProfileSummary((String)profileJson.get( "profile_summary"));
                profile.setProfileUpdatedBy((String)profileJson.get("profile_updated_by"));
                if(!StringUtils.isEmpty((String)profileJson.get( "profile_updated_date"))){
                    profile.setProfileUpdatedDate(DateUtils.parseDate((String)profileJson.get( "profile_updated_date"),"yyyy-MMM-dd HH:mm:ss a", Locale.ENGLISH));
                }
                profile.setStatus(0);
                profile.setCreatedDate(new Date());
                if(!StringUtils.isEmpty(profile.getProfileSummary()) && !StringUtils.isEmpty(profile.getProfileUpdatedBy())){

                    campusProfileDao.insert(profile);
                }
            }
            JSONArray mediaArr = JSONArray.fromObject(campusResult.get("media"));
            if(!StringUtils.isEmpty(mediaArr) && mediaArr.size()>0) {
                for(int j=0;j<mediaArr.size();j++){
                    JSONObject mediaJson = (JSONObject) mediaArr.get(j);
                    CampusMedia media = new CampusMedia();
                    media.setCampusId(campusId);
                    media.setMediaPath((String)mediaJson.get("media_path"));
                    media.setMediaType((String)mediaJson.get("media_type"));
                    media.setStatus(0);
                    media.setCreatedDate(new Date());
                    if(!StringUtils.isEmpty(media.getMediaPath()) && !StringUtils.isEmpty(media.getMediaType())){
                        campusMediaDao.insert(media);
                    }
                }

            }
            return  true;
        }catch (Exception e){
            _logger.info("sync inser or update Campus, CampusInfo:{}",campusResult.toString());
            _logger.error("sync inser or update Campus： ",e);
            return false;
        }


    }


}
