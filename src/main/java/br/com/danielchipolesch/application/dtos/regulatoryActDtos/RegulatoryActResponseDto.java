package br.com.danielchipolesch.application.dtos.regulatoryActDtos;

import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegulatoryActResponseDto {

    private Long idPortaria;
    private Document documento;
    private String nomePortaria;
    private byte[] dadoBase64;
}
