package com.example.kun_uz.repository;

import com.example.kun_uz.entity.CategoryEntity;
import com.example.kun_uz.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer>,
        PagingAndSortingRepository<CategoryEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update CategoryEntity as s set s.nameUz =:nameUz, s.nameRu=:nameRu, s.nameEng=:nameEng where s.id  =:id ")
    int update(@Param("id") Integer id, @Param("key") Integer key, @Param("nameUz") String nameUz, @Param("nameRu") String nameRu, @Param("nameEng") String nameEng);
}
