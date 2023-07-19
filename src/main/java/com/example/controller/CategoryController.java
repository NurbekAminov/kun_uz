package com.example.controller;

import com.example.dto.CategoryDTO;
import com.example.dto.RegionDTO;
import com.example.enums.Language;
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
    private CategoryService categoryService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<?> create(@RequestBody CategoryDTO category) {
        CategoryDTO response = categoryService.add(category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody CategoryDTO category, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(categoryService.update(id, category));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        Boolean response = categoryService.delete(id);
        if (response) {
            return ResponseEntity.ok("Region deleted");
        }
        return ResponseEntity.badRequest().body("Region Not Found");
    }
    @GetMapping("/list")
    public List<CategoryDTO> all() {
        return categoryService.getAll();
    }
    @GetMapping(value = "/lang")
    public List<CategoryDTO> getByLanguage(@RequestParam(value = "lang", defaultValue = "uz") Language lang) {
        return categoryService.getByLanguage2(lang);
    }
}
