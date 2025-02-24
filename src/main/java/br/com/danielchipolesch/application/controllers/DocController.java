package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.application.dtos.ExceptionDto;
import br.com.danielchipolesch.application.dtos.docDtos.DocRequestCreateDto;
import br.com.danielchipolesch.application.dtos.docDtos.DocResponseDto;
import br.com.danielchipolesch.application.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.domain.entities.documentStructure.Doc;
import br.com.danielchipolesch.domain.mappers.DocMapper;
import br.com.danielchipolesch.domain.services.DocService;
import br.com.danielchipolesch.domain.services.DocStatusManagerService;
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

import java.lang.String;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/v1/api/doc")
public class DocController {

    @Autowired
    private DocService docService;

    @Autowired
    private DocStatusManagerService docStatusManagerService;


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
    public ResponseEntity<DocResponseDto> post(@RequestBody @Valid DocRequestCreateDto request) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.CREATED).body(docService.create(request));
    }

    @PostMapping("{id}/clonar")
    public ResponseEntity<DocResponseDto> clone(@PathVariable(value = "id") String id) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.CREATED).body(docService.clone(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<DocResponseDto>> getById(@PathVariable(value = "id") String id) throws RuntimeException{

        Doc document = docService.getById(id);
        EntityModel<DocResponseDto> resource = EntityModel.of(DocMapper.docToDocumentResponseDto(document));

        Link selfLink = linkTo(methodOn(DocController.class).getById(id)).withSelfRel();
        resource.add(selfLink);

        if (document.getRegulatoryActId() != null) {
            Link linkToRegulatoryAct = linkTo(methodOn(RegulatoryActController.class).getRegulatoryActById(document.getRegulatoryActId())).withRel("portaria");
            Link linkToRegulatoryActPdf = linkTo(methodOn(RegulatoryActController.class).getRegulatoryActPdfById(document.getRegulatoryActId())).withRel("portaria-pdf");
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
    public ResponseEntity<List<DocResponseDto>> getByDocumentationTypeAndBasicSubject(
            @RequestParam(value = "especie-normativa") Long documentTypeId,
            @RequestParam(value = "assunto-basico") Long basicSubjectId) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.OK).body(docService.getByDocumentationTypeAndBasicSubject(documentTypeId, basicSubjectId));
    }

    @GetMapping("/obter-todos")
    public ResponseEntity<List<EntityModel<DocResponseDto>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) throws RuntimeException {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        List<Doc> documentsPageable = docService.getAll(pageable);

        List<EntityModel<DocResponseDto>> documents = documentsPageable.stream()
                .map(documentDto -> {
                    EntityModel<DocResponseDto> resource = EntityModel.of(DocMapper.docToDocumentResponseDto(documentDto));
                    resource.add(linkTo(methodOn(DocController.class).getById(documentDto.getId())).withSelfRel());
                    if (documentDto.getRegulatoryActId() != null) {
                        resource.add(linkTo(methodOn(RegulatoryActController.class).getRegulatoryActById(documentDto.getRegulatoryActId())).withRel("portaria"));
                        resource.add(linkTo(methodOn(RegulatoryActController.class).getRegulatoryActPdfById(documentDto.getRegulatoryActId())).withRel("portaria-pdf"));
                    }
                    return resource;
                }).toList();

        return ResponseEntity.status(HttpStatus.OK).body(documents);
    }

    @PutMapping("{id}/aprovar")
    public ResponseEntity<DocResponseDto> setDocumentAsApproved (@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(docStatusManagerService.approveDocument(id));
    }

    //TODO Create methods to change Document status, for example: setDocumentAsArchived...

    @DeleteMapping("{id}")
    public ResponseEntity<DocResponseDto> delete(@PathVariable(value = "id") String id) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(docService.delete(id));
    }
}