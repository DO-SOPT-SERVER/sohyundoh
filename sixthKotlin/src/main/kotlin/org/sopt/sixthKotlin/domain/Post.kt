package org.sopt.sixthKotlin.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "post")
class Post(

    var title: String?,

    var content: String?,

    var imageUrl: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var serviceMember: ServiceMember,


) {
    @Column(name = "category_id")
    var categoryId: CategoryId? = null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0

    fun updateContent(content: String) {
        this.content = content
    }
}