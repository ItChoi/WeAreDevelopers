package com.wearedevs.web.user.domain;

import com.wearedevs.common.domain.BaseDateTimeEntity;
import com.wearedevs.common.enumeration.user.LoginType;
import com.wearedevs.common.enumeration.user.UserActiveStatus;
import com.wearedevs.web.career.domain.CshUserCareerInfo;
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
@Table(name = "CSH_USER_DETAIL")
public class CshUserDetail extends BaseDateTimeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("사용자 상고 고유 번호")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @ApiModelProperty("사용자 고유 번호")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private CshUser cshUser;

    @ApiModelProperty("간략한 자기 소개 내용")
    @Column(name = "INTRODUCE")
    private String introduce;

    @ApiModelProperty("서울, 부산, 대구, ...")
    @Column(name = "AREA1")
    private String area1;

    @ApiModelProperty("관악구, 금천구, 영등포구, ...")
    @Column(name = "AREA2")
    private String area2;

    @ApiModelProperty("신림동, 시흥동, 독산동,, ...")
    @Column(name = "AREA3")
    private String area3;

    @ApiModelProperty("내 지역 중 어떤 특정 범위까지 에서만 스터디를 구하고 싶을 때")
    @Column(name = "SEARCH_MY_AREA")
    private String searchMyArea;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty("사용자 활동 상태")
    @Column(name = "USER_ACTIVE_STATUS")
    private UserActiveStatus userActiveStatus;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty("사용자 로그인 타입(카카오, 네이버, 홈페이지, ...")
    @Column(name = "LOGIN_TYPE")
    private LoginType loginType;

    @ApiModelProperty("마지막 로그인 날짜")
    @Column(name = "LAST_LOGIN_DATE")
    private LocalDateTime lastLoginDate;

    @ApiModelProperty("개인 정보 공개 여부 (하위 스키마 공개 여부)")
    @Column(name = "PRIVACY_INFO_DISPLAY")
    private String privacyInfoDisplay;

    @ApiModelProperty("사용자 경력 정보")
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cshUserDetail", cascade = CascadeType.ALL)
    private CshUserCareerInfo cshUserCareerInfo;





}
