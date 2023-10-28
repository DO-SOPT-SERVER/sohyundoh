package org.sopt.thirdSeminar.service;

import lombok.RequiredArgsConstructor;
import org.sopt.thirdSeminar.Repository.CategoryRepository;
import org.sopt.thirdSeminar.domain.Category;
import org.sopt.thirdSeminar.domain.CategoryId;
import org.sopt.thirdSeminar.dto.response.category.CategoryResponse;
import org.sopt.thirdSeminar.exception.ErrorMessage;
import org.sopt.thirdSeminar.exception.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryJpaRepository;

    public Category getByCategoryId(CategoryId categoryId) {
        return categoryJpaRepository.findById(Short.valueOf(categoryId.getCategoryId())).orElseThrow(
                () -> new NotFoundException(ErrorMessage.CATEGORY_NOT_FOUND_EXCEPTION));
    }

    public CategoryResponse getById(Short id) {
        return CategoryResponse.of(categoryJpaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorMessage.CATEGORY_NOT_FOUND_EXCEPTION)));
    }
}
