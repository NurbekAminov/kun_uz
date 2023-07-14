package com.example.kun_uz.repository;

import com.example.kun_uz.entity.ProfileEntity;
import com.example.kun_uz.entity.RegionEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;

public interface RegionRepository extends CrudRepository<RegionEntity, Integer>,
        PagingAndSortingRepository<RegionEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update RegionEntity as s set s.nameUz =:nameUz, s.nameRu=:nameRu, s.nameEng=:nameEng where s.id  =:id ")
    int update(@Param("id") Integer id, @Param("key") Integer key, @Param("nameUz") String nameUz, @Param("nameRu") String nameRu, @Param("nameEng") String nameEng);
}
