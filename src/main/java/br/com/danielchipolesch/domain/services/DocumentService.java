package br.com.danielchipolesch.domain.services;

import br.com.danielchipolesch.domain.dtos.documentDtos.DocumentRequestCreateDto;
import br.com.danielchipolesch.domain.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.domain.dtos.documentDtos.DocumentUpdateDocumentAttachmentRequestDto;
import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentAttachment;
import br.com.danielchipolesch.domain.entities.documentationNumbering.BasicSubject;
import br.com.danielchipolesch.domain.entities.documentationNumbering.DocumentationType;
import br.com.danielchipolesch.domain.exceptions.ResourceCannotBeUpdatedException;
import br.com.danielchipolesch.domain.exceptions.enums.BasicSubjectException;
import br.com.danielchipolesch.domain.exceptions.enums.DocumentAttachmentException;
import br.com.danielchipolesch.domain.exceptions.enums.DocumentException;
import br.com.danielchipolesch.domain.exceptions.enums.DocumentationTypeException;
import br.com.danielchipolesch.application.helpers.DocumentMapper;
import br.com.danielchipolesch.domain.exceptions.ResourceNotFoundException;
import br.com.danielchipolesch.infrastructure.repositories.BasicSubjectRepository;
import br.com.danielchipolesch.infrastructure.repositories.DocumentAttachmentRepository;
import br.com.danielchipolesch.infrastructure.repositories.DocumentRepository;
import br.com.danielchipolesch.infrastructure.repositories.DocumentationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    //TODO Create logic to new documents
    public DocumentResponseDto create(DocumentRequestCreateDto request) throws Exception {

        BasicSubject basicSubject = basicSubjectRepository.findById(request.getBasicSubjectId()).orElseThrow(() ->  new ResourceNotFoundException(BasicSubjectException.NOT_FOUND.getMessage()));
        DocumentationType documentationType = documentationTypeRepository.findById(request.getDocumentationTypeId()).orElseThrow(() -> new ResourceNotFoundException(DocumentationTypeException.NOT_FOUND.getMessage()));

        DocumentAttachment documentAttachmentCreate = new DocumentAttachment();
        documentAttachmentCreate.setTextAttachment("Insira o texto do documento.");

        var secondaryNumber = calculateSecondaryNumber(documentationType, basicSubject);

        Document document = new Document();

        document.setBasicSubject(basicSubject);
        document.setDocumentationType(documentationType);
        document.setSecondaryNumber(secondaryNumber);
        document.setDocumentTitle(request.getDocumentTitle());
        document.setDocumentStatusEnum(DocumentStatusEnum.RASCUNHO);
        document.setDocumentAttachment(documentAttachmentRepository.save(documentAttachmentCreate));
        documentRepository.save(document);
        return DocumentMapper.documentToDocumentResponseDto(document);
    }

    public DocumentResponseDto getById(Long id) throws ResourceNotFoundException{

        Document document = documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(DocumentException.NOT_FOUND.getMessage()));
        return DocumentMapper.documentToDocumentResponseDto(document);
    }
}
