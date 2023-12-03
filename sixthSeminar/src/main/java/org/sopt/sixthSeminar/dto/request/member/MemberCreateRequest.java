package org.sopt.sixthSeminar.dto.request.member;

import org.sopt.sixthSeminar.domain.SOPT;

public record MemberCreateRequest(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {
}
