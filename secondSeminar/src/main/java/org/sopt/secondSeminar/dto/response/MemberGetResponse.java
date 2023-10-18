package org.sopt.secondSeminar.dto.response;

import org.sopt.secondSeminar.domain.Member;
import org.sopt.secondSeminar.domain.SOPT;


public record MemberGetResponse(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {

    public static MemberGetResponse of(Member member) {
        return new MemberGetResponse(
                member.getName(),
                member.getNickname(),
                member.getAge(),
                member.getSopt()
        );
    }
}

