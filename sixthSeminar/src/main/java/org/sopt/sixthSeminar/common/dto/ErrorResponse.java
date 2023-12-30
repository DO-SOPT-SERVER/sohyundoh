package org.sopt.sixthSeminar.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.sopt.sixthSeminar.exception.ErrorMessage;

@Getter
@ToString
@RequiredArgsConstructor
public class ErrorResponse {
    private final int code;
    private final String message;

    public static ErrorResponse error(ErrorMessage error) {
        return new ErrorResponse(error.getStatus().value(), error.getMessage());
    }


    public static ErrorResponse error(ErrorMessage error, String message) {
        return new ErrorResponse(error.getStatus().value(), message);
    }
}
