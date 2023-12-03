package org.sopt.sixthSeminar.service;

import lombok.RequiredArgsConstructor;
import org.sopt.sixthSeminar.domain.Member;
import org.sopt.sixthSeminar.domain.Post;
import org.sopt.sixthSeminar.dto.request.post.PostCreateRequest;
import org.sopt.sixthSeminar.exception.ErrorMessage;
import org.sopt.sixthSeminar.exception.model.NotFoundException;
import org.sopt.sixthSeminar.external.S3Service;
import org.sopt.sixthSeminar.repository.MemberRepository;
import org.sopt.sixthSeminar.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceV2 {

    private static final String POST_IMAGE_FOLDER_NAME = "posts/";

    private final MemberRepository memberJpaRepository;
    private final PostRepository postJpaRepository;
    private final S3Service s3Service;

    @Transactional
    public String createV2(PostCreateRequest request, MultipartFile image, Long memberId) {
        try {
            final String imageUrl = s3Service.uploadImage(POST_IMAGE_FOLDER_NAME, image);
            Member member = memberJpaRepository.findByIdOrThrow(memberId);
            Post post = postJpaRepository.save(
                    Post.builderWithImageUrl()
                            .title(request.title())
                            .content(request.content())
                            .imageUrl(imageUrl)
                            .member(member)
                            .build());
            return post.getId().toString();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void deleteByIdV2(Long postId) {
        try {
            Post post = postJpaRepository.findById(postId)
                    .orElseThrow(() -> new NotFoundException(ErrorMessage.POST_NOT_FOUND_EXCEPTION));
            s3Service.deleteImage(post.getImageUrl());
            postJpaRepository.deleteById(postId);
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
