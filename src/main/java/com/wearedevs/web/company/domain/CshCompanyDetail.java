package com.wearedevs.web.company.domain;

import com.wearedevs.common.domain.BaseDateTimeEntity;
import com.wearedevs.web.career.domain.CshUserCareerDetailInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@Entity
@ApiModel("회사 상세")
@Table(name = "CSH_USER_CAREER_DETAIL_INFO")
public class CshCompanyDetail extends BaseDateTimeEntity implements Serializable {
    @ApiModelProperty("회사 상세 고유 번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @ApiModelProperty("사용자 경력 정보")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_CAREER_DETAIL_INFO_ID", referencedColumnName = "ID")
    private CshUserCareerDetailInfo cshUserCareerDetailInfo;

    @ApiModelProperty("담당 부서")
    @Column(name = "FEATURE_DEVELOP_TITLE")
    private String featureDevelopTitle;

    @ApiModelProperty("상세설명")
    @Column(name = "EXPLANATION")
    private String explanation;

    @ApiModelProperty("개발 기간 (시작일)")
    @Column(name = "DEVELOPMENT_START_DATE")
    private LocalDateTime developmentStartDate;

    @ApiModelProperty("개발 기간 (시작일)")
    @Column(name = "DEVELOPMENT_END_DATE")
    private LocalDateTime developmentEndDate;

}
