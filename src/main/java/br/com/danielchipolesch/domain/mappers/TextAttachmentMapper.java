package br.com.danielchipolesch.domain.mappers;

import br.com.danielchipolesch.application.dtos.textAttachmentDtos.TextAttachmentResponseDto;
import br.com.danielchipolesch.domain.entities.estruturaDocumento.TextAttachment;

public class TextAttachmentMapper {

    public static TextAttachmentResponseDto textAttachmentToTextAttachmentResponseDto(TextAttachment textAttachment){
        return new TextAttachmentResponseDto(
                textAttachment.getId(),
//                DocumentMapper.documentToDocumentResponseDto(textAttachment.getDocument()),
                textAttachment.getTextAttachment()
        );
    }

}
