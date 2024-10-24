package br.com.danielchipolesch.domain.services;

import br.com.danielchipolesch.application.dtos.documentAttachmentDtos.DocumentAttachmentResponseDto;
import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentAttachment;
import br.com.danielchipolesch.domain.handlers.exceptions.ResourceNotFoundException;
import br.com.danielchipolesch.domain.handlers.exceptions.enums.DocumentAttachmentException;

import br.com.danielchipolesch.domain.mappers.DocumentAttachmentMapper;
import br.com.danielchipolesch.infrastructure.repositories.DocumentAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentAttachmentService {

    @Autowired
    DocumentAttachmentRepository documentAttachmentRepository;

    public DocumentAttachmentResponseDto getById(Long id) throws RuntimeException{

        DocumentAttachment documentAttachment = documentAttachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(DocumentAttachmentException.NOT_FOUND.getMessage()));
        return DocumentAttachmentMapper.documentAttachmentToDocumentAttachmentResponseDto(documentAttachment);
    }
}
