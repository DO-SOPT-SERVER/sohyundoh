package org.sopt.secondSeminar.Repository;

import jakarta.persistence.EntityNotFoundException;
import org.sopt.secondSeminar.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    default Member findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 회원입니다."));
    }

    //오호.. default를 붙이면 구현부를 작성할 수 있구나..
}
