package br.com.danielchipolesch.domain.services;

import br.com.danielchipolesch.domain.dtos.documentationTypeDtos.DocumentationTypeCreateRequestDto;
import br.com.danielchipolesch.domain.dtos.documentationTypeDtos.DocumentationTypeResponseDto;
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
        DocumentationType documentationType = modelMapper.map(request, DocumentationType.class);
        if(documentationTypeRepository.existsByAcronym(request.acronym())){
            throw new Exception("Espécie normativa já existe.");
        }

        documentationTypeRepository.save(documentationType);
        return modelMapper.map(documentationTypeRepository, DocumentationTypeResponseDto.class);
    }

/*
    public DocumentationTypeResponseDto update(DocumentationTypeUpdateRequestDto request) throws Exception{
        /* TODO: Implementar código para atualizar uma espécia normativa * /
        return;
    }
TODO: Implementar método para listar uma espécie normativa
TODO: Implementar método para listar espécies normativas de forma paginada.
TODO: Implementar método para excluir uma espécie normativa.
*/

}
