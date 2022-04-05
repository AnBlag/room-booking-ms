package ru.roombooking.front.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class RecordTableRequest {
    private String id;
    private String email;
    private String employeeId;
    private String vcsRoomNumberRoom;
    private String isActive;
    private String title;
    private String startEvent;
    private String endEvent;
}