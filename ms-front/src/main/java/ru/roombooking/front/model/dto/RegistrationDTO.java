package ru.roombooking.front.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.roombooking.front.config.security.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class RegistrationDTO {
    private Long id;
    private String surname;
    private String name;
    private String middleName;
    private String phone;
    private String email;
    private String login;
    private String password;
    private Boolean isActive;
    private Boolean accountNonLocked;
    private Role role;
    private Long profileId;
    private Long departmentId;
}