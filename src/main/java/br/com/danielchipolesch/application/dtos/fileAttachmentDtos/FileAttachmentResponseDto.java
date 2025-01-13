package br.com.danielchipolesch.application.dtos.fileAttachmentDtos;

import br.com.danielchipolesch.application.dtos.documentDtos.DocumentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileAttachmentResponseDto {

    private Long idAnexoArquivo;
    private String nomeAnexoArquivo;
    private DocumentResponseDto documento;
    private byte[] arquivoBase64;
}
