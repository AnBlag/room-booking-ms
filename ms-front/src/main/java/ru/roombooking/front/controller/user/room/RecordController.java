package ru.roombooking.front.controller.user.room;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/record")
public class RecordController {
    private final RecordTableFeignClient recordTableFeignClient;

    @GetMapping("/")
    public ResponseEntity<List<RecordTableDTO>> findAll() {
        try {
            return ResponseEntity.ok(recordTableFeignClient.findAll());
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
    }

    @GetMapping("/{index}")
    public ResponseEntity<List<RecordTableDTO>> findByIndex(@PathVariable String index) {
        try {
            return ResponseEntity.ok(recordTableFeignClient.findByIndex(index));
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
    }

    @PostMapping("/save/")
    public ResponseEntity<RecordTableDTO> saveRecord(@RequestBody RecordTableDTO recordTableDTO) {
        try {
            return ResponseEntity.ok(recordTableFeignClient.saveRecord(recordTableDTO, getUserAuth().getUsername()));
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
    }

    @PutMapping("/update/")
    public ResponseEntity<RecordTableDTO> updateRecord(@RequestBody RecordTableDTO recordTableDTO) {
        try {
            return ResponseEntity.ok(recordTableFeignClient.updateRecord(recordTableDTO, getUserAuth().getUsername()));
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<RecordTableDTO> deleteRecord(@RequestBody RecordTableDTO recordTableDTO) {
        try {
            return ResponseEntity.ok(recordTableFeignClient.deleteRecord(recordTableDTO, getUserAuth().getUsername()));
        } catch (FeignException e) {
            throw new RecordTableRequestException();
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<RecordTableDTO>> findAllByEmployeeFullNameAndRecordAndIsActiveAndNumberRoom() {
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