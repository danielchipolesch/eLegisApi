package br.com.danielchipolesch.domain.services;

import br.com.danielchipolesch.domain.builders.DocumentBuilder;
import br.com.danielchipolesch.application.dtos.documentDtos.DocumentRequestCreateDto;
import br.com.danielchipolesch.application.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentStatus;
import br.com.danielchipolesch.domain.entities.documentStructure.TextAttachment;
import br.com.danielchipolesch.domain.entities.documentationNumbering.BasicSubject;
import br.com.danielchipolesch.domain.entities.documentationNumbering.DocumentationType;
import br.com.danielchipolesch.domain.handlers.exceptions.enums.BasicSubjectException;
import br.com.danielchipolesch.domain.handlers.exceptions.enums.DocumentException;
import br.com.danielchipolesch.domain.handlers.exceptions.enums.DocumentationTypeException;
import br.com.danielchipolesch.domain.mappers.DocumentMapper;
import br.com.danielchipolesch.domain.handlers.exceptions.ResourceNotFoundException;
import br.com.danielchipolesch.infrastructure.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    DocumentationTypeRepository documentationTypeRepository;

    @Autowired
    BasicSubjectRepository basicSubjectRepository;

    @Autowired
    TextAttachmentRepository textAttachmentRepository;


    @Transactional
    public DocumentResponseDto create(DocumentRequestCreateDto request) throws RuntimeException {

        DocumentationType documentationType = documentationTypeRepository.findById(request.getIdEspecieNormativa()).orElseThrow(() -> new ResourceNotFoundException(DocumentationTypeException.NOT_FOUND.getMessage()));
        BasicSubject basicSubject = basicSubjectRepository.findById(request.getIdAssuntoBasico()).orElseThrow(() ->  new ResourceNotFoundException(BasicSubjectException.NOT_FOUND.getMessage()));

        var secondaryNumber = this.calculateSecondaryNumber(documentationType, basicSubject);

        Document document = new DocumentBuilder()
                .documentationType(documentationType)
                .basicSubject(basicSubject)
                .secondaryNumber(secondaryNumber)
                .documentTitle(request.getTituloDocumento())
                .documentStatus(DocumentStatus.RASCUNHO)
                .build();

        var newDocument = documentRepository.save(document);
        TextAttachment  textAttachmentCreate = new TextAttachment();
        textAttachmentCreate.setDocument(newDocument);
        textAttachmentRepository.save(textAttachmentCreate);
        return DocumentMapper.documentToDocumentResponseDto(document);
    }

    public Document getById(Long id) throws RuntimeException{

        return documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(DocumentException.NOT_FOUND.getMessage()));
    }

    public List<DocumentResponseDto> getByDocumentationTypeAndBasicSubject(Long documentationTypeId, Long basicSubjectId) throws ResourceNotFoundException {

//        var documentationType = documentationTypeRepository.findById(documentationTypeId).orElseThrow(() -> new ResourceNotFoundException(DocumentationTypeException.NOT_FOUND.getMessage()));
//        var basicSubject = basicSubjectRepository.findById(basicSubjectId).orElseThrow(() -> new ResourceNotFoundException(BasicSubjectException.NOT_FOUND.getMessage()));

        List<Document> documents = documentRepository.findDocumentsWithoutRegulatoryAct(documentationTypeId, basicSubjectId);


        // TODO Corrigir o erro que está impedindo o retorno da lista de documentos quando eles tem o RegulatoryAct associado. O @Lob está retornando um erro.
        return documents.stream().map(DocumentMapper::documentToDocumentResponseDto).toList();
    }

    public List<Document> getAll(Pageable pageable) throws RuntimeException {
        try{
            Page<Document> documents = documentRepository.findAll(pageable);
            return documents.stream().toList();
        } catch (Exception e) {
            throw new ResourceNotFoundException(DocumentException.NOT_FOUND.getMessage());
        }
    }

//    @Transactional
//    public DocumentResponseDto updateDocumentAttachment(Long id, DocumentAttachmentUpdateRequestDto request) throws RuntimeException {
//
//        Document document = documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(DocumentException.NOT_FOUND.getMessage()));
//        if(document.getDocumentStatus() == DocumentStatus.RASCUNHO || document.getDocumentStatus() == DocumentStatus.MINUTA) {
//            var documentAttachmentId = document.getDocumentAttachment().getId();
//            var documentAttachment = documentAttachmentRepository.findById(documentAttachmentId).orElseThrow(() -> new ResourceNotFoundException(DocumentAttachmentException.NOT_FOUND.getMessage()));
//            documentAttachment.setTextAttachment(request.getTextAttachment().isBlank() ? documentAttachment.getTextAttachment() : request.getTextAttachment());
//            documentAttachmentRepository.save(documentAttachment);
//            document.setDocumentStatus(DocumentStatus.MINUTA);
//            documentRepository.save(document);
//            return DocumentMapper.documentToDocumentResponseDto(document);
//        }
//
//        throw new StatusCannotBeUpdatedException(DocumentException.CANNOT_BE_UPDATED.getMessage());
//
//    }

    public DocumentResponseDto delete(Long id) throws RuntimeException {

        Document document = documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(DocumentException.NOT_FOUND.getMessage()));
        documentRepository.delete(document);
        return DocumentMapper.documentToDocumentResponseDto(document);
    }


    public DocumentResponseDto clone(Long id) throws RuntimeException {

        Document documentOld = documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(DocumentException.NOT_FOUND.getMessage()));

//        DocumentAttachment documentAttachmentCreate = new DocumentAttachment();
//        documentAttachmentCreate.setTextAttachment(documentOld.getDocumentAttachment().getTextAttachment());

        var secondaryNumber = this.calculateSecondaryNumber(documentOld.getDocumentationType(), documentOld.getBasicSubject());

        Document documentNew = new DocumentBuilder()
                .documentationType(documentOld.getDocumentationType())
                .basicSubject(documentOld.getBasicSubject())
                .secondaryNumber(secondaryNumber)
                .documentTitle(documentOld.getDocumentTitle())
                .documentStatus(DocumentStatus.RASCUNHO)
//                .documentAttachment(documentAttachmentRepository.save(documentAttachmentCreate))
                .build();

        documentRepository.save(documentNew);
        return DocumentMapper.documentToDocumentResponseDto(documentNew);
    }

    private Integer calculateSecondaryNumber(DocumentationType documentationType, BasicSubject basicSubject){

        List<Document> documents = documentRepository.findDocumentsWithoutRegulatoryAct(documentationType.getId(), basicSubject.getId());

        if (documents.isEmpty()) {
            return 1;
        }

        List<Integer> secondaryNumbers = documents.stream()
                .map(Document::getSecondaryNumber)
                .sorted()
                .toList();

        for (int i = 1; i <= secondaryNumbers.size(); i++) {
            if (!secondaryNumbers.contains(i)) {
                return i;  // Returns smaller available number
            }
        }

        return secondaryNumbers.size() + 1;
    }

}
