package org.sopt.thirdSeminar.dto.request;

import org.sopt.thirdSeminar.domain.enums.Part;

public record MemberProfileUpdateRequest(
        int generation,
        Part part

) {

}
