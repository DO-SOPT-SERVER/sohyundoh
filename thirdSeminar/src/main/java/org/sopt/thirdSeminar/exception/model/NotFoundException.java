package org.sopt.thirdSeminar.exception.model;

import org.sopt.thirdSeminar.exception.ErrorMessage;

public class NotFoundException extends GlobalException{
    public NotFoundException(ErrorMessage message) {
        super(message);
    }
}
