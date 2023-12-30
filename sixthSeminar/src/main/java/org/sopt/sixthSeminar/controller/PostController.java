package org.sopt.sixthSeminar.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.sopt.sixthSeminar.common.dto.SuccessResponse;
import org.sopt.sixthSeminar.dto.request.post.PostCreateRequest;
import org.sopt.sixthSeminar.dto.request.post.PostUpdateRequest;
import org.sopt.sixthSeminar.dto.response.post.PostGetResponse;
import org.sopt.sixthSeminar.exception.SuccessMessage;
import org.sopt.sixthSeminar.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private static final String CUSTOM_AUTH_ID = "X-Auth-Id";

    private final PostService postService;

    @GetMapping("{postId}")
    public SuccessResponse<PostGetResponse> getPostById(@PathVariable Long postId) {
        return SuccessResponse.success(SuccessMessage.POST_SEARCH_SUCCESS, postService.getById(postId));
    }

    @GetMapping
    public SuccessResponse<List<PostGetResponse>> getPosts(@RequestHeader(CUSTOM_AUTH_ID) Long memberId) {
        return SuccessResponse.success(SuccessMessage.POST_SEARCH_SUCCESS, postService.getPosts(memberId));
    }


    @PatchMapping("{postId}")
    public SuccessResponse updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest request) {
        postService.editContent(postId, request);
        return SuccessResponse.success(SuccessMessage.POST_UPDATE_SUCCESS);
    }

    @DeleteMapping("{postId}")
    public SuccessResponse deletePost(@PathVariable Long postId) {
        postService.deleteById(postId);
        return SuccessResponse.success(SuccessMessage.POST_DELETE_SUCCESS);
    }
}
