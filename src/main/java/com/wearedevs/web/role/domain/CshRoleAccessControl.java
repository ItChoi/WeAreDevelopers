package com.wearedevs.web.role.domain;

import com.wearedevs.common.domain.BaseDateTimeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ApiModel("권한 접근 제어")
@Entity
@Table(name = "CSH_ROLE_ACCESS_CONTROL")
public class CshRoleAccessControl extends BaseDateTimeEntity {

    @Id
    @ApiModelProperty("권한 접근 제어 ID")
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ApiModelProperty("사용자 권한 ID")
    @JoinColumn(name = "USER_ROLE_ID", referencedColumnName = "ID")
    private CshUserRole cshUserRole;

    @ApiModelProperty("접근 가능 URL")
    @Column(name = "URL")
    private String url;
}
