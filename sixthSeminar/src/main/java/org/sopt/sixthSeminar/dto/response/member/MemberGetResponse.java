package org.sopt.sixthSeminar.dto.response.member;

import org.sopt.sixthSeminar.domain.Member;
import org.sopt.sixthSeminar.domain.SOPT;


public record MemberGetResponse(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {

    public static MemberGetResponse of(final Member member) {
        return new MemberGetResponse(
                member.getName(),
                member.getNickname(),
                member.getAge(),
                member.getSopt()
        );
    }
}

