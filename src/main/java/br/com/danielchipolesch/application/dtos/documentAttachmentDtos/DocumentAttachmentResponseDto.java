package br.com.danielchipolesch.application.dtos.documentAttachmentDtos;

import br.com.danielchipolesch.application.dtos.documentDtos.DocumentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentAttachmentResponseDto {

    private Long idAnexo;
    private DocumentResponseDto documento;
    private String textoAnexo;
}
