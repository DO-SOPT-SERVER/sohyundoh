package org.sopt.thirdSeminar.dto.response.member;

import org.sopt.thirdSeminar.domain.Member;
import org.sopt.thirdSeminar.domain.SOPT;


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

