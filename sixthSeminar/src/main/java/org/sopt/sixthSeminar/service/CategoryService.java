package org.sopt.sixthSeminar.service;

import lombok.RequiredArgsConstructor;
import org.sopt.sixthSeminar.domain.Category;
import org.sopt.sixthSeminar.domain.CategoryId;
import org.sopt.sixthSeminar.exception.ErrorMessage;
import org.sopt.sixthSeminar.exception.model.NotFoundException;
import org.sopt.sixthSeminar.repository.CategoryRepository;
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

}
