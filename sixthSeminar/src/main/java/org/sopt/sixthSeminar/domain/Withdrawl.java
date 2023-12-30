package org.sopt.sixthSeminar.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Withdrawl {

    @Id
    @GeneratedValue
    private Long id;
    private String reason;

    private MemberId memberId; // @OneToOne, @JoinColumn 미사용
    private String memberName;
}