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
import java.util.List;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Getter
@Setter
public class Course extends Model<Course> {

    private static final long serialVersionUID = 1L;

    @TableField("course_id")
    @JsonProperty("course_id")
    private Integer courseId;
    
    @JsonProperty("silver_stripe_id")
    @TableField("silver_stripe_id")
    private Integer silverStripeId;
    
    @JsonProperty("institution_id")
    @TableField("institution_id")
    private Integer institutionId;
    
    @JsonProperty("department_id")
    @TableField("department_id")
    private Integer departmentId;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("course_title")
    @TableField("course_title")
    private String courseTitle;
    
    @JsonProperty("course_description")
    @TableField("course_description")
    private String courseDescription;
    
    @JsonProperty("area_of_study")
    @TableField("area_of_study")
    private String areaOfStudy;
    
    @JsonProperty("area_of_study_code")
    @TableField("area_of_study_code")
    private String areaOfStudyCode;
    
    @JsonProperty("subject_of_study")
    @TableField("subject_of_study")
    private String subjectOfStudy;
    
    @JsonProperty("subject_of_study_code")
    @TableField("subject_of_study_code")
    private String subjectOfStudyCode;
    
    @JsonProperty("level3_subjects")
    @TableField("level3_subjects")
    private String level3Subjects;
    
    @JsonProperty("level3_subjects_code")
    @TableField("level3_subjects_code")
    private String level3SubjectsCode;
    
    @JsonProperty("level_of_study")
    @TableField("level_of_study")
    private String levelOfStudy;
    
    @JsonDeserialize(using=cn.funion.common.utils.CustomerDateAndTimeDeserialize.class)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss a")
    @JsonProperty("last_updated_date")
    @TableField("last_updated_date")
    private Date lastUpdatedDate;
    /**
     * 主键
     */
    @TableId("_id")
    private Long id;
    @TableField("_sync_date")
    private Date syncDate;
    /**
     * 翻译时间
     */
    @TableField("_translation_date")
    private Date translationDate;
    /**
     * 针对要翻译的字段进行MD5加密对比是否需要翻译
     */
    @TableField("_translation_md5")
    private String translationMd5;
    @TableField("_created_date")
    private Date createdDate;
    /**
     * 翻译人员
     */
    @TableField("_translation_by")
    private String translationBy;

    @TableField(exist=false)
    private Institution institution;

    @TableField("_del_status")
    @TableLogic
    private int delStatus;

    @TableField(exist=false)
    private String courseDuration;
    @TableField(exist=false)
    private String startDate;
    @TableField(exist=false)
    private List<Opportunity> campusList;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Course{" +
        ", courseId=" + courseId +
        ", silverStripeId=" + silverStripeId +
        ", institutionId=" + institutionId +
        ", departmentId=" + departmentId +
        ", status=" + status +
        ", courseTitle=" + courseTitle +
        ", courseDescription=" + courseDescription +
        ", areaOfStudy=" + areaOfStudy +
        ", areaOfStudyCode=" + areaOfStudyCode +
        ", subjectOfStudy=" + subjectOfStudy +
        ", subjectOfStudyCode=" + subjectOfStudyCode +
        ", level3Subjects=" + level3Subjects +
        ", level3SubjectsCode=" + level3SubjectsCode +
        ", levelOfStudy=" + levelOfStudy +
        ", lastUpdatedDate=" + lastUpdatedDate +
        ", id=" + id +
        ", syncDate=" + syncDate +
        ", translationDate=" + translationDate +
        ", translationMd5=" + translationMd5 +
        ", createdDate=" + createdDate +
        ", translationBy=" + translationBy +
        "}";
    }
}
