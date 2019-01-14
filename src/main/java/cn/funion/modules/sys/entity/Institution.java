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
 * 机构
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Setter
@Getter
public class Institution extends Model<Institution> {

    private static final long serialVersionUID = 1L;

    @TableField("institution_id")
    private Integer institutionId;
    @TableField("silver_stripe_id")
    private String silverStripeId;
    private String status;
    @TableField("institution_name")
    private String institutionName;
    @TableField("institution_type")
    private String institutionType;
    @TableField("ministry_id")
    private String ministryId;
    private String email;
    private String website;
    @TableField("code_of_practice_signatory")
    private String codeOfPracticeSignatory;
    @TableField("annual_tuition_fees")
    private String annualTuitionFees;
    @TableField("last_updated_date")
    private Date lastUpdatedDate;
    @TableId("_id")
    private Long id;
    @TableField("_sync_date")
    private Date syncDate;
    @TableField("_translation_date")
    private Date translationDate;
    @TableField("licensed_immigration_agent")
    private String licensedImmigrationAgent;
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
    private InstitutionExtraInfo institutionExtraInfo;
    @TableField(exist=false)
    private InstitutionMedia institutionMedia;
    @TableField(exist=false)
    private InstitutionProfile institutionProfile;
    @TableField(exist=false)
    private InstitutionAddress institutionAddress;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Institution{" +
        ", institutionId=" + institutionId +
        ", silverStripeId=" + silverStripeId +
        ", status=" + status +
        ", institutionName=" + institutionName +
        ", institutionType=" + institutionType +
        ", ministryId=" + ministryId +
        ", email=" + email +
        ", website=" + website +
        ", codeOfPracticeSignatory=" + codeOfPracticeSignatory +
        ", annualTuitionFees=" + annualTuitionFees +
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
