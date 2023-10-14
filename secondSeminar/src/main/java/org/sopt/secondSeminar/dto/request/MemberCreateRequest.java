package org.sopt.secondSeminar.dto.request;

import lombok.Data;
import org.sopt.secondSeminar.domain.SOPT;

@Data
public class MemberCreateRequest {
    private String name;
    private String nickname;
    private int age;
    private SOPT sopt;
}
