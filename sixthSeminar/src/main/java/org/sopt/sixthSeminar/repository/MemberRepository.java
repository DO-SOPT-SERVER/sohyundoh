package org.sopt.sixthSeminar.repository;

import jakarta.persistence.EntityNotFoundException;
import org.sopt.thirdSeminar.domain.Member;
import org.sopt.thirdSeminar.exception.ErrorMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    default Member findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.MEMBER_NOT_FOUND_EXCEPTION.getMessage()));
    }
    //default를 붙이면 인터페이스 에서도 구현부를 작성 가능
}
