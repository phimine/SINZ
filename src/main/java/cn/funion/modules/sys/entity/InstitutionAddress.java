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
 * 机构地址
 * </p>
 *
 * @author xuanlin
 * @since 2018-09-03
 */
@Setter
@Getter
@TableName("institution_address")
public class InstitutionAddress extends Model<InstitutionAddress> {

    private static final long serialVersionUID = 1L;

    @TableField("institution_id")
    private Integer institutionId;
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
    private String email;
    @TableLogic
    @TableField("_status")
    private Integer status;
    @TableField("_created_date")
    private Date createdDate;



    @Override
    protected Serializable pkVal() {
        return null;
    }


}
