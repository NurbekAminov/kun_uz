package com.example.repository;

import com.example.dto.ArticleTypeDTO;
import com.example.entity.ArticleTypeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleTypeRepository extends CrudRepository<ArticleTypeEntity, Integer>,
        PagingAndSortingRepository<ArticleTypeEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update ArticleTypeEntity as r set r.orderNumber =:orderNumber,r.nameUz =:nameUz, r.nameRu =:nameRu, r.nameEn =:nameEn where r.id =:id")
    int updateById(@Param("id") Integer id,
                   @Param("orderNumber") Integer order_number,
                   @Param("nameUz") String nameUz,
                   @Param("nameRu") String nameRu,
                   @Param("nameEn") String nameEn);

    List<ArticleTypeEntity> findAllByVisibleTrue();

    @Query("select  new com.example.dto.ArticleTypeDTO(id, orderNumber, nameUz) from ArticleTypeEntity where visible = true order by orderNumber")
    List<ArticleTypeDTO> findAllByNameUz();

    @Query("select  new com.example.dto.ArticleTypeDTO(id, orderNumber, nameEn) from ArticleTypeEntity where visible = true order by orderNumber")
    List<ArticleTypeDTO> findAllByNameEn();

    @Query("select  new com.example.dto.ArticleTypeDTO(id, orderNumber, nameRu) from ArticleTypeEntity where visible = true order by orderNumber")
    List<ArticleTypeDTO> findAllByNameRu();

    /*@Query(value = "select id, order_number, " +
            "CASE :lang " +
            "   WHEN 'en' THEN name_en " +
            "   WHEN 'ru' THEN name_ru" +
            "   ELSE name_uz" +
            " END as name" +
            " from region where visible = true order by order_number", nativeQuery = true)
    List<RegionMapper> findAllByLang(@Param("lang") String lang);*/
}
