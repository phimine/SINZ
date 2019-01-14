package cn.funion.modules.sys.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 部门
学科
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Setter
@Getter
public class Department extends Model<Department> {

    private static final long serialVersionUID = 1L;

    @TableField("department_id")
    private Integer departmentId;
    @TableField("silver_stripe_id")
    private String silverStripeId;
    @TableField("institution_id")
    private Integer institutionId;
    private String status;
    @TableField("department_name")
    private String departmentName;
    @TableField("last_updated_date")
    private Date lastUpdatedDate;
    @TableId("_id")
    private Long id;
    @TableField("_sync_date")
    private Date syncDate;
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

    @TableLogic
    @TableField("_status")
    private int delStatus;
    @TableField(exist=false)
    private DepartmentExtraInfo departmentExtraInfo;
    @TableField(exist=false)
    private DepartmentAddress departmentAddress;
    @TableField(exist=false)
    private DepartmentMedia departmentMedia;
    @TableField(exist=false)
    private DepartmentPofile departmentPofile;




    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Department{" +
        ", departmentId=" + departmentId +
        ", silverStripeId=" + silverStripeId +
        ", institutionId=" + institutionId +
        ", status=" + status +
        ", departmentName=" + departmentName +
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
