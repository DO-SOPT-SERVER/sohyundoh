package org.sopt.sixthSeminar.repository;

import org.sopt.sixthSeminar.domain.Post;
import org.sopt.sixthSeminar.domain.ServiceMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByServiceMember(ServiceMember serviceMember);
}
