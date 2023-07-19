package com.example.repository;

import com.example.dto.CategoryDTO;
import com.example.dto.RegionDTO;
import com.example.entity.CategoryEntity;
import com.example.entity.ProfileEntity;
import com.example.entity.RegionEntity;
import com.example.mapper.CategoryMapper;
import com.example.mapper.RegionMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer>,
        PagingAndSortingRepository<CategoryEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update CategoryEntity as r set r.orderNumber =:orderNumber,r.nameUz =:nameUz, r.nameRu =:nameRu, r.nameEn =:nameEn where r.id =:id")
    int updateById(@Param("id") Integer id,
                   @Param("orderNumber") Integer order_number,
                   @Param("nameUz") String nameUz,
                   @Param("nameRu") String nameRu,
                   @Param("nameEn") String nameEn);
    List<CategoryEntity> findAllByVisibleTrue();

    @Query("select  new com.example.dto.CategoryDTO(id, orderNumber, nameUz) from CategoryEntity where visible = true order by orderNumber")
    List<CategoryDTO> findAllByNameUz();

    @Query("select  new com.example.dto.CategoryDTO(id, orderNumber, nameEn) from CategoryEntity where visible = true order by orderNumber")
    List<CategoryDTO> findAllByNameEn();

    @Query("select  new com.example.dto.CategoryDTO(id, orderNumber, nameRu) from CategoryEntity where visible = true order by orderNumber")
    List<CategoryDTO> findAllByNameRu();

    @Query(value = "select id, order_number, " +
            "CASE :lang " +
            "   WHEN 'en' THEN name_en " +
            "   WHEN 'ru' THEN name_ru" +
            "   ELSE name_uz" +
            " END as name" +
            " from region where visible = true order by order_number", nativeQuery = true)
    List<CategoryMapper> findAllByLang(@Param("lang") String lang);
}
