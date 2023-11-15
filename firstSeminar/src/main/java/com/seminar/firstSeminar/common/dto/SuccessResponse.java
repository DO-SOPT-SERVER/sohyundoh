package com.seminar.firstSeminar.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SuccessResponse {
    private String code;
    private String status;
    private boolean success;

    public static SuccessResponse success(String code, String status, boolean success) {
        return new SuccessResponse(code, status, success);
    }
}
