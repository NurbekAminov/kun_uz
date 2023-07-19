package com.example.kun_uz.service;

import com.example.kun_uz.dto.ProfileDTO;
import com.example.kun_uz.dto.RegionDTO;
import com.example.kun_uz.entity.ProfileEntity;
import com.example.kun_uz.entity.RegionEntity;
import com.example.kun_uz.enums.Language;
import com.example.kun_uz.exp.ItemNotFoundException;
import com.example.kun_uz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public RegionDTO add(RegionDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setCreatedDate(LocalDateTime.now());
        regionRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean update(Integer id, RegionDTO dto) {
        int effect = regionRepository.updateById(id, dto.getOrderNumber(),
                dto.getNameUz(), dto.getNameRu(), dto.getNameEn());
        return effect == 1;
    }


    public Boolean deleteById(Integer id) {
        return null;
    }

    public List<RegionDTO> getAll() {
        return null;
    }


    public List<RegionDTO> getByLanguage(Language lang) {
        List<RegionDTO> dtoList = new LinkedList<>();
        regionRepository.findAllByVisibleTrue().forEach(entity -> {
            dtoList.add(toDTO(entity, lang));
        });
        return dtoList;
    }

    public List<RegionDTO> getByLanguage2(Language lang) {
        List<RegionDTO> dtoList = new LinkedList<>();
        switch (lang) {
            case en: {
                dtoList = regionRepository.findAllByNameEn();
            }
            case uz: {
                dtoList = regionRepository.findAllByNameUz();
            }
            case ru: {
                dtoList = regionRepository.findAllByNameRu();
            }
        }
        return dtoList;
    }

    public List<RegionDTO> getByLanguage3(Language lang) {
        List<RegionDTO> dtoList = new LinkedList<>();
        regionRepository.findAllByLang(lang.name()).forEach(mapper -> {
            RegionDTO dto = new RegionDTO();
            dto.setId(mapper.getId());
            dto.setOrderNumber(mapper.getOrderNumber());
            dto.setName(mapper.getName());
            dtoList.add(dto);
        });
        return dtoList;
    }

    private RegionDTO toDTO(RegionEntity entity, Language lang) {
        RegionDTO dto = new RegionDTO();
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
