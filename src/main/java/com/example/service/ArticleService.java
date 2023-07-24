package com.example.service;

import com.example.dto.ArticleDTO;
import com.example.entity.ArticleEntity;
import com.example.entity.CategoryEntity;
import com.example.entity.RegionEntity;
import com.example.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleDTO add(ArticleDTO dto) {
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setSharedCount(dto.getSharedCount());
        entity.setImageId(dto.getImageId());

        RegionEntity region = new RegionEntity();
        region.setId(dto.getRegionId());

        entity.setRegionId(region);

        CategoryEntity category = new CategoryEntity();
        category.setId(dto.getCategoryId());

        entity.setCategoryId(category);

        articleRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean update(Integer id, ArticleDTO dto) {
        int effect = articleRepository.updateById(id, dto.getTitle(),
                dto.getDescription(), dto.getContent(), dto.getSharedCount(), dto.getImageId(),
                dto.getRegionId(), dto.getCategoryId());
        return effect == 1;
    }
}
