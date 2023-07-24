package com.example.controller;

import com.example.dto.ArticleDTO;
import com.example.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private ArticleService articleService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<?> create(@RequestBody ArticleDTO articleDTO) {
        ArticleDTO response = articleService.add(articleDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@RequestBody ArticleDTO article, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleService.update(id, article));
    }
}
