package org.sopt.sixthKotlin.controller

import lombok.RequiredArgsConstructor
import org.sopt.sixthKotlin.controller.dto.request.PostCreateRequest
import org.sopt.sixthKotlin.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.net.URI
import java.security.Principal

@RestController
@RequestMapping("/api/v2/posts")
class PostController(
    private val postService: PostService
) {
    @PostMapping
    fun createPostV2(
        @RequestPart image: MultipartFile,
        request: PostCreateRequest,
        principal: Principal
    ): ResponseEntity<Void> {
        val location = URI.create(postService.create(request, image, principal.name.toLong()))
        return ResponseEntity.created(location).build()
    }

    @DeleteMapping("{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<Void> {
        postService.deleteById(postId)
        return ResponseEntity.noContent().build()
    }
}
