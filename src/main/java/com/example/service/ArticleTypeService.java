package com.example.service;

import com.example.dto.ArticleTypeDTO;
import com.example.entity.ArticleTypeEntity;
import com.example.enums.Language;
import com.example.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    public ArticleTypeDTO add(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setCreatedDate(LocalDateTime.now());
        articleTypeRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean update(Integer id, ArticleTypeDTO dto) {
        int effect = articleTypeRepository.updateById(id, dto.getOrderNumber(),
                dto.getNameUz(), dto.getNameRu(), dto.getNameEn());
        return effect == 1;
    }


    public Boolean deleteById(Integer id) {
        Optional<ArticleTypeEntity> optional = articleTypeRepository.findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        articleTypeRepository.deleteById(id);
        return true;
    }

    public List<ArticleTypeDTO> getAll() {
        Iterable<ArticleTypeEntity> iterable = articleTypeRepository.findAll();
        List<ArticleTypeDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    public List<ArticleTypeDTO> getByLanguage(Language lang) {
        List<ArticleTypeDTO> dtoList = new LinkedList<>();
        articleTypeRepository.findAllByVisibleTrue().forEach(entity -> {
            dtoList.add(toDTO(entity, lang));
        });
        return dtoList;
    }

    public List<ArticleTypeDTO> getByLanguage2(Language lang) {
        List<ArticleTypeDTO> dtoList = new LinkedList<>();
        switch (lang) {
            case en: {
                dtoList = articleTypeRepository.findAllByNameEn();
            }
            case uz: {
                dtoList = articleTypeRepository.findAllByNameUz();
            }
            case ru: {
                dtoList = articleTypeRepository.findAllByNameRu();
            }
        }
        return dtoList;
    }

    /*public List<ArticleTypeDTO> getByLanguage3(Language lang) {
        List<ArticleTypeDTO> dtoList = new LinkedList<>();
        articleTypeRepository.findAllByLang(lang.name()).forEach(mapper -> {
            ArticleTypeDTO dto = new ArticleTypeDTO();
            dto.setId(mapper.getId());
            dto.setOrderNumber(mapper.getOrderNumber());
            dto.setName(mapper.getName());
            dtoList.add(dto);
        });
        return dtoList;
    }*/

    public ArticleTypeDTO toDTO(ArticleTypeEntity entity) {
        ArticleTypeDTO dto = new ArticleTypeDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    private ArticleTypeDTO toDTO(ArticleTypeEntity entity, Language lang) {
        ArticleTypeDTO dto = new ArticleTypeDTO();
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
