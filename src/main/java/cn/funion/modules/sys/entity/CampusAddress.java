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
@TableName("campus_address")
public class CampusAddress extends Model<CampusAddress> {

    private static final long serialVersionUID = 1L;

    @TableField("campus_id")
    private Integer campusId;
    @TableField("street_address1")
    private String streetAddress1;
    @TableField("street_address2")
    private String streetAddress2;
    private String town;
    private String region;
    private String postcode;
    private String country;
    private String phone;
    private String website;
    private String latitude;
    private String longitude;
    @TableField("_status")
    @TableLogic
    private Integer status;
    private String email;
    @TableField("_created_date")
    private Date createdDate;




    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "CampusAddress{" +
        ", campusId=" + campusId +
        ", streetAddress1=" + streetAddress1 +
        ", streetAddress2=" + streetAddress2 +
        ", town=" + town +
        ", region=" + region +
        ", postcode=" + postcode +
        ", country=" + country +
        ", phone=" + phone +
        ", website=" + website +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", status=" + status +
        "}";
    }
}
