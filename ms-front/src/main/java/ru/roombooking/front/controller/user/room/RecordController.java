package ru.roombooking.front.controller.user.room;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.front.exception.RecordTableRequestException;
import ru.roombooking.front.feign.RecordTableFeignClient;
import ru.roombooking.front.model.dto.RecordTableDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/record")
public class RecordController {
    private final RecordTableFeignClient recordTableFeignClient;

    @GetMapping("/")
    public ResponseEntity<List<RecordTableDTO>> findAll() {
        log.info("Поиск всех бронирований");
        try {
            return ResponseEntity.ok(recordTableFeignClient.findAll());
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
    }

    @GetMapping("/{index}")
    public ResponseEntity<List<RecordTableDTO>> findByIndex(@PathVariable String index) {
        log.info("Поиск всех бронирований по индексу комнаты");
        try {
            return ResponseEntity.ok(recordTableFeignClient.findByIndex(index));
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
    }

    @PostMapping("/save/")
    public ResponseEntity<RecordTableDTO> saveRecord(@RequestBody RecordTableDTO recordTableDTO) {
        log.info("Сохранение бронирования");
        try {
            return ResponseEntity.ok(recordTableFeignClient.saveRecord(recordTableDTO, getUserAuth().getUsername()));
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
    }

    @PutMapping("/update/")
    public ResponseEntity<RecordTableDTO> updateRecord(@RequestBody RecordTableDTO recordTableDTO) {
        log.info("Обновление бронирования");
        try {
            return ResponseEntity.ok(recordTableFeignClient.updateRecord(recordTableDTO, getUserAuth().getUsername()));
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<RecordTableDTO> deleteRecord(@RequestBody RecordTableDTO recordTableDTO) {
        log.info("Удаление бронирования");
        try {
            return ResponseEntity.ok(recordTableFeignClient.deleteRecord(recordTableDTO, getUserAuth().getUsername()));
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<RecordTableDTO>> findAllByEmployeeFullNameAndRecordAndIsActiveAndNumberRoom() {
        log.info("Поиск бронирований по ФИО, дате, времени, активности и номеру комнаты");
        try {
            return ResponseEntity.ok(recordTableFeignClient
                    .findAllByEmployeeFullNameAndRecordAndIsActiveAndNumberRoom());
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
    }

    private User getUserAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}