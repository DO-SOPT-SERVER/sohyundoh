package org.sopt.sixthKotlin.exception

open class SoptException(
    val error: ErrorMessage
) : RuntimeException() {
    class CategoryNotFoundException(error: ErrorMessage) : SoptException(error)
    class PostNotFoundException(error: ErrorMessage) : SoptException(error)
    class MemberNotFoundException(error: ErrorMessage) : SoptException(error)
    class AuthorizationException(error: ErrorMessage) : SoptException(error)
}