package com.wearedevs.api.resource.repository;

import com.wearedevs.api.resource.domain.Resources;
import com.wearedevs.api.resource.domain.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ResourcesRepository extends JpaRepository<Resources, Long>, JpaSpecificationExecutor<RoleResource> {

    List<Resources> findAllByResourceTypeOrderByOrderNumDesc(String url);
}