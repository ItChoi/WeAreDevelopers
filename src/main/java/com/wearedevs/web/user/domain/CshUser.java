package com.wearedevs.web.user.domain;

import com.wearedevs.common.domain.BaseDateTimeEntity;
import com.wearedevs.web.role.domain.CshUserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자 정보
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@ApiModel("사용자 정보")
@Table(name = "CSH_USER")
public class CshUser extends BaseDateTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @ApiModelProperty("사용자 고유 번호")
    @Column(name = "ID", nullable = false)
    private Long id;

    @ApiModelProperty("로그인 아이디")
    @Column(name = "LOGIN_ID")
    private String loginId;

    @ApiModelProperty("비밀번호")
    @Column(name = "PASSWORD")
    private String password;

    @ApiModelProperty("사용자 별명")
    @Column(name = "NICKNAME")
    private String nickname;

    @ApiModelProperty("이름")
    @Column(name = "NAME")
    private String name;

    @ApiModelProperty("이메일")
    @Column(name = "EMAIL")
    private String email;

    @ApiModelProperty("핸드폰 번호")
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @ApiModelProperty("프로필 사진 경로")
    @Column(name = "PROFILE_IMAGE_PATH")
    private String profileImagePath;

    @ApiModelProperty("썸네일 프로필 사진")
    @Column(name = "PROFILE_THUMBNAIL_IMAGE_PATH")
    private String profileThumbnailImagePath;

    @ApiModelProperty("성별")
    @Column(name = "GENDER")
    private String gender;

    @ApiModelProperty("출생연도")
    @Column(name = "BIRTHDAY")
    private String birthday;

    @ApiModelProperty("사용자 상세")
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cshUser", cascade = CascadeType.ALL)
    private CshUserDetail userDetail;

    @ApiModelProperty("사용자 권한")
    @OneToMany(mappedBy = "cshUser", cascade = CascadeType.ALL)
    private List<CshUserRole> userRoleList = new ArrayList<>();

    /**
     * TODO:
     * 1. 계정 정보 연관 관계 매핑
     * 2. 비매너 지수 컬럼 추가
     */

    @Builder
    public CshUser(Long id, String loginId, String password, String nickname, String name, String email, String phoneNumber, String profileImagePath, String profileThumbnailImagePath, String gender, String birthday, CshUserDetail userDetail, List<CshUserRole> userRoleList) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImagePath = profileImagePath;
        this.profileThumbnailImagePath = profileThumbnailImagePath;
        this.gender = gender;
        this.birthday = birthday;
        this.userDetail = userDetail;
        this.userRoleList = userRoleList;
    }
}
