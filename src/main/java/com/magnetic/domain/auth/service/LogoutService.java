package com.magnetic.domain.auth.service;

import com.magnetic.domain.auth.entity.Token;
import com.magnetic.domain.auth.entity.TokenBlackList;
import com.magnetic.domain.auth.repository.TokenBlackListRepository;
import com.magnetic.domain.auth.repository.TokenRepository;
import com.magnetic.global.common.code.status.ErrorStatus;
import com.magnetic.global.common.exception.handler.AuthHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;
    private final TokenBlackListRepository tokenBlackListRepository;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        Token storedToken = tokenRepository.findByToken(jwt)
                .orElseThrow(() -> new AuthHandler(ErrorStatus.TOKEN_NOT_FOUND));

        TokenBlackList blackList= new TokenBlackList();
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            blackList.setToken(jwt);
            tokenRepository.save(storedToken);
            tokenBlackListRepository.save(blackList);
            SecurityContextHolder.clearContext();
        }
    }
}