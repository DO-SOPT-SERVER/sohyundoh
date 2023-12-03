package org.sopt.sixthSeminar.repository;

import org.sopt.sixthSeminar.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Short> {
}
