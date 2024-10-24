package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.application.dtos.regulatoryActDtos.RegulatoryActResponseDto;
import br.com.danielchipolesch.domain.services.RegulatoryActService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/portaria")
public class RegulatoryActController {

    @Autowired
    private RegulatoryActService regulatoryActService;

    @PostMapping(value = "documento/{id}/incluir-pdf", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<RegulatoryActResponseDto> insertDocumentAct(@PathVariable(value = "id") @Valid Long idDocumento,
                                                                 @RequestParam("file") @Valid MultipartFile file) throws RuntimeException, IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(regulatoryActService.insertRegulatoryActInDocument(idDocumento, file));
    }


    @GetMapping("documento/{id}")
    public ResponseEntity<byte[]> getRegulatoryActPdfById(@PathVariable(value = "id") @Valid Long id) throws RuntimeException {

        // Busca o arquivo PDF através do service
        RegulatoryActResponseDto pdfFile = regulatoryActService.getByDocumentId(id);

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
    public ResponseEntity<List<RegulatoryActResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) throws RuntimeException {

        // TODO Incluir link direto para o PDF usando Spring HATEOAS

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.status(HttpStatus.OK).body(regulatoryActService.getAll(pageable));
    }
}