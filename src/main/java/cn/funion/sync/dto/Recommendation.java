/**
  * Copyright 2018 bejson.com 
  */
package cn.funion.sync.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Auto-generated: 2018-09-25 20:10:51
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Recommendation {
    
	@JsonProperty("ministry_id")
    private String ministryId;
	@JsonProperty("institution_id")
    private long institutionId;
	@JsonProperty("campus_id")
    private long campusId;
	@JsonProperty("course_id")
    private String courseId;
	@JsonProperty("opportunity_id")
    private String opportunityId;
	@JsonProperty("shortlist_id")
    private String shortlistId;
	@JsonProperty("url")
    private String url;
	@JsonProperty("InstitutionName")
    private String iInstitutionName;
	@JsonProperty("InstitutionType")
    private String institutionType;
	@JsonProperty("CampusName")
    private String campusName;
	@JsonProperty("Town")
    private String town;
	@JsonProperty("Region")
    private String region;
	@JsonProperty("Latitude")
    private double latitude;
	@JsonProperty("Longitude")
    private double longitude;
	@JsonProperty("CourseTitle")
    private String courseTitle;
	@JsonProperty("LevelofStudy")
    private String levelofStudy;
	@JsonProperty("StudyMode")
    private String studyMode;
	@JsonProperty("CourseDuration")
    private String courseDuration;
	@JsonProperty("Cost")
    private String cost;
	@JsonProperty("StartDate")
    private String startDate;
	@JsonProperty("Match")
    private int match;
	@JsonProperty("LocalityType")
    private String localityType;
	@JsonProperty("Gender")
    private String gender;
	public String getMinistryId() {
		return ministryId;
	}@JsonIgnore
	public void setMinistryId(String ministryId) {
		this.ministryId = ministryId;
	}@JsonIgnore
	public long getInstitutionId() {
		return institutionId;
	}@JsonIgnore
	public void setInstitutionId(long institutionId) {
		this.institutionId = institutionId;
	}@JsonIgnore
	public long getCampusId() {
		return campusId;
	}@JsonIgnore
	public void setCampusId(long campusId) {
		this.campusId = campusId;
	}@JsonIgnore
	public String getCourseId() {
		return courseId;
	}@JsonIgnore
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}@JsonIgnore
	public String getOpportunityId() {
		return opportunityId;
	}@JsonIgnore
	public void setOpportunityId(String opportunityId) {
		this.opportunityId = opportunityId;
	}@JsonIgnore
	public String getShortlistId() {
		return shortlistId;
	}@JsonIgnore
	public void setShortlistId(String shortlistId) {
		this.shortlistId = shortlistId;
	}@JsonIgnore
	public String getUrl() {
		return url;
	}@JsonIgnore
	public void setUrl(String url) {
		this.url = url;
	}@JsonIgnore
	public String getiInstitutionName() {
		return iInstitutionName;
	}@JsonIgnore
	public void setiInstitutionName(String iInstitutionName) {
		this.iInstitutionName = iInstitutionName;
	}@JsonIgnore
	public String getInstitutionType() {
		return institutionType;
	}@JsonIgnore
	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}@JsonIgnore
	public String getCampusName() {
		return campusName;
	}@JsonIgnore
	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}@JsonIgnore
	public String getTown() {
		return town;
	}@JsonIgnore
	public void setTown(String town) {
		this.town = town;
	}@JsonIgnore
	public String getRegion() {
		return region;
	}@JsonIgnore
	public void setRegion(String region) {
		this.region = region;
	}@JsonIgnore
	public double getLatitude() {
		return latitude;
	}@JsonIgnore
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}@JsonIgnore
	public double getLongitude() {
		return longitude;
	}@JsonIgnore
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}@JsonIgnore
	public String getCourseTitle() {
		return courseTitle;
	}@JsonIgnore
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}@JsonIgnore
	public String getLevelofStudy() {
		return levelofStudy;
	}@JsonIgnore
	public void setLevelofStudy(String levelofStudy) {
		this.levelofStudy = levelofStudy;
	}@JsonIgnore
	public String getStudyMode() {
		return studyMode;
	}@JsonIgnore
	public void setStudyMode(String studyMode) {
		this.studyMode = studyMode;
	}@JsonIgnore
	public String getCourseDuration() {
		return courseDuration;
	}@JsonIgnore
	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}@JsonIgnore
	public String getCost() {
		return cost;
	}@JsonIgnore
	public void setCost(String cost) {
		this.cost = cost;
	}@JsonIgnore
	public String getStartDate() {
		return startDate;
	}@JsonIgnore
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}@JsonIgnore
	public int getMatch() {
		return match;
	}@JsonIgnore
	public void setMatch(int match) {
		this.match = match;
	}@JsonIgnore
	public String getLocalityType() {
		return localityType;
	}@JsonIgnore
	public void setLocalityType(String localityType) {
		this.localityType = localityType;
	}@JsonIgnore
	public String getGender() {
		return gender;
	}@JsonIgnore
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Recommendation [ministryId=" + ministryId + ", institutionId="
				+ institutionId + ", campusId=" + campusId + ", courseId="
				+ courseId + ", opportunityId=" + opportunityId
				+ ", shortlistId=" + shortlistId + ", url=" + url
				+ ", iInstitutionName=" + iInstitutionName
				+ ", institutionType=" + institutionType + ", campusName="
				+ campusName + ", town=" + town + ", region=" + region
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", courseTitle=" + courseTitle + ", levelofStudy="
				+ levelofStudy + ", studyMode=" + studyMode
				+ ", courseDuration=" + courseDuration + ", cost=" + cost
				+ ", startDate=" + startDate + ", match=" + match
				+ ", localityType=" + localityType + ", gender=" + gender + "]";
	}
	
	
    
	
}