package org.sopt.thirdSeminar.dto.request;

import org.sopt.thirdSeminar.domain.SOPT;

public record MemberCreateRequest(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {
}
