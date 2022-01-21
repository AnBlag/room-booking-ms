package ru.roombooking.history.maper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.roombooking.history.model.HistoryRecordTableEmployee;
import ru.roombooking.history.model.dto.RecordTableDTO;


@Mapper(componentModel = "spring")
public interface HistoryRecordTableMapper extends VCMapper<HistoryRecordTableEmployee, RecordTableDTO> {
    @Mappings({
            @Mapping(target="id", source="historyRecordTableEmployee.id"),
            @Mapping(target="email", source="historyRecordTableEmployee.email"),
            @Mapping(target="title", source="historyRecordTableEmployee.title"),
            @Mapping(target="start", source="historyRecordTableEmployee.startEvent"),
            @Mapping(target="end",source= "historyRecordTableEmployee.endEvent"),
            @Mapping(target="isActive", source="historyRecordTableEmployee.isActive"),
            @Mapping(target="numberRoomId", source="historyRecordTableEmployee.numberRoomId"),
            @Mapping(target="employeeId", source="historyRecordTableEmployee.employeeId")
    })
    @Override
     RecordTableDTO toDTO(HistoryRecordTableEmployee historyRecordTableEmployee);
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
    @Override
     HistoryRecordTableEmployee toModel(RecordTableDTO recordTableDTO) ;
}
