package com.wearedevs.api.user.domain;

import com.wearedevs.api.role.domain.UserRole;
import com.wearedevs.common.domain.BaseDateTimeEntity;
import com.wearedevs.common.enumeration.user.GenderType;
import com.wearedevs.common.enumeration.user.LoginAccessType;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;


@Getter
@Table(name = "CSH_USER")
@Entity
public class CshUser extends BaseDateTimeEntity {

    /**
     * 사용자 PK
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRole> userRoleList;

    /**
     * 사용자 로그인 타입 (BASIC, KAKAO, GOOGLE, NAVER, ...)
     */
    @Enumerated(EnumType.STRING)
    @Column
    private LoginAccessType loginType;

    /**
     * 로그인 아이디
     */
    @Column
    private String loginId;

    /**
     * 비밀번호
     */
    @Column
    private String password;

    /**
     * 이름
     */
    @Column
    private String name;

    /**
     * 이메일
     */
    @Column
    private String email;

    /**
     * 핸드폰 번호
     */
    @Column
    private String phoneNumber;

    /**
     * 프로필 사진 경로
     */
    @Column
    private String profileImagePath;

    /**
     * 성별 (여성: F, 남성: M, 비공개: S)
     */
    @Enumerated(EnumType.STRING)
    @Column
    private GenderType gender;

    /**
     * 생년월일
     */
    @Column
    private String birthday;

}
