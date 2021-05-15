package com.wearedevs.web.user.domain;

import com.wearedevs.common.domain.BaseDateTimeEntity;
import com.wearedevs.common.enumeration.user.LoginType;
import com.wearedevs.common.enumeration.user.UserActiveStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 사용자 정보
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@ApiModel("사용자 정보")
@Table(name = "csh_user")
public class User extends BaseDateTimeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 사용자 고유 번호
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @ApiModelProperty("사용자 고유 번호")
    @Column(name = "ID", nullable = false)
    private Long id;

    /**
     * 로그인 아이디
     */
    @Column(name = "LOGIN_ID")
    @ApiModelProperty("로그인 아이디")
    private String loginId;

    /**
     * 비밀번호
     */
    @ApiModelProperty("비밀번호")
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 이름
     */
    @Column(name = "NAME")
    @ApiModelProperty("이름")
    private String name;

    /**
     * 이메일
     */
    @Column(name = "EMAIL")
    @ApiModelProperty("이메일")
    private String email;

    /**
     * 프로필 사진
     */
    @ApiModelProperty("프로필 사진")
    @Column(name = "PROFILE_IMAGE_NAME")
    private String profileImageName;

    /**
     * 썸네일 프로필 사진
     */
    @ApiModelProperty("썸네일 프로필 사진")
    @Column(name = "PROFILE_THUMBNAIL_IMAGE_NAME")
    private String profileThumbnailImageName;

    /**
     * 사용자 로그인 타입 (kakao, google, naver, ...)
     */
    @Enumerated
    @Column(name = "LOGIN_TYPE")
    @ApiModelProperty("사용자 로그인 타입 (kakao, google, naver, ...)")
    private LoginType loginType;

    /**
     * 사용자 활동 상태
     */
    @Enumerated
    @ApiModelProperty("사용자 활동 상태")
    @Column(name = "USER_ACTIVE_STATUS")
    private UserActiveStatus userActiveStatus;

    /**
     * 마지막 로그인 날짜
     */
    @ApiModelProperty("마지막 로그인 날짜")
    @Column(name = "LAST_LOGIN_DATE")
    private LocalDateTime lastLoginDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private UserInfo userInfo;

    @Builder
    public User(String loginId, String password, String name, String email, String profileImageName, LoginType loginType, UserActiveStatus userActiveStatus, LocalDateTime lastLoginDate, UserInfo userInfo) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.profileImageName = profileImageName;
        this.loginType = loginType;
        this.userActiveStatus = userActiveStatus;
        this.lastLoginDate = lastLoginDate;
        this.userInfo = userInfo;
    }
}
