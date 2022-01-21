package ru.roombooking.front.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class RecordTableDTO {
    private Long id;
    private String email;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime start;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime end;

    private String title;
    private Boolean isActive;
    private Long numberRoomId;

    private Long employeeId;
    private String employeeName, employeeSurname, employeeMiddleName;
    private String vcsRoomNumberRoom;
    // FIXME: 28.08.2021 Передалать нейминг roomId 
    @JsonProperty("roomId")
    private String roomId;
    private ZoneId timeZone;

    //select employee.name, employee.surname, employee.middle_name, record_table.record, vsc_room.is_active,  vsc_room.number_room

}
