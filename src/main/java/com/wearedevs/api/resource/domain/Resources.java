package com.wearedevs.api.resource.domain;

import com.wearedevs.common.domain.BaseDateTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "csh_resources")
public class Resources extends BaseDateTimeEntity {

    /**
     * 권한 자원 PK
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "resources")
    private List<RoleResource> roleResourceList;

    /**
     * http method
     */
    @Column(name = "http_method")
    private String httpMethod;

    /**
     * 순서
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 자원 이름 (url명, 메소드명, ...)
     */
    @Column(name = "resource_name")
    private String resourceName;

    /**
     * 자원 타입 (url, method, ...)
     */
    @Column(name = "resource_type")
    private String resourceType;

}
