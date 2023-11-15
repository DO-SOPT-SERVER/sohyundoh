package org.sopt.thirdSeminar.Repository;

import org.sopt.thirdSeminar.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Short> {
}
