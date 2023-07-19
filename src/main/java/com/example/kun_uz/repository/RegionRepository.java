package com.example.kun_uz.repository;

import com.example.kun_uz.dto.RegionDTO;
import com.example.kun_uz.entity.ProfileEntity;
import com.example.kun_uz.entity.RegionEntity;
import com.example.kun_uz.mapper.RegionMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.net.URL;
import java.util.List;

public interface RegionRepository extends CrudRepository<RegionEntity, Integer>,
        PagingAndSortingRepository<RegionEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update RegionEntity as r set r.orderNumber =:orderNumber,r.nameUz =:nameUz, r.nameRu =:nameRu, r.nameEn =:nameEn where r.id =:id")
    int updateById(@Param("id") Integer id,
                   @Param("orderNumber") Integer order_number,
                   @Param("nameUz") String nameUz,
                   @Param("nameRu") String nameRu,
                   @Param("nameEn") String nameEn);

    List<RegionEntity> findAllByVisibleTrue();

    @Query("select  new com.example.dto.RegionDTO(id, orderNumber, nameUz) from RegionEntity where visible = true order by orderNumber")
    List<RegionDTO> findAllByNameUz();

    @Query("select  new com.example.dto.RegionDTO(id, orderNumber, nameEn) from RegionEntity where visible = true order by orderNumber")
    List<RegionDTO> findAllByNameEn();

    @Query("select  new com.example.dto.RegionDTO(id, orderNumber, nameRu) from RegionEntity where visible = true order by orderNumber")
    List<RegionDTO> findAllByNameRu();

    @Query(value = "select id, order_number, " +
            "CASE :lang " +
            "   WHEN 'en' THEN name_en " +
            "   WHEN 'ru' THEN name_ru" +
            "   ELSE name_uz" +
            " END as name" +
            " from region where visible = true order by order_number", nativeQuery = true)
    List<RegionMapper> findAllByLang(@Param("lang") String lang);
}
