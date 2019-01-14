package cn.funion.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author aixc
 * @email dev@4union.cn
 * @date 2018-11-28 21:07:11
 */
@TableName("member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Long id;
	/**
	 * 
	 */
	private String memberFirstName;
	/**
	 * 
	 */
	private String memberLastName;
	/**
	 * 
	 */
	private String memberEmail;
	/**
	 * 
	 */
	private String memberMobile;
	/**
	 * 数据字典PersonTypes": [ "Student",  "Parent/Guardian",  "Agent/Advisor
	 */
	private String memberPersonType;
	/**
	 * 字典Countries
	 */
	private String studentNationality;
	/**
	 * 字典Countries
	 */
	private String studentCountryOfResidence;
	/**
	 * 字典AttainedLevelofStudy
	 */
	private String studentHighestLevelOfStudy;
	/**
	 * 字典Gender": [ "Male", "Female","Other", "Prefer not to say"
	 */
	private String studentGender;
	/**
	 * 
	 */
	private String studentFirstName;
	/**
	 * 
	 */
	private String studentLastName;
	/**
	 * YYYY-MM-DD
	 */
	private String studentdob;
	/**
	 * 字典InstitutionType
	 */
	private String intendedInstitutionType;
	/**
	 * 字典IntendedLevelofStudy
	 */
	private String intendedLevelOfStudy;
	/**
	 * 字典AreaofStudy
	 */
	private String intendedAreaOfStudy;
	/**
	 * 字典SubjectofStudy
	 */
	private String intendedSubjectOfStudy;
	/**
	 * YYYY-MM-DD
	 */
	private String intendedStartDate;
	/**
	 * 字典Budget
	 */
	private String annualTuitionBudget;
	/**
	 * 字典LocalityType
	 */
	private String preferredLocalityType;
	/**
	 * 
	 */
	private String tester;
	/**
	 * 默认true 
	 */
	private String permissionHowTo;
	/**
	 * 默认true 
	 */
	private String permissionNews;
	/**
	 * 默认true 
	 */
	private String permissionLocal;
	/**
	 * 
	 */
	private String permissionInspire;
	/**
	 * 
	 */
	private String permissionAlert;
	/**
	 * 字典VisaProgress
	 */
	private String visaProgress;
	/**
	 * 
	 */
	private Integer privacyPolicyAgreed;
	/**
	 * 
	 */
	private Integer inzMatchingPermission;
	/**
	 * 
	 */
	private String language;
	/**
	 * 
	 */
	private String province;
	/**
	 * 
	 */
	private String city;
	/**
	 * 
	 */
	private String weChatOpenId;
	/**
	 * 
	 */
	private String wxNickname;
	/**
	 * 0   未关注 1关注
	 */
	private Integer wxFollowedStatus;
	/**
	 * 
	 */
	private Date wxFollowedDate;
	/**
	 * 
	 */
	private String weChatGroup;
	/**
	 * 
	 */
	private String created;
	/**
	 * 
	 */
	private String updated;
	/**
	 * 00:institution enquiry form
            02:agent enquiry form
            03:event updates form
            04:blog updates form
            05:lead capture pop up form
	 */
	private String source;
	/**
	 * 是否已进行过匹配
	 */
	private Integer matched;
	/**
	 * 
	 */
	@TableId
	private Long memberId;
	/**
	 * 
	 */
	private String englishLevel;
	/**
	 * 
	 */
	@TableField("_updated_date")
	private Date updatedDate;
	/**
	 * 更新人
	 */
	@TableField("_updated_by")
	private String updatedBy;
	/**
	 * 
	 */
	@TableField("_created_date")
	private Date createdDate;
	/**
	 * 删除标识 1删除
	 */
	@TableField("_status")
	private Integer status;
	/**
	 * 翻译人员
	 */
	@TableField("_created_by")
	private String createdBy;
	/**
	 * 
	 */
	private String weChatUnionId;
	/**
	 * 公众号openID
	 */
	private String gzhOpenId;
	/**
	 * 小程序进入场景
	 */
	private String wechatAppletScene;
	/**
	 * 留学资金来源
	 */
	private String intendedFundingSource;
	/**
	 * 
	 */
	private String agentUse;
	/**
	 * 
	 */
	private String avatarUrl;
	/**
	 * 
	 */
	private String country;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setMemberFirstName(String memberFirstName) {
		this.memberFirstName = memberFirstName;
	}
	/**
	 * 获取：
	 */
	public String getMemberFirstName() {
		return memberFirstName;
	}
	/**
	 * 设置：
	 */
	public void setMemberLastName(String memberLastName) {
		this.memberLastName = memberLastName;
	}
	/**
	 * 获取：
	 */
	public String getMemberLastName() {
		return memberLastName;
	}
	/**
	 * 设置：
	 */
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	/**
	 * 获取：
	 */
	public String getMemberEmail() {
		return memberEmail;
	}
	/**
	 * 设置：
	 */
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	/**
	 * 获取：
	 */
	public String getMemberMobile() {
		return memberMobile;
	}
	/**
	 * 设置：数据字典PersonTypes": [ "Student",  "Parent/Guardian",  "Agent/Advisor
	 */
	public void setMemberPersonType(String memberPersonType) {
		this.memberPersonType = memberPersonType;
	}
	/**
	 * 获取：数据字典PersonTypes": [ "Student",  "Parent/Guardian",  "Agent/Advisor
	 */
	public String getMemberPersonType() {
		return memberPersonType;
	}
	/**
	 * 设置：字典Countries
	 */
	public void setStudentNationality(String studentNationality) {
		this.studentNationality = studentNationality;
	}
	/**
	 * 获取：字典Countries
	 */
	public String getStudentNationality() {
		return studentNationality;
	}
	/**
	 * 设置：字典Countries
	 */
	public void setStudentCountryOfResidence(String studentCountryOfResidence) {
		this.studentCountryOfResidence = studentCountryOfResidence;
	}
	/**
	 * 获取：字典Countries
	 */
	public String getStudentCountryOfResidence() {
		return studentCountryOfResidence;
	}
	/**
	 * 设置：字典AttainedLevelofStudy
	 */
	public void setStudentHighestLevelOfStudy(String studentHighestLevelOfStudy) {
		this.studentHighestLevelOfStudy = studentHighestLevelOfStudy;
	}
	/**
	 * 获取：字典AttainedLevelofStudy
	 */
	public String getStudentHighestLevelOfStudy() {
		return studentHighestLevelOfStudy;
	}
	/**
	 * 设置：字典Gender": [ "Male", "Female","Other", "Prefer not to say"
	 */
	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}
	/**
	 * 获取：字典Gender": [ "Male", "Female","Other", "Prefer not to say"
	 */
	public String getStudentGender() {
		return studentGender;
	}
	/**
	 * 设置：
	 */
	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}
	/**
	 * 获取：
	 */
	public String getStudentFirstName() {
		return studentFirstName;
	}
	/**
	 * 设置：
	 */
	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}
	/**
	 * 获取：
	 */
	public String getStudentLastName() {
		return studentLastName;
	}
	/**
	 * 设置：YYYY-MM-DD
	 */
	public void setStudentdob(String studentdob) {
		this.studentdob = studentdob;
	}
	/**
	 * 获取：YYYY-MM-DD
	 */
	public String getStudentdob() {
		return studentdob;
	}
	/**
	 * 设置：字典InstitutionType
	 */
	public void setIntendedInstitutionType(String intendedInstitutionType) {
		this.intendedInstitutionType = intendedInstitutionType;
	}
	/**
	 * 获取：字典InstitutionType
	 */
	public String getIntendedInstitutionType() {
		return intendedInstitutionType;
	}
	/**
	 * 设置：字典IntendedLevelofStudy
	 */
	public void setIntendedLevelOfStudy(String intendedLevelOfStudy) {
		this.intendedLevelOfStudy = intendedLevelOfStudy;
	}
	/**
	 * 获取：字典IntendedLevelofStudy
	 */
	public String getIntendedLevelOfStudy() {
		return intendedLevelOfStudy;
	}
	/**
	 * 设置：字典AreaofStudy
	 */
	public void setIntendedAreaOfStudy(String intendedAreaOfStudy) {
		this.intendedAreaOfStudy = intendedAreaOfStudy;
	}
	/**
	 * 获取：字典AreaofStudy
	 */
	public String getIntendedAreaOfStudy() {
		return intendedAreaOfStudy;
	}
	/**
	 * 设置：字典SubjectofStudy
	 */
	public void setIntendedSubjectOfStudy(String intendedSubjectOfStudy) {
		this.intendedSubjectOfStudy = intendedSubjectOfStudy;
	}
	/**
	 * 获取：字典SubjectofStudy
	 */
	public String getIntendedSubjectOfStudy() {
		return intendedSubjectOfStudy;
	}
	/**
	 * 设置：YYYY-MM-DD
	 */
	public void setIntendedStartDate(String intendedStartDate) {
		this.intendedStartDate = intendedStartDate;
	}
	/**
	 * 获取：YYYY-MM-DD
	 */
	public String getIntendedStartDate() {
		return intendedStartDate;
	}
	/**
	 * 设置：字典Budget
	 */
	public void setAnnualTuitionBudget(String annualTuitionBudget) {
		this.annualTuitionBudget = annualTuitionBudget;
	}
	/**
	 * 获取：字典Budget
	 */
	public String getAnnualTuitionBudget() {
		return annualTuitionBudget;
	}
	/**
	 * 设置：字典LocalityType
	 */
	public void setPreferredLocalityType(String preferredLocalityType) {
		this.preferredLocalityType = preferredLocalityType;
	}
	/**
	 * 获取：字典LocalityType
	 */
	public String getPreferredLocalityType() {
		return preferredLocalityType;
	}
	/**
	 * 设置：
	 */
	public void setTester(String tester) {
		this.tester = tester;
	}
	/**
	 * 获取：
	 */
	public String getTester() {
		return tester;
	}
	/**
	 * 设置：默认true 
	 */
	public void setPermissionHowTo(String permissionHowTo) {
		this.permissionHowTo = permissionHowTo;
	}
	/**
	 * 获取：默认true 
	 */
	public String getPermissionHowTo() {
		return permissionHowTo;
	}
	/**
	 * 设置：默认true 
	 */
	public void setPermissionNews(String permissionNews) {
		this.permissionNews = permissionNews;
	}
	/**
	 * 获取：默认true 
	 */
	public String getPermissionNews() {
		return permissionNews;
	}
	/**
	 * 设置：默认true 
	 */
	public void setPermissionLocal(String permissionLocal) {
		this.permissionLocal = permissionLocal;
	}
	/**
	 * 获取：默认true 
	 */
	public String getPermissionLocal() {
		return permissionLocal;
	}
	/**
	 * 设置：
	 */
	public void setPermissionInspire(String permissionInspire) {
		this.permissionInspire = permissionInspire;
	}
	/**
	 * 获取：
	 */
	public String getPermissionInspire() {
		return permissionInspire;
	}
	/**
	 * 设置：
	 */
	public void setPermissionAlert(String permissionAlert) {
		this.permissionAlert = permissionAlert;
	}
	/**
	 * 获取：
	 */
	public String getPermissionAlert() {
		return permissionAlert;
	}
	/**
	 * 设置：字典VisaProgress
	 */
	public void setVisaProgress(String visaProgress) {
		this.visaProgress = visaProgress;
	}
	/**
	 * 获取：字典VisaProgress
	 */
	public String getVisaProgress() {
		return visaProgress;
	}
	/**
	 * 设置：
	 */
	public void setPrivacyPolicyAgreed(Integer privacyPolicyAgreed) {
		this.privacyPolicyAgreed = privacyPolicyAgreed;
	}
	/**
	 * 获取：
	 */
	public Integer getPrivacyPolicyAgreed() {
		return privacyPolicyAgreed;
	}
	/**
	 * 设置：
	 */
	public void setInzMatchingPermission(Integer inzMatchingPermission) {
		this.inzMatchingPermission = inzMatchingPermission;
	}
	/**
	 * 获取：
	 */
	public Integer getInzMatchingPermission() {
		return inzMatchingPermission;
	}
	/**
	 * 设置：
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * 获取：
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * 设置：
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：
	 */
	public void setWeChatOpenId(String weChatOpenId) {
		this.weChatOpenId = weChatOpenId;
	}
	/**
	 * 获取：
	 */
	public String getWeChatOpenId() {
		return weChatOpenId;
	}
	/**
	 * 设置：
	 */
	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}
	/**
	 * 获取：
	 */
	public String getWxNickname() {
		return wxNickname;
	}
	/**
	 * 设置：0   未关注 1关注
	 */
	public void setWxFollowedStatus(Integer wxFollowedStatus) {
		this.wxFollowedStatus = wxFollowedStatus;
	}
	/**
	 * 获取：0   未关注 1关注
	 */
	public Integer getWxFollowedStatus() {
		return wxFollowedStatus;
	}
	/**
	 * 设置：
	 */
	public void setWxFollowedDate(Date wxFollowedDate) {
		this.wxFollowedDate = wxFollowedDate;
	}
	/**
	 * 获取：
	 */
	public Date getWxFollowedDate() {
		return wxFollowedDate;
	}
	/**
	 * 设置：
	 */
	public void setWeChatGroup(String weChatGroup) {
		this.weChatGroup = weChatGroup;
	}
	/**
	 * 获取：
	 */
	public String getWeChatGroup() {
		return weChatGroup;
	}
	/**
	 * 设置：
	 */
	public void setCreated(String created) {
		this.created = created;
	}
	/**
	 * 获取：
	 */
	public String getCreated() {
		return created;
	}
	/**
	 * 设置：
	 */
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	/**
	 * 获取：
	 */
	public String getUpdated() {
		return updated;
	}
	/**
	 * 设置：00:institution enquiry form
            02:agent enquiry form
            03:event updates form
            04:blog updates form
            05:lead capture pop up form
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 获取：00:institution enquiry form
            02:agent enquiry form
            03:event updates form
            04:blog updates form
            05:lead capture pop up form
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 设置：是否已进行过匹配
	 */
	public void setMatched(Integer matched) {
		this.matched = matched;
	}
	/**
	 * 获取：是否已进行过匹配
	 */
	public Integer getMatched() {
		return matched;
	}
	/**
	 * 设置：
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：
	 */
	public Long getMemberId() {
		return memberId;
	}
	/**
	 * 设置：
	 */
	public void setEnglishLevel(String englishLevel) {
		this.englishLevel = englishLevel;
	}
	/**
	 * 获取：
	 */
	public String getEnglishLevel() {
		return englishLevel;
	}
	/**
	 * 设置：
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	/**
	 * 获取：
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * 设置：更新人
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * 获取：更新人
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * 设置：
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * 获取：
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * 设置：删除标识 1删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：删除标识 1删除
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：翻译人员
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * 获取：翻译人员
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * 设置：
	 */
	public void setWeChatUnionId(String weChatUnionId) {
		this.weChatUnionId = weChatUnionId;
	}
	/**
	 * 获取：
	 */
	public String getWeChatUnionId() {
		return weChatUnionId;
	}
	/**
	 * 设置：公众号openID
	 */
	public void setGzhOpenId(String gzhOpenId) {
		this.gzhOpenId = gzhOpenId;
	}
	/**
	 * 获取：公众号openID
	 */
	public String getGzhOpenId() {
		return gzhOpenId;
	}
	/**
	 * 设置：小程序进入场景
	 */
	public void setWechatAppletScene(String wechatAppletScene) {
		this.wechatAppletScene = wechatAppletScene;
	}
	/**
	 * 获取：小程序进入场景
	 */
	public String getWechatAppletScene() {
		return wechatAppletScene;
	}
	/**
	 * 设置：留学资金来源
	 */
	public void setIntendedFundingSource(String intendedFundingSource) {
		this.intendedFundingSource = intendedFundingSource;
	}
	/**
	 * 获取：留学资金来源
	 */
	public String getIntendedFundingSource() {
		return intendedFundingSource;
	}
	/**
	 * 设置：
	 */
	public void setAgentUse(String agentUse) {
		this.agentUse = agentUse;
	}
	/**
	 * 获取：
	 */
	public String getAgentUse() {
		return agentUse;
	}
	/**
	 * 设置：
	 */
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	/**
	 * 获取：
	 */
	public String getAvatarUrl() {
		return avatarUrl;
	}
	/**
	 * 设置：
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * 获取：
	 */
	public String getCountry() {
		return country;
	}
}
