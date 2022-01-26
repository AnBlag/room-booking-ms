package ru.roombooking.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.roombooking.history.model.VscRoom;

import java.util.Optional;

@Repository
public interface VscRoomRepository extends JpaRepository<VscRoom, Long> {
    Optional<VscRoom> findByNumberRoom(Long room);
}