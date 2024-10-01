package br.com.danielchipolesch.application.dtos.documentationTypeDtos;

import lombok.Data;

@Data
public class DocumentationTypeResponseDto {

    private Long id;
    private String acronym;
    private String name;
    private String description;
}
