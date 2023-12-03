package org.sopt.sixthSeminar.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.sixthSeminar.domain.Category;
import org.sopt.sixthSeminar.domain.CategoryId;
import org.sopt.sixthSeminar.domain.Member;
import org.sopt.sixthSeminar.domain.Post;
import org.sopt.sixthSeminar.dto.request.post.PostCreateRequest;
import org.sopt.sixthSeminar.dto.request.post.PostUpdateRequest;
import org.sopt.sixthSeminar.dto.response.post.PostGetResponse;
import org.sopt.sixthSeminar.exception.ErrorMessage;
import org.sopt.sixthSeminar.exception.model.NotFoundException;
import org.sopt.sixthSeminar.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final CategoryService categoryService;

    @Transactional
    public String create(PostCreateRequest request, Long memberId) {
        Member member = memberService.findById(memberId);
        //역할 분리
        Post post = postRepository.save(
                Post.builder()
                        .member(member)
                        .title(request.title())
                        .content(request.content())
                        .categoryId(
                                CategoryId.builder()
                                        .categoryId(request.categoryId()) //메소드 체이닝이 너무 많음 -> categoryId를 request body에서 String으로 받기
                                        .build())
                        .build());
        return post.getId().toString();
    }

    @Transactional
    public void editContent(Long postId, PostUpdateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.POST_NOT_FOUND_EXCEPTION));
        post.updateContent(request.content());
    }

    @Transactional
    public void deleteById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException(ErrorMessage.POST_NOT_FOUND_EXCEPTION));
        postRepository.delete(post);
    }

    public List<PostGetResponse> getPosts(Long memberId) {
        return postRepository.findAllByMember(memberService.findById(memberId))
                .stream()
                .map(post -> PostGetResponse.of(post, getCategoryByPost(post)))
                .toList();
    }

    public PostGetResponse getById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException(ErrorMessage.POST_NOT_FOUND_EXCEPTION));
        return PostGetResponse.of(post, getCategoryByPost(post));
    }

    private Category getCategoryByPost(Post post) {
        return categoryService.getByCategoryId(post.getCategoryId());
    }

}