package ru.roombooking.registration.service;


import ru.roombooking.registration.model.dto.RegistrationDTO;

public interface RegistrationService {
    void saveEmployeeAndProfile(RegistrationDTO model);
    boolean doesUserExist(RegistrationDTO model);
}
