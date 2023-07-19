package com.example.controller;

import com.example.dto.CategoryDTO;
import com.example.dto.RegionDTO;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CategoryDTO category) {
        CategoryDTO response = service.add(category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody CategoryDTO category, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.update(id, category));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        Boolean response = service.delete(id);
        if (response) {
            return ResponseEntity.ok("Region deleted");
        }
        return ResponseEntity.badRequest().body("Region Not Found");
    }
    @GetMapping("/list")
    public List<CategoryDTO> all() {
        return service.getAll();
    }
    @GetMapping(value = "/language")
    public List<CategoryDTO> getByLanguage(@RequestParam("language") String language) {
        return service.getByLanguage(language);
    }
}
