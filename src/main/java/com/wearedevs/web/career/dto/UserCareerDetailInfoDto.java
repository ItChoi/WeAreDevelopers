package com.wearedevs.web.career.dto;

import com.wearedevs.common.enumeration.TwoAnswerType;
import com.wearedevs.web.company.dto.CompanyAssignedWorkDetailDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserCareerDetailInfoDto {
    private Long id;
    //private UserCareerInfoDto cshUserCareerInfo;
    private TwoAnswerType currentCompanyType;
    private TwoAnswerType representativeCompanyType;
    private String companyName;
    private String position;
    private String department;
    private LocalDateTime workStartDate;
    private LocalDateTime workEndDate;
    private List<CompanyAssignedWorkDetailDto> cshCompanyAssignedWorkDetailList = new ArrayList<>();
}
