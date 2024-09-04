package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.domain.dtos.documentationTypeDtos.DocumentationTypeCreateRequestDto;
import br.com.danielchipolesch.domain.dtos.documentationTypeDtos.DocumentationTypeResponseDto;
import br.com.danielchipolesch.domain.dtos.documentationTypeDtos.DocumentationTypeUpdateRequestDto;
import br.com.danielchipolesch.domain.services.DocumentationTypeService;
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
@RequestMapping(value = "/api/documentation-type")
@CrossOrigin(origins = "*")
public class DocumentationTypeController {

    @Autowired
    DocumentationTypeService documentationTypeService;

    @PostMapping
    public ResponseEntity<DocumentationTypeResponseDto> post(@RequestBody @Valid DocumentationTypeCreateRequestDto request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentationTypeService.create(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<DocumentationTypeResponseDto> put(@PathVariable(value = "id") Long id,
                                                            @RequestBody DocumentationTypeUpdateRequestDto request) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(documentationTypeService.update(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DocumentationTypeResponseDto> delete(@PathVariable(value = "id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(documentationTypeService.delete(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<DocumentationTypeResponseDto>  getById(@PathVariable(value = "id") Long id) throws Exception {
        return  ResponseEntity.status(HttpStatus.OK).body(documentationTypeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<DocumentationTypeResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.status(HttpStatus.OK).body(documentationTypeService.getAll(pageable));
    }
}
