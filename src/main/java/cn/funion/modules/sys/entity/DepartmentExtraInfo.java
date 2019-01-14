package cn.funion.modules.sys.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Setter
@Getter
@TableName("department_extra_info")
public class DepartmentExtraInfo extends Model<DepartmentExtraInfo> {

    private static final long serialVersionUID = 1L;

    @TableField("department_id")
    private Integer departmentId;
    private String accommodation;
    @TableField("religious_clubs")
    private String religiousClubs;
    @TableField("on_site_food_options")
    private String onSiteFoodOptions;
    @TableField("on_site_religious_facilities")
    private String onSiteReligiousFacilities;
    private String gender;
    @TableField("number_of_students")
    private String numberOfStudents;
    @TableField("number_of_international_students")
    private String numberOfInternationalStudents;
    private String description;
    @TableField("_status")
    @TableLogic
    private int status;
    @TableField("_created_date")
    private Date createdDate;



    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "DepartmentExtraInfo{" +
        ", departmentId=" + departmentId +
        ", accommodation=" + accommodation +
        ", religiousClubs=" + religiousClubs +
        ", onSiteFoodOptions=" + onSiteFoodOptions +
        ", onSiteReligiousFacilities=" + onSiteReligiousFacilities +
        ", gender=" + gender +
        ", numberOfStudents=" + numberOfStudents +
        ", numberOfInternationalStudents=" + numberOfInternationalStudents +
        ", description=" + description +
        "}";
    }
}
