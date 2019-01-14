package cn.funion.modules.sys.entity.wapperDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AgentDto {
    private Integer institutionId;
    private String institutionType;
    private String institutionName;
    private String institutionExtraInfoDesc;
    private String institutionAddRegion;
    private String institutionMediaMediaPath;
    private String institutionWebsite;
    private String institutionAddressStreetAddress1;
    private String institutionAddressPostcode;
    //用于组装展示
    private List<String> areaOfStudy;

    //查询条件
    private String institutionAddressCountry;
    private String institutionAddressTown;
    private  List<String> institutionAddressTowns;
    private List<String> institutionTypes;
    private String licensedImmigrationAgent;


}
