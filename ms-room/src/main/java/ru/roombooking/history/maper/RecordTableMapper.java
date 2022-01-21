package ru.roombooking.history.maper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.roombooking.history.model.RecordTable;
import ru.roombooking.history.model.dto.RecordTableDTO;

@Mapper(componentModel = "spring")
public interface RecordTableMapper extends VCMapper<RecordTable, RecordTableDTO> {
    @Mappings({
            @Mapping(target="id", source="recordTable.id"),
            @Mapping(target="email", source="recordTable.email"),
            @Mapping(target="title", source="recordTable.title"),
            @Mapping(target="start", source="recordTable.startEvent"),
            @Mapping(target="end",source= "recordTable.endEvent"),
            @Mapping(target="isActive", source="recordTable.isActive"),
            @Mapping(target="numberRoomId", source="recordTable.numberRoomId"),
            @Mapping(target="employeeId", source="recordTable.employeeId")
    })
     RecordTableDTO toDTO(RecordTable recordTable);

    @Mappings({
            @Mapping(target="id", source="recordTableDTO.id"),
            @Mapping(target="email", source="recordTableDTO.email"),
            @Mapping(target="title", source="recordTableDTO.title"),
            @Mapping(target="startEvent", source="recordTableDTO.start"),
            @Mapping(target="endEvent",source= "recordTableDTO.end"),
            @Mapping(target="isActive", source="recordTableDTO.isActive"),
            @Mapping(target="numberRoomId", source="recordTableDTO.numberRoomId"),
            @Mapping(target="employeeId", source="recordTableDTO.employeeId")
    })
     RecordTable toModel(RecordTableDTO recordTableDTO);
}
