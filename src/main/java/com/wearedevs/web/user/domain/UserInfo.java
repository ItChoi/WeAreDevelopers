package com.wearedevs.web.user.domain;

import com.wearedevs.common.domain.BaseDateTimeEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "csh_user_info")
public class UserInfo extends BaseDateTimeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 사용자 정보 고유 번호
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @ApiModelProperty("사용자 정보 고유 번호")
    @Column(name = "ID", nullable = false)
    private Long id;

    /**
     * (사용자 고유 번호)
     */
    @OneToOne(fetch = FetchType.LAZY)
    @ApiModelProperty("사용자 고유 번호")
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private CshUser cshUser;

    /**
     * 소개 내용
     */
    @ApiModelProperty("소개 내용")
    @Column(name = "INTRODUCE")
    private String introduce;

    /**
     * 핸드폰 번호
     */
    @ApiModelProperty("핸드폰 번호")
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Builder
    public UserInfo(Long id, CshUser cshUser, String introduce, String phoneNumber) {
        this.id = id;
        this.cshUser = cshUser;
        this.introduce = introduce;
        this.phoneNumber = phoneNumber;
    }
}
