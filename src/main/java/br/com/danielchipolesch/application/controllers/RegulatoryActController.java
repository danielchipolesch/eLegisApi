package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.application.dtos.regulatoryActDtos.RegulatoryActDto;
import br.com.danielchipolesch.application.dtos.regulatoryActDtos.RegulatoryActResponseDto;
import br.com.danielchipolesch.application.dtos.regulatoryActDtos.RegulatoryActResponseNoPdfDto;
import br.com.danielchipolesch.domain.mappers.RegulatoryActMapper;
import br.com.danielchipolesch.domain.services.RegulatoryActService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/portaria")
public class RegulatoryActController {

    @Autowired
    private RegulatoryActService regulatoryActService;

    @PostMapping(value = "documento/{id}/incluir-pdf", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<RegulatoryActResponseNoPdfDto> insertDocumentAct(@PathVariable(value = "id") @Valid Long idDocumento,
                                                                           @RequestParam("file") @Valid MultipartFile file) throws RuntimeException, IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(regulatoryActService.insertRegulatoryActInDocument(idDocumento, file));
    }

    @GetMapping("{idPortaria}")
    public ResponseEntity<EntityModel<RegulatoryActResponseNoPdfDto>> getRegulatoryActById(@PathVariable(value = "idPortaria") @Valid Long idPortaria) throws RuntimeException {

        RegulatoryActResponseNoPdfDto regulatoryActResponseDto = regulatoryActService.getRegulatoryActNoPdfById(idPortaria);
        EntityModel<RegulatoryActResponseNoPdfDto> resource = EntityModel.of(regulatoryActResponseDto);

        Link selfLink = linkTo(methodOn(RegulatoryActController.class).getRegulatoryActById(idPortaria)).withSelfRel();
        resource.add(selfLink);

        resource.add(linkTo(methodOn(RegulatoryActController.class).getRegulatoryActPdfById(regulatoryActResponseDto.getIdPortaria())).withRel("portaria-pdf"));
        resource.add(linkTo(methodOn(DocumentController.class).getById(regulatoryActResponseDto.getIdPortaria())).withRel("documento"));

        return ResponseEntity.status(HttpStatus.OK).body(resource);
    }

    @GetMapping("{idPortaria}/pdf")
    public ResponseEntity<byte[]> getRegulatoryActPdfById(@PathVariable(value = "idPortaria") @Valid Long idPortaria) throws RuntimeException {
        RegulatoryActResponseDto pdfFile = regulatoryActService.getById(idPortaria);

        // Define os headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);  // Define o tipo como PDF
        headers.setContentDispositionFormData("inline", pdfFile.getNomePortaria());  // Define a exibição como inline

        // Retorna a resposta com status OK e o conteúdo do PDF em bytes
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)  // Define os headers
                .contentType(MediaType.APPLICATION_PDF)  // Define o tipo de conteúdo
                .body(pdfFile.getDadoBase64());  // Retorna o conteúdo do PDF
    }

    @GetMapping("documento/{idDocumento}/pdf")
    public ResponseEntity<byte[]> getRegulatoryActPdfByDocumentId(@PathVariable(value = "idDocumento") @Valid Long idDocumento) throws RuntimeException {

        // Busca o arquivo PDF através do service
        RegulatoryActResponseDto pdfFile = regulatoryActService.getByDocumentId(idDocumento);

        // Define os headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);  // Define o tipo como PDF
        headers.setContentDispositionFormData("inline", pdfFile.getNomePortaria());  // Define a exibição como inline

        // Retorna a resposta com status OK e o conteúdo do PDF em bytes
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)  // Define os headers
                .contentType(MediaType.APPLICATION_PDF)  // Define o tipo de conteúdo
                .body(pdfFile.getDadoBase64());  // Retorna o conteúdo do PDF
    }

    @GetMapping("obter-todas")
    public ResponseEntity<List<EntityModel<RegulatoryActResponseNoPdfDto>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) throws RuntimeException {

        // TODO Incluir link direto para o PDF usando Spring HATEOAS

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        List<RegulatoryActDto> regulatoryActsPageable = regulatoryActService.getAll(pageable);

        List<EntityModel<RegulatoryActResponseNoPdfDto>> regulatoryActs = regulatoryActsPageable.stream()
                .map(regulatoryAct -> {
                    RegulatoryActResponseNoPdfDto regulatoryActResponseDto = RegulatoryActMapper.regulatoryActDtoToRegulatoryActResponseNoPdfDto(regulatoryAct);
                    EntityModel<RegulatoryActResponseNoPdfDto> resource = EntityModel.of(regulatoryActResponseDto);

                    Link selfLink = linkTo(methodOn(RegulatoryActController.class).getRegulatoryActById(regulatoryActResponseDto.getIdPortaria())).withSelfRel();
                    resource.add(selfLink);

                    resource.add(linkTo(methodOn(RegulatoryActController.class).getRegulatoryActPdfById(regulatoryAct.getIdPortaria())).withRel("portaria-pdf"));
                    resource.add(linkTo(methodOn(DocumentController.class).getById(regulatoryAct.getDocumento().getIdDocumento())).withRel("documento"));

                    return resource;
                }).toList();

        return ResponseEntity.status(HttpStatus.OK).body(regulatoryActs);
    }
}