package com.wearedevs.web.user.repository;

import com.wearedevs.web.user.domain.CshUserAccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CshUserAccountInfoRepository extends JpaRepository<CshUserAccountInfo, Long>, JpaSpecificationExecutor<CshUserAccountInfo> {

}