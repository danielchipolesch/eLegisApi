package br.com.danielchipolesch.domain.services;

import br.com.danielchipolesch.application.dtos.fileAttachmentDtos.FileAttachmentResponseDto;
import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import br.com.danielchipolesch.domain.entities.documentStructure.FileAttachment;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentStatus;
import br.com.danielchipolesch.domain.handlers.exceptions.ResourceNotFoundException;
import br.com.danielchipolesch.domain.handlers.exceptions.StatusCannotBeUpdatedException;
import br.com.danielchipolesch.domain.handlers.exceptions.enums.DocumentAttachmentException;
import br.com.danielchipolesch.domain.handlers.exceptions.enums.DocumentException;
import br.com.danielchipolesch.domain.mappers.FileAttachmentMapper;
import br.com.danielchipolesch.infrastructure.repositories.FileAttachmentRepository;
import br.com.danielchipolesch.infrastructure.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileAttachmentService {

    @Autowired
    FileAttachmentRepository fileAttachmentRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Transactional
    public FileAttachmentResponseDto insertRegulatoryActInDocument(Long id, MultipartFile file) throws RuntimeException, IOException {
        Document document = documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(DocumentException.NOT_FOUND.getMessage()));

        //TODO Create validation for each status
        if (document.getDocumentStatus() != DocumentStatus.APROVADO){
            throw new StatusCannotBeUpdatedException(DocumentException.DOCUMENT_ACT_APROVADO.getMessage());
        }

        FileAttachment fileAttachment = new FileAttachment();
        fileAttachment.setDocument(document);
        fileAttachment.setFileName(file.isEmpty() ? null : file.getOriginalFilename());
        fileAttachment.setData(file.isEmpty() ? null : file.getBytes());
        fileAttachmentRepository.save(fileAttachment);
        return FileAttachmentMapper.documentFileAttachmentToDocumentFileAttachmentResponseDto(fileAttachment);
    }

    public FileAttachmentResponseDto getById(Long id) throws RuntimeException{

        FileAttachment fileAttachment = fileAttachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(DocumentAttachmentException.NOT_FOUND.getMessage()));
        return FileAttachmentMapper.documentFileAttachmentToDocumentFileAttachmentResponseDto(fileAttachment);
    }

    //TODO Create Insert, Update and Delete methods
}
