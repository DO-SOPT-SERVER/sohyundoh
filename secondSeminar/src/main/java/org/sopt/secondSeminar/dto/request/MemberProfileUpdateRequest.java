package org.sopt.secondSeminar.dto.request;

import lombok.Data;
import org.sopt.secondSeminar.domain.enums.Part;

@Data
public class MemberProfileUpdateRequest {
    private int generation;
    private Part part;
}
