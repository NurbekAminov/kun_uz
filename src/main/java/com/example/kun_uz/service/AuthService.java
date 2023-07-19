package com.example.kun_uz.service;

import com.example.kun_uz.dto.ApiResponseDTO;
import com.example.kun_uz.dto.AuthDTO;
import com.example.kun_uz.dto.ProfileDTO;
import com.example.kun_uz.entity.ProfileEntity;
import com.example.kun_uz.enums.ProfileStatus;
import com.example.kun_uz.repository.ProfileRepository;
import com.example.kun_uz.util.JWTUtil;
import com.example.kun_uz.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;

    public ApiResponseDTO login(AuthDTO dto) {
        // check
        Optional<ProfileEntity> optional = profileRepository.findByPhone(dto.getPhone());
        if (optional.isEmpty()) {
            return new ApiResponseDTO(false, "Login or Password not found");
        }
        ProfileEntity profileEntity = optional.get();
        if (!profileEntity.getPassword().equals(MD5Util.encode(dto.getPassword()))) {
            return new ApiResponseDTO(false, "Login or Password not found");
        }
        if (!profileEntity.getStatus().equals(ProfileStatus.ACTIVE) || !profileEntity.getVisible()) {
            return new ApiResponseDTO(false, "Your status not active. Please contact with support.");
        }

        ProfileDTO response = new ProfileDTO();
        response.setId(profileEntity.getId());
        response.setName(profileEntity.getName());
        response.setSurname(profileEntity.getSurname());
        response.setRole(profileEntity.getRole());
        response.setPhone(profileEntity.getPhone());
        response.setJwt(JWTUtil.encode(profileEntity.getId(), profileEntity.getRole()));
        return new ApiResponseDTO(true, response);
    }
}
