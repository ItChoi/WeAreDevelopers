package com.wearedevs.api.user.repository;

import com.wearedevs.api.user.domain.CshUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<CshUser, Long>, JpaSpecificationExecutor<CshUser> {
    Optional<CshUser> findByLoginId(String username);
}