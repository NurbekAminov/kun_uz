package com.example.kun_uz.repository;

import com.example.kun_uz.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer>,
        PagingAndSortingRepository<ProfileEntity, Integer> {

    @Transactional
    @Modifying
    @Query("update ProfileEntity as s set s.name =:name, s.surname=:surname, s.email=:email, s.phone=:phone, s.password=:password, s.photoId=:photoId where s.id  =:id ")
    int update(@Param("id") Integer id, @Param("name") String name, @Param("surname") String surname, @Param("email") String email, @Param("phone") Integer phone, @Param("password") String password, @Param("photoId") URL photoId);
}