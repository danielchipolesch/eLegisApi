package br.com.danielchipolesch.infrastructure.runners;

import br.com.danielchipolesch.domain.entities.documentationNumbering.DocumentationType;
import br.com.danielchipolesch.infrastructure.enums.DocumentationTypeEnum;
import br.com.danielchipolesch.infrastructure.repositories.DocumentationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DocumentationTypeRunner implements CommandLineRunner {

    @Autowired
    DocumentationTypeRepository documentationTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        DocumentationTypeEnum[] documentationTypeEnumAcronyms = DocumentationTypeEnum.values();
        for (DocumentationTypeEnum documentationTypeEnumAcronym : documentationTypeEnumAcronyms){
            if (!documentationTypeRepository.existsByAcronym(documentationTypeEnumAcronym.toString())){
                DocumentationType documentationType = new DocumentationType();
                documentationType.setAcronym(documentationTypeEnumAcronym.name());
                documentationType.setName(documentationTypeEnumAcronym.getName());
                documentationType.setDescription(documentationType.getDescription());
                documentationTypeRepository.save(documentationType);
            }
        }
    }
}