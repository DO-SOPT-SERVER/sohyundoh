package org.sopt.sixthSeminar.repository;

import org.sopt.sixthSeminar.domain.Member;
import org.sopt.sixthSeminar.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByMemberId(Long memberId);
    List<Post> findAllByMember(Member Member);
}
