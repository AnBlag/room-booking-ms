package ru.roombooking.history.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.roombooking.history.exception.VscRoomDeleteException;
import ru.roombooking.history.exception.VscRoomSaveException;
import ru.roombooking.history.exception.VscRoomUpdateException;
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

    @Transactional(rollbackFor = VscRoomSaveException.class)
    public VscRoom saveRoom(VscRoom vscRoom) {
        return vscRoomService.save(vscRoom);
    }

    @Transactional(rollbackFor = VscRoomUpdateException.class)
    public VscRoom updateRoom(VscRoom vscRoom, String id) {
        return vscRoomService.update(vscRoom, Long.parseLong(id));
    }

    @Transactional(rollbackFor = VscRoomDeleteException.class)
    public VscRoom deleteRoom(String id) {
        return vscRoomService.deleteById(Long.parseLong(id));
    }

    @Transactional(readOnly = true)
    public VscRoom findByNumberRoomId(String number) {
        return vscRoomService.findByNumberRoomId(Long.parseLong(number));
    }

    @Transactional(rollbackFor = VscRoomUpdateException.class)
    public void batchUpdateVscRoom(List<VscRoom> vscRoomList) {
        vscRoomService.batchUpdateVscRoom(vscRoomList);
    }
}