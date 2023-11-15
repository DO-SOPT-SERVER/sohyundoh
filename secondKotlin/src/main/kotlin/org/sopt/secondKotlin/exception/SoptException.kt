package org.sopt.secondKotlin.exception

import org.sopt.secondKotlin.exception.enums.Error

open class SoptException(
    val error: Error
) : RuntimeException() {
    class CategoryNotFoundException(error: Error) : SoptException(error)
    class PostNotFoundException(error: Error) : SoptException(error)
    class MemberNotFoundException(error: Error) : SoptException(error)
}