package cn.funion.modules.sys.entity.wapperDto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ScholarshipDto {
    private Integer scholarshipId ;
    private Integer institutionId;
    private String scholarshipTitle  ;
    private String studyLevel  ;
    private String areaOfStudy  ;
    private String areaOfStudyCode ;
    private String subjectOfStudy  ;
    private String subjectOfStudyCode ;
    private String level3Subjects  ;
    private String level3SubjectsCode ;
    private String scholarshipValue  ;
    private String scholarshipValueDuration ;
    private String scholarshipValueDescription ;
    private String numberOfAwards  ;
    private String nationality  ;
    private String applicationDeadlineDate  ;
    private String howToApply  ;
    private String eligibilityCriteria ;
    private String linkToProvidersWebsite ;

    //假设providerId为InstitutionId
    private String institutionType;
    private String institutionName;
    private String institutionExtraInfoDesc;
    private String institutionMediaMediaPath;
    private String institutionWebsite;


}
