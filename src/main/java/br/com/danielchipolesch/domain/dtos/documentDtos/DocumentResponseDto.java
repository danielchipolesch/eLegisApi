package br.com.danielchipolesch.domain.dtos.documentDtos;

import br.com.danielchipolesch.domain.entities.documentStructure.DocumentAct;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentAttachment;
import br.com.danielchipolesch.domain.entities.documentationNumbering.BasicSubject;
import br.com.danielchipolesch.domain.entities.documentationNumbering.DocumentationType;
import br.com.danielchipolesch.domain.services.DocumentStatusEnum;
import lombok.Data;


import java.sql.Timestamp;

@Data
public class DocumentResponseDto {

    private Long id;

    private BasicSubject basicSubject;

    private DocumentationType documentationType;

    private Integer secondaryNumber;

    private String documentTitle;

    private DocumentStatusEnum documentStatusEnum;

    private DocumentAct documentAct;

    private DocumentAttachment documentAttachment;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Integer version;


}
