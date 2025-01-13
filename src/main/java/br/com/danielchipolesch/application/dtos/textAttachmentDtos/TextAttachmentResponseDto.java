package br.com.danielchipolesch.application.dtos.textAttachmentDtos;

import br.com.danielchipolesch.application.dtos.documentDtos.DocumentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TextAttachmentResponseDto {

    private Long idAnexoTextual;
//    private DocumentResponseDto documento;
    private String textoAnexoTextual;
}
