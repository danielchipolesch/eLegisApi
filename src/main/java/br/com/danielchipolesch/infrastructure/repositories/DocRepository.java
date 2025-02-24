package br.com.danielchipolesch.infrastructure.repositories;

import br.com.danielchipolesch.domain.entities.documentStructure.Doc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

//@Repository
public interface DocRepository extends MongoRepository<Doc, String> {

    List<Doc> findByDocumentationTypeAndBasicSubject(
            @Param("documentationTypeName") String documentationTypeAcronym,
            @Param("basicSubjectName") String basicSubjectName
    );
}
