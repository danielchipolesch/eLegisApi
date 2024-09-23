package br.com.danielchipolesch.domain.dtos.documentDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DocumentUpdateDocumentAttachmentRequestDto {

    @NotBlank
    private String textAttachment;
}
