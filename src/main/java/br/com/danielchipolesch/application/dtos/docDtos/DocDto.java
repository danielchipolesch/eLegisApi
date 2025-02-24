package br.com.danielchipolesch.application.dtos.docDtos;

import br.com.danielchipolesch.domain.entities.documentStructure.DocumentStatus;
import br.com.danielchipolesch.domain.entities.documentStructure.RegulatoryAct;
import br.com.danielchipolesch.domain.entities.documentStructure.TextAttachment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DocDto {

    private String documentoId;
    private String siglaEspecieNormativa;
    private String codigoAssuntoBasico;
    private Integer numeroSecundario;
    private String codigoDocumento;
//    private String nomeAssuntoBasico;
    private String tituloDocumento;
    private String statusDocumento;
//    private RegulatoryAct portaria;
    private TextAttachment anexo;
}
