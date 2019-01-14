package cn.funion.sync;


import cn.funion.common.utils.DateUtils;
import cn.funion.common.utils.HttpUtils;
import cn.funion.sync.dto.Member;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MemberSync {
	private  Logger _logger = LoggerFactory.getLogger(MemberSync.class);
	@Value("${sinz.member.sinzURL}")
	private   String sinzURL ;
	@Value("${sinz.member.sinzClientId}")
	private   String sinzClientId;
	@Value("${sinz.member.sinzClientSecret}")
	private   String sinzClientSecret;
	private  String token;
	private   Date expiresIn;

    /**
     * h获取token
     * @return
     */
	public  String sinzAuth(){
		if(expiresIn != null && expiresIn.after(new Date())){
			_logger.info("old token:{},expiresIn:{}",token,expiresIn);
			return token;
		}
		JSONObject params = new JSONObject();
		params.put("clientId", sinzClientId);
		params.put("clientSecret",sinzClientSecret);
		Map<String,String> heads = new HashMap<>();
		heads.put("Content-Type", "application/json");
		JSONObject result = HttpUtils.postBody(sinzURL+"/auth", params, heads, null);
		expiresIn = DateUtils.addDateMinutes(new Date(), 5);
		token=result.getString("token");
		_logger.info("new token:{},expiresIn:{}",token,expiresIn);
		return token;
	}

    /**
     * 新增用户信息
     * @param member
     * @return
     */
	public  JSONObject createMember(Member member){
		String tocken = sinzAuth();
		Map<String,String> heads = new HashMap<>();
		heads.put("Content-Type", "application/json");
		heads.put("Authorization", "Bearer "+tocken);
		
		/*JSONObject params = new JSONObject();
		params.put("MemberMobile", member.getMemberMobile());
		params.put("Tester", true);
		params.put("PrivacyPolicyAgreed", 1);
		params.put("INZMatchingPermission", 1);
		params.put("WeChatOpenId", member.getWeChatOpenId());
		params.put("Source", "WECHAT_APPLET");
		params.put("MemberFirstName", member.getMemberFirstName());
		params.put("MemberLastName", member.getMemberLastName());
		//关注信息字段
		if(member.getWxFollowedDate() != null){
			params.put("Language", member.getLanguage());
			params.put("Province", member.getProvince());
			params.put("City", member.getCity());
			params.put("WXNickname", member.getWxNickname());
			params.put("WXFollowedStatus", true);
			params.put("WXFollowedDate", DateUtils.format(member.getWxFollowedDate()));
			params.put("WeChatGroup", member.getWeChatGroup());
		}*/
		
		JSONObject result = HttpUtils.postBody(sinzURL+"/members",member.toJson(false), heads, null);
		return result;
		
	}
	
	/**
	 * 更新用户信息
	 * @param member
	 * @return
	 */
	public  JSONObject updateMember(Member member){
		String tocken = sinzAuth();
		Map<String,String> heads = new HashMap<>();
		heads.put("Content-Type", "application/json");
		heads.put("Authorization", "Bearer "+tocken);
		//手机号不能再更新列
		member.setMemberMobile(null);
		JSONObject jsonResult =  HttpUtils.patchBody(sinzURL+"/members/"+member.getId(),member.toJson(true), heads, null);
//		return JsonHelper.str2obj(jsonResult.toString(),Member.class);
		return jsonResult;
		
	}

    /**
     * 获取用户信息
     * @param memberId
     * @return
     */
	public  JSONObject getMember(int memberId){
		String tocken = sinzAuth();
		Map<String,String> heads = new HashMap<>();
		heads.put("Authorization", "Bearer "+tocken);
		JSONObject result = (JSONObject)HttpUtils.get(sinzURL+"/members/"+memberId, heads, false);
		if(result.get("error") != null){return null;}
		try {
//			Member member = (Member)JsonHelper.str2obj(result.toString(), Member.class);
			return result;
		} catch (Exception e) {
			_logger.error("member get exceptiong",e);
			return null;
		}
	}

    /**
     * 根据memberId获取推荐列表
     * @param memberId  对应本地id
     * @return
     */
	public  JSONArray getRecommendateions(Long memberId) {
		String tocken = sinzAuth();
		Map<String,String> heads = new HashMap<>();
		heads.put("Authorization", "Bearer "+tocken);
		JSONArray jsonArry = (JSONArray)HttpUtils.get(sinzURL+"/members/"+memberId+"/recommendations", heads, true);
		if(StringUtils.isEmpty(jsonArry) ||
			jsonArry.isEmpty()){	return  null;	}
		_logger.info(jsonArry.toString());

		return jsonArry;
	}

	/**
	 * 根据推荐结果添加收藏夹
	 * @param memberId 对应本地id
	 * @param campusId
	 * @param opportunityId
	 * @param match
	 * @return
	 */
	public  JSONObject addShortList(Long memberId,Long campusId,Long opportunityId,Long match){
		String tocken = sinzAuth();
		JSONObject param = new JSONObject();
		param.put("campus_id",campusId);
		param.put("opportunity_id",opportunityId == null ? JSONNull.getInstance() : opportunityId);
		param.put("Match",match == null ? JSONNull.getInstance() : match);
		Map<String,String> heads = new HashMap<>();
		heads.put("Authorization", "Bearer "+tocken);
		heads.put("Content-Type", "application/json");
		JSONObject jsonResult = (JSONObject)HttpUtils.postBody(sinzURL+"/members/"+memberId+"/shortlist",param,heads,null);
		_logger.info(jsonResult.toString());
		if(jsonResult.get("error") != null){return null;}
		return jsonResult;
	}

	/**
	 * 获取收藏清单
	 * @param memberId 对应本地id
	 * @return
	 */
	public  JSONArray getShotLists(Long memberId) {
		Map<String,String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+sinzAuth());
		JSONArray jsonArrayResult = (JSONArray) HttpUtils.get(sinzURL+"/members/"+memberId+"/shortlist",headers,true);
		if(StringUtils.isEmpty(jsonArrayResult) ||
				jsonArrayResult.isEmpty()){	return  null;	}
		_logger.info(jsonArrayResult.toString());

		return jsonArrayResult;
	}
	
	/**
	 * 删除收藏项
	 * @param memberId 对应本地id
	 * @param itemId
	 * @return
	 */
	public  boolean deleteShortListItem(Long memberId,Long itemId){
		Map<String,String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+sinzAuth());
		return HttpUtils.delete(sinzURL+"/members/"+memberId+"/shortlist/"+itemId,null,headers,null);

	}
	
	/**
	 * 发送咨询信息
	 * @param memberId 对应本地id
	 * @param itemId
	 * @param message
	 * @return
	 */
	public  String sendEnquiry(Long memberId,Long itemId,String message){
		String token = sinzAuth();
		Map<String,String> heads = new HashMap<>();
		heads.put("Authorization", "Bearer "+token);
		heads.put("Content-Type", "application/json");
		
		JSONObject params = new JSONObject();
		params.put("message", message );
		JSONObject jsonResult =  HttpUtils.postBody(sinzURL+"/members/"+memberId+"/shortlist/"+itemId+"/enquiry",params, heads, null);
		if(jsonResult.get("error") != null){return null;}
		return (String)jsonResult.get("LastEnquiry");

	}
	
	/**
	 * 获取收藏项目的费用清单
	 * @param memberId 对应本地id
	 * @param itemId
	 * @return
	 */
	public  JSONArray getBudget(Long memberId,Long itemId){
		String tocken = sinzAuth();
		Map<String,String> heads = new HashMap<>();
		heads.put("Authorization", "Bearer "+tocken);
		JSONArray jsonArry = (JSONArray)HttpUtils.get(sinzURL+"/members/"+memberId+"/shortlist/"+itemId+"/budget", heads, true);
		if(StringUtils.isEmpty(jsonArry) ||
			jsonArry.isEmpty()){	return  null;	}
		_logger.info(jsonArry.toString());
//		List<Recommendation> results = JsonHelper.str2list(jsonArry.toString(), Recommendation.class);
		return jsonArry;
	}
	
	/**
	 * 获取笔记清单
	 * @param memberId 本地对应id
	 * @param itemId
	 * @return
	 */
	public  JSONArray getNoteList(Long memberId,Long itemId){
		String tocken = sinzAuth();
		Map<String,String> heads = new HashMap<>();
		heads.put("Authorization", "Bearer "+tocken);
		Object result = HttpUtils.get(sinzURL+"/members/"+memberId+"/shortlist/"+itemId+"/notes", heads, true);
		if(result != null){
			JSONArray jsonArry = (JSONArray)result;
			return jsonArry;
		}
		return null;
		
	}
	
	/**
	 * 新增note
	 * @param memberId 对应本地 id
	 * @param itemId
	 * @param body
	 * @return
	 */
	public  JSONObject addNote(Long memberId,Long itemId,String body){
		String tocken = sinzAuth();
		Map<String,String> heads = new HashMap<>();
		heads.put("Authorization", "Bearer "+tocken);
		heads.put("Content-Type", "application/json");
		JSONObject params = new JSONObject();
		params.put("body", body);
		JSONObject jsonResult =  HttpUtils.postBody(sinzURL+"/members/"+memberId+"/shortlist/"+itemId+"/notes",params, heads, null);
		if(jsonResult.get("error") != null){return null;}
		return jsonResult;

	}
	
	/**
	 * 删除note
	 * @param memberId 对应本地 id
	 * @param itemId
	 * @param noteId
	 * @return
	 */
	public  boolean deleteNote(Long memberId,Long itemId,Long noteId){
		Map<String,String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+sinzAuth());
		return HttpUtils.delete(sinzURL+"/members/"+memberId+"/shortlist/"+itemId+"/notes/"+noteId,null,headers,null);

	}

	/**
	 *
	 * @param memberId 对应本地 id
	 * @param itemId
	 * @param enrolment
	 * @return
	 */
	public  JSONObject enrolment(Long memberId,Long itemId,String enrolment){
		String tocken = sinzAuth();
		Map<String,String> heads = new HashMap<>();
		heads.put("Authorization", "Bearer "+tocken);
		heads.put("Content-Type", "application/json");
		JSONObject params = new JSONObject();
		params.put("status", enrolment);
		JSONObject jsonResult =  HttpUtils.postBody(sinzURL+"/members/"+memberId+"/shortlist/"+itemId+"/enrolment",params, heads, null);
		if(jsonResult.get("error") != null){return null;}
		return jsonResult;
	}




}
