package org.sopt.sixthKotlin.service

import org.sopt.sixthKotlin.controller.dto.request.PostCreateRequest
import org.sopt.sixthKotlin.domain.Post
import org.sopt.sixthKotlin.exception.ErrorMessage
import org.sopt.sixthKotlin.exception.SoptException.PostNotFoundException
import org.sopt.sixthKotlin.external.S3Service
import org.sopt.sixthKotlin.repository.PostJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Service
@Transactional(readOnly = true)
class PostService(
    private val serviceMemberService: ServiceMemberService,
    private val postJpaRepository: PostJpaRepository,
    private val s3Service: S3Service
) {
    @Transactional
    fun create(
        request: PostCreateRequest,
        image: MultipartFile?,
        memberId: Long?
    ): String {
        return try {
            val imageUrl = s3Service.uploadImage(POST_IMAGE_FOLDER_NAME, image!!)
            val serviceMember = serviceMemberService.findById(memberId!!)
            val post = postJpaRepository.save(
                Post(
                    request.title,
                    request.content,
                    imageUrl,
                    serviceMember
                )
            )
            post.id.toString()
        } catch (e: RuntimeException) {
            throw RuntimeException(e.message)
        } catch (e: IOException) {
            throw RuntimeException(e.message)
        }
    }

    @Transactional
    fun deleteById(postId: Long) {
        try {
            val post =
                postJpaRepository.findByIdOrNull(postId) ?: throw PostNotFoundException(ErrorMessage.POST_NOT_FOUND)
            s3Service.deleteImage(post.imageUrl)
            postJpaRepository.deleteById(postId)
        } catch (e: IOException) {
            throw RuntimeException(e.message)
        } catch (e: RuntimeException) {
            throw RuntimeException(e.message)
        }
    }

    companion object {
        private const val POST_IMAGE_FOLDER_NAME = "posts/"
    }
}
