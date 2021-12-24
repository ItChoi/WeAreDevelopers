package com.wearedevs.api.role.domain;

import com.wearedevs.api.resource.domain.RoleResource;
import com.wearedevs.common.domain.BaseDateTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "csh_role")
public class Role extends BaseDateTimeEntity {

    /**
     * 권한 PK
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 권한 설명
     */
    @Column(name = "role_description")
    private String roleDescription;

    /**
     * 권한명
     */
    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<RoleResource> roleResourceList;


}
