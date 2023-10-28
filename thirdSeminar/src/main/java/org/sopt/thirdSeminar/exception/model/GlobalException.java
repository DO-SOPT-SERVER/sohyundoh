package org.sopt.thirdSeminar.exception.model;

import lombok.Getter;
import org.sopt.thirdSeminar.exception.ErrorMessage;

@Getter
public class GlobalException extends RuntimeException{
    private ErrorMessage errorMessage;
    public GlobalException(ErrorMessage message) {
        super(message.getMessage());
        this.errorMessage = message;
    }
}