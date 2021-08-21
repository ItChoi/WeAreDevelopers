package com.wearedevs.web.user.repository;

import com.wearedevs.web.role.domain.CshUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CshUserRoleRepository extends JpaRepository<CshUserRole, Long>, JpaSpecificationExecutor<CshUserRole> {

}