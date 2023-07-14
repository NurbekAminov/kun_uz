package com.example.kun_uz.service;

import com.example.kun_uz.dto.ProfileDTO;
import com.example.kun_uz.dto.RegionDTO;
import com.example.kun_uz.entity.ProfileEntity;
import com.example.kun_uz.entity.RegionEntity;
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
        RegionEntity entity = toEntity(dto);

        regionRepository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }
    public Boolean update(Integer id, RegionDTO region) {
        int effectedRows = regionRepository.update(id, region.getKey(), region.getNameUz(), region.getNameRu(), region.getNameEng());
        return effectedRows != 0;
    }
    public Boolean delete(Integer id) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        regionRepository.deleteById(id);
        return true;
    }
    public List<RegionDTO> getAll() {
        Iterable<RegionEntity> iterable = regionRepository.findAll();
        List<RegionDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }
    public List<RegionDTO> getByLanguage(String language) {
        Iterable<RegionEntity> iterable = regionRepository.findAll();
        List<RegionDTO> dtoList = new LinkedList<>();
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
    public RegionEntity toEntity(RegionDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEng(dto.getNameEng());
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }
    public RegionDTO toDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEng(entity.getNameEng());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public RegionDTO toDTOUz(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setNameUz(entity.getNameUz());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public RegionDTO toDTORu(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setNameRu(entity.getNameRu());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public RegionDTO toDTOEng(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setNameEng(entity.getNameEng());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
