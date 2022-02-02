package ru.roombooking.admin.service.notification;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.roombooking.admin.exception.RecordTableDeleteException;
import ru.roombooking.admin.exception.RecordTableViewRequestException;
import ru.roombooking.admin.exception.RecordTableUpdateException;
import ru.roombooking.admin.exception.VscRoomRequestException;
import ru.roombooking.admin.feign.RecordTableFeignClient;
import ru.roombooking.admin.feign.RecordTableViewFeignClient;
import ru.roombooking.admin.feign.VscRoomFeignClient;
import ru.roombooking.admin.model.RecordTableView;
import ru.roombooking.admin.model.dto.VscRoomDTO;
import ru.roombooking.admin.model.dto.RecordTableDTO;
import ru.roombooking.admin.model.dto.RecordTableRequest;
import ru.roombooking.admin.model.dto.RecordTableViewListAndVscRoomListDTO;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordTableNotificationService {
    private final RecordTableFeignClient recordTableFeignClient;
    private final VscRoomFeignClient vscRoomFeignClient;
    private final RecordTableViewFeignClient recordTableViewFeignClient;

    public RecordTableViewListAndVscRoomListDTO records(String search) {
        log.info("Поиск бронирований");
        List<RecordTableView> recordTableViewList;
        try {
            if (search != null) {
                log.info("Поиск бронирований по URL");
                recordTableViewList = recordTableViewFeignClient.getRecordTableViewListByURLParams(search);
            } else {
                log.info("Поиск всех бронирований");
                recordTableViewList = recordTableViewFeignClient.findAll();
            }
            try {
                log.info("Поиск всех комнат");
                List<VscRoomDTO> vscRoomList = vscRoomFeignClient.findAll();
                return new RecordTableViewListAndVscRoomListDTO(recordTableViewList, vscRoomList);
            } catch (FeignException e) {
                throw new VscRoomRequestException();
            }
        } catch (FeignException e) {
            throw new RecordTableViewRequestException();
        }
    }

    public RecordTableViewListAndVscRoomListDTO findRecords(RecordTableView findRecord) {
        log.info("Поиск бронирований по параметрам");
        try {
            List<RecordTableView> list = recordTableViewFeignClient.getRecordTableViewListByRecordTableViewParams(findRecord);
            try {
                log.info("Поиск всех комнат");
                List<VscRoomDTO> vscRoomList = vscRoomFeignClient.findAll();
                return new RecordTableViewListAndVscRoomListDTO(list, vscRoomList);
            } catch (FeignException e) {
                throw new VscRoomRequestException();
            }
        } catch (FeignException e) {
            throw new RecordTableViewRequestException();
        }
    }

    public void updateRecords(RecordTableRequest recordTableRequest) {
        log.info("Обновление бронирований");
        try {
            recordTableFeignClient.batchUpdateRecords(getRecordTableListFromParams(recordTableRequest.getId(),
                    recordTableRequest.getEmail(),
                    recordTableRequest.getEmployeeId(),
                    recordTableRequest.getVcsRoomNumberRoom(),
                    recordTableRequest.getIsActive(),
                    recordTableRequest.getTitle(),
                    recordTableRequest.getStartEvent(),
                    recordTableRequest.getEndEvent()));
            log.info("Обновление бронирований успешно завершено");
        } catch (FeignException e) {
            throw new RecordTableUpdateException();
        }
    }

    public void deleteRecord(String id) {
        log.info("Удаление бронирования");
        try {
            recordTableFeignClient.deleteRecordById(id);
            log.info("Удаление бронирования успешно завершено");
        } catch (FeignException e) {
            throw new RecordTableDeleteException();
        }
    }

    private List<RecordTableDTO> getRecordTableListFromParams(String id,
                                                              String email,
                                                              String employeeId,
                                                              String vcsRoomNumberRoom,
                                                              String isActive,
                                                              String title,
                                                              String startEvent,
                                                              String endEvent) {
        String[] idMas = id.split(",");
        String[] emailMas = email.split(",");
        String[] employeeIdMas = employeeId.split(",");
        String[] vcsRoomNumberRoomMas = vcsRoomNumberRoom.split(",");
        String[] isActiveMas = isActive.split(",");
        String[] titleMas = title.split(",");
        String[] startEventMas = startEvent.split(",");
        String[] endEventMas = endEvent.split(",");

        List<RecordTableDTO> recordTableList = new ArrayList<>();
        try {
            for (int i = 0; i < idMas.length; i++) {
                recordTableList.add(
                        RecordTableDTO.builder()
                                .id(Long.parseLong(idMas[i]))
                                .email(emailMas[i])
                                .employeeId(Long.parseLong(employeeIdMas[i]))
                                .numberRoomId(vscRoomFeignClient.findByNumberRoomId(vcsRoomNumberRoomMas[i])
                                        .getId())
                                .isActive(Boolean.valueOf(isActiveMas[i]))
                                .title(titleMas[i])
                                .start(ZonedDateTime.parse(startEventMas[i]))
                                .end(ZonedDateTime.parse(endEventMas[i]))
                                .build()
                );
            }
        } catch (FeignException e) {
            throw new VscRoomRequestException();
        }
        return recordTableList;
    }
}