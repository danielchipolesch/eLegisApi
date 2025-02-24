package br.com.danielchipolesch.application.dtos.docDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocRequestCreateDto {

    @NotNull
    private Long IdEspecieNormativa;

    @NotNull
    private Long IdAssuntoBasico;

    @NotBlank
    private String tituloDocumento;
}
