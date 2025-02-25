package br.com.danielchipolesch.domain.mappers;

import br.com.danielchipolesch.application.dtos.documentDtos.*;
import br.com.danielchipolesch.domain.entities.estruturaDocumento.Document;

public class DocumentMapper {

    public static DocumentResponseDto documentToDocumentResponseDto(Document document){
        return new DocumentResponseDto(
                document.getId(),
                document.getEspecieNormativa().getSigla(),
                document.getAssuntoBasico().getCodigo(),
                document.getSecondaryNumber(),
                String.format("%s %s-%d", document.getEspecieNormativa().getSigla(), document.getAssuntoBasico().getCodigo(), document.getSecondaryNumber()),
                document.getAssuntoBasico().getNome(),
                document.getDocumentTitle(),
                document.getDocumentoStatusEnum()
        );
    }

    public static DocumentDto documentToDocumentDto(Document document) {
        return new DocumentDto(
                document.getId(),
                document.getEspecieNormativa().getSigla(),
                document.getAssuntoBasico().getCodigo(),
                document.getSecondaryNumber(),
                String.format("%s %s-%d", document.getEspecieNormativa().getSigla(), document.getAssuntoBasico().getCodigo(), document.getSecondaryNumber()),
                document.getAssuntoBasico().getNome(),
                document.getDocumentTitle(),
                document.getDocumentoStatusEnum(),
//                document.getRegulatoryAct(),
                document.getTextAttachment()
        );
    }

    public static DocumentResponseDto documentDtoToDocumentResponseDto(DocumentDto documentDto){
        return new DocumentResponseDto(
                documentDto.getDocumentoId(),
                documentDto.getSiglaEspecieNormativa(),
                documentDto.getCodigoAssuntoBasico(),
                documentDto.getNumeroSecundario(),
                documentDto.getCodigoDocumento(),
                documentDto.getNomeAssuntoBasico(),
                documentDto.getTituloDocumento(),
                documentDto.getStatusDocumento()
        );
    }
}
