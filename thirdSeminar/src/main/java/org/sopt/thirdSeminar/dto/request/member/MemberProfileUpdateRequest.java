package org.sopt.thirdSeminar.dto.request.member;

import org.sopt.thirdSeminar.domain.enums.Part;

public record MemberProfileUpdateRequest(
        int generation,
        Part part

) {

}
