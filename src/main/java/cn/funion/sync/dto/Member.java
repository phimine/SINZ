package cn.funion.sync.dto;



import cn.funion.common.utils.DateUtils;
import cn.funion.common.utils.TinyPinyinUtils;
import lombok.Getter;
import lombok.Setter;
import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * "required": [ "MemberFirstName", "MemberLastName", "PrivacyPolicyAgreed",
 * "INZMatchingPermission" ]
 *
 * @author xjk
 *
 */
@Setter
@Getter
public class Member {

	private static final long serialVersionUID = 1L;
	private Long memberId;
	private Long id;

	/**
	 * 名字
	 */
	private String memberFirstName;
	/**
	 * 姓氏
	 */
	private String memberLastName;

	/**
	 * 邮箱
	 */
	private String memberEmail;

	/**
	 * 手机号
	 */
	private String memberMobile;
	/**
	 * 数据字典PersonTypes": [ "Student",  "Parent/Guardian",  "Agent/Advisor
	 */
	private String memberPersonType;

	/**
	 * 会员当前学历
	 */
	private String studentHighestLevelofStudy;
	/**
	 * 会员性别
	 */
	private String studentGender;

	/**
	 * 出生年月
	 */
	private String studentDOB;
	/**
	 * 感兴趣院校
	 */

	private String intendedInstitutionType;

	/**
	 * 感兴趣学位
	 */
	private String intendedLevelofStudy;
	/**
	 * 感兴趣专业
	 */
	private String intendedAreaofStudy;

	/**
	 * 感兴趣专业
	 */
	private String intendedSubjectofStudy;

	/**
	 * 期望开始时间
	 */
	private String intendedStartDate;

	/**
	 * 留学预算
	 */
	private String annualTuitionBudget;

	/**
	 * 期望城市类型
	 */
	private String preferredLocalityType;


	/**
	 * 测试表示
	 */
	private Boolean tester;
	/**
	 * 许可授权
	 */
	private Boolean permissionHowTo;

	/**
	 * 是否同意广告
	 */
	private Boolean permissionNews;
	/**
	 * 是否同意获位置
	 */
	private Boolean permissionLocal;
	/**
	 * 是否同意推荐
	 */
	private Boolean permissionInspire;
	/**
	 * 是否同意广告
	 */
	private Boolean permissionAlert;

	/**
	 * 签证进度
	 */
	private String visaProgress;

	/**
	 * 常量1
	 */
	private Integer privacyPolicyAgreed;

	/**
	 * 常量1
	 */
	private Integer inzMatchingPermission ;
	/**
	 * 语言
	 */
	private String language;

	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 微信OpenId
	 */
	private String weChatOpenId;
	/**
	 * 微信昵称
	 */
	private String wxNickname;

	/**
	 * 头像
	 */
	private String avatarUrl;

	/**
	 * 国籍
	 */
	private String country;

	/**
	 * 微信关注状态
	 */
	private Boolean wxFollowedStatus;
	/**
	 * 微信关注时间
	 */
	private Date wxFollowedDate;
	/**
	 * 微信聊天组
	 */
	private String weChatGroup;

	private String weChatUnionId;

	private String gzhOpenId;
	/**
	 * 用户来源H5
	 */
	private String source;
	private String studentFirstName;
	private String studentLastName;

	private Boolean matched;

	private String createdDate;
	private String updatedDate;
	private int status;
	private String intendedFundingSource;
	private String agentUse;
	private String englishLevel;

	//lead add
	private boolean optIn;
	private String googleId;
	//munchkin code _mkto_trk
	private String cookie;
	private String enquiryComment;
	private Integer enquiryCourseId;
	private Integer enquiryScholarshipId;
	private Integer enquirySubjectId;
	private String enquiryScholarshipName;
	private boolean planningWorkAfterSutdy;
	private String utmCampaign;
	private String utmContent;
	private String utmMedium;
	private String utmSource;
	private boolean planningWorkAfterStudy;




	public JSONObject toJson (boolean update){
		JSONObject json = new JSONObject();
		if(update){
			if(!StringUtils.isEmpty(this.memberFirstName)){json.put("MemberFirstName",this.memberFirstName == null ? "" :TinyPinyinUtils.coverToPinyin(this.memberFirstName));}
			if(!StringUtils.isEmpty(this.memberLastName)){json.put("MemberLastName",this.memberLastName == null ? "" : TinyPinyinUtils.coverToPinyin(this.memberLastName));}
		}else{
			json.put("MemberFirstName",this.memberFirstName !=null ? TinyPinyinUtils.coverToPinyin(this.memberFirstName) :"");
			json.put("MemberLastName",this.memberLastName != null ? TinyPinyinUtils.coverToPinyin(this.memberLastName) : "");
		}
		if(!StringUtils.isEmpty(this.englishLevel)){
			json.put("EnglishLevel",this.englishLevel);
		}
		json.put("MemberMobile",this.memberMobile);
		json.put("PrivacyPolicyAgreed",1);
		json.put("INZMatchingPermission",1);
		json.put("WeChatOpenId",this.weChatOpenId);
		if(!StringUtils.isEmpty(this.memberEmail)){json.put("MemberEmail",this.memberEmail);}
		if(!StringUtils.isEmpty(this.memberPersonType)){json.put("MemberPersonType",this.memberPersonType);}
//    	json.put("StudentNationality","China");
//    	json.put("StudentCountryofResidence","China");
		if(!StringUtils.isEmpty(this.studentHighestLevelofStudy)){json.put("StudentHighestLevelofStudy",this.studentHighestLevelofStudy);}
		if(!StringUtils.isEmpty(this.studentGender)){json.put("StudentGender",this.studentGender);}
		//转拼音
		if(!StringUtils.isEmpty(this.studentFirstName)){json.put("StudentFirstName",this.studentFirstName == null ? "" :TinyPinyinUtils.coverToPinyin(this.studentFirstName));}
		if(!StringUtils.isEmpty(this.studentLastName)){json.put("StudentLastName",this.studentLastName ==null ? "" :TinyPinyinUtils.coverToPinyin(this.studentLastName));}
		if(!StringUtils.isEmpty(this.studentDOB)){json.put("StudentDOB",this.studentDOB);}
		if(!StringUtils.isEmpty(this.intendedInstitutionType)){json.put("IntendedInstitutionType",this.intendedInstitutionType);}
		if(!StringUtils.isEmpty(this.intendedLevelofStudy))json.put("IntendedLevelofStudy",this.intendedLevelofStudy);
		if(!StringUtils.isEmpty(this.intendedAreaofStudy))json.put("IntendedAreaofStudy",this.intendedAreaofStudy);
		if(!StringUtils.isEmpty(this.intendedSubjectofStudy))json.put("IntendedSubjectofStudy",this.intendedSubjectofStudy);

		if(!StringUtils.isEmpty(this.intendedStartDate))json.put("IntendedStartDate",this.intendedStartDate);
		if(!StringUtils.isEmpty(this.intendedSubjectofStudy))json.put("IntendedSubjectofStudy",this.intendedSubjectofStudy);
		if(!StringUtils.isEmpty(this.annualTuitionBudget))json.put("AnnualTuitionBudget",this.annualTuitionBudget);
		if(!StringUtils.isEmpty(this.language))json.put("Language",this.language);
		if(!StringUtils.isEmpty(this.province))json.put("Province",this.province);
		if(!StringUtils.isEmpty(this.city))json.put("City",this.city);
		if(!StringUtils.isEmpty(this.wxNickname))json.put("WXNickname",this.wxNickname);
		if(!StringUtils.isEmpty(this.wxFollowedStatus))json.put("WXFollowedStatus",this.wxFollowedStatus.booleanValue());
		if(!StringUtils.isEmpty(this.wxFollowedDate))json.put("WXFollowedDate", DateUtils.format(this.wxFollowedDate));
		if(!StringUtils.isEmpty(this.weChatGroup))json.put("WeChatGroup",this.weChatGroup);
		if(!StringUtils.isEmpty(this.source))json.put("Source","WECHAT_APPLET");
		if(!StringUtils.isEmpty(this.preferredLocalityType))json.put("PreferredLocalityType",this.preferredLocalityType);
		if(!StringUtils.isEmpty(this.tester))json.put("Tester",this.tester);
		if(!StringUtils.isEmpty(this.visaProgress))json.put("VisaProgress",this.visaProgress);
		if(!StringUtils.isEmpty(this.agentUse))json.put("AgentUse",this.agentUse);
		if(!StringUtils.isEmpty(this.intendedFundingSource))json.put("IntendedFundingSource",this.intendedFundingSource);
		return json;
	}

	public JSONObject toLeadJson () {
		JSONObject json = new JSONObject();
		if(!StringUtils.isEmpty(this.source))json.put("source",this.source);
		if(!StringUtils.isEmpty(this.memberEmail))json.put("email",this.memberEmail);
		if(!StringUtils.isEmpty(this.optIn))json.put("optIn",this.optIn);
		if(!StringUtils.isEmpty(this.memberFirstName))json.put("firstName",this.memberFirstName);
		if(!StringUtils.isEmpty(this.memberLastName))json.put("lastName",this.memberLastName);
		if(!StringUtils.isEmpty(this.country))json.put("nationality",this.country);
//		json.put("nationality","China");
		if(!StringUtils.isEmpty(this.memberMobile))json.put("mobilePhone",this.memberMobile);
		if(!StringUtils.isEmpty(this.studentDOB))json.put("dateOfBirth",this.studentDOB);
		if(!StringUtils.isEmpty(this.memberPersonType))json.put("personType",this.memberPersonType);
		if(!StringUtils.isEmpty(this.language))json.put("language",this.language);
		if(!StringUtils.isEmpty(this.englishLevel))json.put("englishLevel",this.englishLevel);
		if(!StringUtils.isEmpty(this.intendedLevelofStudy))json.put("levelOfStudy",this.intendedLevelofStudy);
		if(!StringUtils.isEmpty(this.intendedAreaofStudy))json.put("areaofStudy",this.intendedAreaofStudy);
		if(!StringUtils.isEmpty(this.intendedStartDate))json.put("whenToStart",this.intendedStartDate);
		if(!StringUtils.isEmpty(this.agentUse))json.put("useOfAgent",this.agentUse);
		if(!StringUtils.isEmpty(this.googleId))json.put("googleId",this.googleId);
		if(!StringUtils.isEmpty(this.cookie))json.put("cookie",this.cookie);
		if(!StringUtils.isEmpty(this.intendedSubjectofStudy))json.put("enquirySubjectId",this.intendedSubjectofStudy);
		if(!StringUtils.isEmpty(this.enquiryComment))json.put("enquiryComment",this.enquiryComment);
		if(!StringUtils.isEmpty(this.enquiryCourseId))json.put("enquiryCourseId",this.enquiryCourseId);
		if(!StringUtils.isEmpty(this.enquiryScholarshipId))json.put("enquiryScholarshipId",this.enquiryScholarshipId);
		if(!StringUtils.isEmpty(this.enquirySubjectId))json.put("enquirySubjectId",this.enquirySubjectId);

		if(!StringUtils.isEmpty(this.enquiryScholarshipName))json.put("enquiryScholarshipName",this.enquiryScholarshipName);
		if(!StringUtils.isEmpty(this.studentGender))json.put("gendar",this.studentGender);

		if(!StringUtils.isEmpty(this.planningWorkAfterSutdy))json.put("planningWorkAfterSutdy",this.planningWorkAfterSutdy);
		if(!StringUtils.isEmpty(this.utmCampaign))json.put("utmCampaign",this.utmCampaign);
		if(!StringUtils.isEmpty(this.utmContent))json.put("utmContent",this.utmContent);
		if(!StringUtils.isEmpty(this.utmMedium))json.put("utmMedium",this.utmMedium);
		if(!StringUtils.isEmpty(this.utmSource))json.put("utmSource",this.utmSource);
		if(!StringUtils.isEmpty(this.planningWorkAfterStudy))json.put("planningWorkAfterStudy",this.planningWorkAfterStudy);

		return  json;
	}
	public static void main(String[] args) {
		Member member = new Member();
		member.setWxFollowedStatus(true);
		member.setMemberFirstName("chot天涯重庆");
		member.setId(1L);
		System.out.println(member.toJson(true));

	}

	interface  FormType{
		String institutionEnquryForm = "Institution Enquiry Form";
		String agentEnquityForm = "Agent Enquiry Form";
		String courseEnquiryForm =	"Course Enquiry Form";
		String scholarshipEnquiryForm =	"Scholarship Enquiry Form";
		String institutionEnquryFormChina =	"Institution Enquiry Form China";
		String agentEnquityFormChina =	"Agent Enquiry Form China";
		String courseEnquiryFormChina =	"Course Enquiry Form China";
		String scholarshipEnquiryFormChina =	"Scholarship Enquiry Form China";
		String eventUpdatesForm =	"Event Updates Form";
		String blogUpdatesForm =	"Blog Updates Form";
		String leadCapturePopUpForm =	"Lead Capture Pop Up Form";
		String inzLeadForm =	"INZ Lead Form";
		String inzStudentVisa=	"INZ Student Visa";
		String DSXStudent =	"DSX Student";
		String CFLead =	"CF Lead";
		String sinzCnLead =	"sinz.cn Lead";
	}

	@Override
	public String toString() {
		return "Member{" +
				"memberId=" + memberId +
				", id=" + id +
				", memberFirstName='" + memberFirstName + '\'' +
				", memberLastName='" + memberLastName + '\'' +
				", memberEmail='" + memberEmail + '\'' +
				", memberMobile='" + memberMobile + '\'' +
				", memberPersonType='" + memberPersonType + '\'' +
				", studentHighestLevelofStudy='" + studentHighestLevelofStudy + '\'' +
				", studentGender='" + studentGender + '\'' +
				", studentDOB='" + studentDOB + '\'' +
				", intendedInstitutionType='" + intendedInstitutionType + '\'' +
				", intendedLevelofStudy='" + intendedLevelofStudy + '\'' +
				", intendedAreaofStudy='" + intendedAreaofStudy + '\'' +
				", intendedSubjectofStudy='" + intendedSubjectofStudy + '\'' +
				", intendedStartDate='" + intendedStartDate + '\'' +
				", annualTuitionBudget='" + annualTuitionBudget + '\'' +
				", preferredLocalityType='" + preferredLocalityType + '\'' +
				", tester=" + tester +
				", permissionHowTo=" + permissionHowTo +
				", permissionNews=" + permissionNews +
				", permissionLocal=" + permissionLocal +
				", permissionInspire=" + permissionInspire +
				", permissionAlert=" + permissionAlert +
				", visaProgress='" + visaProgress + '\'' +
				", privacyPolicyAgreed=" + privacyPolicyAgreed +
				", inzMatchingPermission=" + inzMatchingPermission +
				", language='" + language + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", weChatOpenId='" + weChatOpenId + '\'' +
				", wxNickname='" + wxNickname + '\'' +
				", avatarUrl='" + avatarUrl + '\'' +
				", country='" + country + '\'' +
				", wxFollowedStatus=" + wxFollowedStatus +
				", wxFollowedDate=" + wxFollowedDate +
				", weChatGroup='" + weChatGroup + '\'' +
				", weChatUnionId='" + weChatUnionId + '\'' +
				", gzhOpenId='" + gzhOpenId + '\'' +
				", source='" + source + '\'' +
				", studentFirstName='" + studentFirstName + '\'' +
				", studentLastName='" + studentLastName + '\'' +
				", matched=" + matched +
				", createdDate='" + createdDate + '\'' +
				", updatedDate='" + updatedDate + '\'' +
				", status=" + status +
				", intendedFundingSource='" + intendedFundingSource + '\'' +
				", agentUse='" + agentUse + '\'' +
				", englishLevel='" + englishLevel + '\'' +
				", optIn=" + optIn +
				", googleId='" + googleId + '\'' +
				", cookie='" + cookie + '\'' +
				", enquiryComment='" + enquiryComment + '\'' +
				", enquiryCourseId=" + enquiryCourseId +
				", enquiryScholarshipId=" + enquiryScholarshipId +
				", enquiryScholarshipName='" + enquiryScholarshipName + '\'' +
				", planningWorkAfterSutdy=" + planningWorkAfterSutdy +
				", utmCampaign='" + utmCampaign + '\'' +
				", utmContent='" + utmContent + '\'' +
				", utmMedium='" + utmMedium + '\'' +
				", utmSource='" + utmSource + '\'' +
				'}';
	}
}