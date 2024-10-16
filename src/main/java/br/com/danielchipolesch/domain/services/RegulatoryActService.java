package br.com.danielchipolesch.domain.services;

import br.com.danielchipolesch.application.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.application.dtos.documentDtos.RegulatoryActRequestDto;
import br.com.danielchipolesch.application.dtos.documentDtos.RegulatoryActResponseDto;
import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentStatus;
import br.com.danielchipolesch.domain.entities.documentStructure.RegulatoryAct;
import br.com.danielchipolesch.domain.handlers.exceptions.ResourceNotFoundException;
import br.com.danielchipolesch.domain.handlers.exceptions.StatusCannotBeUpdatedException;
import br.com.danielchipolesch.domain.handlers.exceptions.enums.DocumentException;
import br.com.danielchipolesch.domain.mappers.DocumentMapper;
import br.com.danielchipolesch.infrastructure.repositories.DocumentRepository;
import br.com.danielchipolesch.infrastructure.repositories.RegulatoryActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class RegulatoryActService {

    @Autowired
    RegulatoryActRepository regulatoryActRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Transactional
    public DocumentResponseDto insertRegulatoryActInDocument(Long id, MultipartFile file) throws RuntimeException, IOException {
        Document document = documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(DocumentException.NOT_FOUND.getMessage()));
        if (document.getDocumentStatus() != DocumentStatus.APROVADO){
            throw new StatusCannotBeUpdatedException(DocumentException.DOCUMENT_ACT_APROVADO.getMessage());
        }

        RegulatoryAct regulatoryAct = new RegulatoryAct();
        regulatoryAct.setFileName(file.isEmpty() ? null : file.getOriginalFilename());
        regulatoryAct.setFileName(file.isEmpty() ? null : file.getOriginalFilename());
        regulatoryAct.setData(file.isEmpty() ? null : file.getBytes());
        var newRegulatoryAct = regulatoryActRepository.save(regulatoryAct);
        document.setRegulatoryAct(newRegulatoryAct);
        documentRepository.save(document);
        return DocumentMapper.documentToDocumentResponseDto(document);
    }

    @Transactional
    public DocumentResponseDto updateRegulatoryActInDocument(Long id, RegulatoryActRequestDto request) throws RuntimeException, IOException {
        return null;
    }

    public RegulatoryActResponseDto getRegulatoryActfromDocument(Long id) throws RuntimeException {

        Document document = documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(DocumentException.NOT_FOUND.getMessage()));
        return DocumentMapper.documentToRegulatoryActResponseDto(document);
    }
}
