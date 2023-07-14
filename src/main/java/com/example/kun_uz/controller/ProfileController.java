package com.example.kun_uz.controller;

import com.example.kun_uz.dto.ProfileDTO;
import com.example.kun_uz.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
