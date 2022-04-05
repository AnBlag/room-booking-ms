package ru.roombooking.admin.service.notification;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.roombooking.admin.exception.VscRoomDeleteException;
import ru.roombooking.admin.exception.VscRoomSaveException;
import ru.roombooking.admin.exception.VscRoomUpdateException;
import ru.roombooking.admin.exception.VscRoomRequestException;
import ru.roombooking.admin.feign.VscRoomFeignClient;
import ru.roombooking.admin.model.dto.VscRoomDTO;
import ru.roombooking.admin.model.dto.VscRoomRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VscRoomNotificationService {
    private final VscRoomFeignClient vscRoomFeignClient;

    public List<VscRoomDTO> vscRooms() {
        log.info("Поиск всех комнат");
        try {
            return vscRoomFeignClient.findAll();
        } catch (FeignException e) {
            throw new VscRoomRequestException();
        }
    }

    public void updateVscRoom(VscRoomRequest vscRoomRequest) {
        log.info("Обновление комнат");
        try {
            vscRoomFeignClient.batchUpdateVscRoom(getVscRoomListFromParams(vscRoomRequest.getId(),
                    vscRoomRequest.getIsActive(),
                    vscRoomRequest.getNumberRoom()));
            log.info("Обновление комнат успешно завершено");
        } catch (FeignException e) {
            throw new VscRoomUpdateException();
        }
    }

    public void deleteRoom(String id) {
        log.info("Удаление комнаты");
        try {
            vscRoomFeignClient.deleteRoom(id);
            log.info("Удаление комнаты успешно завершено");
        } catch (FeignException e) {
            throw new VscRoomDeleteException();
        }
    }

    public void saveNewRoom(VscRoomDTO vscRoom) {
        log.info("Сохранение новой комнаты");
        try {
            vscRoomFeignClient.saveRoom(vscRoom);
            log.info("Сохранение новой комнаты успешно завершено");
        } catch (FeignException e) {
            throw new VscRoomSaveException();
        }
    }

    private List<VscRoomDTO> getVscRoomListFromParams(String id,
                                                      String isActive,
                                                      String numberRoom) {
        String[] idMas = id.split(",");
        String[] isActiveMas = isActive.split(",");
        String[] numberRoomMas = numberRoom.split(",");

        List<VscRoomDTO> vscRoomList = new ArrayList<>();
        for (int i = 0; i < idMas.length; i++) {
            vscRoomList.add(
                    VscRoomDTO.builder()
                            .id(Long.parseLong(idMas[i]))
                            .isActive(Boolean.valueOf(isActiveMas[i]))
                            .numberRoom(Long.parseLong(numberRoomMas[i]))
                            .build()
            );
        }
        return vscRoomList;
    }
}