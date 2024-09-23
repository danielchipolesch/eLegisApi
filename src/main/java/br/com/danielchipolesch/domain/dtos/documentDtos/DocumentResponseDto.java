package br.com.danielchipolesch.domain.dtos.documentDtos;

import br.com.danielchipolesch.domain.entities.documentStructure.DocumentAct;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentAttachment;
import br.com.danielchipolesch.domain.services.DocumentStatusEnum;
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

    private DocumentStatusEnum documentStatusEnum;

    private DocumentAct documentAct;

    private DocumentAttachment documentAttachment;

    private String createdAt;

    private String updatedAt;

    private Integer version;
}
