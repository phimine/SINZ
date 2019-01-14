package cn.funion.modules.sys.entity.wapperDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampusDto {
    private  Long campusId;
    private String campusName;
    private String campusAddressStreetAddress1;
    private String campusAddressTown;
    private String campusAddressPhone;
    private String campusAddressEmail;
    private String campusAddressRegion;
    private String campusAddressPostcode;
    private String campusAddressWebsite;
    private String campusAddressLatitude;
    private String campusAddressLongitude;
    private String campusAddressCountry;

}
