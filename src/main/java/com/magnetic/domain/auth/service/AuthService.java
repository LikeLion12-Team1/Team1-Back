package com.magnetic.domain.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magnetic.domain.auth.dto.AuthRequestDto;
import com.magnetic.domain.auth.dto.AuthResponseDto;
import com.magnetic.domain.auth.dto.RegisterRequestDto;
import com.magnetic.domain.auth.entity.Token;
import com.magnetic.domain.auth.entity.enums.TokenType;
import com.magnetic.domain.auth.repository.TokenBlackListRepository;
import com.magnetic.domain.auth.repository.TokenRepository;
import com.magnetic.domain.email.dto.EmailResponseDto;
import com.magnetic.domain.plant.entity.Plant;
import com.magnetic.domain.plant.repository.PlantRepository;
import com.magnetic.domain.user.entity.User;
import com.magnetic.domain.user.entity.UserPlant;
import com.magnetic.domain.user.entity.enums.Role;
import com.magnetic.domain.user.repository.UserPlantRepository;
import com.magnetic.domain.user.repository.UserRepository;
import com.magnetic.global.common.code.status.ErrorStatus;
import com.magnetic.global.common.exception.handler.AuthHandler;
import com.magnetic.global.common.exception.handler.PlantHandler;
import com.magnetic.global.common.exception.handler.UserHandler;
import com.magnetic.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;
    private final UserPlantRepository userPlantRepository;
    private final TokenRepository tokenRepository;
    private final TokenBlackListRepository tokenBlackListRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequestDto request) {
        User user = User.builder()
//                .profileImg(request.getProfileImg())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .nickname(request.email())
                .region(request.region())
                .role(Role.ROLE_USER)
                .plantToken(3L)

                .build();
        User savedUser = userRepository.save(user);

        // 처음 가입하면 주는 식물 (1번 식물 지급)
        Plant plant = plantRepository.findById(1L)
                .orElseThrow(() -> new PlantHandler(ErrorStatus._NOT_FOUND_PLANT));
        userPlantRepository.save(UserPlant.builder()
                .user(savedUser)
                .plant(plant)
                .getAt(LocalDate.now())
                .isLocked((byte) 1)
                .isMain((byte) 1)
                .build());
    }

    public AuthResponseDto authenticate(AuthRequestDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (AuthenticationException e) {
            throw new UserHandler(ErrorStatus._INVALID_USER);
        }
        var user = userRepository.findByEmail(request.email())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthResponseDto.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public EmailResponseDto authenticateByToken(User user) {
        String jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return EmailResponseDto.builder()
                .accessToken(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        if (tokenBlackListRepository.existsByToken(refreshToken)) {
            throw new AuthHandler(ErrorStatus.INVALID_TOKEN);
        }
        userEmail = jwtService.extractUserName(refreshToken);
        if (userEmail != null) {
            var userOptional = this.userRepository.findByEmail(userEmail);
            if (userOptional.isPresent()) {
                var user = userOptional.get();
                // 사용자가 존재하는 경우에 대한 처리
                if (jwtService.isTokenValid(refreshToken, user)) {
                    String accessToken = jwtService.generateToken(user);
                    revokeAllUserTokens(user);
                    saveUserToken(user, accessToken);
                    AuthResponseDto authResponse = AuthResponseDto.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build();
                    new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
                }
            } else {
                throw new UserHandler(ErrorStatus._NOT_FOUND_USER);
            }
        }
    }
}