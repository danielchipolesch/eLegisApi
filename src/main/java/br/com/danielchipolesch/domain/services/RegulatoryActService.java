package br.com.danielchipolesch.domain.services;

import br.com.danielchipolesch.application.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.application.dtos.regulatoryActDtos.RegulatoryActRequestDto;
import br.com.danielchipolesch.application.dtos.regulatoryActDtos.RegulatoryActResponseDto;
import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentStatus;
import br.com.danielchipolesch.domain.entities.documentStructure.RegulatoryAct;
import br.com.danielchipolesch.domain.handlers.exceptions.ResourceNotFoundException;
import br.com.danielchipolesch.domain.handlers.exceptions.StatusCannotBeUpdatedException;
import br.com.danielchipolesch.domain.handlers.exceptions.enums.DocumentException;
import br.com.danielchipolesch.domain.handlers.exceptions.enums.RegulatoryActException;
import br.com.danielchipolesch.domain.mappers.RegulatoryActMapper;
import br.com.danielchipolesch.infrastructure.repositories.DocumentRepository;
import br.com.danielchipolesch.infrastructure.repositories.RegulatoryActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class RegulatoryActService {

    @Autowired
    RegulatoryActRepository regulatoryActRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Transactional
    public RegulatoryActResponseDto insertRegulatoryActInDocument(Long id, MultipartFile file) throws RuntimeException, IOException {
        Document document = documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(DocumentException.NOT_FOUND.getMessage()));
        if (document.getDocumentStatus() != DocumentStatus.APROVADO){
            throw new StatusCannotBeUpdatedException(DocumentException.DOCUMENT_ACT_APROVADO.getMessage());
        }

        RegulatoryAct regulatoryAct = new RegulatoryAct();
        regulatoryAct.setDocument(document);
        regulatoryAct.setFileName(file.isEmpty() ? null : file.getOriginalFilename());
        regulatoryAct.setData(file.isEmpty() ? null : file.getBytes());
        regulatoryActRepository.save(regulatoryAct);
        return RegulatoryActMapper.regulatoryActToResponseActResponseDto(regulatoryAct);
    }

    public List<RegulatoryActResponseDto> getAll(Pageable pageable) throws RuntimeException {
        try{
            Page<RegulatoryAct> regulatoryActs = regulatoryActRepository.findAll(pageable);
            return regulatoryActs.stream().map(RegulatoryActMapper::regulatoryActToResponseActResponseDto).toList();
        } catch (Exception e) {
            throw new ResourceNotFoundException(RegulatoryActException.NOT_FOUND.getMessage());
        }
    }

    public RegulatoryActResponseDto getByDocumentId(Long documentId) throws RuntimeException {

        Document document = documentRepository.findById(documentId).orElseThrow(() -> new ResourceNotFoundException(DocumentException.NOT_FOUND.getMessage()));
        return RegulatoryActMapper.regulatoryActToResponseActResponseDto(document.getRegulatoryAct());
    }

    @Transactional
    public DocumentResponseDto updateRegulatoryActInDocument(Long id, RegulatoryActRequestDto request) throws RuntimeException, IOException {
        return null;
    }
}
