package br.com.danielchipolesch.application.dtos.documentDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegulatoryActResponseDto {

    private Long id;
    private String fileName;
    private byte[] data;
}
