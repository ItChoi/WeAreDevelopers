package com.wearedevs.web.user.repository;

import com.wearedevs.web.user.domain.CshLogLastLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CshLogLastLoginRepository extends JpaRepository<CshLogLastLogin, Long>, JpaSpecificationExecutor<CshLogLastLogin> {

}