package br.com.danielchipolesch.domain.dtos.documentDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DocumentRequestCreateDto {

    @NotNull
    private Long basicSubjectId;

    @NotNull
    private Long documentationTypeId;

    @NotBlank
    private String documentTitle;
}
