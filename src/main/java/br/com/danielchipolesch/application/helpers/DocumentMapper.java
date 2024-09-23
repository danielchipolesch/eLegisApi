package br.com.danielchipolesch.application.helpers;


import br.com.danielchipolesch.domain.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.domain.entities.documentStructure.Document;

public class DocumentMapper {

    public static DocumentResponseDto documentToDocumentResponseDto(Document document){
        return new DocumentResponseDto(
                document.getId(),
                document.getDocumentationType().getAcronym(),
                document.getBasicSubject().getCode(),
                document.getBasicSubject().getName(),
                document.getSecondaryNumber(),
                document.getDocumentTitle(),
                document.getDocumentStatusEnum(),
                document.getDocumentAct(),
                document.getDocumentAttachment(),
                document.getCreatedAt().toString(),
                document.getUpdatedAt().toString(),
                document.getVersion()
        );
    }

}
