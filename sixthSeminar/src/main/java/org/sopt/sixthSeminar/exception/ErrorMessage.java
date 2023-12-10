package org.sopt.sixthSeminar.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorMessage {

    BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    FILE_EXTENSION_BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "이미지 확장자는 jpg, png, webp만 가능합니다."),
    FILE_SIZE_BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "이미지 사이즈는 5MB를 넘을 수 없습니다."),
    FILE_DELETE_EXCEPTION(HttpStatus.BAD_REQUEST, "이미지 삭제를 원할하게 처리하지 못하였습니다."),
    /*
    not found
     */
    MEMBER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "회원 정보가 존재하지 않습니다."),
    POST_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"해당 게시글은 존재하지 않습니다."),
    CATEGORY_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 카테고리는 존재하지 않습니다."),

    /*
    unauthorized
     */
    PASSWORD_INCORRECT_EXCEPTION(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
