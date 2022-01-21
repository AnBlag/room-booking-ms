package ru.roombooking.mail.model.dto;

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
public class PreviousAndCurrentRecordTableDTO {

    private RecordTableDTO previous;
    private RecordTableDTO current;

}
