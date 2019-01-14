package cn.funion.modules.sys.entity.wapperDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDto {
    private Integer departmentId;
    private String departmentName;
    private String departmentAddressStreetAddress1;
    private String departmentAddressStreetAddress2;
    private String departmentAddressTown;
    private String departmentAddressRegion;
    private String departmentAddressPostcode;
    private String departmentAddressPhone;
    private String departmentAddressEmail;
    private String departmentAddressWebsit;
}
