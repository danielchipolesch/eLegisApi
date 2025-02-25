package br.com.danielchipolesch.domain.mappers;

import br.com.danielchipolesch.application.dtos.documentoDtos.DocumentoResponseComAnexoTextualDto;
import br.com.danielchipolesch.application.dtos.documentoDtos.DocumentoResponseComPortariaDto;
import br.com.danielchipolesch.application.dtos.documentoDtos.DocumentoResponseSemAnexoTextualDto;
import br.com.danielchipolesch.domain.entities.estruturaDocumento.Documento;

public class DocumentoMapper {

    public static DocumentoResponseSemAnexoTextualDto documentoToDocumentoSemAnexoTextualResponseDto(Documento documento){
        return new DocumentoResponseSemAnexoTextualDto(
                documento.getId(),
                documento.getEspecieNormativa(),
                documento.getAssuntoBasico(),
                documento.getNumeroSecundario(),
                String.format("%s %s-%d", documento.getEspecieNormativa(), documento.getAssuntoBasico(), documento.getNumeroSecundario()),
                documento.getTituloDocumento(),
                documento.getDocumentoStatus()
        );
    }

    public static DocumentoResponseComAnexoTextualDto documentoToDocumentoComAnexoTextualResponseDto(Documento documento) {
        return new DocumentoResponseComAnexoTextualDto(
                documento.getId(),
                documento.getEspecieNormativa(),
                documento.getAssuntoBasico(),
                documento.getNumeroSecundario(),
                String.format("%s %s-%d", documento.getEspecieNormativa(), documento.getAssuntoBasico(), documento.getNumeroSecundario()),
                documento.getTituloDocumento(),
                documento.getDocumentoStatus(),
                documento.getItens()
        );
    }

    public static DocumentoResponseSemAnexoTextualDto documentoDtoToDocumentoSemAnexoTextualResponseDto(DocumentoResponseComPortariaDto documentoResponseComPortariaDto){
        return new DocumentoResponseSemAnexoTextualDto(
                documentoResponseComPortariaDto.getIdDocumento(),
                documentoResponseComPortariaDto.getSiglaEspecieNormativa(),
                documentoResponseComPortariaDto.getCodigoAssuntoBasico(),
                documentoResponseComPortariaDto.getNumeroSecundario(),
                documentoResponseComPortariaDto.getCodigoDocumento(),
//                documentDto.getNomeAssuntoBasico(),
                documentoResponseComPortariaDto.getTituloDocumento(),
                documentoResponseComPortariaDto.getStatusDocumento()
        );
    }
}
