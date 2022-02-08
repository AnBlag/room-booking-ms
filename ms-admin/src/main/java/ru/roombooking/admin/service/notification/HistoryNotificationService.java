package ru.roombooking.admin.service.notification;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.roombooking.admin.exception.RecordTableRequestException;
import ru.roombooking.admin.feign.HistoryFeignClient;
import ru.roombooking.admin.model.dto.RecordTableDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HistoryNotificationService {
    private final HistoryFeignClient historyFeignClient;

    public List<RecordTableDTO> records() {
        log.info("Поиск бронирований в истории");
        try {
            return historyFeignClient.findAll();
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
    }

    public RecordTableDTO deleteById(String id) {
        log.info("Удаление бронирования из истории");
        try {
            return historyFeignClient.deleteById(id);
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
    }
}