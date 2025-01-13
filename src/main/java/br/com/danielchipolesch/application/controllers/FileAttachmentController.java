package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.application.dtos.textAttachmentDtos.TextAttachmentResponseDto;
import br.com.danielchipolesch.domain.services.TextAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/anexo-arquivo")
public class FileAttachmentController {
}
