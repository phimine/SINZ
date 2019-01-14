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
@Getter
@Setter
@TableName("institution_media")
public class InstitutionMedia extends Model<InstitutionMedia> {

    private static final long serialVersionUID = 1L;

    @TableField("institution_id")
    private Integer institutionId;
    @TableField("media_path")
    private String mediaPath;
    @TableField("media_type")
    private String mediaType;
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
        return "InstitutionMedia{" +
        ", institutionId=" + institutionId +
        ", mediaPath=" + mediaPath +
        ", mediaType=" + mediaType +
        "}";
    }
}
