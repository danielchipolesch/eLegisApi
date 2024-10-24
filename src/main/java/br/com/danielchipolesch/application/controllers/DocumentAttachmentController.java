package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.application.dtos.documentAttachmentDtos.DocumentAttachmentResponseDto;
import br.com.danielchipolesch.domain.services.DocumentAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class DocumentAttachmentController {

    @Autowired
    DocumentAttachmentService documentAttachmentService;

    @GetMapping("documento/{idDocumento}/portaria")
    public ResponseEntity<DocumentAttachmentResponseDto> getById(@PathVariable(value = "idDocumento") Long idDocumento) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.OK).body(documentAttachmentService.getById(idDocumento));
    }
}
