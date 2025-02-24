package br.com.danielchipolesch.application.dtos.docDtos;

import br.com.danielchipolesch.domain.entities.documentStructure.DocumentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DocResponseDto {

    private String idDocumento;
    private String siglaEspecieNormativa;
    private String codigoAssuntoBasico;
    private Integer numeroSecundario;
    private String codigoDocumento;
//    private String nomeAssuntoBasico;
    private String tituloDocumento;
    private String statusDocumento;
}
