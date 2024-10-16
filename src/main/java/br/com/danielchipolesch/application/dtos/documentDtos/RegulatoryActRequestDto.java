package br.com.danielchipolesch.application.dtos.documentDtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegulatoryActRequestDto {

    private MultipartFile file;

}
