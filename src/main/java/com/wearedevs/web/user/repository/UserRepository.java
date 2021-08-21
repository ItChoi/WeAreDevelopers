package com.wearedevs.web.user.repository;

import com.wearedevs.web.user.domain.CshUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<CshUser, Long>, JpaSpecificationExecutor<CshUser> {
    Optional<CshUser> findByLoginId(String username);
    boolean existsByLoginId(String loginId);
    //Optional<CshUser> findByEmailAndLoginType(String email, LoginAccessType loginType); // TODO: 임시 주석
}