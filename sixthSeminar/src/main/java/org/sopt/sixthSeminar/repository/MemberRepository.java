package org.sopt.sixthSeminar.repository;

import org.sopt.sixthSeminar.domain.Member;
import org.sopt.sixthSeminar.exception.ErrorMessage;
import org.sopt.sixthSeminar.exception.model.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    default Member findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND_EXCEPTION));
    }
    //default를 붙이면 인터페이스 에서도 구현부를 작성 가능
}
