package ru.roombooking.history.service;

import ru.roombooking.history.model.VscRoom;

import java.util.List;

public interface VscRoomService extends RoomServiceCRUD<VscRoom, Long> {
    void findByNumberRoomIfNotFoundByNumberRoomThrowException(Long number);

    VscRoom findById(Long aLong);

    VscRoom findByNumberRoomId(Long aLong);

    void batchUpdateVscRoom(List<VscRoom> vscRoomList);
}