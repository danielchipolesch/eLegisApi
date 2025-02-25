package br.com.danielchipolesch.application.dtos.documentoDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class DocumentoMainDto {

    private String idDocumento;
    private String siglaEspecieNormativa;
    private String codigoAssuntoBasico;
    private Integer numeroSecundario;
    private String codigoDocumento;
    //    private String nomeAssuntoBasico;
    private String tituloDocumento;
    private String statusDocumento;
}
