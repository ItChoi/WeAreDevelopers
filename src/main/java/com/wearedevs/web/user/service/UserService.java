package com.wearedevs.web.user.service;

import com.wearedevs.web.user.domain.User;
import com.wearedevs.web.user.dto.UserRegisterRequestDto;
import com.wearedevs.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return null;
    }

    @Transactional
    public Long createUser(UserRegisterRequestDto requestDto) {
        User user = modelMapper.map(requestDto, User.class);
        /*User user = User.builder()
                .loginId(requestDto.getLoginId())
                .password(requestDto.getPassword())
                .build();*/
        userRepository.save(user);
        return user.getId();
    }


}
