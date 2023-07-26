package com.example.entity;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private ProfileStatus status;

    @Column(name = "role")
    private ProfileRole role;

//    @Column(name = "photo_id")
//    private Integer photoId;

    @Column(name = "profile_id")
    private Integer profileId;
}
