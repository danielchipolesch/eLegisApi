package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.application.dtos.ExceptionDto;
import br.com.danielchipolesch.application.dtos.documentDtos.DocumentRequestCreateDto;
import br.com.danielchipolesch.application.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.application.dtos.documentDtos.DocumentUpdateDocumentAttachmentRequestDto;
import br.com.danielchipolesch.domain.services.DocumentService;
import br.com.danielchipolesch.domain.services.DocumentStatusManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Autowired
    private DocumentStatusManagerService documentStatusManagerService;


    @PostMapping
    @Operation(summary = "Cria um novo documento", description = "Espécie normativa e número básico precisam ser válidos. Número secundário é criado automaticamente de acordo com o menor sequencial disponível.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Criação bem sucedida",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DocumentResponseDto.class)
                            )
            }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Assunto Básico ou Espécie Normativa não encontrada",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class)
                            )
                    }
            )
    })
    public ResponseEntity<DocumentResponseDto> post(@RequestBody @Valid DocumentRequestCreateDto request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.create(request));
    }

    @PostMapping("{id}/clone")
    public ResponseEntity<DocumentResponseDto> clone(@PathVariable(value = "id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.clone(id));
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

    @PutMapping("{id}/anexo")
    public ResponseEntity<DocumentResponseDto> putDocumentAttachmentDocument(@PathVariable(value = "id") Long id,
                                                                             @RequestBody DocumentUpdateDocumentAttachmentRequestDto request) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(documentService.updateDocumentAttachment(id, request));
    }

    @PutMapping("{id}/aprovar")
    public ResponseEntity<DocumentResponseDto> setDocumentAsApproved (@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(documentStatusManagerService.approveDocument(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DocumentResponseDto> delete(@PathVariable(value = "id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(documentService.delete(id));
    }
}
