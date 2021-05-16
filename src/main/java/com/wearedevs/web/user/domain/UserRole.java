package com.wearedevs.web.user.domain;

import com.wearedevs.common.domain.BaseDateTimeEntity;
import com.wearedevs.common.enumeration.user.UserAuthority;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 사용자 권한 정보
 */
@NoArgsConstructor
@Getter
@Setter
@ApiModel("사용자 권한 정보")
@Entity
@Table(name = "CSH_USER_ROLE")
public class UserRole extends BaseDateTimeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 유저 권한 고유 번호
     */
    @Id
    @ApiModelProperty("유저 권한 고유 번호")
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 사용자 고유 번호
     */
    @OneToOne(fetch = FetchType.LAZY)
    @ApiModelProperty("사용자 고유 번호")
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private CshUser cshUser;

    /**
     * 권한 이름
     */
    @Enumerated(EnumType.STRING)
    @ApiModelProperty("권한 이름")
    @Column(name = "AUTHORITY")
    private UserAuthority authority;

    @Builder
    public UserRole(Long id, CshUser cshUser, UserAuthority authority) {
        this.id = id;
        this.cshUser = cshUser;
        this.authority = authority;
    }
}
