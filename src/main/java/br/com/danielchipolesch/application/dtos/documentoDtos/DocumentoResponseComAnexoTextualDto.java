package br.com.danielchipolesch.application.dtos.documentoDtos;

import br.com.danielchipolesch.domain.entities.estruturaDocumento.AnexoParteNormativaItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DocumentoResponseComAnexoTextualDto {

    private String idDocumento;
    private String siglaEspecieNormativa;
    private String codigoAssuntoBasico;
    private Integer numeroSecundario;
    private String codigoDocumento;
    //    private String nomeAssuntoBasico;
    private String tituloDocumento;
    private String statusDocumento;
    private List<AnexoParteNormativaItem> itens;
}
