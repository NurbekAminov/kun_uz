package com.example.controller;

import com.example.dto.ArticleDTO;
import com.example.dto.JwtDTO;
import com.example.dto.ProfileDTO;
import com.example.enums.ArticleStatus;
import com.example.enums.ProfileRole;
import com.example.service.ArticleService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /*@DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id, HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.delete(id, jwtDTO.getId()));
    }*/

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") String id, @RequestBody ArticleDTO dto,
                                          @RequestBody ArticleStatus status, HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.PUBLISHER);
        return ResponseEntity.ok(articleService.changeStatus(id, dto, status, jwtDTO.getId()));
    }

    @GetMapping(value = "/get/5")
    public ResponseEntity<List<ArticleDTO>> getLast5(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleService.getLast(id, 5));
    }

    @GetMapping(value = "/get/3")
    public ResponseEntity<List<ArticleDTO>> getLast3(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleService.getLast(id, 3));
    }

}
