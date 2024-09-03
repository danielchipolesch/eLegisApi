package br.com.danielchipolesch.domain.services;

import br.com.danielchipolesch.domain.exceptions.DocumentationTypeException;
import br.com.danielchipolesch.domain.dtos.documentationTypeDtos.DocumentationTypeCreateRequestDto;
import br.com.danielchipolesch.domain.dtos.documentationTypeDtos.DocumentationTypeResponseDto;
import br.com.danielchipolesch.domain.dtos.documentationTypeDtos.DocumentationTypeUpdateRequestDto;
import br.com.danielchipolesch.domain.entities.documentationNumbering.DocumentationType;
import br.com.danielchipolesch.infrastructure.repositories.DocumentationTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentationTypeService {

    @Autowired
    DocumentationTypeRepository documentationTypeRepository;

    @Autowired
    ModelMapper modelMapper;

    public DocumentationTypeResponseDto create(DocumentationTypeCreateRequestDto request) throws Exception {
        if(documentationTypeRepository.existsByAcronym(request.acronym())){
            throw new Exception(DocumentationTypeException.ALREADY_EXISTS.getMessage());
        }

        DocumentationType documentationType = modelMapper.map(request, DocumentationType.class);
        documentationTypeRepository.save(documentationType);
        return modelMapper.map(documentationTypeRepository, DocumentationTypeResponseDto.class);
    }


    public DocumentationTypeResponseDto update(Long id, DocumentationTypeUpdateRequestDto request) throws Exception{

        DocumentationType documentationType = documentationTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(DocumentationTypeException.NOT_FOUND.getMessage()));

        documentationType.setAcronym(request.acronym().isBlank() ? documentationType.getAcronym() : request.acronym());
        documentationType.setName(request.name().isBlank() ? documentationType.getName() : request.name());
        documentationType.setDescription(request.description().isBlank() ? documentationType.getDescription() : request.description());

        documentationTypeRepository.save(documentationType);

        return modelMapper.map(documentationTypeRepository, DocumentationTypeResponseDto.class);
    }

    public DocumentationTypeResponseDto delete(Long id) throws Exception {
        DocumentationType documentationType =
                documentationTypeRepository.findById(id).orElseThrow(() -> new RuntimeException(DocumentationTypeException.NOT_FOUND.getMessage()));

        documentationTypeRepository.delete(documentationType);

        return modelMapper.map(documentationType, DocumentationTypeResponseDto.class);
    }

    public DocumentationTypeResponseDto getById(Long id) throws Exception {
        DocumentationType documentationType =
                documentationTypeRepository.findById(id).orElseThrow(() -> new RuntimeException(DocumentationTypeException.NOT_FOUND.getMessage()));

        return modelMapper.map(documentationType, DocumentationTypeResponseDto.class);
    }

//  TODO: Implementar método para listar espécies normativas de forma paginada.
}
