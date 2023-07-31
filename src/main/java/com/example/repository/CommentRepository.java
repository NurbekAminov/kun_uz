package com.example.repository;

import com.example.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<ArticleEntity, String> {
}
