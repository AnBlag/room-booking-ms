package ru.roombooking.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class Department {
    private Long id;
    private String nameDepartment;
    private String position;
}
