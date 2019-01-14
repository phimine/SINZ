package cn.funion.modules.sys.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 大学
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Getter
@Setter
public class Campus extends Model<Campus> {

    private static final long serialVersionUID = 1L;

    @TableField("campus_id")
    private Integer campusId;
    @TableField("institution_id")
    private Integer institutionId;
    @TableField("silver_stripe_id")
    private String silverStripeId;
    @TableField("ministry_id")
    private String ministryId;
    private String status;
    @TableField("campus_name")
    private String campusName;
    @TableField("last_updated_date")
    private Date lastUpdatedDate;
    /**
     * 物理主键
     */
    @TableId(value = "_id", type = IdType.AUTO)
    private Long id;
    /**
     * 同步时间
     */
    @TableField("_sync_date")
    private Date syncDate;
    /**
     * 翻译时间
     */
    @TableField("_translation_date")
    private Date translationDate;
    /**
     * 针对要翻译的字段进行MD5加密
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
    @TableField("_status")
    @TableLogic
    private int delStatus;

    @TableField(exist = false)
    private CampusAddress campusAddress;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Campus{" +
        ", campusId=" + campusId +
        ", institutionId=" + institutionId +
        ", silverStripeId=" + silverStripeId +
        ", ministryId=" + ministryId +
        ", status=" + status +
        ", campusName=" + campusName +
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
