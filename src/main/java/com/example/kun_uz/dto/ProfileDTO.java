package com.example.kun_uz.dto;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDateTime;
@Getter
@Setter
public class ProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private Integer phone;
    private String password;
    private Enum status;
    private Enum role;
    private Boolean visible;
    private LocalDateTime createdDate;
    private URL photoId;
}
