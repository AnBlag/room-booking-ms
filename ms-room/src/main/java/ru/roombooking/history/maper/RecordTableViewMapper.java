package ru.roombooking.history.maper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.roombooking.history.model.RecordTableView;
import ru.roombooking.history.model.dto.RecordTableDTO;

@Mapper(componentModel = "spring")
public interface RecordTableViewMapper extends VCMapper<RecordTableView, RecordTableDTO> {
    @Mappings({
            @Mapping(target = "employeeName", source = "recordTableView.employeeName"),
            @Mapping(target = "employeeMiddleName", source = "recordTableView.employeeMiddleName"),
            @Mapping(target = "employeeSurname", source = "recordTableView.employeeSurname"),
            @Mapping(target = "isActive", source = "recordTableView.isActive"),
            @Mapping(target = "vcsRoomNumberRoom", expression = "java(String.valueOf(recordTableView.getVcsRoomNumberRoom()))"),
            @Mapping(target = "title", source = "recordTableView.endEvent"),
            @Mapping(target = "start", source = "recordTableView.startEvent")
    })
    @Override
    RecordTableDTO toDTO(RecordTableView recordTableView);

    @Override
    RecordTableView toModel(RecordTableDTO recordTableDTO);
}