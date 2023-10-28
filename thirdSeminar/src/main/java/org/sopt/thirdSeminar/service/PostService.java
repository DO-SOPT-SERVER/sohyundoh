package org.sopt.thirdSeminar.service;

import lombok.RequiredArgsConstructor;
import org.sopt.thirdSeminar.Repository.MemberRepository;
import org.sopt.thirdSeminar.Repository.PostRepository;
import org.sopt.thirdSeminar.domain.Category;
import org.sopt.thirdSeminar.domain.Member;
import org.sopt.thirdSeminar.domain.Post;
import org.sopt.thirdSeminar.dto.request.post.PostCreateRequest;
import org.sopt.thirdSeminar.dto.request.post.PostUpdateRequest;
import org.sopt.thirdSeminar.dto.response.post.PostGetResponse;
import org.sopt.thirdSeminar.exception.ErrorMessage;
import org.sopt.thirdSeminar.exception.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryService categoryService;

    @Transactional
    public String create(PostCreateRequest request, Long memberId) {
        Member member = memberRepository.findByIdOrThrow(memberId);
        Post post = postRepository.save(
                Post.builder()
                        .member(member)
                        .title(request.title())
                        .content(request.content()).build());
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

        postRepository.findAllByMember(memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(ErrorMessage.POST_NOT_FOUND_EXCEPTION)));
        return postRepository.findAllByMemberId(memberId)
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