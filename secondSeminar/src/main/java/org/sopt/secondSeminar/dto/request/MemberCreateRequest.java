package org.sopt.secondSeminar.dto.request;

import org.sopt.secondSeminar.domain.SOPT;

public record MemberCreateRequest(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {
}
