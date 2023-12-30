package org.sopt.sixthSeminar.exception.handler;

import org.sopt.sixthSeminar.common.dto.ErrorResponse;
import org.sopt.sixthSeminar.exception.ErrorMessage;
import org.sopt.sixthSeminar.exception.model.AuthException;
import org.sopt.sixthSeminar.exception.model.FileBadRequestException;
import org.sopt.sixthSeminar.exception.model.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(final IllegalArgumentException e) {
        return ErrorResponse.error(ErrorMessage.BAD_REQUEST_EXCEPTION);
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUnAuthorizedException(final AuthException e) {
        return ErrorResponse.error(e.getErrorMessage());
    }

    @ExceptionHandler(FileBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleFileBadRequestException(final FileBadRequestException e) {
        return ErrorResponse.error(e.getErrorMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final NotFoundException e) {
        return ErrorResponse.error(e.getErrorMessage());
    }
}