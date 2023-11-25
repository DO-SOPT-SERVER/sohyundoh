package org.sopt.sixthSeminar.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.sopt.thirdSeminar.common.dto.SuccessResponse;
import org.sopt.thirdSeminar.dto.request.post.PostCreateRequest;
import org.sopt.thirdSeminar.dto.request.post.PostUpdateRequest;
import org.sopt.thirdSeminar.dto.response.post.PostGetResponse;
import org.sopt.thirdSeminar.exception.SuccessMessage;
import org.sopt.thirdSeminar.service.PostService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private static final String CUSTOM_AUTH_ID = "X-Auth-Id";

    private static final String LOCATION_PREFIX = "api/member/";

    private final PostService postService;

    @GetMapping("{postId}")
    public SuccessResponse<PostGetResponse> getPostById(@PathVariable Long postId) {
        return SuccessResponse.success(SuccessMessage.POST_SEARCH_SUCCESS, postService.getById(postId));
    }

    @GetMapping
    public SuccessResponse<List<PostGetResponse>> getPosts(@RequestHeader(CUSTOM_AUTH_ID) Long memberId) {
        return SuccessResponse.success(SuccessMessage.POST_SEARCH_SUCCESS, postService.getPosts(memberId));
    }

    @PostMapping
    public SuccessResponse createPost(@RequestHeader(CUSTOM_AUTH_ID) Long memberId,
                                      @RequestBody final PostCreateRequest request,
                                      final HttpServletResponse response) {
        String location = LOCATION_PREFIX + postService.create(request, memberId);
        response.setHeader("Location", location);
        return SuccessResponse.success(SuccessMessage.MEMBER_CREATE_SUCCESS);
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
