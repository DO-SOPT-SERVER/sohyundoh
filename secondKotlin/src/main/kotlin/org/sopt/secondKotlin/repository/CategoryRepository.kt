package org.sopt.secondKotlin.repository

import org.sopt.secondKotlin.domain.Category
import org.sopt.secondKotlin.domain.CategoryId
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, CategoryId> {
}