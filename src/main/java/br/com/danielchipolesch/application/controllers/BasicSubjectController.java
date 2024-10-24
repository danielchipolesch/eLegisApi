package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.application.dtos.basicSubjectDtos.BasicSubjectRequestCreateDto;
import br.com.danielchipolesch.application.dtos.basicSubjectDtos.BasicSubjectRequestUpdateDto;
import br.com.danielchipolesch.application.dtos.basicSubjectDtos.BasicSubjectResponseDto;
import br.com.danielchipolesch.domain.services.BasicSubjectService;
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
@RequestMapping(value = "/api/assunto-basico")
public class BasicSubjectController {

    @Autowired BasicSubjectService basicSubjectService;

    @PostMapping
    public ResponseEntity<BasicSubjectResponseDto> post(@RequestBody @Valid BasicSubjectRequestCreateDto request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(basicSubjectService.create(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<BasicSubjectResponseDto> put(@PathVariable(value = "id") Long id,
                                                            @RequestBody BasicSubjectRequestUpdateDto request) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(basicSubjectService.update(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BasicSubjectResponseDto> delete(@PathVariable(value = "id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(basicSubjectService.delete(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<BasicSubjectResponseDto>  getById(@PathVariable(value = "id") Long id) throws Exception {
        return  ResponseEntity.status(HttpStatus.OK).body(basicSubjectService.getById(id));
    }

    @GetMapping("/obter-por-codigo-assunto-basico/{code}")
    public ResponseEntity<BasicSubjectResponseDto>  getByNumber(@PathVariable(value = "code") String code) throws Exception {
        return  ResponseEntity.status(HttpStatus.OK).body(basicSubjectService.getByNumber(code));
    }

    @GetMapping("obter-todos")
    public ResponseEntity<List<BasicSubjectResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.status(HttpStatus.OK).body(basicSubjectService.getAll(pageable));
    }
}
