package org.sopt.sixthKotlin.common.dto

import org.sopt.sixthKotlin.exception.ErrorMessage

class ErrorResponse(
    private val code: Int,
    private val message: String
) {

    companion object {
        fun error(error: ErrorMessage): ErrorResponse {
            return ErrorResponse(error.status.value(), error.message)
        }

        fun error(error: ErrorMessage, message: String): ErrorResponse {
            return ErrorResponse(error.status.value(), message)
        }
    }
}