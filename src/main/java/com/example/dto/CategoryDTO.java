package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CategoryDTO {
    private Integer id;
    private Integer orderNumber;
    private String name;
    private String nameUz;
    private String nameRu;
    private String nameEn;
    private Boolean visible;
    private LocalDateTime createdDate;

    public CategoryDTO() {
    }

    public CategoryDTO(Integer id, Integer orderNumber, String name) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.name = name;
    }
}
