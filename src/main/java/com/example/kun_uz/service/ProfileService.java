package com.example.kun_uz.service;

import com.example.kun_uz.dto.ProfileDTO;
import com.example.kun_uz.entity.ProfileEntity;
import com.example.kun_uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    public ProfileDTO add(ProfileDTO dto) {
        ProfileEntity entity = toEntity(dto);

        profileRepository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }
    public Boolean update(Integer id, ProfileDTO profile) {
        int effectedRows = profileRepository.update(id, profile.getName(), profile.getSurname(), profile.getEmail(), profile.getPhone(), profile.getPassword(), profile.getPhotoId());
        return effectedRows != 0;
    }
    public PageImpl<ProfileDTO> profilePagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProfileEntity> pageObj = profileRepository.findAll(pageable);

        List<ProfileEntity> entityList = pageObj.getContent();
        Long totalCount = pageObj.getTotalElements();

        List<ProfileDTO> studentDTOList = new LinkedList<>();
        entityList.forEach(entity -> {
            studentDTOList.add(toDTO(entity));
        });

        PageImpl<ProfileDTO> pageImpl = new PageImpl<ProfileDTO>(studentDTOList, pageable, totalCount);
        return pageImpl;
    }
    public Boolean delete(Integer id) {
        Optional<ProfileEntity> optional = profileRepository.findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        profileRepository.deleteById(id);
        return true;
    }
    public ProfileEntity toEntity(ProfileDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }
    public ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setPassword(entity.getPassword());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
