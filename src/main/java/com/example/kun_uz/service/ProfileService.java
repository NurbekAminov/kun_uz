package com.example.kun_uz.service;

import com.example.kun_uz.dto.ProfileDTO;
import com.example.kun_uz.entity.ProfileEntity;
import com.example.kun_uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
