package com.example.dto;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDateTime;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;

    @NotBlank(message = "Name required")
    private String name;
    @NotBlank(message = "Surname required")
    private String surname;

    @NotBlank(message = "Email required")
    private String email;
    @NotBlank(message = "phone required")
    private String phone;
    @NotBlank(message = "Password required")
    private String password;
    @NotNull
    private ProfileRole role;

    private ProfileStatus status;
    private Boolean visible;
    private LocalDateTime createdDate;
//    private Integer photoId;
    private String jwt;
}
