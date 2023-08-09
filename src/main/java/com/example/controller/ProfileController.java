package com.example.controller;

import com.example.dto.JwtDTO;
import com.example.dto.ProfileDTO;
import com.example.enums.ProfileRole;
import com.example.service.ProfileService;
import com.example.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<?> create(@RequestBody ProfileDTO dto,
                                    @RequestHeader("Authorization") String authToken) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(authToken, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(profileService.create(dto, jwtDTO.getId()));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Boolean> update(@RequestBody ProfileDTO dto,
                                          @PathVariable("id") Integer id,
                                          @RequestHeader("Authorization") String authToken) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(authToken, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(profileService.update(id, dto));
    }
    @PutMapping(value = "/detail")
    public ResponseEntity<Boolean> updateDetail(@RequestBody ProfileDTO dto,
                                                @RequestHeader("Authorization") String authToken) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(authToken, null);
        return ResponseEntity.ok(profileService.updateDetail(jwtDTO.getId(), dto));
    }
    @GetMapping(value = "")
    public ResponseEntity<List<ProfileDTO>> getAll(@RequestHeader("Authorization") String authToken) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(authToken, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(profileService.getList());
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                          @RequestHeader("Authorization") String authToken) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(authToken, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(profileService.delete(id));
    }

    // TODO filter
}
