package com.magnetic.global.common.code.status;

import com.magnetic.global.common.code.BaseErrorCode;
import com.magnetic.global.common.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 기본 에러
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // User 에러
    _NOT_FOUND_USER(HttpStatus.NOT_FOUND, "USER400", "사용자가 존재하지 않습니다."),
    _INVALID_USER(HttpStatus.BAD_REQUEST, "USER401" , "아이디 또는 비밀번호가 틀렸습니다."),
    _NOT_FOUND_USER_CREW(HttpStatus.BAD_REQUEST, "USER402", "해당 사용자가 크루에 속하고 있지 않습니다."),

    // Crew 에러
    _NOT_FOUND_CREW(HttpStatus.NOT_FOUND, "CREW400", "해당 크루가 존재하지 않습니다."),
    _NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "CREW401", "크루에 해당 사용자가 존재하지 않습니다."),

    // Challenge 에러
    _NOT_FOUND_CHALLENGE(HttpStatus.NOT_FOUND, "CHALLENGE400", "해당 챌린지가 존재하지 않습니다."),
    _NOT_CHALLENGING(HttpStatus.NOT_FOUND, "CHALLENGE401", "챌린지 수행중이 아닙니다."),
    _ALREADY_PAID(HttpStatus.BAD_REQUEST, "CHALLENGE402", "이미 보상을 수령한 챌린지입니다."),

    // Plant 에러
    _NOT_FOUND_PLANT(HttpStatus.NOT_FOUND, "PLANT400", "해당 플랜트가 존재하지 않습니다."),

    // Post 에러
    _NOT_FOUND_POST(HttpStatus.NOT_FOUND, "POST400", "해당 게시글이 존재하지 않습니다."),

    // Calendar 에러
    _NOT_FOUND_TODO(HttpStatus.NOT_FOUND, "TODO400", "해당 일정이 존재하지 않습니다."),

    // Security 에러
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "SEC4001", "잘못된 형식의 토큰입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "SEC4010", "인증이 필요합니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "SEC4011", "토큰이 만료되었습니다."),
    TOKEN_SIGNATURE_ERROR(HttpStatus.UNAUTHORIZED, "SEC4012", "토큰이 위조되었거나 손상되었습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "SEC4030", "권한이 없습니다."),
    TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "SEC4041", "토큰이 존재하지 않습니다."),
    TOKEN_ORGANIZATION_NOT_FOND(HttpStatus.UNAUTHORIZED, "SEC4042", "존재하지 않는 단체입니다."),
    INTERNAL_SECURITY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SEC5000", "인증 처리 중 서버 에러가 발생했습니다."),
    INTERNAL_TOKEN_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SEC5001", "토큰 처리 중 서버 에러가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder().message(message).code(code).isSuccess(false).build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}