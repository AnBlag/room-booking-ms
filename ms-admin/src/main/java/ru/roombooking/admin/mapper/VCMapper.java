package ru.roombooking.admin.mapper;

public interface VCMapper<Model, DTO> {
    DTO toDTO(Model model);

    Model toModel(DTO dto);
}