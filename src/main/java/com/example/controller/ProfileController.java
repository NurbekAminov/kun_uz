package com.example.controller;

import com.example.dto.ProfileDTO;
import com.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<?> create(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO response = profileService.create(profileDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody ProfileDTO profile,
                                 @PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.update(id, profile));
    }
    @PutMapping(value = "/detail/{id}")
    public ResponseEntity<Boolean> updateDetail(@RequestBody ProfileDTO dto,
                                                @PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.updateDetail(id, dto));
    }
    @GetMapping(value = "")
    public ResponseEntity<List<ProfileDTO>> getAll() {
        return ResponseEntity.ok(profileService.getList());
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.delete(id));
    }

    // TODO filter
}
