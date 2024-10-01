package br.com.danielchipolesch.application.dtos.documentDtos;

import br.com.danielchipolesch.domain.entities.documentStructure.DocumentAct;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentAttachment;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentResponseDto {

    private Long id;

    private String documentationTypeAcronym;

    private String basicSubjectCode;

    private String basicSubjectName;

    private Integer secondaryNumber;

    private String documentTitle;

    private DocumentStatus documentStatus;

    private DocumentAct documentAct;

    private DocumentAttachment documentAttachment;

    private String createdAt;

    private String updatedAt;

    private Integer version;
}
