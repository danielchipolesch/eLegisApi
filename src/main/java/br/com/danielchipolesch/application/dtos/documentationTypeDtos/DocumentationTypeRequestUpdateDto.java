package br.com.danielchipolesch.application.dtos.documentationTypeDtos;

import lombok.Data;

@Data
public class DocumentationTypeRequestUpdateDto {

    private String acronym;
    private String name;
    private String description;
}
