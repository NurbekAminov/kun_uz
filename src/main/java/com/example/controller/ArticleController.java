package com.example.controller;

import com.example.dto.ArticleDTO;
import com.example.dto.JwtDTO;
import com.example.enums.ArticleStatus;
import com.example.enums.ProfileRole;
import com.example.service.ArticleService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.PageImpl;
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
    public ResponseEntity<?> update(@PathVariable("id") String articleId,
                                    @RequestBody ArticleDTO dto, HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.update(articleId, dto, jwtDTO.getId()));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id, HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.delete(id, jwtDTO.getId()));
    }

    @PutMapping(value = "/status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") String articleId, @RequestBody ArticleDTO dto,
                                          @RequestBody ArticleStatus status, HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.PUBLISHER);
        return ResponseEntity.ok(articleService.changeStatus(articleId, dto, status, jwtDTO.getId()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<ArticleDTO>> getLast5(@PathVariable("id") Integer articleTypeId) {
        return ResponseEntity.ok(articleService.getLast(articleTypeId, 5));
    }

    @GetMapping(value = "/get/3")
    public ResponseEntity<List<ArticleDTO>> getLast3(@RequestParam("id") Integer articleTypeId) {
        return ResponseEntity.ok(articleService.getLast(articleTypeId, 3));
    }

    @GetMapping(value = "/get/8")
    public ResponseEntity<List<ArticleDTO>> getLast8(@RequestParam("list") List<Integer> list) {
        return ResponseEntity.ok(articleService.getLast8(list));
    }

    @GetMapping(value = "/get/4/notexcept")
    public ResponseEntity<List<ArticleDTO>> getLast4NotExcept(@RequestParam("articleTypeId") Integer articleTypeId,
                                                              @RequestParam("articleId") String articleId) {
        return ResponseEntity.ok(articleService.getLast4NotExcept(articleId, articleTypeId));
    }

    @GetMapping(value = "/get/4/mostview")
    public ResponseEntity<List<ArticleDTO>> getLast4MostRead() {
        return ResponseEntity.ok(articleService.getLast4MostRead());
    }

    @GetMapping(value = "/get/5/type/region")
    public ResponseEntity<List<ArticleDTO>> getLast5ByTypeAndRegion(@RequestParam("articleTypeId") Integer articleTypeId,
                                                                    @RequestParam("regionId") Integer regionId) {
        return ResponseEntity.ok(articleService.getLast5ByTypeAndRegion(articleTypeId, regionId));
    }

    @GetMapping(value = "/pagination/category")
    public ResponseEntity<PageImpl<ArticleDTO>> paginationByCategory(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                                                 @RequestParam(value = "level") Integer category) {
        PageImpl<ArticleDTO> response = articleService.articlePaginationByCategory(category, page - 1, size);
        return ResponseEntity.ok(response);
    }
}
