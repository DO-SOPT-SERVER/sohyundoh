package org.sopt.sixthSeminar.exception.model;

import lombok.Getter;
import org.sopt.sixthSeminar.exception.ErrorMessage;

@Getter
public class GlobalException extends RuntimeException{
    private ErrorMessage errorMessage;
    public GlobalException(ErrorMessage message) {
        super(message.getMessage());
        this.errorMessage = message;
    }
}