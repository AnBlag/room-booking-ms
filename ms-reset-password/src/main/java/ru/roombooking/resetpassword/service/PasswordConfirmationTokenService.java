package ru.roombooking.resetpassword.service;

import ru.roombooking.resetpassword.model.PasswordConfirmationToken;

public interface PasswordConfirmationTokenService {
    PasswordConfirmationToken findByToken(String token);

    PasswordConfirmationToken save(PasswordConfirmationToken passwordConfirmationToken);

    PasswordConfirmationToken deleteById(Long aLong);
}