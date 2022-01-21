
package ru.roombooking.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

