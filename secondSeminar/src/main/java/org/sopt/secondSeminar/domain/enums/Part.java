package org.sopt.secondSeminar.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Part {
    SERVER("서버"),
    WEB("웹"),
    ANDROID("안드로이드"),
    IOS("iOS"),
    PLAN("기획"),
    DESIGN("디자인");

    private final String name;
}