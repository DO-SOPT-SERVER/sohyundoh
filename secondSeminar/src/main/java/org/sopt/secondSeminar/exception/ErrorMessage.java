package org.sopt.secondSeminar.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorMessage {

    MEMBER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "회원 정보가 존재하지 않습니다");

    HttpStatus status;
    String message;
}
