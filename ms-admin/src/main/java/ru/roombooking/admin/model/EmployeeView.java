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
public class EmployeeView {
    private Long id;
    private String name;
    private String surname;
    private String middleName;
    private String  phone;
    private String email;
    private Boolean banned;
}
