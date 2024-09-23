package br.com.danielchipolesch.infrastructure.repositories;

import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import br.com.danielchipolesch.domain.entities.documentationNumbering.BasicSubject;
import br.com.danielchipolesch.domain.entities.documentationNumbering.DocumentationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
//   @Query("select u from Document u where u.id_especie_normativa = ?1 and u.id_assunto_basico = ?2") -> Não está funcionando
   List<Document> findByDocumentationTypeAndBasicSubject(DocumentationType documentationType, BasicSubject basicSubject);
   List<Document> findByDocumentationType(DocumentationType documentationType);
}
