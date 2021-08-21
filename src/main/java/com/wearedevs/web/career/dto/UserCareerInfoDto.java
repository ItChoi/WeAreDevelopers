package com.wearedevs.web.career.dto;

import com.wearedevs.common.enumeration.user.WorkStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserCareerInfoDto {
    private Long id;
    //private CshUserDetail cshUserDetail;
    private String existsCareerDisplay;
    private String allCompanyYears;
    private WorkStatus workStatus;
    private String careerDetailDisplay;
    private String currentCompanyDisplay;
    private List<UserCareerDetailInfoDto> cshUserCareerDetailInfoList = new ArrayList<>();
}
