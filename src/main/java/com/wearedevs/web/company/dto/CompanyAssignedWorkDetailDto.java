package com.wearedevs.web.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CompanyAssignedWorkDetailDto {
    private Long id;
    //private UserCareerDetailInfoDto cshUserCareerDetailInfo;
    private String featureDevelopTitle;
    private String explanation;
    private LocalDateTime developmentStartDate;
    private LocalDateTime developmentEndDate;
}
