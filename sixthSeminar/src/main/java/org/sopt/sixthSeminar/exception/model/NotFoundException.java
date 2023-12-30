package org.sopt.sixthSeminar.exception.model;

import org.sopt.sixthSeminar.exception.ErrorMessage;

public class NotFoundException extends GlobalException{
    public NotFoundException(ErrorMessage message) {
        super(message);
    }
}
