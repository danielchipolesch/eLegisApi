package br.com.danielchipolesch.domain.services;

import br.com.danielchipolesch.application.dtos.documentoDtos.DocumentoResponseSemAnexoTextualDto;
import br.com.danielchipolesch.application.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.domain.entities.estruturaDocumento.Documento;
import br.com.danielchipolesch.domain.entities.estruturaDocumento.DocumentoStatusEnum;
import br.com.danielchipolesch.domain.handlers.exceptions.ResourceNotFoundException;
import br.com.danielchipolesch.domain.handlers.exceptions.StatusCannotBeUpdatedException;
import br.com.danielchipolesch.domain.handlers.exceptions.enums.DocumentException;
import br.com.danielchipolesch.domain.mappers.DocumentoMapper;
import br.com.danielchipolesch.infrastructure.repositories.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoStatusService {

    @Autowired
    DocumentoRepository documentRepository;

    public DocumentoResponseSemAnexoTextualDto approveDocument(String id) throws RuntimeException {

        Documento document = documentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(DocumentException.NOT_FOUND.getMessage()));
        if (document.getDocumentoStatus().equals(DocumentoStatusEnum.RASCUNHO.name()) || document.getDocumentoStatus().equals(DocumentoStatusEnum.MINUTA.name())) {
            document.setDocumentoStatus(DocumentoStatusEnum.APROVADO.name());
            documentRepository.save(document);
            return DocumentoMapper.documentoToDocumentoSemAnexoTextualResponseDto(document);
        } else if (document.getDocumentoStatus().equals(DocumentoStatusEnum.APROVADO.name())) {
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
