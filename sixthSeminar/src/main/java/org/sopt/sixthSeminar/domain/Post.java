package org.sopt.sixthSeminar.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private ServiceMember serviceMember;

    @Column(name = "category_id")
    private CategoryId categoryId;

    @Builder
    private Post(String title, String content, ServiceMember serviceMember, CategoryId categoryId) {
        this.title = title;
        this.content = content;
        this.serviceMember = serviceMember;
        this.categoryId = categoryId;
    }

    @Builder(builderMethodName = "builderWithImageUrl", buildMethodName = "buildWithImageUrl")
    public Post(String title, String content, String imageUrl, ServiceMember serviceMember) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.serviceMember = serviceMember;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}