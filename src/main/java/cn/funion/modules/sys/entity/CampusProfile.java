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
@TableName("campus_profile")
public class CampusProfile extends Model<CampusProfile> {

    private static final long serialVersionUID = 1L;

    @TableField("campus_id")
    private Integer campusId;
    @TableField("profile_summary")
    private String profileSummary;
    @TableField("profile_updated_date")
    private Date profileUpdatedDate;
    @TableField("profile_updated_by")
    private String profileUpdatedBy;
    @TableLogic
    @TableField("_status")
    private int status;
    @TableField("_created_date")
    private Date createdDate;



    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "CampusProfile{" +
        ", campusId=" + campusId +
        ", profileSummary=" + profileSummary +
        ", profileUpdatedDate=" + profileUpdatedDate +
        ", profileUpdatedBy=" + profileUpdatedBy +
        "}";
    }
}
