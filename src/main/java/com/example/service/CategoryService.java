package com.example.service;

import com.example.dto.CategoryDTO;
import com.example.dto.RegionDTO;
import com.example.entity.CategoryEntity;
import com.example.enums.Language;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO add(CategoryDTO dto) {
        CategoryEntity entity = toEntity(dto);

        categoryRepository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }
    public Boolean update(Integer id, CategoryDTO category) {
        int effect = categoryRepository.updateById(id, category.getOrderNumber(), category.getNameUz(), category.getNameRu(), category.getNameEn());
        return effect == 1;
    }
    public Boolean delete(Integer id) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        categoryRepository.deleteById(id);
        return true;
    }
    public List<CategoryDTO> getAll() {
        Iterable<CategoryEntity> iterable = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }
    public List<CategoryDTO> getByLanguage(Language lang) {
        List<CategoryDTO> dtoList = new LinkedList<>();
        categoryRepository.findAllByVisibleTrue().forEach(entity -> {
            dtoList.add(toDTO(entity, lang));
        });
        return dtoList;
    }

    public List<CategoryDTO> getByLanguage2(Language lang) {
        List<CategoryDTO> dtoList = new LinkedList<>();
        switch (lang) {
            case en: {
                dtoList = categoryRepository.findAllByNameEn();
            }
            case uz: {
                dtoList = categoryRepository.findAllByNameUz();
            }
            case ru: {
                dtoList = categoryRepository.findAllByNameRu();
            }
        }
        return dtoList;
    }
    public List<CategoryDTO> getByLanguage3(Language lang) {
        List<CategoryDTO> dtoList = new LinkedList<>();
        categoryRepository.findAllByLang(lang.name()).forEach(mapper -> {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(mapper.getId());
            dto.setOrderNumber(mapper.getOrderNumber());
            dto.setName(mapper.getName());
            dtoList.add(dto);
        });
        return dtoList;
    }
    public CategoryEntity toEntity(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }
    public CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    private CategoryDTO toDTO(CategoryEntity entity, Language lang) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        switch (lang) {
            case en -> dto.setName(entity.getNameEn());
            case uz -> dto.setName(entity.getNameUz());
            case ru -> dto.setName(entity.getNameRu());
            default -> dto.setName(entity.getNameUz());
        }
        return dto;
    }
}
