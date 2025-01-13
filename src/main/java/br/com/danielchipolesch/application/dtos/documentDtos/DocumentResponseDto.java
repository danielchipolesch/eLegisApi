package br.com.danielchipolesch.application.dtos.documentDtos;

import br.com.danielchipolesch.domain.entities.documentStructure.DocumentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentResponseDto {

    private Long idDocumento;
    private String siglaEspecieNormativa;
    private String codigoAssuntoBasico;
    private Integer numeroSecundario;
    private String codigoDocumento;
    private String nomeAssuntoBasico;
    private String tituloDocumento;
    private DocumentStatus statusDocumento;
}
