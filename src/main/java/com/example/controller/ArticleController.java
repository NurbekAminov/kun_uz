package com.example.controller;

import com.example.dto.ArticleDTO;
import com.example.dto.JwtDTO;
import com.example.enums.ProfileRole;
import com.example.service.ArticleService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private ArticleService articleService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<?> create(@RequestBody ArticleDTO dto, HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.create(dto, jwtDTO.getId()));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody ArticleDTO dto, HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.update(id, dto, jwtDTO.getId()));
    }


}
