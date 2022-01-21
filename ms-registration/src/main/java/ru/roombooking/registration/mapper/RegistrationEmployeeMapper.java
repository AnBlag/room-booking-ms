package ru.roombooking.registration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


import ru.roombooking.registration.model.dto.EmployeeDTO;
import ru.roombooking.registration.model.dto.RegistrationDTO;

@Mapper(componentModel = "spring")
public interface RegistrationEmployeeMapper extends VCMapper<EmployeeDTO, RegistrationDTO> {
    @Override
    @Mappings({

            @Mapping(target="surname", source="employeeDTO.surname"),
            @Mapping(target="name", source="employeeDTO.name"),
            @Mapping(target="middleName", source="employeeDTO.middleName"),
            @Mapping(target="profileId", source="employeeDTO.profileId"),
            @Mapping(target="departmentId", source="employeeDTO.departmentId"),
            @Mapping(target="phone", source="employeeDTO.phone"),
            @Mapping(target="email", source="employeeDTO.email")
    })
    RegistrationDTO toDTO(EmployeeDTO employeeDTO);

    @Override
    @Mappings({
            @Mapping(target="surname", source="registrationDTO.surname"),
            @Mapping(target="name", source="registrationDTO.name"),
            @Mapping(target="middleName", source="registrationDTO.middleName"),
            @Mapping(target="profileId", source="registrationDTO.profileId"),
            @Mapping(target="departmentId", source="registrationDTO.departmentId"),
            @Mapping(target="phone", source="registrationDTO.phone"),
            @Mapping(target="email", source="registrationDTO.email")
    })
    EmployeeDTO toModel(RegistrationDTO registrationDTO);
}
