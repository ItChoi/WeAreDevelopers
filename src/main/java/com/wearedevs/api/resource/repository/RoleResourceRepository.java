package com.wearedevs.api.resource.repository;

import com.wearedevs.api.resource.domain.Resources;
import com.wearedevs.api.resource.domain.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleResourceRepository extends JpaRepository<RoleResource, Long>, JpaSpecificationExecutor<Resources> {

}