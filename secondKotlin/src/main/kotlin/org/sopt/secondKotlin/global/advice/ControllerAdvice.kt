package org.sopt.secondKotlin.global.advice

import org.sopt.secondKotlin.exception.SoptException
import org.sopt.secondKotlin.exception.enums.Error
import org.sopt.secondKotlin.global.dto.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(SoptException::class)
    fun handleSoptException(e : SoptException): ErrorResponse {
        return ErrorResponse(e.error.status.value(), e.error.message);
    }
}