package com.wearedevs.web.user.service;

import com.wearedevs.common.enumeration.user.UserActiveStatus;
import com.wearedevs.common.exception.user.ExistsUserException;
import com.wearedevs.web.user.domain.CshUser;
import com.wearedevs.web.user.domain.UserInfo;
import com.wearedevs.web.user.domain.UserRole;
import com.wearedevs.web.user.dto.UserRegisterRequestDto;
import com.wearedevs.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CshUser findCshUser = userRepository.findByLoginId(username).orElse(null);
        if (findCshUser == null) throw new UsernameNotFoundException(username + "가 존재하지 않습니다.");

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(findCshUser.getUserRole().getAuthority().getCode()));

        return new User(username, findCshUser.getPassword(), authorities);
    }

    @Transactional
    public Long createUser(UserRegisterRequestDto requestDto) throws Exception {
        boolean existsUserLoginId = userRepository.existsByLoginId(requestDto.getLoginId());
        if (existsUserLoginId) throw new ExistsUserException("이미 존재하는 아이디 입니다.");
        CshUser user = builderCshUserByRequestDto(requestDto);
        userRepository.save(user);
        return user.getId();
    }

    public CshUser builderCshUserByRequestDto(UserRegisterRequestDto requestDto) {
        // TODO 파일 처리
        return CshUser.builder()
                .loginId(requestDto.getLoginId())
                //.password(passwordEncoder.encode(requestDto.getPassword()))
                .password(requestDto.getPassword())
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .loginType(requestDto.getLoginType())
                .userActiveStatus(UserActiveStatus.ACTIVITY)
                .userInfo(
                    UserInfo.builder()
                    .introduce(requestDto.getIntroduce())
                    .phoneNumber(requestDto.getPhoneNumber())
                    .build()
                )
                .userRole(
                    UserRole.builder()
                    .authority(requestDto.getUserAuthority())
                    .build()
                )
                .build();
    }


}
