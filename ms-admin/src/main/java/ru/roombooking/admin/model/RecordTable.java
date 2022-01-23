package ru.roombooking.admin.model;

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
public class RecordTable {
    private Long id;
    private String email;
    private String title;
    private ZonedDateTime startEvent;
    private ZonedDateTime endEvent;
    private Boolean isActive;
    private Long numberRoomId;
    private Long employeeId;
}