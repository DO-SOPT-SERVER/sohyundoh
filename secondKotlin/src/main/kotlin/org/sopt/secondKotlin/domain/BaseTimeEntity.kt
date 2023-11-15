package org.sopt.secondKotlin.domain

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {
    @CreatedDate
    private var createdAt : LocalDateTime = LocalDateTime.MIN

    @LastModifiedDate
    private var modifiedAt : LocalDateTime = LocalDateTime.MIN
}