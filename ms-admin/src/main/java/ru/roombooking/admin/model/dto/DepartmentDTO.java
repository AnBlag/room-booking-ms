package ru.roombooking.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class DepartmentDTO {
    private Long id;
    private String nameDepartment;
    private String position;
}