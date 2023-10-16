package org.sopt.secondSeminar.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessMessage {

    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED, "사용자 정보가 생성되었습니다."),
    MEMBER_SEARCH_SUCCESS(HttpStatus.OK, "사용자 정보가 조회되었습니다 "),
    ALL_MEMBERS_SEARCH_SUCCESS(HttpStatus.OK, "사용자 전체 정보가 조회되었습니다."),
    MEMBER_UPDATE_SUCCESS(HttpStatus.NO_CONTENT, "사용자 정보가 업데이트되었습니다."),
    MEMBER_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "사용자 정보가 삭제되었습니다.");

    HttpStatus status;
    String message;
}
