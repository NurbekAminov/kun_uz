package com.example.entity;

import com.example.entity.base.BaseEntity;
import com.example.entity.base.SequencesBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "articleType")
public class ArticleTypeEntity extends SequencesBaseEntity {
    @Column(name = "order_number", nullable = false, unique = true)
    private Integer orderNumber;

    @Column(name = "name_uz", nullable = false, unique = true)
    private String nameUz;

    @Column(name = "name_ru", nullable = false, unique = true)
    private String nameRu;

    @Column(name = "name_en", nullable = false, unique = true)
    private String nameEn;

    @Column
    private Integer prtId;
}
