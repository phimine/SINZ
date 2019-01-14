package cn.funion.modules.sys.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("department_address")
public class DepartmentAddress extends Model<DepartmentAddress> {

    private static final long serialVersionUID = 1L;

    @TableField("department_id")
    private Integer departmentId;
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
        return "DepartmentAddress{" +
        ", departmentId=" + departmentId +
        ", streetAddress1=" + streetAddress1 +
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
