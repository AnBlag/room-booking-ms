package ru.roombooking.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.roombooking.admin.model.RecordTableView;
import ru.roombooking.admin.model.VscRoom;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = "true")
public class RecordTableViewListAndVscRoomListRequest {
    private List<RecordTableView> recordTableViewList;
    private List<VscRoom> vscRoomList;
}