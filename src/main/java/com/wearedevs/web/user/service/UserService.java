package com.wearedevs.web.user.service;

import com.wearedevs.common.enumeration.user.UserActiveStatus;
import com.wearedevs.common.exception.user.ExistsUserException;
import com.wearedevs.web.user.domain.CshUser;
import com.wearedevs.web.user.domain.UserInfo;
import com.wearedevs.web.user.domain.UserRole;
import com.wearedevs.web.user.dto.UserDetailInfoResponseDto;
import com.wearedevs.web.user.dto.UserRegisterRequestDto;
import com.wearedevs.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CshUser findCshUser = userRepository.findByLoginId(username).orElse(null);
        if (findCshUser == null) throw new UsernameNotFoundException(username + "가 존재하지 않습니다.");

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(findCshUser.getUserRole().getAuthority().getCode()));

        return new User(username, findCshUser.getPassword(), authorities);
    }

    @Transactional
    public Long createUser(UserRegisterRequestDto requestDto) {
        boolean existsUserLoginId = userRepository.existsByLoginId(requestDto.getLoginId());
        if (existsUserLoginId) throw new ExistsUserException("이미 존재하는 아이디 입니다.");
        CshUser user = builderCshUserByRequestDto(requestDto);
        userRepository.save(user);
        return user.getId();
    }

    public CshUser builderCshUserByRequestDto(UserRegisterRequestDto requestDto) {
        // TODO 파일 처리
        CshUser userBuilder = CshUser.builder()
                .loginId(requestDto.getLoginId())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .loginType(requestDto.getLoginType())
                .userActiveStatus(UserActiveStatus.ACTIVITY)
                .build();

        UserInfo userInfoBuilder = builderUserInfoByRequestDto(requestDto, userBuilder);
        UserRole userRoleBuilder = builderUserRoleByRequestDto(requestDto, userBuilder);

        userBuilder.setUserInfo(userInfoBuilder);
        userBuilder.setUserRole(userRoleBuilder);

        return userBuilder;
    }

    private UserRole builderUserRoleByRequestDto(UserRegisterRequestDto requestDto, CshUser userBuilder) {
        return UserRole.builder()
                .cshUser(userBuilder)
                .authority(requestDto.getAuthority())
                .build();
    }

    private UserInfo builderUserInfoByRequestDto(UserRegisterRequestDto requestDto, CshUser userBuilder) {
        UserInfo userInfoBuilder = UserInfo.builder()
                .cshUser(userBuilder)
                .introduce(requestDto.getIntroduce())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();
        return userInfoBuilder;
    }

    public UserDetailInfoResponseDto findUserDetailInfo(Long userId) {
        CshUser findUser = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException(userId + "가 존재하지 않습니다."));

        return UserDetailInfoResponseDto.builder()
                .loginId(findUser.getLoginId())
                .name(findUser.getName())
                .email(findUser.getEmail())
                .profileImageName(findUser.getProfileImageName())
                .introduce(findUser.getUserInfo().getIntroduce())
                .phoneNumber(findUser.getUserInfo().getPhoneNumber())
                .loginType(findUser.getLoginType())
                .authority(findUser.getUserRole().getAuthority())
                .build();
    }
}
