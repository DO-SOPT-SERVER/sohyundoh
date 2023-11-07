package org.sopt.secondKotlin.service

import org.sopt.secondKotlin.domain.Category
import org.sopt.secondKotlin.domain.CategoryId
import org.sopt.secondKotlin.domain.Post
import org.sopt.secondKotlin.domain.enums.CategoryEnum
import org.sopt.secondKotlin.dto.request.PostCreateRequest
import org.sopt.secondKotlin.dto.request.PostUpdateRequest
import org.sopt.secondKotlin.dto.response.PostGetResponse
import org.sopt.secondKotlin.exception.SoptException
import org.sopt.secondKotlin.exception.enums.Error
import org.sopt.secondKotlin.repository.CategoryRepository
import org.sopt.secondKotlin.repository.MemberRepository
import org.sopt.secondKotlin.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
    private val postRepository: PostRepository,
    private val memberRepository: MemberRepository,
    private val categoryRepository: CategoryRepository,
    private val categoryService: CategoryService
) {
    @Transactional
    fun create(request: PostCreateRequest, memberId: Long): String {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw SoptException.MemberNotFoundException(Error.MEMBER_NOT_FOUND)
        val categoryId = CategoryId(request.categoryId)
        val post = postRepository.save(
            Post(
                title = request.title,
                content = request.content,
                member = member,
                categoryId = categoryId
            )
        )
        categoryRepository.save(
            Category(id = categoryId, content = CategoryEnum.getCategoryNameById(request.categoryId))
        )

        return post.id.toString()
    }

    fun getById(postId: Long): PostGetResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw SoptException.PostNotFoundException(Error.POST_NOT_FOUND)

        return PostGetResponse.of(post, getCategoryByPost(post))
    }

    fun getPosts(memberId: Long): List<PostGetResponse> {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw  SoptException.MemberNotFoundException(Error.MEMBER_NOT_FOUND)
        val posts = postRepository.findAllByMember(member)

        return posts.map { PostGetResponse.of(it, getCategoryByPost(it)) }.toList()
    }

    @Transactional
    fun editContent(postId: Long, request: PostUpdateRequest) {
        val post = postRepository.findByIdOrNull(postId) ?: throw SoptException.PostNotFoundException(Error.POST_NOT_FOUND)
        post.updateContent(request.content)
    }

    @Transactional
    fun deleteById(postId: Long) {
        postRepository.deleteById(postId)
    }

    private fun getCategoryByPost(post: Post): Category {
        return categoryService.getByCategoryId(post.categoryId)
    }
}