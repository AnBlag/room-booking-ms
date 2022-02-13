package ru.roombooking.resetpassword.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.resetpassword.exception.TokenDeleteException;
import ru.roombooking.resetpassword.exception.TokenNotFoundException;
import ru.roombooking.resetpassword.exception.TokenSaveException;
import ru.roombooking.resetpassword.model.PasswordConfirmationToken;
import ru.roombooking.resetpassword.repository.PasswordConfirmationTokenRepository;
import ru.roombooking.resetpassword.service.PasswordConfirmationTokenService;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Slf4j
public class PasswordConfirmationTokenServiceImpl implements PasswordConfirmationTokenService {
    private final PasswordConfirmationTokenRepository passwordConfirmationTokenRepository;

    @Override
    @Transactional(readOnly = true)
    public PasswordConfirmationToken findByToken(String token) {
        log.info("Поиск токена");
        return passwordConfirmationTokenRepository.findByToken(token)
                .orElseThrow(TokenNotFoundException::new);
    }

    @Override
    @Transactional(rollbackFor = TokenSaveException.class)
    public PasswordConfirmationToken save(PasswordConfirmationToken passwordConfirmationToken) {
        log.info("Сохранение токена");
        return passwordConfirmationTokenRepository.save(passwordConfirmationToken);
    }

    @Override
    @Transactional(rollbackFor = TokenDeleteException.class)
    public PasswordConfirmationToken deleteById(Long aLong) {
        log.info("Удаление токена по ID");
        PasswordConfirmationToken passwordConfirmationToken = passwordConfirmationTokenRepository.findById(aLong)
                .orElseThrow(TokenNotFoundException::new);
        passwordConfirmationTokenRepository.deleteById(aLong);
        return passwordConfirmationToken;
    }
}