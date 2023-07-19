package com.example.controller;

import com.example.dto.RegionDTO;
import com.example.enums.Language;
import com.example.service.RegionService;
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

    @PostMapping(value = {"", "/"})
    public ResponseEntity<?> create(@RequestBody RegionDTO regionDTO) {
        RegionDTO response = regionService.add(regionDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody RegionDTO region, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(regionService.update(id, region));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
        Boolean result = regionService.deleteById(id);
        if (result) {
            return ResponseEntity.ok("Region deleted!!!");
        }
        return ResponseEntity.badRequest().body("Region not found");
    }

    @GetMapping("/all")
    public List<RegionDTO> all() {
        return regionService.getAll();
    }

    @GetMapping("/lang")
    public ResponseEntity<List<RegionDTO>> getByLan(@RequestParam(value = "lang", defaultValue = "uz") Language lang) {
        return ResponseEntity.ok(regionService.getByLanguage2(lang));
    }

}
