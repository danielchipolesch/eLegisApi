package br.com.danielchipolesch.application.dtos.regulatoryActDtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegulatoryActRequestDto {

    // TODO Colocar os nomes em língua portuguesa
    @NotNull
    private MultipartFile file;
}
