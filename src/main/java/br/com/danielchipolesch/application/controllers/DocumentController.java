package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.domain.dtos.documentDtos.DocumentRequestCreateDto;
import br.com.danielchipolesch.domain.dtos.documentDtos.DocumentResponseDto;
import br.com.danielchipolesch.domain.services.DocumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/documento")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentResponseDto> post(@RequestBody @Valid DocumentRequestCreateDto request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.create(request));
    }
}
