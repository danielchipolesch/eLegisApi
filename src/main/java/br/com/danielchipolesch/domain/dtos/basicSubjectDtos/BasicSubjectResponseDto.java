package br.com.danielchipolesch.domain.dtos.basicSubjectDtos;

import lombok.*;

@Data
public class BasicSubjectResponseDto {

    private Long id;
    private String code;
    private String name;
    private String description;
}
