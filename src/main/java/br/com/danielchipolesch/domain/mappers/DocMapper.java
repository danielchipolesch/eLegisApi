package br.com.danielchipolesch.domain.mappers;


import br.com.danielchipolesch.application.dtos.docDtos.DocDto;
import br.com.danielchipolesch.application.dtos.docDtos.DocResponseDto;
import br.com.danielchipolesch.application.dtos.documentDtos.DocumentDto;
import br.com.danielchipolesch.application.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.domain.entities.documentStructure.Doc;
import br.com.danielchipolesch.domain.entities.documentStructure.Document;

public class DocMapper {

    public static DocResponseDto docToDocumentResponseDto(Doc document){
        return new DocResponseDto(
                document.getId(),
                document.getDocumentationType(),
                document.getBasicSubject(),
                document.getSecondaryNumber(),
                String.format("%s %s-%d", document.getDocumentationType(), document.getBasicSubject(), document.getSecondaryNumber()),
                document.getDocumentTitle(),
                document.getDocumentStatus()
        );
    }

    public static DocDto docToDocumentDto(Doc document) {
        return new DocDto(
                document.getId(),
                document.getDocumentationType(),
                document.getBasicSubject(),
                document.getSecondaryNumber(),
                String.format("%s %s-%d", document.getDocumentationType(), document.getBasicSubject(), document.getSecondaryNumber()),
                document.getDocumentTitle(),
                document.getDocumentStatus(),
                document.getTextAttachment()
        );
    }

    public static DocResponseDto docDtoToDocumentResponseDto(DocDto documentDto){
        return new DocResponseDto(
                documentDto.getDocumentoId(),
                documentDto.getSiglaEspecieNormativa(),
                documentDto.getCodigoAssuntoBasico(),
                documentDto.getNumeroSecundario(),
                documentDto.getCodigoDocumento(),
//                documentDto.getNomeAssuntoBasico(),
                documentDto.getTituloDocumento(),
                documentDto.getStatusDocumento()
        );
    }
}
