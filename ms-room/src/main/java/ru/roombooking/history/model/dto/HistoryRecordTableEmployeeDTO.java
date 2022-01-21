package ru.roombooking.history.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class HistoryRecordTableEmployeeDTO {
    private Long id;
    private String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime start;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime end;

    private String title;
    private Boolean isActive;
    private Long numberRoomId;

    private Long employeeId;
    private String employeeName, employeeSurname, employeeMiddleName;
    private String vcsRoomNumberRoom;

    @JsonProperty("roomId")
    private String roomId;
}
