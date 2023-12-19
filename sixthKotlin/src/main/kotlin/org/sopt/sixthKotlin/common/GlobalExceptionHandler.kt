package org.sopt.sixthKotlin.common

import org.sopt.sixthKotlin.common.dto.ErrorResponse
import org.sopt.sixthKotlin.exception.SoptException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(SoptException::class)
    fun handleSoptException(e : SoptException): ErrorResponse {
        return ErrorResponse(e.error.status.value(), e.error.message);
    }
}