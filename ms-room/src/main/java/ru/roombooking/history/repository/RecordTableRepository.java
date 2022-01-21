package ru.roombooking.history.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.roombooking.history.model.RecordTable;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecordTableRepository extends JpaRepository<RecordTable,Long> {
    @Query(nativeQuery =  true, value = "select * from record_table inner join vsc_room vr on record_table.number_room_id = vr.id where number_room = ?1")
    List<RecordTable> findAllByNumberRoom(Long numberRoom);
    @Query( nativeQuery = true,
            value = "select * from record_table where number_room_id=?1 and employee_id=?2")
    Optional<RecordTable> findByNumberRoomIdAndEmployeeId(Long roomNumberId, Long employeeId);

    Optional<RecordTable> findByStartEventAndEndEvent(ZonedDateTime start, ZonedDateTime end);

    @Query(nativeQuery = true, value = "select * from record_table\n" +
            "where  number_room_id = ?3 and ((start_event<=?1 and end_event>=?2)\n" +
            "    or (start_event>=?1 and end_event<=?2)\n" +
            "    or ( start_event>?1 and  end_event>?2\n" +
            "        and start_event<?2 )\n" +
            "    or (start_event<?1 and  end_event<?2\n" +
            "        and end_event>?1 ))")
    Optional<RecordTable> findOverlappingRecordsByStartEventAndEndEvent(ZonedDateTime start, ZonedDateTime end, Long numberRoomId);

    @Query(nativeQuery = true,
    value = "select * " +
            "from record_table inner join employee e on e.id = record_table.employee_id " +
            "join vsc_room vr on vr.id = record_table.number_room_id " +
            "join department d on d.id = e.department_id join profile p on p.id = e.profile_id " +
            "where login=?1 ORDER BY login DESC LIMIT 1")
    Optional<RecordTable> findByLogin(String login);


    @Query( nativeQuery = true, value = "select employee.name, employee.surname, employee.middle_name, record_table.record, vsc_room.is_active,  vsc_room.number_room\n" +
            "from record_table inner join employee  on record_table.employee_id = employee.id\n" +
            "join vsc_room  on record_table.number_room_id = vsc_room.id")
    List<RecordTable> findAllByEmployeeNameAndSurnameAndMiddleNameAndRecordAndIsActiveAndNumberRoom();

    @Query(nativeQuery = true, value = "select * from record_table" +
            " join employee e on e.id = record_table.employee_id " +
            "join profile p on p.id = e.profile_id " +
            "where login=?1 and record_table.id=?2")
    Optional<RecordTable> findByLoginAndId(String login, Long id);


    @Query(nativeQuery = true, value = "select * from record_table where number_room_id=?1")
    List<RecordTable> findAllByNumberRoomId(Long id);
}
