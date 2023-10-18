package org.sopt.secondSeminar.dto.request;

import org.sopt.secondSeminar.domain.enums.Part;

public record MemberProfileUpdateRequest(
        int generation,
        Part part

) {

}
