package br.com.danielchipolesch.domain.services;

import br.com.danielchipolesch.application.dtos.docDtos.DocResponseDto;
import br.com.danielchipolesch.application.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.domain.entities.documentStructure.Doc;
import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentStatus;
import br.com.danielchipolesch.domain.handlers.exceptions.ResourceNotFoundException;
import br.com.danielchipolesch.domain.handlers.exceptions.StatusCannotBeUpdatedException;
import br.com.danielchipolesch.domain.handlers.exceptions.enums.DocumentException;
import br.com.danielchipolesch.domain.mappers.DocMapper;
import br.com.danielchipolesch.domain.mappers.DocumentMapper;
import br.com.danielchipolesch.infrastructure.repositories.DocRepository;
import br.com.danielchipolesch.infrastructure.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DocStatusManagerService {

    @Autowired
    DocRepository documentRepository;

    public DocResponseDto approveDocument(String id) throws RuntimeException {

        Doc document = documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(DocumentException.NOT_FOUND.getMessage()));
        if (document.getDocumentStatus().equals(DocumentStatus.RASCUNHO.name()) || document.getDocumentStatus().equals(DocumentStatus.MINUTA.name())) {
            document.setDocumentStatus(DocumentStatus.APROVADO.name());
            documentRepository.save(document);
            return DocMapper.docToDocumentResponseDto(document);
        } else if (document.getDocumentStatus().equals(DocumentStatus.APROVADO.name())) {
            throw new StatusCannotBeUpdatedException(DocumentException.APROVADO.getMessage());
        }
        throw new StatusCannotBeUpdatedException(DocumentException.CANNOT_BE_UPDATED.getMessage());
    }

    /* TODO Must review statuses and its usability. */

    public DocumentResponseDto publishDocument(){
        /* TODO Insert logic to publish documents (PUBLICADO). To choose this status, current status must be APROVADO. */
        return null;
    }

    public DocumentResponseDto archiveDocument(){
        /* TODO Insert logic to archive documents (ARQUIVADO). To choose this status, current status must be XYZ. */
        return null;
    }

    public DocumentResponseDto cancelDocument(){
        /* TODO Insert logic to cancel documents (CANCELADO). To choose this status, current status must be ZYX. */
        return null;
    }

    public DocumentResponseDto revokeDocument(){
        /* TODO Insert logic to revoke documents (REVOGAR). To choose this status, current status must be AAAAA. */
        return null;
    }
}
