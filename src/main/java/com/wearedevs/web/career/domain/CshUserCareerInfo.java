package com.wearedevs.web.career.domain;


import com.wearedevs.common.domain.BaseDateTimeEntity;
import com.wearedevs.common.enumeration.user.WorkStatus;
import com.wearedevs.web.user.domain.CshUserDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ApiModel("사용자 경력 정보")
@Table(name = "CSH_USER_CAREER_INFO")
public class CshUserCareerInfo extends BaseDateTimeEntity {

    @ApiModelProperty("사용자 정보 고유 번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @ApiModelProperty("사용자 상세 고유 번호")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_DETAIL_ID", referencedColumnName = "ID")
    private CshUserDetail cshUserDetail;

    @ApiModelProperty("경력 상태 존재 여부")
    @Column(name = "EXISTS_CAREER_DISPLAY")
    private String existsCareerDisplay;

    @ApiModelProperty("총 경력")
    @Column(name = "ALL_COMPANY_YEARS")
    private String allCompanyYears;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty("재직상태 (취준, 재직, 이직 준비 등)")
    @Column(name = "WORK_STATUS")
    private WorkStatus workStatus;

    @ApiModelProperty("경력 상세 정보 공개 여부")
    @Column(name = "CAREER_DETAIL_DISPLAY")
    private String careerDetailDisplay;

    @ApiModelProperty("회사 이름 공개 여부 (인증된 사용자만 노출 (인증 방식 - 이메일?) / 재직 기간에 따라 '전', '현' 표시 / 현재 회사, 대표 회사 두 개 다 있는 경우 대표 회사를 보여준다.")
    @Column(name = "CURRENT_COMPANY_DISPLAY")
    private String currentCompanyDisplay;

    @ApiModelProperty("사용자 경력 상세 정보")
    @OneToMany(mappedBy = "cshUserCareerInfo", cascade = CascadeType.ALL)
    private List<CshUserCareerDetailInfo> cshUserCareerDetailInfoList = new ArrayList<>();

}
