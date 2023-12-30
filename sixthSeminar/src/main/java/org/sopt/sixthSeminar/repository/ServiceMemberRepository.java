package org.sopt.sixthSeminar.repository;

import org.sopt.sixthSeminar.domain.ServiceMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceMemberRepository extends JpaRepository<ServiceMember, Long> {
    Optional<ServiceMember> findByNickname(String nickname);
}
