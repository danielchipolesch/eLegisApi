package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.application.dtos.ExceptionDto;
import br.com.danielchipolesch.application.dtos.documentDtos.*;
import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import br.com.danielchipolesch.domain.mappers.DocumentMapper;
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
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public ResponseEntity<DocumentResponseDto> post(@RequestBody @Valid DocumentRequestCreateDto request) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.create(request));
    }

    @PostMapping("{id}/clonar")
    public ResponseEntity<DocumentResponseDto> clone(@PathVariable(value = "id") Long id) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.clone(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<DocumentResponseDto>> getById(@PathVariable(value = "id") Long id) throws RuntimeException{

        Document document = documentService.getById(id);
        EntityModel<DocumentResponseDto> resource = EntityModel.of(DocumentMapper.documentToDocumentResponseDto(document));

        Link selfLink = linkTo(methodOn(DocumentController.class).getById(id)).withSelfRel();
        resource.add(selfLink);

        if (document.getRegulatoryAct() != null) {
            Link linkToRegulatoryAct = linkTo(methodOn(RegulatoryActController.class).getRegulatoryActById(document.getRegulatoryAct().getId())).withRel("portaria");
            Link linkToRegulatoryActPdf = linkTo(methodOn(RegulatoryActController.class).getRegulatoryActPdfById(document.getRegulatoryAct().getId())).withRel("portaria-pdf");
            resource.add(linkToRegulatoryAct);
            resource.add(linkToRegulatoryActPdf);
        }

        if (document.getTextAttachment() != null) {
            Link LinkToTextAttachment = linkTo(methodOn(TextAttachmentController.class).getById(document.getTextAttachment().getId())).withRel("parteTextual");
            resource.add(LinkToTextAttachment);
        }

        return ResponseEntity.status(HttpStatus.OK).body(resource);
    }

    @GetMapping("filtrar")
    public ResponseEntity<List<DocumentResponseDto>> getByDocumentationTypeAndBasicSubject(
            @RequestParam(value = "especie-normativa") Long documentTypeId,
            @RequestParam(value = "assunto-basico") Long basicSubjectId) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.OK).body(documentService.getByDocumentationTypeAndBasicSubject(documentTypeId, basicSubjectId));
    }

    @GetMapping("/obter-todos")
    public ResponseEntity<List<EntityModel<DocumentResponseDto>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) throws RuntimeException {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        List<Document> documentsPageable = documentService.getAll(pageable);

        List<EntityModel<DocumentResponseDto>> documents = documentsPageable.stream()
                .map(documentDto -> {
                    EntityModel<DocumentResponseDto> resource = EntityModel.of(DocumentMapper.documentToDocumentResponseDto(documentDto));
                    resource.add(linkTo(methodOn(DocumentController.class).getById(documentDto.getId())).withSelfRel());
                    if (documentDto.getRegulatoryAct() != null) {
                        resource.add(linkTo(methodOn(RegulatoryActController.class).getRegulatoryActById(documentDto.getRegulatoryAct().getId())).withRel("portaria"));
                        resource.add(linkTo(methodOn(RegulatoryActController.class).getRegulatoryActPdfById(documentDto.getRegulatoryAct().getId())).withRel("portaria-pdf"));
                    }
                    return resource;
                }).toList();

        return ResponseEntity.status(HttpStatus.OK).body(documents);
    }

    @PutMapping("{id}/aprovar")
    public ResponseEntity<DocumentResponseDto> setDocumentAsApproved (@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(documentStatusManagerService.approveDocument(id));
    }

    //TODO Create methods to change Document status, for example: setDocumentAsArchived...

    @DeleteMapping("{id}")
    public ResponseEntity<DocumentResponseDto> delete(@PathVariable(value = "id") Long id) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(documentService.delete(id));
    }
}