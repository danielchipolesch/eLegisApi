package br.com.danielchipolesch.application.dtos.documentDtos;

import br.com.danielchipolesch.domain.entities.estruturaDocumento.TextAttachment;
import br.com.danielchipolesch.domain.entities.estruturaDocumento.DocumentoStatusEnum;
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
    private DocumentoStatusEnum statusDocumento;
//    private RegulatoryAct portaria;
    private TextAttachment anexo;
}
