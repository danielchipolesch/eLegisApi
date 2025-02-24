package br.com.danielchipolesch.application.dtos.documentDtos;

import br.com.danielchipolesch.domain.entities.documentStructure.TextAttachment;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentStatus;
import br.com.danielchipolesch.domain.entities.documentStructure.RegulatoryAct;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentDto {

    private Long documentoId;
    private String siglaEspecieNormativa;
    private String codigoAssuntoBasico;
    private Integer numeroSecundario;
    private String codigoDocumento;
    private String nomeAssuntoBasico;
    private String tituloDocumento;
    private DocumentStatus statusDocumento;
//    private RegulatoryAct portaria;
    private TextAttachment anexo;
}
