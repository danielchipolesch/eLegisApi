package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.application.dtos.documentationTypeDtos.DocumentationTypeRequestCreateDto;
import br.com.danielchipolesch.application.dtos.documentationTypeDtos.DocumentationTypeResponseDto;
import br.com.danielchipolesch.application.dtos.documentationTypeDtos.DocumentationTypeRequestUpdateDto;
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
@RequestMapping(value = "/api/especie-normativa")
//@CrossOrigin(origins = "*")
public class DocumentationTypeController {

    @Autowired
    DocumentationTypeService documentationTypeService;

    @PostMapping
    public ResponseEntity<DocumentationTypeResponseDto> post(@RequestBody @Valid DocumentationTypeRequestCreateDto request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentationTypeService.create(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<DocumentationTypeResponseDto>  getById(@PathVariable(value = "id") Long id) throws Exception {
        return  ResponseEntity.status(HttpStatus.OK).body(documentationTypeService.getById(id));
    }

    @GetMapping("obter-todos")
    public ResponseEntity<List<DocumentationTypeResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.status(HttpStatus.OK).body(documentationTypeService.getAll(pageable));
    }

    @PutMapping("{id}")
    public ResponseEntity<DocumentationTypeResponseDto> put(@PathVariable(value = "id") Long id,
                                                            @RequestBody DocumentationTypeRequestUpdateDto request) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(documentationTypeService.update(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DocumentationTypeResponseDto> delete(@PathVariable(value = "id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(documentationTypeService.delete(id));
    }
}
