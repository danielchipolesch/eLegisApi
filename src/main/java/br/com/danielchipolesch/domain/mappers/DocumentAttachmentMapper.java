package br.com.danielchipolesch.domain.mappers;

import br.com.danielchipolesch.application.dtos.documentAttachmentDtos.DocumentAttachmentResponseDto;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentAttachment;

public class DocumentAttachmentMapper {

    public static DocumentAttachmentResponseDto documentAttachmentToDocumentAttachmentResponseDto(DocumentAttachment documentAttachment){
        return new DocumentAttachmentResponseDto(
                documentAttachment.getId(),
                documentAttachment.getDocument(),
                documentAttachment.getTextAttachment()
        );
    }
}
