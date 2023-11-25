package org.sopt.sixthSeminar.dto.request.member;

import org.sopt.thirdSeminar.domain.SOPT;

public record MemberCreateRequest(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {
}
