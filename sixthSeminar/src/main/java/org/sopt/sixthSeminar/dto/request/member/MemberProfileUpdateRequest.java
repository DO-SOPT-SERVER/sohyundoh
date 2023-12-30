package org.sopt.sixthSeminar.dto.request.member;

import org.sopt.sixthSeminar.domain.enums.Part;

public record MemberProfileUpdateRequest(
        int generation,
        Part part

) {

}
