package br.com.danielchipolesch.application.dtos.basicSubjectDtos;

import lombok.*;

@Data
public class BasicSubjectResponseDto {

    private Long id;
    private String code;
    private String name;
    private String description;
}
