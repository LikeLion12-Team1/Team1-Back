package com.magnetic.domain.user.service;

import com.magnetic.domain.auth.service.AuthService;
import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.domain.email.dto.EmailRequestDto;
import com.magnetic.domain.email.dto.EmailResponseDto;
import com.magnetic.domain.user.converter.UserConverter;
import com.magnetic.domain.user.dto.UserRequestDto;
import com.magnetic.domain.user.dto.UserResponseDto;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.repository.UserCrewRepository;
import com.magnetic.domain.user.repository.UserRepository;
import com.magnetic.global.common.code.status.ErrorStatus;
import com.magnetic.global.common.exception.handler.UserHandler;
import com.magnetic.s3.S3Manager;
import com.magnetic.s3.entity.Uuid;
import com.magnetic.s3.repository.UuidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final UserCrewRepository userCrewRepository;
    private final UuidRepository uuidRepository;
    private final S3Manager s3Manager;

    public boolean emailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public void setPassword(UserRequestDto.UpdateUserPasswordDto userPasswordDto) {
        User user = userRepository.findByEmail(userPasswordDto.email())
                .orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        user.setPassword(passwordEncoder.encode(userPasswordDto.password()));
        userRepository.save(user);
    }

    public EmailResponseDto redirectToken(EmailRequestDto.AuthRequest emailAuthRequest) {
        User user = userRepository.findByEmail(emailAuthRequest.email())
                .orElseThrow(() -> new UserHandler(ErrorStatus._NOT_FOUND_USER));
        return authService.authenticateByToken(user);
    }

    public UserResponseDto.ProfilePreview getProfile(User user) {
        List<Crew> crewList = userCrewRepository.findAllCrewByUser(user);
        return UserConverter.toProfilePreviewDto(user, crewList);
    }

    public UserResponseDto.ProfilePreview updateProfile(UserRequestDto.Profile request, User user) {
        user.updateProfile(request);
        User updatedUser = userRepository.save(user);
        List<Crew> crewList = userCrewRepository.findAllCrewByUser(updatedUser);
        return UserConverter.toProfilePreviewDto(updatedUser, crewList);
    }

    public void updateProfileImg(MultipartFile file, User user) {

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateImage(savedUuid), file);

        user.setProfileImg(pictureUrl);
        userRepository.save(user);
    }

    public boolean nicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public void inactiveCrew(User user, String crewName) {
        userCrewRepository.updateUserCrewStatusToInactive(user, crewName, LocalDate.now());
    }

//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("해당 이메일을 가진 사용자가 존재하지 않습니다."));
//    }
}
