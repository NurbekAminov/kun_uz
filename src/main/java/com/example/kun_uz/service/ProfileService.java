package com.example.kun_uz.service;

import com.example.kun_uz.dto.ProfileDTO;
import com.example.kun_uz.entity.ProfileEntity;
import com.example.kun_uz.enums.ProfileStatus;
import com.example.kun_uz.exp.AppBadRequestException;
import com.example.kun_uz.repository.ProfileRepository;
import com.example.kun_uz.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    public ProfileDTO create(ProfileDTO dto) {
        // TODO checking
        isValidProfile(dto);
        Optional<ProfileEntity> profileByEmail = profileRepository.findByEmail(dto.getEmail());
        if (profileByEmail.isPresent()) {
            throw new AppBadRequestException("Email already exists");
        }
        Optional<ProfileEntity> profileByPhone = profileRepository.findByPhone(dto.getPhone());
        if (profileByPhone.isPresent()) {
            throw new AppBadRequestException("Phone already exits");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(MD5Util.encode(dto.getPassword()));
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole(dto.getRole());
        profileRepository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public Boolean update(Integer profileId, ProfileDTO dto) {
        isValidProfile(dto); // check
        ProfileEntity entity = get(profileId); // get
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        profileRepository.save(entity);
        return true;
    }
    public List<ProfileDTO> getList() {
//        List<ProfileEntity> entityList = profileRepository.findAllByVisibleTrue();
//        List<ProfileDTO> dtoList = new LinkedList<>();
//        entityList.forEach(entity -> {
//            dtoList.add(toDTO(entity));
//        });
//        return dtoList;
        return profileRepository.findAllByVisibleTrue().stream().map(this::toDTO)
                .collect(Collectors.toList());
    }
    public boolean delete(Integer id) {
        int effectedRows = profileRepository.delete(id);
        return effectedRows == 1;
    }
    void isValidProfile(ProfileDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank() || dto.getName().length() < 3) {
            throw new AppBadRequestException("Name required");
        }
        // ....
        // phone ..
    }
    public ProfileEntity get(Integer profileId) {
        return profileRepository.findById(profileId).orElseThrow(() -> {
            throw new AppBadRequestException("Profile not found");
        });
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
