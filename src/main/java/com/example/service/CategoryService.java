package com.example.service;

import com.example.dto.CategoryDTO;
import com.example.dto.RegionDTO;
import com.example.entity.CategoryEntity;
import com.example.entity.RegionEntity;
import com.example.exp.ItemNotFoundException;
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
        int effectedRows = categoryRepository.update(id, category.getKey(), category.getNameUz(), category.getNameRu(), category.getNameEng());
        return effectedRows != 0;
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
    public List<CategoryDTO> getByLanguage(String language) {
        Iterable<CategoryEntity> iterable = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new LinkedList<>();
        if (language.isEmpty()){
            throw new ItemNotFoundException("language not found");
        }
        if (language.equals("nameUz")){
            iterable.forEach(entity -> {
                dtoList.add(toDTOUz(entity));
            });
        }else if (language.equals("nameRu")){
            iterable.forEach(entity -> {
                dtoList.add(toDTORu(entity));
            });
        }else if (language.equals("nameEng")){
            iterable.forEach(entity -> {
                dtoList.add(toDTOEng(entity));
            });
        }else throw new ItemNotFoundException("language not found");

        return dtoList;
    }
    public CategoryEntity toEntity(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEng(dto.getNameEng());
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }
    public CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEng(entity.getNameEng());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public CategoryDTO toDTOUz(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setNameUz(entity.getNameUz());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public CategoryDTO toDTORu(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setNameRu(entity.getNameRu());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public CategoryDTO toDTOEng(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setNameEng(entity.getNameEng());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
