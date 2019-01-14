package cn.funion.modules.sys.entity.wapperDto;

import cn.funion.modules.sys.entity.InstitutionMedia;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InstitutionDto {
    private Integer institutionId;
    private String institutionType;
    private String institutionName;
    private String institutionExtraInfoDesc;
    private String institutionAddRegion;
    private String institutionMediaMediaPath;
    private List<InstitutionMedia> institutionMedia;
    private String institutionWebsite;

    //详情补充页
    private String institutionProfileSummary;
    private String institutionAddressStreetAddress1;
    private String institutionAddressTown;
    private String institutionAddressPostcode;
    private String institutionAddressPhone;
    private List<CampusDto> campusList;
    private List<DepartmentDto> departmentList;
    private String institutionExtraInfoAccommodation;
    private String institutionExtraInfoGender;
    private String institutionExtraInfoOnSiteReligiousFacilities;
    private String institutionExtraInfoNumberOfStudents;
    private String institutionExtraInfoNumberOfIntStudents;


    //用于组装展示
    private List<String> areaOfStudy;
}
