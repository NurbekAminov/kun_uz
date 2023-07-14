package com.example.kun_uz.controller;

import com.example.kun_uz.dto.ProfileDTO;
import com.example.kun_uz.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO response = profileService.add(profileDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody ProfileDTO profile, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.update(id, profile));
    }
    @GetMapping(value = "/list")
    public ResponseEntity<PageImpl<ProfileDTO>> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                           @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<ProfileDTO> response = profileService.profilePagination(page - 1, size);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        Boolean response = profileService.delete(id);
        if (response) {
            return ResponseEntity.ok("Profile deleted");
        }
        return ResponseEntity.badRequest().body("Profile Not Found");
    }
}
