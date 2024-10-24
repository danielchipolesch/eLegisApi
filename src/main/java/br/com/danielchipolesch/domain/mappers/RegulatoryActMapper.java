package br.com.danielchipolesch.domain.mappers;

import br.com.danielchipolesch.application.dtos.regulatoryActDtos.RegulatoryActResponseDto;
import br.com.danielchipolesch.domain.entities.documentStructure.RegulatoryAct;

public class RegulatoryActMapper {

    public static RegulatoryActResponseDto regulatoryActToResponseActResponseDto(RegulatoryAct regulatoryAct){
        return new RegulatoryActResponseDto(
                regulatoryAct.getId(),
                regulatoryAct.getDocument(),
                regulatoryAct.getFileName(),
                regulatoryAct.getData()
        );
    }
}
