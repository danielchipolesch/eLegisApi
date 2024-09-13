package br.com.danielchipolesch.domain.dtos.basicSubjectDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BasicSubjectRequestCreateDto {

        @NotBlank(message = "Número básico não pode estar vazio")
        private String basicNumber;

        @NotBlank(message = "Classificação não pode estar vazia")
        private String name;

        @NotBlank(message = "Descrição não pode estar vazia")
        private String description;
}
