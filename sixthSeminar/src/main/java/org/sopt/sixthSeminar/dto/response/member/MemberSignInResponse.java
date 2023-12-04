package org.sopt.sixthSeminar.dto.response.member;

public record MemberSignInResponse(
        String token
) {
    public static MemberSignInResponse of(final String token) {
        return new MemberSignInResponse(token);
    }
}
