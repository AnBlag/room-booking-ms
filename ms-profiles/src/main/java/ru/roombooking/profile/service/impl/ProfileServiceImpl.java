package ru.roombooking.profile.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.roombooking.profile.exception.ProfileNotFoundException;
import ru.roombooking.profile.model.Profile;
import ru.roombooking.profile.repository.ProfileRepository;
import ru.roombooking.profile.service.ProfileService;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    @Value("${sql.query.sql-change-account-non-locked}")
    private String SQL_CHANGE_ACCOUNT_NON_LOCKED;
    private final JdbcTemplate jdbcTemplate;
    private final ProfileRepository profileRepository;

    @Override
    public Profile save(Profile model) {
        log.info("Сохранение профиля");
        return profileRepository.save(model);
    }

    @Override
    public List<Profile> findAll() {
        log.info("Поиск всех профилей");
        return profileRepository.findAll();
    }

    @Override
    public Profile deleteById(Long aLong) {
        log.info("Удаление профиля по ID");
        Profile profile = profileRepository.findById(aLong)
                .orElseThrow(() -> new ProfileNotFoundException("Не найден ID"));
        profileRepository.deleteById(aLong);
        log.info("Удаление профиля по ID успешно завершена");
        return profile;
    }

    @Override
    public Profile deleteByProfile(Profile profile) {
        log.info("Удаление профиля");
        profileRepository.delete(profile);
        log.info("Удаление профиля успешно завершена");
        return profile;
    }

    @Override
    public Profile findByLogin(String login) {
        log.info("Поиск профиля по логину");
        return profileRepository.findByLogin(login)
                .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден"));
    }

    @Override
    public Profile findById(Long profId) {
        log.info("Поиск профиля по ID");
        return profileRepository.findById(profId)
                .orElseThrow(() -> new ProfileNotFoundException("Профиль не найден"));
    }

    @Override
    public boolean doesProfileExist(String login) {
        log.info("Проверка существования профиля");
        return profileRepository.findByLogin(login).isPresent();
    }

    @Override
    public Profile changeAccountNonLocked(Boolean account_non_locked, Long id) {
        log.info("Смена активности профиля");
        jdbcTemplate.update(SQL_CHANGE_ACCOUNT_NON_LOCKED, account_non_locked, id);
        return profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Не найден ID"));
    }

    @Override
    public Profile update(Profile model, Long id) {
        log.info("Обновление профиля");
        model.setId(id);
        return profileRepository.save(model);
    }
}