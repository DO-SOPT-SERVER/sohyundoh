package org.sopt.sixthSeminar.exception.model;

import org.sopt.sixthSeminar.exception.ErrorMessage;

public class AuthException extends GlobalException {
    public AuthException(ErrorMessage message) {
        super(message);
    }
}