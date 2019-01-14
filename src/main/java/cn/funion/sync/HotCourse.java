package cn.funion.sync;

import cn.funion.common.utils.HttpUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class HotCourse {
	public static final Logger _logger= LoggerFactory.getLogger(HotCourse.class);
	public static final String COURSE_URL ="https://api.hotcoursesabroad.com/studyinapi/type1/courses/";
	public static final String INSTITUTION_URL="https://api.hotcoursesabroad.com/studyinapi/type1/institutions/";
	public static final String CAMPUS_URL="https://api.hotcoursesabroad.com/studyinapi/type1/campuses/";
	public static final String DEPARTMENT_URL="https://api.hotcoursesabroad.com/studyinapi/type1/departments/";
	public static final String OPPORTUNITY_URL="https://api.hotcoursesabroad.com/studyinapi/type1/opportunities/";
	public static final String SCHOLARSHP_URL="https://api.hotcoursesabroad.com/studyinapi/type1/scholarships/";


	private static final String AFFILIATE_ID="70317";
	private static final String ACCESS_TOKEN="14FA7DF69F27ABDCA3ED5808C5AE74E3";
	/**
	 * 同步课程信息
	 * @param pageNum
	 * @return
	 */
	public static  JSONObject syncHotCourse(int pageNum,String url){
		Map<String,String>  param = new HashMap<>();
		param.put("affiliate_id", AFFILIATE_ID);
		param.put("access_token", ACCESS_TOKEN);
		param.put("page_number", pageNum+"");
		
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");


//		JSONObject resultJson = HttpUtils.get(url, param,headers);
		JSONObject resultJson = JSONObject.fromObject(HttpUtils.doGetSSL(url,param,null));
		if(StringUtils.isEmpty(resultJson)){return null;}
	
		return resultJson;
	}

	public static void main(String[] args){
		System.out.println(syncHotCourse(1,COURSE_URL));
	}

}
