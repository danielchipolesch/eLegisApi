package br.com.danielchipolesch.infrastructure.runners;

import br.com.danielchipolesch.domain.entities.documentationNumbering.BasicSubject;
import br.com.danielchipolesch.infrastructure.enums.BasicSubjectEnum;
import br.com.danielchipolesch.infrastructure.repositories.BasicSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BasicSubjectRunner implements CommandLineRunner {

    @Autowired
    BasicSubjectRepository basicSubjectRepository;

    @Override
    public void run(String... args) throws Exception {
        BasicSubjectEnum[] basicSubjectEnums = BasicSubjectEnum.values();
        for (BasicSubjectEnum basicSubjectEnum : basicSubjectEnums){
            if (!basicSubjectRepository.existsByNumber(basicSubjectEnum.getNumber())){
                BasicSubject basicSubject = new BasicSubject();
                basicSubject.setNumber(basicSubjectEnum.getNumber());
                basicSubject.setName(basicSubjectEnum.getName());
                basicSubject.setDescription(basicSubjectEnum.getDescription());
                basicSubjectRepository.save(basicSubject);
            }
        }
    }
}
