package ru.roombooking.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.roombooking.employee.model.Employee;
import ru.roombooking.employee.model.dto.EmployeeDTO;


//@Component
@Mapper(componentModel = "spring")
public interface EmployeeMyMapper extends VCMapper<Employee, EmployeeDTO> {
    @Mappings({
            @Mapping(target="id", source="employee.id"),
            @Mapping(target="surname", source="employee.surname"),
            @Mapping(target="name", source="employee.name"),
            @Mapping(target="middleName", source="employee.middleName"),
            @Mapping(target="isActive", source="employee.isActive"),
            @Mapping(target="profileId", source="employee.profileId"),
            @Mapping(target="phone", source="employee.phone"),
            @Mapping(target="email", source="employee.email"),
            @Mapping(target="departmentId", source="employee.departmentId")
    })
    EmployeeDTO toDTO(Employee employee);

    @Mappings({
            @Mapping(target="id", source="employeeDTO.id"),
            @Mapping(target="surname", source="employeeDTO.surname"),
            @Mapping(target="name", source="employeeDTO.name"),
            @Mapping(target="middleName", source="employeeDTO.middleName"),
            @Mapping(target="isActive", source="employeeDTO.isActive"),
            @Mapping(target="profileId", source="employeeDTO.profileId"),
            @Mapping(target="phone", source="employeeDTO.phone"),
            @Mapping(target="email", source="employeeDTO.email"),
            @Mapping(target="departmentId", source="employeeDTO.departmentId")
    })
    Employee toModel(EmployeeDTO employeeDTO);

}