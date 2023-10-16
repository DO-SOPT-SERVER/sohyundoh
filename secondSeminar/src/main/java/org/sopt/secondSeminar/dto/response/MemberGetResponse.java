package org.sopt.secondSeminar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.sopt.secondSeminar.domain.Member;
import org.sopt.secondSeminar.domain.SOPT;

@AllArgsConstructor
@Data
public class MemberGetResponse {
    private String name;
    private String nickname;
    private int age;
    private SOPT sopt;

    public static MemberGetResponse of(Member member) {
        return new MemberGetResponse(
                member.getName(),
                member.getNickname(),
                member.getAge(),
                member.getSopt()
        );
    }
}
