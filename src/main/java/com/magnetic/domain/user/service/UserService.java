package com.magnetic.domain.user.service;

import com.magnetic.domain.user.dto.UserRequestDto;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.repository.UserRepository;
import com.magnetic.global.common.code.status.ErrorStatus;
import com.magnetic.global.common.exception.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean emailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public void setPassword(UserRequestDto.UpdateUserPasswordDto userPasswordDto) {
        User user = userRepository.findByEmail(userPasswordDto.email())
                .orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        user.setPassword(passwordEncoder.encode(userPasswordDto.password()));
        userRepository.save(user);
    }
}
