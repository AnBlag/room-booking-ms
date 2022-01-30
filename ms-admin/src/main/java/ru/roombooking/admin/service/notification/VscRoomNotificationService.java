package ru.roombooking.admin.service.notification;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
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
public class VscRoomNotificationService {
    private final VscRoomFeignClient vscRoomFeignClient;

    public List<VscRoomDTO> vscRooms() {
        try {
            return vscRoomFeignClient.findAll();
        } catch (FeignException e) {
            throw new VscRoomRequestException();
        }
    }

    public void updateVscRoom(VscRoomRequest vscRoomRequest) {
        try {
            vscRoomFeignClient.batchUpdateVscRoom(getVscRoomListFromParams(vscRoomRequest.getId(),
                    vscRoomRequest.getIsActive(),
                    vscRoomRequest.getNumberRoom()));
        } catch (FeignException e) {
            throw new VscRoomUpdateException();
        }
    }

    public void deleteRoom(String id) {
        try {
            vscRoomFeignClient.deleteRoom(id);
        } catch (FeignException e) {
            throw new VscRoomDeleteException();
        }
    }

    public void saveNewRoom(VscRoomDTO vscRoom) {
        try {
            vscRoomFeignClient.saveRoom(vscRoom);
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