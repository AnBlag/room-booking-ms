package ru.roombooking.registration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


import ru.roombooking.registration.model.Profile;
import ru.roombooking.registration.model.dto.RegistrationDTO;

@Mapper(componentModel = "spring")
public interface RegistrationProfileMapper extends VCMapper<Profile, RegistrationDTO> {
    @Override
    @Mappings({
            @Mapping(target="id", source="profile.id"),
            @Mapping(target="isActive", source="profile.isActive"),
            @Mapping(target="accountNonLocked", source="profile.accountNonLocked"),
            @Mapping(target="role", source="profile.role"),
            @Mapping(target="login", source="profile.login"),
            @Mapping(target="password", source="profile.password")
    })
    RegistrationDTO toDTO(Profile profile);

    @Override
    @Mappings({
            @Mapping(target="id", source="registrationDTO.id"),
            @Mapping(target="isActive", source="registrationDTO.isActive"),
            @Mapping(target="accountNonLocked", source="registrationDTO.accountNonLocked"),
            @Mapping(target="role", source="registrationDTO.role"),
            @Mapping(target="login", source="registrationDTO.login"),
            @Mapping(target="password", source="registrationDTO.password")
    })
    Profile toModel(RegistrationDTO registrationDTO);
}
