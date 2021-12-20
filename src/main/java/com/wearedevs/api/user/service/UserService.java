package com.wearedevs.api.user.service;

import com.wearedevs.api.user.domain.CshUser;
import com.wearedevs.api.user.dto.UserDto;
import com.wearedevs.api.user.dto.UserSecurity;
import com.wearedevs.api.user.repository.UserRepository;
import com.wearedevs.common.util.msg.ExceptionMsgUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CshUser findUser = userRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException(ExceptionMsgUtil.NOT_EXISTS_ACCOUNT));

        return new UserSecurity(findUser, getUserRolesByUser());
    }

    @Transactional
    public Long createUser(UserDto userDto) {
        // TODO
        return null;
    }

    private List<GrantedAuthority> getUserRolesByUser() {
        List<GrantedAuthority> roles = new ArrayList<>();
        // TODO: 유저 권한 연관관계 설정 후 변경
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        return roles;
    }

}
