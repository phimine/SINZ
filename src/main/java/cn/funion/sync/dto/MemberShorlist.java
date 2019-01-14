package cn.funion.sync.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
public class MemberShorlist  {

    @JsonProperty("shortlist_id")
    private int shortlistId;
    @JsonProperty("ministry_id")
    private String ministryId;
    @JsonProperty("institution_id")
    private int institutionId;
    @JsonProperty("campus_id")
    private int campusId;
    @JsonProperty("course_id")
    private String courseId;
    @JsonProperty("opportunity_id")
    private String opportunityId;
    @JsonProperty("InstitutionName")
    private String institutionName;
    @JsonProperty("InstitutionType")
    private String institutionType;
    @JsonProperty("CampusName")
    private String campusName;
    @JsonProperty("Town")
    private String town;
    @JsonProperty("Region")
    private String region;
    @JsonProperty("LocalityType")
    private String localityType;
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
    @JsonProperty("CostGroup")
    private String costGroup;
    @JsonProperty("StartDate")
    private String startDate;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("EnrolmentProgress")
    private String enrolmentProgress;
    @JsonProperty("Study_match_score")
    private int studyMatchScore;
    @JsonProperty("VisaPredictionDone")
    private boolean visaPredictionDone;
    @JsonProperty("LastVisaPrediction")
    private String lastVisaPrediction;
    @JsonProperty("BudgetCheckerDone")
    private boolean budgetCheckerDone;
    @JsonProperty("DefaultBudgetPeriod")
    private String defaultBudgetPeriod;
    @JsonProperty("DefaultBudgetAccomType")
    private String defaultBudgetAccomType;
    @JsonProperty("LastEnquiry")
    private String lastEnquiry;
    @JsonProperty("LastAutoEnquiry")
    private String lastAutoEnquiry;
    @JsonProperty("InstitutionResponded")
    private boolean institutionResponded;
    @JsonProperty("UserFollowedUp")
    private boolean userFollowedUp;
    @JsonProperty("ContactMade")
    private boolean contactMade;
    @JsonProperty("NotesSaved")
    private int notesSaved;
    @JsonProperty("EnrolmentStarted")
    private boolean enrolmentStarted;
    @JsonProperty("created")
    private String created;
    @JsonProperty("updated")
    private String updated;
    @JsonIgnore
    public int getShortlistId() {
        return shortlistId;
    }
    @JsonIgnore
    public void setShortlistId(int shortlistId) {
        this.shortlistId = shortlistId;
    }
    @JsonIgnore
    public String getMinistryId() {
        return ministryId;
    }
    @JsonIgnore
    public void setMinistryId(String ministryId) {
        this.ministryId = ministryId;
    }
    @JsonIgnore
    public int getInstitutionId() {
        return institutionId;
    }
    @JsonIgnore
    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }
    @JsonIgnore
    public int getCampusId() {
        return campusId;
    }
    @JsonIgnore
    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }
    @JsonIgnore
    public String getCourseId() {
        return courseId;
    }
    @JsonIgnore
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    @JsonIgnore
    public String getOpportunityId() {
        return opportunityId;
    }
    @JsonIgnore
    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }
    @JsonIgnore
    public String getInstitutionName() {
        return institutionName;
    }
    @JsonIgnore
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }
    @JsonIgnore
    public String getInstitutionType() {
        return institutionType;
    }
    @JsonIgnore
    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }
    @JsonIgnore
    public String getCampusName() {
        return campusName;
    }
    @JsonIgnore
    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }
    @JsonIgnore
    public String getTown() {
        return town;
    }
    @JsonIgnore
    public void setTown(String town) {
        this.town = town;
    }
    @JsonIgnore
    public String getRegion() {
        return region;
    }
    @JsonIgnore
    public void setRegion(String region) {
        this.region = region;
    }
    @JsonIgnore
    public String getLocalityType() {
        return localityType;
    }
    @JsonIgnore
    public void setLocalityType(String localityType) {
        this.localityType = localityType;
    }
    @JsonIgnore
    public double getLatitude() {
        return latitude;
    }
    @JsonIgnore
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    @JsonIgnore
    public double getLongitude() {
        return longitude;
    }
    @JsonIgnore
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    @JsonIgnore
    public String getCourseTitle() {
        return courseTitle;
    }
    @JsonIgnore
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    @JsonIgnore
    public String getLevelofStudy() {
        return levelofStudy;
    }
    @JsonIgnore
    public void setLevelofStudy(String levelofStudy) {
        this.levelofStudy = levelofStudy;
    }
    @JsonIgnore
    public String getStudyMode() {
        return studyMode;
    }
    @JsonIgnore
    public void setStudyMode(String studyMode) {
        this.studyMode = studyMode;
    }
    @JsonIgnore
    public String getCourseDuration() {
        return courseDuration;
    }
    @JsonIgnore
    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }
    @JsonIgnore
    public String getCost() {
        return cost;
    }
    @JsonIgnore
    public void setCost(String cost) {
        this.cost = cost;
    }
    @JsonIgnore
    public String getCostGroup() {
        return costGroup;
    }

    public void setCostGroup(String costGroup) {
        this.costGroup = costGroup;
    }
    @JsonIgnore
    public String getStartDate() {
        return startDate;
    }
    @JsonIgnore
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    @JsonIgnore
    public String getStatus() {
        return status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        this.status = status;
    }
    @JsonIgnore
    public String getEnrolmentProgress() {
        return enrolmentProgress;
    }
    @JsonIgnore
    public void setEnrolmentProgress(String enrolmentProgress) {
        this.enrolmentProgress = enrolmentProgress;
    }
    @JsonIgnore
    public int getStudyMatchScore() {
        return studyMatchScore;
    }
    @JsonIgnore
    public void setStudyMatchScore(int studyMatchScore) {
        this.studyMatchScore = studyMatchScore;
    }
    @JsonIgnore
    public boolean isVisaPredictionDone() {
        return visaPredictionDone;
    }
    @JsonIgnore
    public void setVisaPredictionDone(boolean visaPredictionDone) {
        this.visaPredictionDone = visaPredictionDone;
    }
    @JsonIgnore
    public String getLastVisaPrediction() {
        return lastVisaPrediction;
    }
    @JsonIgnore
    public void setLastVisaPrediction(String lastVisaPrediction) {
        this.lastVisaPrediction = lastVisaPrediction;
    }
    @JsonIgnore
    public boolean isBudgetCheckerDone() {
        return budgetCheckerDone;
    }
    @JsonIgnore
    public void setBudgetCheckerDone(boolean budgetCheckerDone) {
        this.budgetCheckerDone = budgetCheckerDone;
    }
    @JsonIgnore
    public String getDefaultBudgetPeriod() {
        return defaultBudgetPeriod;
    }
    @JsonIgnore
    public void setDefaultBudgetPeriod(String defaultBudgetPeriod) {
        this.defaultBudgetPeriod = defaultBudgetPeriod;
    }
    @JsonIgnore
    public String getDefaultBudgetAccomType() {
        return defaultBudgetAccomType;
    }
    @JsonIgnore
    public void setDefaultBudgetAccomType(String defaultBudgetAccomType) {
        this.defaultBudgetAccomType = defaultBudgetAccomType;
    }
    @JsonIgnore
    public String getLastEnquiry() {
        return lastEnquiry;
    }
    @JsonIgnore
    public void setLastEnquiry(String lastEnquiry) {
        this.lastEnquiry = lastEnquiry;
    }
    @JsonIgnore
    public String getLastAutoEnquiry() {
        return lastAutoEnquiry;
    }
    @JsonIgnore
    public void setLastAutoEnquiry(String lastAutoEnquiry) {
        this.lastAutoEnquiry = lastAutoEnquiry;
    }
    @JsonIgnore
    public boolean isInstitutionResponded() {
        return institutionResponded;
    }
    @JsonIgnore
    public void setInstitutionResponded(boolean institutionResponded) {
        this.institutionResponded = institutionResponded;
    }
    @JsonIgnore
    public boolean isUserFollowedUp() {
        return userFollowedUp;
    }
    @JsonIgnore
    public void setUserFollowedUp(boolean userFollowedUp) {
        this.userFollowedUp = userFollowedUp;
    }
    @JsonIgnore
    public boolean isContactMade() {
        return contactMade;
    }
    @JsonIgnore
    public void setContactMade(boolean contactMade) {
        this.contactMade = contactMade;
    }
    @JsonIgnore
    public int getNotesSaved() {
        return notesSaved;
    }
    @JsonIgnore
    public void setNotesSaved(int notesSaved) {
        this.notesSaved = notesSaved;
    }
    @JsonIgnore
    public boolean isEnrolmentStarted() {
        return enrolmentStarted;
    }
    @JsonIgnore
    public void setEnrolmentStarted(boolean enrolmentStarted) {
        this.enrolmentStarted = enrolmentStarted;
    }
    @JsonIgnore
    public String getCreated() {
        return created;
    }
    @JsonIgnore
    public void setCreated(String created) {
        this.created = created;
    }
    @JsonIgnore
    public String getUpdated() {
        return updated;
    }
    @JsonIgnore
    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
