package com.example.repository;

import com.example.entity.ArticleEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Integer>,
        PagingAndSortingRepository<ArticleEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update ArticleEntity as r set r.title =:title,r.description =:description, r.content =:content, r.sharedCount =:sharedCount, r.imageId =:imageId, r.regionId =:regionId, r.categoryId =:categoryId where r.id =:id")
    int updateById(@Param("id") Integer id,
                   @Param("title") Integer title,
                   @Param("description") String description,
                   @Param("content") String content,
                   @Param("sharedCount") String shared_count,
                   @Param("imageId") String image_id,
                   @Param("regionId") String region_id,
                   @Param("categoryId") String category_id);
}
