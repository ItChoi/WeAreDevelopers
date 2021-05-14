package com.wearedevs.web.user.service;

import com.wearedevs.web.user.dto.UserRegisterRequestDto;
import com.wearedevs.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long createUser(UserRegisterRequestDto requestDto) {

        return null;
    }
}
