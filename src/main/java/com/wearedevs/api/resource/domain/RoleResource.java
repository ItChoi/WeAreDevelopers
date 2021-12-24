package com.wearedevs.api.resource.domain;

import com.wearedevs.api.role.domain.Role;
import com.wearedevs.common.domain.BaseDateTimeEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "csh_role_resource")
public class RoleResource extends BaseDateTimeEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 자원 PK
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", referencedColumnName = "id")
    private Resources resources;

    /**
     * 권한 PK
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;


}
