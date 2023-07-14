package com.example.kun_uz.controller;

import com.example.kun_uz.dto.ProfileDTO;
import com.example.kun_uz.dto.RegionDTO;
import com.example.kun_uz.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody RegionDTO regionDTO) {
        RegionDTO response = regionService.add(regionDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody RegionDTO region, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(regionService.update(id, region));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        Boolean response = regionService.delete(id);
        if (response) {
            return ResponseEntity.ok("Region deleted");
        }
        return ResponseEntity.badRequest().body("Region Not Found");
    }

    @GetMapping("/list")
    public List<RegionDTO> all() {
        return regionService.getAll();
    }

    @GetMapping(value = "/language")
    public List<RegionDTO> getByLanguage(@RequestParam("language") String language) {
        return regionService.getByLanguage(language);
    }

}
