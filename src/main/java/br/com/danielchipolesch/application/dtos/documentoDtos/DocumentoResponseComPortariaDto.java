package br.com.danielchipolesch.application.dtos.documentoDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentoResponseComPortariaDto {

    private String idDocumento;
    private String siglaEspecieNormativa;
    private String codigoAssuntoBasico;
    private Integer numeroSecundario;
    private String codigoDocumento;
    //    private String nomeAssuntoBasico;
    private String tituloDocumento;
    private String statusDocumento;
}
