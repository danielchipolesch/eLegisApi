package br.com.danielchipolesch.application.dtos.basicSubjectDtos;

import lombok.Data;

@Data
public class BasicSubjectRequestUpdateDto {

    private String code;
    private String name;
    private String description;
}
