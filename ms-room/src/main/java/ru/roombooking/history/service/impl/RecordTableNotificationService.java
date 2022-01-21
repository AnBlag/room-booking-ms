package ru.roombooking.history.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.history.exception.MailDoNotSendException;
import ru.roombooking.history.exception.RecordTableBadRequestException;
import ru.roombooking.history.exception.UpdateRecordTableException;
import ru.roombooking.history.exception.UpdateVscRoomException;
import ru.roombooking.history.feign.MailFeignClient;
import ru.roombooking.history.maper.RecordTableMapper;
import ru.roombooking.history.maper.VCMapper;
import ru.roombooking.history.model.HistoryRecordTableEmployee;
import ru.roombooking.history.model.RecordTable;
import ru.roombooking.history.model.dto.PreviousAndCurrentRecordTableDTO;
import ru.roombooking.history.model.dto.RecordTableDTO;
import ru.roombooking.history.service.HistoryRecordTableEmployeeAndRecordTableService;
import ru.roombooking.history.service.RecordTableAndEmployeeService;
import ru.roombooking.history.service.RecordTableService;
import ru.roombooking.history.service.VscRoomService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordTableNotificationService {

    private final RecordTableService recordTableService;
    private final RecordTableAndEmployeeService recordTableAndEmployeeService;
    private final HistoryRecordTableEmployeeAndRecordTableService<RecordTableDTO, String, Long> historyRecordTableEmployeeAndRecordTableService;
    //private final MailSenderService mailSenderService;
    private final MailFeignClient mailFeignClient;
    private final VscRoomService vscRoomService;


    @Transactional(readOnly = true)
    public List<RecordTableDTO> findAll() {
        return recordTableService.findAll();
    }

    @Transactional(readOnly = true)
    public List<RecordTableDTO> findByIndex(String index) {
        return recordTableService.findByNumberRoom(Long.parseLong(index));

    }


    public RecordTableDTO saveRecord(RecordTableDTO recordTableDTO, String login) {
        setCorrectRoomIdFormat(recordTableDTO);
        //setCurrentZone(recordTableDTO);
        RecordTableDTO resultRecordTableDto = historyRecordTableEmployeeAndRecordTableService
                .save(recordTableDTO, login);

        resultRecordTableDto.setTimeZone(recordTableDTO.getTimeZone());

        try {
            mailFeignClient.sendConfirmMessageToEmployee(resultRecordTableDto, recordTableDTO.getRoomId());
        }
        catch (FeignException e) {
            throw new MailDoNotSendException();
        }

        return recordTableDTO;
    }

    public RecordTableDTO updateRecord(RecordTableDTO recordTableDTO, String login) {
        checkPermissionToEditRecord(login, recordTableDTO);
        RecordTableDTO tempRecordTableDTO = recordTableService.findById(recordTableDTO.getId());
        tempRecordTableDTO.setTimeZone(recordTableDTO.getTimeZone());
        recordTableDTO.setEmail(tempRecordTableDTO.getEmail());
        recordTableDTO.setIsActive(tempRecordTableDTO.getIsActive());
        recordTableDTO.setNumberRoomId(tempRecordTableDTO.getNumberRoomId());
        recordTableDTO.setEmployeeId(tempRecordTableDTO.getEmployeeId());
        recordTableDTO.setRoomId(vscRoomService.findById(recordTableDTO.getNumberRoomId()).getNumberRoom().toString());
        RecordTableDTO resultRecordTableDTO = historyRecordTableEmployeeAndRecordTableService.update(recordTableDTO,
                recordTableDTO.getId());

        try {
            mailFeignClient
                    .sendConfirmUpdateMessageToEmployee(new PreviousAndCurrentRecordTableDTO(tempRecordTableDTO, recordTableDTO));
        }
        catch (FeignException e) {
            throw new MailDoNotSendException();
        }
        return resultRecordTableDTO;
    }



    public RecordTableDTO deleteRecord(RecordTableDTO recordTableDTO, String login) {
        checkPermissionToEditRecord(login, recordTableDTO);
        RecordTableDTO tempRecordTableDTO = recordTableService.findById(recordTableDTO.getId());
        tempRecordTableDTO.setTimeZone(recordTableDTO.getTimeZone());
        tempRecordTableDTO.setRoomId(vscRoomService.findById(tempRecordTableDTO.getNumberRoomId()).getNumberRoom().toString());
        RecordTableDTO resultRecordTableDTO = recordTableService.delete(recordTableDTO);

        try {
            mailFeignClient.sendConfirmDeleteMessageToEmployee(tempRecordTableDTO);
        }
        catch (FeignException e) {
            throw new MailDoNotSendException();
        }
        return resultRecordTableDTO;
    }

    public RecordTableDTO deleteRecordById(String id) {


        RecordTableDTO resultRecordTableDTO = recordTableService.deleteById(Long.parseLong(id));

        /*try {
            mailFeignClient.sendConfirmDeleteMessageToEmployee(resultRecordTableDTO);
        }
        catch (FeignException e) {
            throw new MailDoNotSendException();
        }*/
        return resultRecordTableDTO;
    }


    @Transactional(readOnly = true)
    public List<RecordTableDTO> findAllByEmployeeFullNameAndRecordAndIsActiveAndNumberRoom() {
        return recordTableService.findAllByEmployeeFullNameAndRecordAndIsActiveAndNumberRoom();
    }

    /*private String getUserAuth () {
        LoginSuccessResponse loginSuccessResponse = securityFeignClient.getUserAuth();
        if (loginSuccessResponse.getStatus().equals(0L)) {
            return loginSuccessResponse.getLogin();
        }
        else throw new SecurityBadRequestException();
    }*/

    private void setCurrentZone(RecordTableDTO recordTableDTO) {
        recordTableDTO.setStart(recordTableDTO.getStart().withZoneSameInstant(recordTableDTO.getTimeZone()));
        recordTableDTO.setEnd(recordTableDTO.getEnd().withZoneSameInstant(recordTableDTO.getTimeZone()));
    }

    private void setCorrectRoomIdFormat (RecordTableDTO recordTableDTO) {
        String[] urlMassive = recordTableDTO.getRoomId().split("/");
        recordTableDTO.setRoomId(urlMassive[urlMassive.length - 1]);
    }

    private void checkPermissionToEditRecord(String login, RecordTableDTO recordTableDTO) {
        if (!recordTableAndEmployeeService.checkPermissionByUserAndRecordId(login,recordTableDTO.getId())) {
            throw new RecordTableBadRequestException("Нет доступа к записи!");
        }
    }

    @Transactional(rollbackFor = UpdateRecordTableException.class)
    public void batchUpdateRecords(List<RecordTable> recordTableList) {
        recordTableService.batchUpdateRecords(recordTableList);
    }
}
