package ru.roombooking.front.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private ZonedDateTime start;
    private ZonedDateTime end;
    private String title;
    private Boolean isActive;
    private Long numberRoomId;
    private Long employeeId;
    private String employeeName, employeeSurname, employeeMiddleName;
    private String vcsRoomNumberRoom;
    private String roomId;
    private ZoneId timeZone;
}