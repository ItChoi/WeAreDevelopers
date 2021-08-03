package com.wearedevs.web.user.repository;

import com.wearedevs.web.user.domain.CshUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserInfoRepository extends JpaRepository<CshUserDetail, Long>, JpaSpecificationExecutor<CshUserDetail> {

}