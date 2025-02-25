package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.application.dtos.ExceptionDto;
import br.com.danielchipolesch.application.dtos.documentoDtos.DocumentoRequestCreateDto;
import br.com.danielchipolesch.application.dtos.documentoDtos.DocumentoResponseComAnexoTextualDto;
import br.com.danielchipolesch.application.dtos.documentoDtos.DocumentoResponseSemAnexoTextualDto;
import br.com.danielchipolesch.application.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.domain.entities.estruturaDocumento.Documento;
import br.com.danielchipolesch.domain.mappers.DocumentoMapper;
import br.com.danielchipolesch.domain.services.DocumentoService;
import br.com.danielchipolesch.domain.services.DocumentoStatusService;
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
@RequestMapping(value = "/v1/api/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private DocumentoStatusService documentoStatusService;


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
    public ResponseEntity<DocumentoResponseSemAnexoTextualDto> post(@RequestBody @Valid DocumentoRequestCreateDto request) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentoService.create(request));
    }

    @PostMapping("{id}/clonar")
    public ResponseEntity<DocumentoResponseSemAnexoTextualDto> clone(@PathVariable(value = "id") String id) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentoService.clone(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<DocumentoResponseComAnexoTextualDto>> getById(@PathVariable(value = "id") String id) throws RuntimeException{

        Documento document = documentoService.getById(id);
        EntityModel<DocumentoResponseComAnexoTextualDto> resource = EntityModel.of(DocumentoMapper.documentoToDocumentoComAnexoTextualResponseDto(document));

        Link selfLink = linkTo(methodOn(DocumentoController.class).getById(id)).withSelfRel();
        resource.add(selfLink);

        if (document.getIdPortaria() != null) {
            Link linkToRegulatoryAct = linkTo(methodOn(PortariaController.class).getRegulatoryActById(document.getIdPortaria())).withRel("portaria");
            Link linkToRegulatoryActPdf = linkTo(methodOn(PortariaController.class).getRegulatoryActPdfById(document.getIdPortaria())).withRel("portaria-pdf");
            resource.add(linkToRegulatoryAct);
            resource.add(linkToRegulatoryActPdf);
        }

//        if (document.getItens() != null) {
//            Link LinkToTextAttachment = linkTo(methodOn(TextAttachmentController.class).getById(document.getTextAttachment().getId())).withRel("parteTextual");
//            resource.add(LinkToTextAttachment);
//        }

        return ResponseEntity.status(HttpStatus.OK).body(resource);
    }

    @GetMapping("filtrar")
    public ResponseEntity<List<DocumentoResponseSemAnexoTextualDto>> getByDocumentationTypeAndBasicSubject(
            @RequestParam(value = "especie-normativa") Long documentTypeId,
            @RequestParam(value = "assunto-basico") Long basicSubjectId) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.OK).body(documentoService.getByDocumentationTypeAndBasicSubject(documentTypeId, basicSubjectId));
    }

    @GetMapping("/obter-todos")
    public ResponseEntity<List<EntityModel<DocumentoResponseSemAnexoTextualDto>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) throws RuntimeException {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        List<Documento> documentsPageable = documentoService.getAll(pageable);

        List<EntityModel<DocumentoResponseSemAnexoTextualDto>> documents = documentsPageable.stream()
                .map(documentDto -> {
                    EntityModel<DocumentoResponseSemAnexoTextualDto> resource = EntityModel.of(DocumentoMapper.documentoToDocumentoSemAnexoTextualResponseDto(documentDto));
                    resource.add(linkTo(methodOn(DocumentoController.class).getById(documentDto.getId())).withSelfRel());
                    if (documentDto.getIdPortaria() != null) {
                        resource.add(linkTo(methodOn(PortariaController.class).getRegulatoryActById(documentDto.getIdPortaria())).withRel("portaria"));
                        resource.add(linkTo(methodOn(PortariaController.class).getRegulatoryActPdfById(documentDto.getIdPortaria())).withRel("portaria-pdf"));
                    }
                    return resource;
                }).toList();

        return ResponseEntity.status(HttpStatus.OK).body(documents);
    }

    @PutMapping("{id}/aprovar")
    public ResponseEntity<DocumentoResponseSemAnexoTextualDto> setDocumentAsApproved (@PathVariable(value = "id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(documentoStatusService.approveDocument(id));
    }

    //TODO Create methods to change Document status, for example: setDocumentAsArchived...

    @DeleteMapping("{id}")
    public ResponseEntity<DocumentoResponseSemAnexoTextualDto> delete(@PathVariable(value = "id") String id) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(documentoService.delete(id));
    }
}