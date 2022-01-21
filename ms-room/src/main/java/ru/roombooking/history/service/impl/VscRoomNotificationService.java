package ru.roombooking.history.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.history.exception.DeleteVscRoomException;
import ru.roombooking.history.exception.SaveVscRoomException;
import ru.roombooking.history.exception.UpdateVscRoomException;
import ru.roombooking.history.model.VscRoom;
import ru.roombooking.history.service.VscRoomService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VscRoomNotificationService {
    private final VscRoomService vscRoomService;

    @Transactional(readOnly = true)
    public List<VscRoom> findAll() {
        return vscRoomService.findAll();
    }

    @Transactional(rollbackFor = SaveVscRoomException.class)
    public VscRoom saveRoom(VscRoom vscRoom) {
        return vscRoomService.save(vscRoom);
    }

    @Transactional(rollbackFor = UpdateVscRoomException.class)
    public VscRoom updateRoom(VscRoom vscRoom, String id) {
        return vscRoomService.update(vscRoom,Long.parseLong(id));
    }

    @Transactional(rollbackFor = DeleteVscRoomException.class)
    public VscRoom deleteRoom(String id) {
        return vscRoomService.deleteById(Long.parseLong(id));
    }

    @Transactional(readOnly = true)
    public VscRoom findByNumberRoomId(String number) {
        return vscRoomService.findByNumberRoomId(Long.parseLong(number));
    }

    @Transactional(rollbackFor = UpdateVscRoomException.class)
    public void batchUpdateVscRoom(List<VscRoom> vscRoomList) {
        vscRoomService.batchUpdateVscRoom(vscRoomList);
    }
}
