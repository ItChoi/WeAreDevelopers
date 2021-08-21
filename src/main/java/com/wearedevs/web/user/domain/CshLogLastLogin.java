package com.wearedevs.web.user.domain;

import com.wearedevs.common.enumeration.user.LoginType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 로그인 로그
 */
@ApiModel("로그인 로그")
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "wearedevs.CSH_LOG_LAST_LOGIN")
public class CshLogLastLogin implements Serializable {

    /**
     * 로그인 로그 ID
     */
    @ApiModelProperty("로그인 로그 ID")
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @ApiModelProperty("(사용자 ID)")
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    /**
     * (LOGIN: 로그인, LOGOUT: 로그아웃)
     */
    @ApiModelProperty("(LOGIN: 로그인, LOGOUT: 로그아웃)")
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private LoginType type;

    /**
     * 사용자 ip
     */
    @ApiModelProperty("사용자 ip")
    @Column(name = "IP")
    private String ip;

    /**
     * 접속 브라우저 (CHROME, SAFARI, ...)
     */
    @ApiModelProperty("접속 브라우저 (CHROME, SAFARI, ...)")
    @Column(name = "ACCESS_BROWSER")
    private String accessBrowser;

    /**
     * 접속 장비 (PC / MOBILE)
     */
    @ApiModelProperty("접속 장비 (PC / MOBILE)")
    @Column(name = "ACCESS_DEVICE")
    private String accessDevice;

    /**
     * 로케일 정보 (KO, EN, ...)
     */
    @ApiModelProperty("로케일 정보 (KO, EN, ...)")
    @Column(name = "ACCESS_LOCALE")
    private String accessLocale;

    /**
     * 생성자 USER_ID
     */
    @ApiModelProperty("생성자 USER_ID")
    @Column(name = "CREATED_USER_ID")
    private Long createdUserId;

    /**
     * 생성일
     */
    @ApiModelProperty("생성일")
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    /**
     * 수정자 USER_ID
     */
    @ApiModelProperty("수정자 USER_ID")
    @Column(name = "UPDATED_USER_ID")
    private Long updatedUserId;

    /**
     * 수정일
     */
    @ApiModelProperty("수정일")
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;


}
