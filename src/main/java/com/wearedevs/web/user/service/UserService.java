package com.wearedevs.web.user.service;

import com.wearedevs.web.user.domain.CshUser;
import com.wearedevs.web.user.dto.SecurityUserDto;
import com.wearedevs.web.user.dto.UserDto;
import com.wearedevs.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;
    //private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: 인증 컬럼 추가 후 계정 인증 로직 추가 -> SecurityUserDto 내부에 작성
        CshUser findCshUser = userRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "가 존재하지 않습니다."));

        List<GrantedAuthority> authorities = new ArrayList<>();
        findCshUser.getCshUserRole().forEach(userRole -> {
            authorities.add(
                    new SimpleGrantedAuthority(userRole.getAuthority().getCode())
            );
        });

        // TODO: 값 제대로 들어오는지 테스트 필요
        /*UserDto tempUserDto = modelMapper.map(findCshUser, UserDto.class);
        tempUserDto.setAuthorities(authorities);*/

        //return new User(username, findCshUser.getPassword(), authorities);
        //return modelMapper.map(tempUserDto, SecurityUserDto.class);
        return null;
    }

    /*@Transactional
    public Long createUser(UserRegisterRequestDto requestDto) {
        boolean existsUserLoginId = userRepository.existsByLoginId(requestDto.getLoginId());
        if (existsUserLoginId) throw new ExistsUserException("이미 존재하는 아이디 입니다.");
        CshUser user = builderCshUserByRequestDto(requestDto);
        userRepository.save(user);
        return user.getId();
    }*/

    /*public CshUser builderCshUserByRequestDto(UserRegisterRequestDto requestDto) {
        // TODO 파일 처리
        String password = requestDto.getPassword();
        CshUser userBuilder = CshUser.builder()
                .loginId(requestDto.getLoginId())
                .password(StringUtils.isEmpty(password) ? "" : passwordEncoder.encode(password))
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .profileImageName(requestDto.getProfileImageName())
                .loginType(requestDto.getLoginType())
                .userActiveStatus(UserActiveStatus.ACTIVITY)
                .build();

        CshUserDetail cshUserDetailBuilder = builderUserInfoByRequestDto(requestDto, userBuilder);
        UserRole userRoleBuilder = builderUserRoleByRequestDto(requestDto, userBuilder);

        userBuilder.setUserInfo(cshUserDetailBuilder);
        userBuilder.setUserRole(userRoleBuilder);

        return userBuilder;
    }*/

    /*private UserRole builderUserRoleByRequestDto(UserRegisterRequestDto requestDto, CshUser userBuilder) {
        return UserRole.builder()
                .cshUser(userBuilder)
                .authority(requestDto.getAuthority())
                .build();
    }*/

    /*private CshUserDetail builderUserInfoByRequestDto(UserRegisterRequestDto requestDto, CshUser userBuilder) {
        CshUserDetail cshUserDetailBuilder = CshUserDetail.builder()
                .cshUser(userBuilder)
                .introduce(requestDto.getIntroduce())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();
        return cshUserDetailBuilder;
    }*/

    /*public UserDetailInfoResponseDto findUserDetailInfo(Long userId) {
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
    }*/

    public void beforeLoginUserAuthChecksByUsername(String username) {
        /**
         * TODO: 계정 승인 체크
         * 1. Lock
         * 2. 사용 유무
         * 3. 계정 만료 체크
         */
    }

    public boolean isAvaliablePassword(String password) {


        return false;
    }

    // loadUserByUsername에 로직 추가해야될까?
    public boolean existsUserByUsername(String username) {
        if (!StringUtils.hasText(username)) return false;
        return userRepository.existsByLoginId(username);
    }

    /*private boolean isAvailableUsernameAndPassword(String username, String password) {
        // TODO: 로그인 아이디랑 패스워드 정규식 적용하여 검증 로직 보완하기 - 여기선 필요X
        return isNotEmptyUsernameOrPassword(username, password) && isAvailablePassword(password);
    }*/



}
