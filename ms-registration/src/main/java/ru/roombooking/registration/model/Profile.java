package ru.roombooking.registration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class Profile {
    private Long id;
    private String login;
    private String password;
    private Role role;
    private Boolean isActive;
    private Boolean accountNonLocked;
}