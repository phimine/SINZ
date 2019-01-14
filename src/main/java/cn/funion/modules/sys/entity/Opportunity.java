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
public class Opportunity extends Model<Opportunity> {
    private static final long serialVersionUID = 1L;
    @JsonProperty("_id")
    @TableId("_id")
    private Integer Id  ;
    @JsonProperty("opportunity_id")
    @TableField("opportunity_id")
    private Integer opportunityId ;
    @JsonProperty("silver_stripe_id")
    @TableField("silver_stripe_id")
    private Integer silverStripeId  ;
    @JsonProperty("status")
    @TableField("status")
    private String status  ;
    @JsonProperty("course_id")
    @TableField("course_id")
    private Integer courseId  ;
    @JsonProperty("campus_id")
    @TableField("campus_id")
    private Integer campusId  ;
    @JsonProperty("study_mode")
    @TableField("study_mode")
    private String studyMode ;
    @JsonProperty("course_duration")
    @TableField("course_duration")
    private String courseDuration  ;
    @JsonProperty("cost")
    @TableField("cost")
    private String cost ;
    @JsonProperty("cost_duration")
    @TableField("cost_duration")
    private String costDuration  ;
    @JsonProperty("cost_description")
    @TableField("cost_description")
    private String costDescription ;

    @JsonProperty("start_date")
    @TableField("start_date")
    private String startDate  ;
    @JsonProperty("start_date_description")
    @TableField("start_date_description")
    private String startDateDescription  ;

    @JsonProperty("enrollment_deadline")
    @TableField("enrollment_deadline")
    private String enrollmentDeadline  ;
    @JsonDeserialize(using=cn.funion.common.utils.CustomerDateAndTimeDeserialize.class)
    @JsonProperty("last_updated_date")
    @TableField("last_updated_date")
    private Date lastUpdatedDate ;
    @TableLogic
    @TableField("_status")
    private int deStatus ;
    @TableField("_sync_date")
    private Date syncDate;
    @TableField("_created_date")
    private Date createdDate;


    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "Opportunity{" +
                "Id=" + Id +
                ", opportunityId=" + opportunityId +
                ", silverStripeId=" + silverStripeId +
                ", status='" + status + '\'' +
                ", courseId=" + courseId +
                ", campusId=" + campusId +
                ", studyMode='" + studyMode + '\'' +
                ", courseDuration='" + courseDuration + '\'' +
                ", cost='" + cost + '\'' +
                ", costDuration='" + costDuration + '\'' +
                ", costDescription='" + costDescription + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startDateDescription='" + startDateDescription + '\'' +
                ", enrollmentDeadline='" + enrollmentDeadline + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", deStatus='" + deStatus + '\'' +
                ", syncDate=" + syncDate +
                ", createdDate=" + createdDate +
                '}';
    }
}
