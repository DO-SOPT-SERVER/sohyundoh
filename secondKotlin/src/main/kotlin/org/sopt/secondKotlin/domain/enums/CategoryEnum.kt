package org.sopt.secondKotlin.domain.enums

enum class CategoryEnum(
    val categoryName: String
) {
    JAVA("java"),
    SPRING("spring"),
    REACT("react"),
    PYTHON("python");

    companion object {
        private val categoryMap = mapOf(
            1.toShort() to JAVA,
            2.toShort() to SPRING,
            3.toShort() to REACT,
            4.toShort() to PYTHON
        )

        fun getCategoryNameById(categoryId: Short): String {
            return categoryMap[categoryId]?.categoryName ?: throw IllegalArgumentException("해당 카테고리명은 존재하지 않습니다")
        }
    }
}