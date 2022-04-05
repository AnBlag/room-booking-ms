package ru.roombooking.front.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.roombooking.front.model.RecordTableView;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class RecordTableViewListAndVscRoomListDTO {
    private List<RecordTableView> recordTableViewList;
    private List<VscRoomDTO> vscRoomList;
}