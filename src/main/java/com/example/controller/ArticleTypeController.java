package com.example.controller;

import com.example.dto.ArticleTypeDTO;
import com.example.enums.Language;
import com.example.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articletype")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<?> create(@RequestBody ArticleTypeDTO articleTypeDTO) {
        ArticleTypeDTO response = articleTypeService.add(articleTypeDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody ArticleTypeDTO article, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleTypeService.update(id, article));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
        Boolean result = articleTypeService.deleteById(id);
        if (result) {
            return ResponseEntity.ok("Region deleted!!!");
        }
        return ResponseEntity.badRequest().body("Region not found");
    }

    @GetMapping("/all")
    public List<ArticleTypeDTO> all() {
        return articleTypeService.getAll();
    }

    @GetMapping("/lang")
    public ResponseEntity<List<ArticleTypeDTO>> getByLanguage(@RequestParam(value = "lang", defaultValue = "uz") Language lang) {
        return ResponseEntity.ok(articleTypeService.getByLanguage2(lang));
    }
}
