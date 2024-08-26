package br.com.danielchipolesch.domain.dtos.documentationTypeDtos;

import jakarta.validation.constraints.NotBlank;

public record DocumentationTypeCreateRequestDto(

    @NotBlank(message = "Sigla não pode estar vazia")
    String acronym,

    @NotBlank(message = "Nome não pode estar vazio")
    String name,

    @NotBlank(message = "Descrição não pode estar vazia")
    String description
) {
}
