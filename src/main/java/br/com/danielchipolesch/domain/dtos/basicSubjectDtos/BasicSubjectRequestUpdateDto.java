package br.com.danielchipolesch.domain.dtos.basicSubjectDtos;

import lombok.Data;

@Data
public class BasicSubjectRequestUpdateDto {

    private String basicNumber;
    private String name;
    private String description;
}
