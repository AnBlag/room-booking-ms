package ru.roombooking.front.model.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class VscRoomDTO {

    private Long id;
    private Long numberRoom;
    private Boolean isActive;
}
