package ru.roombooking.history.maper;

public interface VCMapper<Model, DTO> {
    DTO toDTO(Model model);

    Model toModel(DTO dto);
}