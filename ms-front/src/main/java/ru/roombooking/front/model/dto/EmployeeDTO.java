package ru.roombooking.front.model.dto;

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
public class EmployeeDTO {
    private Long id;
    private String surname;
    private String name;
    private String middleName;
    private String phone;
    private String email;
    private Boolean isActive;
    private Long profileId;
    private Long departmentId;
}