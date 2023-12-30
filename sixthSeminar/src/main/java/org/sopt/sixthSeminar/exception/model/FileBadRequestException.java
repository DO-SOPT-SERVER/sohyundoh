package org.sopt.sixthSeminar.exception.model;

import org.sopt.sixthSeminar.exception.ErrorMessage;

public class FileBadRequestException extends GlobalException {
    public FileBadRequestException(ErrorMessage message) {
        super(message);
    }
}