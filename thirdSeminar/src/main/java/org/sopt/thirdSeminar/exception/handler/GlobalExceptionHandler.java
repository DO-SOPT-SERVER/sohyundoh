package org.sopt.thirdSeminar.exception.handler;

import org.sopt.thirdSeminar.common.dto.ErrorResponse;
import org.sopt.thirdSeminar.exception.ErrorMessage;
import org.sopt.thirdSeminar.exception.model.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(final IllegalArgumentException e) {
        return ErrorResponse.error(ErrorMessage.BAD_REQUEST_EXCEPTION);
    }

    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(final NotFoundException e) {
        return ErrorResponse.error(e.getErrorMessage());
    }
}