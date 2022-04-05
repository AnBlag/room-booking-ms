package ru.roombooking.history.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.history.exception.VscRoomBadRequestException;
import ru.roombooking.history.model.VscRoom;
import ru.roombooking.history.repository.VscRoomRepository;
import ru.roombooking.history.service.VscRoomService;
import static ru.roombooking.history.exception.ExceptionMessage.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class VscRoomServiceImpl implements VscRoomService {
    private final VscRoomRepository vscRepository;
    private final JdbcTemplate jdbcTemplate;
    private String SQL_BATCH_UPDATE_VSC_ROOM = "update vsc_room set is_active=?, number_room=? where id=?";

    @Override
    public VscRoom save(VscRoom model) {
        log.info("Сохранение новой комнаты");
        return vscRepository.save(model);
    }

    @Override
    public VscRoom update(VscRoom model, Long id) {
        log.info("Обновление комнаты");
        model.setId(id);
        return vscRepository.save(model);
    }

    @Override
    public List<VscRoom> findAll() {
        log.info("Поиск всех комнат");
        return vscRepository.findAll();
    }

    @Override
    public VscRoom deleteById(Long aLong) {
        log.info("Удаление комнаты по ID");
        VscRoom vscRoom = vscRepository.findById(aLong)
                .orElseThrow(() -> new VscRoomBadRequestException(ID_NOT_FOUND.getMessage()));
        vscRepository.deleteById(aLong);
        log.info("Удаление комнаты по ID успешно завершено");
        return vscRoom;
    }

    @Override
    public VscRoom findById(Long aLong) {
        log.info("Поиск комнаты по ID");
        return vscRepository.findById(aLong)
                .orElseThrow(() -> new VscRoomBadRequestException(ID_NOT_FOUND.getMessage()));
    }

    @Override
    public VscRoom findByNumberRoomId(Long number) {
        log.info("Поиск по номеру комнаты");
        return vscRepository.findByNumberRoom(number)
                .orElseThrow(() -> new VscRoomBadRequestException(NUMBER_ROOM_ID_NOT_FOUND.getMessage()));
    }

    @Transactional
    @Override
    public void batchUpdateVscRoom(List<VscRoom> vscRoomList) {
        log.info("Обновление всех комнат");
        jdbcTemplate.batchUpdate(SQL_BATCH_UPDATE_VSC_ROOM,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setBoolean(1, vscRoomList.get(i).getIsActive());
                        ps.setLong(2, vscRoomList.get(i).getNumberRoom());
                        ps.setLong(3, vscRoomList.get(i).getId());
                    }

                    @Override
                    public int getBatchSize() {
                        return vscRoomList.size();
                    }
                });
    }
}