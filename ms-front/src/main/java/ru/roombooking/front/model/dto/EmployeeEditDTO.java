package ru.roombooking.front.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class EmployeeEditDTO {
    private EmployeeDTO employeeDTO;
    private ProfileDTO profile;
    private List<DepartmentDTO> departmentList;
}