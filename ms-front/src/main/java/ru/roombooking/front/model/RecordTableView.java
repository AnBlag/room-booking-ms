package ru.roombooking.front.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class RecordTableView {
    private Long id;
    private String email;
    private Long employeeId;
    private String employeeName;
    private String employeeSurname;
    private String employeeMiddleName;
    private Long vcsRoomNumberRoom;
    private Boolean isActive;
    private String title;
    private ZonedDateTime startEvent;
    private ZonedDateTime  endEvent;
}
