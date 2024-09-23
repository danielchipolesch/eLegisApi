package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.domain.dtos.documentDtos.DocumentRequestCreateDto;
import br.com.danielchipolesch.domain.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.domain.dtos.documentDtos.DocumentUpdateDocumentAttachmentRequestDto;
import br.com.danielchipolesch.domain.dtos.documentationTypeDtos.DocumentationTypeRequestUpdateDto;
import br.com.danielchipolesch.domain.dtos.documentationTypeDtos.DocumentationTypeResponseDto;
import br.com.danielchipolesch.domain.services.DocumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/documento")
public class DocumentController {

    @Autowired
    private DocumentService documentService;


    @PostMapping
    public ResponseEntity<DocumentResponseDto> post(@RequestBody @Valid DocumentRequestCreateDto request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.create(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<DocumentResponseDto> getById(@PathVariable(value = "id") Long id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(documentService.getById(id));
    }

    @GetMapping("/especie-normativa/{documentTypeId}/assunto-basico/{basicSubjectId}")
    public ResponseEntity<List<DocumentResponseDto>> getByDocumentationTypeAndBasicSubject(
            @PathVariable(value = "documentTypeId") Long documentTypeId,
            @PathVariable(value = "basicSubjectId") Long basicSubjectId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(documentService.getByDocumentationTypeAndBasicSubject(documentTypeId, basicSubjectId));
    }

    @GetMapping
    public ResponseEntity<List<DocumentResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) throws Exception {


        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.status(HttpStatus.OK).body(documentService.getAll(pageable));
    }

    @PutMapping("{id}")
    public ResponseEntity<DocumentResponseDto> putDocumentAttachmentInDocument(@PathVariable(value = "id") Long id,
                                                                               @RequestBody DocumentUpdateDocumentAttachmentRequestDto request) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(documentService.updateDocumentAttachment(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DocumentResponseDto> delete(@PathVariable(value = "id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(documentService.delete(id));
    }

    @PostMapping("{id}/clone")
    public ResponseEntity<DocumentResponseDto> clone(@PathVariable(value = "id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.clone(id));
    }
}
