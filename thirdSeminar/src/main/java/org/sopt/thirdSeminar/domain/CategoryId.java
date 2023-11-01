package org.sopt.thirdSeminar.domain;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class CategoryId implements Serializable {

    private String categoryId;

    @Builder
    private CategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}