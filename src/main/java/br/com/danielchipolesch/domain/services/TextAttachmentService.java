package br.com.danielchipolesch.domain.services;

import br.com.danielchipolesch.domain.entities.estruturaDocumento.TextAttachment;
import br.com.danielchipolesch.domain.handlers.exceptions.ResourceNotFoundException;
import br.com.danielchipolesch.domain.handlers.exceptions.enums.DocumentAttachmentException;

import br.com.danielchipolesch.infrastructure.repositories.DocumentRepository;
import br.com.danielchipolesch.infrastructure.repositories.TextAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextAttachmentService {

    @Autowired
    TextAttachmentRepository textAttachmentRepository;

    @Autowired
    DocumentRepository documentRepository;

    public TextAttachment getTextAttachmentById(Long textAttachmentId) throws RuntimeException {

        return textAttachmentRepository.findById(textAttachmentId).orElseThrow(() -> new ResourceNotFoundException(DocumentAttachmentException.NOT_FOUND.getMessage()));
    }

}
