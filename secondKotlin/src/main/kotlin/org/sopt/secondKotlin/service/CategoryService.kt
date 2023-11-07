package org.sopt.secondKotlin.service

import org.sopt.secondKotlin.domain.Category
import org.sopt.secondKotlin.domain.CategoryId
import org.sopt.secondKotlin.exception.SoptException
import org.sopt.secondKotlin.exception.enums.Error
import org.sopt.secondKotlin.repository.CategoryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun getByCategoryId(categoryId: CategoryId): Category {
        return categoryRepository.findByIdOrNull(categoryId) ?: throw SoptException.CategoryNotFoundException(Error.CATEGORY_NOT_FOUND)
    }
}