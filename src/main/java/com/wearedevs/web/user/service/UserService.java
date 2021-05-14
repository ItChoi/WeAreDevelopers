package com.wearedevs.web.user.service;

import com.wearedevs.web.user.domain.User;
import com.wearedevs.web.user.dto.UserRegisterRequestDto;
import com.wearedevs.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Long createUser(UserRegisterRequestDto requestDto) {
        //User userEntity = modelMapper.map(requestDto, User.class);
        User user = User.builder()
                .loginId(requestDto.getLoginId())
                .password(requestDto.getPassword())
                .build();
        userRepository.save(user);
        return user.getId();
    }


}
