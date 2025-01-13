package br.com.danielchipolesch.application.dtos.textAttachmentDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TextAttachmentUpdateRequestDto {

    @NotBlank
    private String textoAnexo;
}
