package br.com.danielchipolesch.domain.mappers;


import br.com.danielchipolesch.application.dtos.documentDtos.*;
import br.com.danielchipolesch.domain.entities.documentStructure.Document;

public class DocumentMapper {

    public static DocumentResponseDto documentToDocumentResponseDto(Document document){
        return new DocumentResponseDto(
                document.getId(),
                document.getDocumentationType().getAcronym(),
                document.getBasicSubject().getCode(),
                document.getSecondaryNumber(),
                String.format("%s %s-%d", document.getDocumentationType().getAcronym(), document.getBasicSubject().getCode(), document.getSecondaryNumber()),
                document.getBasicSubject().getName(),
                document.getDocumentTitle(),
                document.getDocumentStatus()
//                document.getRegulatoryAct(),
//                document.getDocumentAttachment()
        );
    }
}
