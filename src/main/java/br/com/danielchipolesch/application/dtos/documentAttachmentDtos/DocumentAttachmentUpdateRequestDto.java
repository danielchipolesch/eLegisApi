package br.com.danielchipolesch.application.dtos.documentAttachmentDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DocumentAttachmentUpdateRequestDto {

    @NotBlank
    private String textoAnexo;
}
