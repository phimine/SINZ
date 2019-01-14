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
@TableName("campus_media")
public class CampusMedia extends Model<CampusMedia> {

    private static final long serialVersionUID = 1L;

    @TableField("campus_id")
    private Integer campusId;
    @TableField("media_path")
    private String mediaPath;
    @TableField("media_type")
    private String mediaType;
    @TableLogic
    @TableField("_status")
    private int Status;
    @TableField("_created_date")
    private Date createdDate;


    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "CampusMedia{" +
        ", campusId=" + campusId +
        ", mediaPath=" + mediaPath +
        ", mediaType=" + mediaType +
        "}";
    }
}
