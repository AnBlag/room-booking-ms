package ru.roombooking.admin.service.notification;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.roombooking.admin.exception.RecordTableDeleteException;
import ru.roombooking.admin.exception.RecordTableViewBadRequestException;
import ru.roombooking.admin.exception.RecordTableUpdateException;
import ru.roombooking.admin.exception.VscRoomBadRequestException;
import ru.roombooking.admin.feign.RecordTableFeignClient;
import ru.roombooking.admin.feign.RecordTableViewFeignClient;
import ru.roombooking.admin.feign.VscRoomFeignClient;
import ru.roombooking.admin.model.RecordTableView;
import ru.roombooking.admin.model.dto.VscRoomDTO;
import ru.roombooking.admin.model.dto.RecordTableDTO;
import ru.roombooking.admin.model.dto.RecordTableRequest;
import ru.roombooking.admin.model.dto.RecordTableViewListAndVscRoomListRequest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordTableNotificationService {
    private final RecordTableFeignClient recordTableFeignClient;
    private final VscRoomFeignClient vscRoomFeignClient;
    private final RecordTableViewFeignClient recordTableViewFeignClient;

    public RecordTableViewListAndVscRoomListRequest records(String search) {
        List<RecordTableView> recordTableViewList;
        try {
            if (search != null) {
                recordTableViewList = recordTableViewFeignClient.getRecordTableViewListByURLParams(search);
            } else {
                recordTableViewList = recordTableViewFeignClient.findAll();
            }
            try {
                List<VscRoomDTO> vscRoomList = vscRoomFeignClient.findAll();
                return new RecordTableViewListAndVscRoomListRequest(recordTableViewList, vscRoomList);
            } catch (FeignException e) {
                throw new VscRoomBadRequestException();
            }
        } catch (FeignException e) {
            throw new RecordTableViewBadRequestException();
        }
    }

    public RecordTableViewListAndVscRoomListRequest findRecords(RecordTableView findRecord) {
        try {
            List<RecordTableView> list = recordTableViewFeignClient.getRecordTableViewListByRecordTableViewParams(findRecord);
            try {
                List<VscRoomDTO> vscRoomList = vscRoomFeignClient.findAll();
                return new RecordTableViewListAndVscRoomListRequest(list, vscRoomList);
            } catch (FeignException e) {
                throw new VscRoomBadRequestException();
            }
        } catch (FeignException e) {
            throw new RecordTableViewBadRequestException();
        }
    }

    public void updateRecords(RecordTableRequest recordTableRequest) {
        try {
            recordTableFeignClient.batchUpdateRecords(getRecordTableListFromParams(recordTableRequest.getId(),
                    recordTableRequest.getEmail(),
                    recordTableRequest.getEmployeeId(),
                    recordTableRequest.getVcsRoomNumberRoom(),
                    recordTableRequest.getIsActive(),
                    recordTableRequest.getTitle(),
                    recordTableRequest.getStartEvent(),
                    recordTableRequest.getEndEvent()));
        } catch (FeignException e) {
            throw new RecordTableUpdateException();
        }
    }

    public void deleteRecord(String id) {
        try {
            recordTableFeignClient.deleteRecordById(id);
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
            throw new VscRoomBadRequestException();
        }
        return recordTableList;
    }
}