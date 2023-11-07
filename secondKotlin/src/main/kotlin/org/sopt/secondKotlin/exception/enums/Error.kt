package org.sopt.secondKotlin.exception.enums

import org.springframework.http.HttpStatus

enum class Error(
    val status: HttpStatus,
    val message: String
) {
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시물이 없습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회원이 없습니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 카테고리가 없습니다.")
}