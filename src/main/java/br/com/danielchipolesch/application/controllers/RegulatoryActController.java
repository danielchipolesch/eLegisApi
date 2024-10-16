package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.application.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.application.dtos.documentDtos.RegulatoryActResponseDto;
import br.com.danielchipolesch.domain.services.RegulatoryActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController("/api/documento")
@RequestMapping(value = "/api/documento")
public class RegulatoryActController {

    @Autowired
    private RegulatoryActService regulatoryActService;

    @PostMapping("{id}/portaria")
    public ResponseEntity<DocumentResponseDto> insertDocumentAct(@PathVariable(value = "id") Long id,
                                                                 @RequestParam("file") MultipartFile file) throws RuntimeException, IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(regulatoryActService.insertRegulatoryActInDocument(id, file));
    }


    @GetMapping("{id}/portaria")
    public ResponseEntity<byte[]> viewPdf(@PathVariable(value = "id") Long id) throws RuntimeException {

        // Busca o arquivo PDF através do service
        RegulatoryActResponseDto pdfFile = regulatoryActService.getRegulatoryActfromDocument(id);

        // Define os headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);  // Define o tipo como PDF
        headers.setContentDispositionFormData("inline", pdfFile.getFileName());  // Define a exibição como inline


        // Retorna a resposta com status OK e o conteúdo do PDF em bytes
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)  // Define os headers
                .contentType(MediaType.APPLICATION_PDF)  // Define o tipo de conteúdo
                .body(pdfFile.getData());  // Retorna o conteúdo do PDF
    }
}