package com.example.repository;

import com.example.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer>,
        PagingAndSortingRepository<ProfileEntity, Integer> {

    Optional<ProfileEntity> findByEmail(String email);
    Optional<ProfileEntity> findByPhone(String phone);
    List<ProfileEntity> findAllByVisibleTrue();

    @Transactional
    @Modifying
    @Query("update ProfileEntity as s set s.name =:name, s.surname=:surname, s.email=:email, s.phone=:phone, s.password=:password where s.id  =:id ")
    int update(@Param("id") Integer id, @Param("name") String name, @Param("surname") String surname, @Param("email") String email, @Param("phone") Integer phone, @Param("password") String password);

    @Transactional
    @Modifying
    @Query("update ProfileEntity  set name =?2, surname =?3 where id =?1")
    int updateDetail(Integer id, String name, String surname);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set visible = false where id =:id")
    int delete(@Param("id") Integer id);
}
