package cn.funion.modules.sys.entity.wapperDto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CourseDto {
    private static final long serialVersionUID = 1L;

    private Integer courseId;
    private Integer institutionId;
    private Integer departmentId;
    private String courseTitle;
    private String courseDescription;
    private String areaOfStudy;
    private String levelOfStudy;
    @JsonDeserialize(using=cn.funion.common.utils.CustomerDateAndTimeDeserialize.class)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss a")
    @JsonProperty("last_updated_date")
    @TableField("last_updated_date")
    private Date lastUpdatedDate;
    private String institutionType;
    private String institutionName;
    private String institutionWebsite;
    private String institutionExtraInfoDesc;
    private String institutionMediaMediaPath;
    private String opportunityCourseDruation;
    private String opportunityStartDate;
    private String campusName;
    private Integer campusId;

    private String institutionAddRegion;
    private String studyMode;

}
