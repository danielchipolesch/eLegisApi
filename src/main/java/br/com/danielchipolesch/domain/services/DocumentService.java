package br.com.danielchipolesch.domain.services;

import br.com.danielchipolesch.domain.dtos.documentDtos.DocumentRequestCreateDto;
import br.com.danielchipolesch.domain.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.infrastructure.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    //TODO Create logic to new documents
    public DocumentResponseDto create(DocumentRequestCreateDto request) throws Exception {
        return null;
    }
}
