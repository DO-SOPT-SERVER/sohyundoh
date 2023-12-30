package org.sopt.sixthSeminar.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.sixthSeminar.common.dto.SuccessResponse;
import org.sopt.sixthSeminar.dto.request.post.PostCreateRequest;
import org.sopt.sixthSeminar.exception.SuccessMessage;
import org.sopt.sixthSeminar.service.PostServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/api/v2/posts")
@RequiredArgsConstructor
public class PostControllerV2 {

    private final PostServiceV2 postService;

    @PostMapping
    public SuccessResponse createPostV2(@RequestPart final MultipartFile image,
                                        final PostCreateRequest request,
                                        final Principal principal) {
        postService.createV2(request, image, Long.valueOf(principal.getName()));
        return SuccessResponse.success(SuccessMessage.POST_CREATE_SUCCESS);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deleteByIdV2(postId);
        return ResponseEntity.noContent().build();
    }
}