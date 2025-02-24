package br.com.danielchipolesch.application.dtos.regulatoryActDtos;

import br.com.danielchipolesch.application.dtos.documentDtos.DocumentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegulatoryActResponseDto {

    private Long idPortaria;
//    private DocumentResponseDto documento;
    private String nomePortaria;
    private byte[] dadoBase64;
}
