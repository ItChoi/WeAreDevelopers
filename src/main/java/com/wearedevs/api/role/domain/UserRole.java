package com.wearedevs.api.role.domain;

import com.wearedevs.api.user.domain.CshUser;
import com.wearedevs.common.domain.BaseDateTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "csh_user_role")
public class UserRole extends BaseDateTimeEntity {

    /**
     * 사용자 권한 PK
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 사용자 PK
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private CshUser user;

    /**
     * 권한 PK
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;


}
