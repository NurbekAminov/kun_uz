package com.example.repository;

import com.example.entity.ArticleTypesEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleTypesRepository extends CrudRepository<ArticleTypesEntity, Integer> {
    @Transactional
    @Modifying
    @Query("delete from ArticleTypesEntity  as a where a.articleId =:articleId")
    int deleteByArticleId(@Param("articleId") String articleId);

    @Query("SELECT  a.articleTypeId from ArticleTypesEntity  as a where a.articleId =:articleId ")
    List<Integer> getAllArticleTypeIdList(@Param("articleId") String articleId);

    @Transactional
    @Modifying
    @Query("delete from ArticleTypesEntity  as a where a.articleId =:articleId  and a.articleTypeId=:articleTypeId")
    int deleteByArticleIdAndTypeId(@Param("articleId") String articleId,
                                   @Param("articleTypeId") Integer articleTypeId);
}
