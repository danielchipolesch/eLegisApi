package br.com.danielchipolesch.application.dtos.documentAttachmentDtos;

import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentAttachmentResponseDto {

    private Long idAnexo;
    private Document documento;
    private String textoAnexo;
}
