package com.wearedevs.web.user.service;

import com.wearedevs.common.enumeration.user.UserActiveStatus;
import com.wearedevs.common.exception.user.ExistsUserException;
import com.wearedevs.common.objectmapper.securityuser.SecurityUserObjectMapper;
import com.wearedevs.web.role.domain.CshUserRole;
import com.wearedevs.web.user.domain.CshUser;
import com.wearedevs.web.user.domain.CshUserDetail;
import com.wearedevs.web.user.dto.SecurityUserDto;
import com.wearedevs.web.user.dto.UserDetailInfoResponseDto;
import com.wearedevs.web.user.dto.UserRegisterRequestDto;
import com.wearedevs.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final ModelMapper modelMapper;
    private final SecurityUserObjectMapper securityUserObjectMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: 인증 컬럼 추가 후 계정 인증 로직 추가 -> SecurityUserDto 내부에 작성
        CshUser findCshUser = userRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "가 존재하지 않습니다."));

        List<GrantedAuthority> authorities = new ArrayList<>();
        findCshUser.getUserRoleList().forEach(userRole -> {
            authorities.add(new SimpleGrantedAuthority(userRole.getAuthority().getCode()));
        });


        //SecurityUserDto securityUserDto = modelMapper.map(findCshUser, SecurityUserDto.class);
        SecurityUserDto securityUserDto = securityUserObjectMapper.toDto(findCshUser);
        return securityUserDto;


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
        String password = requestDto.getPassword();

        CshUserDetail userDetailBuilder = builderUserInfoByRequestDto(requestDto);
        List<CshUserRole> userRoleListBuilder = builderUserRoleByRequestDto(requestDto);
        CshUser userBuilder = CshUser.builder()
                .loginId(requestDto.getLoginId())
                .password(StringUtils.hasText(password) ? passwordEncoder.encode(password) : "")
                .nickname(requestDto.getNickname())
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .phoneNumber(requestDto.getPhoneNumber())
                .profileImagePath(requestDto.getProfileImagePath())
                .profileThumbnailImagePath(requestDto.getProfileThumbnailImagePath())
                .gender(requestDto.getGender())
                .birthday(requestDto.getBirthday())
                .userDetail(userDetailBuilder)
                .userRoleList(userRoleListBuilder)
                .build();
        userDetailBuilder.setCshUser(userBuilder);
        userRoleListBuilder.forEach(userRole -> userRole.setCshUser(userBuilder));

        return userBuilder;
    }

    private List<CshUserRole> builderUserRoleByRequestDto(UserRegisterRequestDto requestDto) {
        return requestDto.getUserRoleList().stream()
                .map(userAuthority -> CshUserRole.builder().authority(userAuthority.getAuthority()).build())
                .collect(Collectors.toList());
    }

    private CshUserDetail builderUserInfoByRequestDto(UserRegisterRequestDto requestDto) {
        return CshUserDetail.builder()
                .introduce(requestDto.getIntroduce())
                .areaOne(requestDto.getAreaOne())
                .areaTwo(requestDto.getAreaTwo())
                .areaThree(requestDto.getAreaThree())
                .searchAreaPermitScope(requestDto.getSearchAreaPermitScope())
                .userActiveStatus(UserActiveStatus.ACTIVITY)
                // TODO .loginType(requestDto.getLoginAccessType())
                .privacyInfoDisplay(requestDto.getPrivacyInfoDisplay())
                .build();
    }

    public UserDetailInfoResponseDto findUserDetailInfo(Long userId) {
        CshUser findUser = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("[userId = " + userId + "]가 존재하지 않습니다."));

        return UserDetailInfoResponseDto.builder()
                .loginId(findUser.getLoginId())
                .name(findUser.getName())
                .email(findUser.getEmail())
                .profileImagePath(findUser.getProfileImagePath())
                .profileThumbnailImagePath(findUser.getProfileThumbnailImagePath())
                .phoneNumber(findUser.getPhoneNumber())
                .introduce(findUser.getUserDetail().getIntroduce())
                // TODO .loginType(findUser.getUserDetail().getLoginAccessType())
                .userAuthorityList(
                        findUser.getUserRoleList().stream()
                                .map(CshUserRole::getAuthority)
                                .collect(Collectors.toList())
                )
                .build();
    }

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
