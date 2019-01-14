package cn.funion.modules.sys.entity;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter

public class Scholarship extends Model<Scholarship> {
    private static final long serialVersionUID = 1L;

    @JsonProperty("scholarship_id ")
    @TableField("scholarship_id")
    private Integer scholarshipId ;
    @JsonProperty("provider_id")
    @TableField("provider_id")
    private Integer providerId  ;
    @JsonProperty("scholarship_title")
    @TableField("scholarship_title")
    private String scholarshipTitle  ;
    @JsonProperty("study_level")
    @TableField("study_level")
    private String studyLevel  ;
    @JsonProperty("area_of_study")
    @TableField("area_of_study")
    private String areaOfStudy  ;
    @JsonProperty("area_of_study_code")
    @TableField("area_of_study_code")
    private String areaOfStudyCode ;
    @JsonProperty("subject_of_study")
    @TableField("subject_of_study")
    private String subjectOfStudy  ;
    @JsonProperty("subject_of_study_code")
    @TableField("subject_of_study_code")
    private String subjectOfStudyCode ;
    @JsonProperty("level3_subjects")
    @TableField("level3_subjects")
    private String level3Subjects  ;
    @JsonProperty("level3_subjects_code")
    @TableField("level3_subjects_code")
    private String level3SubjectsCode ;
    @JsonProperty("scholarship_value")
    @TableField("scholarship_value")
    private String scholarshipValue  ;
    @JsonProperty("scholarship_value_duration")
    @TableField("scholarship_value_duration")
    private String scholarshipValueDuration ;
    @JsonProperty("scholarship_value_description")
    @TableField("scholarship_value_description")
    private String scholarshipValueDescription ;
    @JsonProperty("number_of_awards")
    @TableField("number_of_awards")
    private String numberOfAwards  ;
    @JsonProperty("nationality")
    @TableField("nationality")
    private String nationality  ;
    @JsonProperty("application_deadline_date")
    @TableField("application_deadline_date")
    private String applicationDeadlineDate  ;
    @JsonProperty("how_to_apply")
    @TableField("how_to_apply")
    private String howToApply  ;
    @JsonProperty("eligibility_criteria")
    @TableField("eligibility_criteria")
    private String eligibilityCriteria ;
    @JsonProperty("link_to_providers_website")
    @TableField("link_to_providers_website")
    private String linkToProvidersWebsite ;

    @JsonDeserialize(using=cn.funion.common.utils.CustomerDateAndTimeDeserialize.class)
    @JsonProperty("last_updated_on")
    @TableField("last_updated_date")
    private Date lastUpdatedDate ;
    @JsonProperty("status")
    @TableField("status")
    private String status ;
    @TableField("_sync_date")
    private Date syncDate;
    @TableField("_created_date")
    private Date createdDate;
    @TableLogic
    @TableField("_status")
    private int deStatus ;
    @TableId("_id")
    private Integer Id  ;
    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "Scholarship{" +
                "Id=" + Id +
                ", scholarshipId=" + scholarshipId +
                ", institutionId=" + providerId +
                ", scholarshipTitle='" + scholarshipTitle + '\'' +
                ", studyLevel='" + studyLevel + '\'' +
                ", areaOfStudy='" + areaOfStudy + '\'' +
                ", areaOfStudyCode='" + areaOfStudyCode + '\'' +
                ", subjectOfStudy='" + subjectOfStudy + '\'' +
                ", subjectOfStudyCode='" + subjectOfStudyCode + '\'' +
                ", level3Subjects='" + level3Subjects + '\'' +
                ", level3SubjectsCode='" + level3SubjectsCode + '\'' +
                ", scholarshipValue='" + scholarshipValue + '\'' +
                ", scholarshipValueDuration='" + scholarshipValueDuration + '\'' +
                ", scholarshipValueDescription='" + scholarshipValueDescription + '\'' +
                ", numberOfAwards='" + numberOfAwards + '\'' +
                ", nationality='" + nationality + '\'' +
                ", applicationDeadlineDate='" + applicationDeadlineDate + '\'' +
                ", howToApply='" + howToApply + '\'' +
                ", eligibilityCriteria='" + eligibilityCriteria + '\'' +
                ", linkToProvidersWebsite='" + linkToProvidersWebsite + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", status='" + status + '\'' +
                '}';
    }
}
