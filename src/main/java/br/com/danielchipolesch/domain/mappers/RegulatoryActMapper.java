package br.com.danielchipolesch.domain.mappers;

import br.com.danielchipolesch.application.dtos.regulatoryActDtos.RegulatoryActDto;
import br.com.danielchipolesch.application.dtos.regulatoryActDtos.RegulatoryActResponseDto;
import br.com.danielchipolesch.application.dtos.regulatoryActDtos.RegulatoryActResponseNoPdfDto;
import br.com.danielchipolesch.domain.entities.documentStructure.RegulatoryAct;

public class RegulatoryActMapper {

    public static RegulatoryActResponseNoPdfDto regulatoryActToRegulatoryActResponseNoPdfDto(RegulatoryAct regulatoryAct){
        return new RegulatoryActResponseNoPdfDto(
                regulatoryAct.getId(),
                DocumentMapper.documentToDocumentResponseDto(regulatoryAct.getDocument()),
                regulatoryAct.getFileName()
        );
    }

    public static RegulatoryActResponseDto regulatoryActToRegulatoryActResponseDto(RegulatoryAct regulatoryAct) {
        return new RegulatoryActResponseDto(
                regulatoryAct.getId(),
                DocumentMapper.documentToDocumentResponseDto(regulatoryAct.getDocument()),
                regulatoryAct.getFileName(),
                regulatoryAct.getData()
        );
    }

    public static RegulatoryActDto regulatoryActToRegulatoryActDto(RegulatoryAct regulatoryAct){
        return new RegulatoryActDto(
                regulatoryAct.getId(),
                DocumentMapper.documentToDocumentResponseDto(regulatoryAct.getDocument()),
                regulatoryAct.getFileName(),
                regulatoryAct.getData()
        );
    }

    public static RegulatoryActResponseNoPdfDto regulatoryActDtoToRegulatoryActResponseNoPdfDto(RegulatoryActDto regulatoryActDto){
        return new RegulatoryActResponseNoPdfDto(
                regulatoryActDto.getIdPortaria(),
                regulatoryActDto.getDocumento(),
                regulatoryActDto.getNomePortaria()
        );
    }
}
