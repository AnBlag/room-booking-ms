package ru.roombooking.resetpassword.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class ProfileDTO {
    private Long id;
    private String login;
    private String password;
    private Role role;
    private Boolean isActive;
    private Boolean accountNonLocked;
}