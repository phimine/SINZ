package cn.funion.sync;

import cn.funion.common.utils.DateUtils;
import cn.funion.common.utils.HttpUtils;
import cn.funion.modules.sys.dao.LeadCollectionDao;
import cn.funion.sync.dto.Member;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class LeadCollection {
	@Autowired
	private LeadCollectionDao leadCollectionDao;
	private static Logger _logger = LoggerFactory.getLogger(LeadCollection.class);
//	@Value("${sinz.lead.sinzURL}")
	private   String sinzURL="https://lead-api.staging.enz.cloud" ;
//	@Value("${sinz.lead.sinzClientId}")
	private   String sinzClientId="df5927e0-8329-11e8-aae1-5f040c87bdcf";
//	@Value("${sinz.lead.sinzClientSecret}")
	private   String sinzClientSecret="bai7eeyem~aoNg@u";
	private static String token;
	private static  Date expiresIn;
    /**
     * h获取token
     * @return
     */
	public  String leadAuth(){
		if(expiresIn != null && expiresIn.after(new Date())){
			_logger.error("old token:{},expiresIn:{}",token,expiresIn);
			return token;
		}
		JSONObject params = new JSONObject();
		params.put("clientId", sinzClientId);
		params.put("clientSecret",sinzClientSecret);
		Map<String,String> heads = new HashMap<>();
		heads.put("Content-Type", "application/json");
		JSONObject result = HttpUtils.postBody(sinzURL+"/auth", params, heads, null);
		expiresIn = DateUtils.addDateMinutes(new Date(), 30);
		token=result.getString("token");
		_logger.error("new token:{},expiresIn:{}",token,expiresIn);
		return token;
	}

	/**
	 * 新增用户信息
	 * @param member
	 * @return
	 */
	public  JSONObject upload(Member member){
		leadCollectionDao.save(member);
		String tocken = leadAuth();
		Map<String,String> heads = new HashMap<>();
		heads.put("Content-Type", "application/json");
		heads.put("Authorization", "Bearer "+tocken);
		JSONObject result = HttpUtils.postBody(sinzURL+"/upload",member.toLeadJson(), heads, null);
		return result;
	}
	
}
