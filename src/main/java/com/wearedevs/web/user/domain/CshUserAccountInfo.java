package com.wearedevs.web.user.domain;

import com.wearedevs.common.domain.BaseDateTimeEntity;
import com.wearedevs.common.enumeration.account.AccountType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 사용자 계정 정보
 */
@ApiModel("사용자 계정 정보")
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CSH_USER_ACCOUNT_INFO")
public class CshUserAccountInfo extends BaseDateTimeEntity {

    @ApiModelProperty("사용자 계정 정보 ID")
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @ApiModelProperty("사용자 고유 번호")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private CshUser cshUser;

    @ApiModelProperty("계정 사용 유무 (사용:Y, 미사용:N)")
    @Column(name = "ACCOUNT_USE_TYPE")
    private String accountUseType;

    @ApiModelProperty("비밀번호 실패 횟수")
    @Column(name = "PASSWORD_FAIL_COUNT")
    private int passwordFailCount;

    @ApiModelProperty("비밀번호 실패 횟수 초과 시간")
    @Column(name = "PASSWORD_FAIL_EXCEED_DATE")
    private LocalDateTime passwordFailExceedDate;

    @ApiModelProperty("계정 잠김 상태(잠김: LOCK, 안잠김: UNLOCK, ...)")
    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_LOCK_STATUS")
    private AccountType accountLockStatus;

    @ApiModelProperty("마지막 비밀번호 변경 날짜")
    @Column(name = "LAST_PASSWORD_CHANGE_DATE")
    private LocalDateTime lastPasswordChangeDate;

    @ApiModelProperty("2차 인증 여부 (CERTIFICATION: 인증, UNCERTIFICATION: 미인증)")
    @Column(name = "TFA_STATUS")
    private String tfaStatus;
}
