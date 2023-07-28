package com.example.entity;

import com.example.entity.base.IdentityBaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "article_types")
public class ArticleTypesEntity extends IdentityBaseEntity {
    @Column(name = "article_id", nullable = false)
    private String articleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", insertable = false, updatable = false)
    private ArticleEntity article;

    @Column(name = "article_type_id", nullable = false)
    private Integer articleTypeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_type_id", insertable = false, updatable = false)
    private ArticleTypeEntity articleType;
}
