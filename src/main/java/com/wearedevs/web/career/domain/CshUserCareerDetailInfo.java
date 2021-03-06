package com.wearedevs.web.career.domain;

import com.wearedevs.common.domain.BaseDateTimeEntity;
import com.wearedevs.common.enumeration.TwoAnswerType;
import com.wearedevs.web.company.domain.CshCompanyAssignedWorkDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ApiModel("사용자 경력 상세 정보")
@Table(name = "CSH_USER_CAREER_DETAIL_INFO")
public class CshUserCareerDetailInfo extends BaseDateTimeEntity {

    @ApiModelProperty("사용자 정보 고유 번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @ApiModelProperty("사용자 경력 정보")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_CAREER_INFO_ID", referencedColumnName = "ID")
    private CshUserCareerInfo cshUserCareerInfo;

    @ApiModelProperty("현재 회사 여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENT_COMPANY_TYPE")
    private TwoAnswerType currentCompanyType;

    @ApiModelProperty("대표 회사 여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "REPRESENTATIVE_COMPANY_TYPE")
    private TwoAnswerType representativeCompanyType;

    @ApiModelProperty("회사명")
    @Column(name = "COMPANY_NAME")
    private String companyName;

    @ApiModelProperty("직급")
    @Column(name = "POSITION")
    private String position;

    @ApiModelProperty("담당 부서")
    @Column(name = "DEPARTMENT")
    private String department;

    @ApiModelProperty("재직 기간 (시작일)")
    @Column(name = "WORK_START_DATE")
    private LocalDateTime workStartDate;

    @ApiModelProperty("재직 기간 (종료일)")
    @Column(name = "WORK_END_DATE")
    private LocalDateTime workEndDate;

    @ApiModelProperty("회사 상세")
    @OneToMany(mappedBy = "cshUserCareerDetailInfo", cascade = CascadeType.ALL)
    private List<CshCompanyAssignedWorkDetail> cshCompanyAssignedWorkDetailList = new ArrayList<>();

}
