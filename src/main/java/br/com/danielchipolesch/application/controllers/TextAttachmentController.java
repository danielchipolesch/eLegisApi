package br.com.danielchipolesch.application.controllers;

import br.com.danielchipolesch.application.dtos.textAttachmentDtos.TextAttachmentResponseDto;
import br.com.danielchipolesch.domain.entities.estruturaDocumento.TextAttachment;
import br.com.danielchipolesch.domain.mappers.TextAttachmentMapper;
import br.com.danielchipolesch.domain.services.TextAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api")
public class TextAttachmentController {

    @Autowired
    TextAttachmentService textAttachmentService;

    @GetMapping("anexo-textual/{idAnexoTextual}")
    public ResponseEntity<EntityModel<TextAttachmentResponseDto>> getById(@PathVariable(value = "idAnexoTextual") Long idAnexoTextual) throws RuntimeException {

        TextAttachment textAttachment = textAttachmentService.getTextAttachmentById(idAnexoTextual);
        EntityModel<TextAttachmentResponseDto> resource = EntityModel.of(TextAttachmentMapper.textAttachmentToTextAttachmentResponseDto(textAttachment));

        Link selfLink = linkTo(methodOn(TextAttachmentController.class).getById(idAnexoTextual)).withSelfRel();
        resource.add(selfLink);

        if (textAttachment.getDocument() != null) {
            Link linkToDocument = linkTo(methodOn(DocumentController.class).getById(textAttachment.getDocument().getId())).withRel("documento");
            resource.add(linkToDocument);
        }

        return ResponseEntity.status(HttpStatus.OK).body(resource);
    }

    //TODO Create Update and Delete methods. Unnecessary method create because it's created at document creation. Must confirm this info!
}
