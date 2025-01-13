package br.com.danielchipolesch.application.dtos.textAttachmentDtos;

import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TextAttachmentDto {

    private Long idAnexoTextual;
    private Document documento;
    private String textoAnexoTextual;
}
