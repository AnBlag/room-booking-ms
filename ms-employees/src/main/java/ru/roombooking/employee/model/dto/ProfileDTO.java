package ru.roombooking.employee.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ru.roombooking.employee.model.Role;

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