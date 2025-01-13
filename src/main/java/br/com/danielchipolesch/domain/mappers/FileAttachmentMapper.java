package br.com.danielchipolesch.domain.mappers;

import br.com.danielchipolesch.application.dtos.fileAttachmentDtos.FileAttachmentResponseDto;
import br.com.danielchipolesch.domain.entities.documentStructure.FileAttachment;

public class FileAttachmentMapper {

    public static FileAttachmentResponseDto documentFileAttachmentToDocumentFileAttachmentResponseDto(FileAttachment fileAttachment){
        return new FileAttachmentResponseDto(
                fileAttachment.getId(),
                fileAttachment.getFileName(),
                DocumentMapper.documentToDocumentResponseDto(fileAttachment.getDocument()),
                fileAttachment.getData()
        );
    }
}
