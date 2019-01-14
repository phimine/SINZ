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
@TableName("department_pofile")
public class DepartmentPofile extends Model<DepartmentPofile> {

    private static final long serialVersionUID = 1L;

    @TableField("department_id")
    private Integer departmentId;
    @TableField("profile_summary")
    private String profileSummary;
    @TableField("profile_updated_date")
    private Date profileUpdatedDate;
    @TableField("profile_updated_by")
    private String profileUpdatedBy;
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
        return "DepartmentPofile{" +
        ", departmentId=" + departmentId +
        ", profileSummary=" + profileSummary +
        ", profileUpdatedDate=" + profileUpdatedDate +
        ", profileUpdatedBy=" + profileUpdatedBy +
        "}";
    }
}
