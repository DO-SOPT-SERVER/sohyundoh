package org.sopt.sixthSeminar.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessMessage {

    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED, "사용자 정보가 생성되었습니다."),
    POST_CREATE_SUCCESS(HttpStatus.CREATED, "게시글 정보가 생성되었습니다."),
    MEMBER_SEARCH_SUCCESS(HttpStatus.OK, "사용자 정보가 조회되었습니다 "),
    POST_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "게시글이 삭제되었습니다."),
    POST_UPDATE_SUCCESS(HttpStatus.NO_CONTENT, "게시글이 업데이트되었습니다."),
    POST_SEARCH_SUCCESS(HttpStatus.OK, "게시글이 조회되었습니다."),
    ALL_MEMBERS_SEARCH_SUCCESS(HttpStatus.OK, "사용자 전체 정보가 조회되었습니다."),
    MEMBER_UPDATE_SUCCESS(HttpStatus.NO_CONTENT, "사용자 정보가 업데이트되었습니다."),
    MEMBER_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "사용자 정보가 삭제되었습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
