package org.sopt.secondSeminar.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorMessage {

    MEMBER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "회원 정보가 존재하지 않습니다");

    private final HttpStatus status;
    private final String message;
}
