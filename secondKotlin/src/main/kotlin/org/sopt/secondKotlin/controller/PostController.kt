package org.sopt.secondKotlin.controller

import org.sopt.secondKotlin.dto.request.PostCreateRequest
import org.sopt.secondKotlin.dto.request.PostUpdateRequest
import org.sopt.secondKotlin.dto.response.PostGetResponse
import org.sopt.secondKotlin.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/post")
class PostController(
    private val postService: PostService
) {
    companion object {
        private const val CUSTOM_AUTH_ID: String = "X-Auth-Id"
        private const val POST_API_ENDPOINT: String = "/api/post/"
    }
    @PostMapping
    fun createPost(
        @RequestHeader(CUSTOM_AUTH_ID) memberId: Long,
        @RequestBody request: PostCreateRequest
    ): ResponseEntity<Void> {
        val location = URI.create(POST_API_ENDPOINT + postService.create(request, memberId))

        return ResponseEntity.created(location).build()
    }

    @GetMapping("/{postId}")
    fun getPostById(@PathVariable postId: Long): ResponseEntity<PostGetResponse> {
        return ResponseEntity.ok().body(postService.getById(postId))
    }

    @GetMapping
    fun getPosts(@RequestHeader(CUSTOM_AUTH_ID) memberId: Long): ResponseEntity<List<PostGetResponse>> {
        return ResponseEntity.ok().body(postService.getPosts(memberId))
    }

    @PatchMapping("/{postId}")
    fun updatePost(
        @PathVariable postId: Long,
        @RequestBody request: PostUpdateRequest
    ): ResponseEntity<Void> {
        postService.editContent(postId, request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<Void> {
        postService.deleteById(postId)
        return ResponseEntity.noContent().build()
    }

}