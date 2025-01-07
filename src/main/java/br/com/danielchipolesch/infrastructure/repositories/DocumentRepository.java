package br.com.danielchipolesch.infrastructure.repositories;

import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import br.com.danielchipolesch.domain.entities.documentationNumbering.BasicSubject;
import br.com.danielchipolesch.domain.entities.documentationNumbering.DocumentationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
//   @Query("SELECT d.id, d.documentStatus from Document d where d.documentationType = 3 and d.basicSubject = 5") // -> Não está funcionando
//   @Query(value = "SELECT d.id_documento, d.nm_titulo_documento, d.id_especie_normativa, d.id_assunto_basico, d.nr_numero_secundario, d.st_documento FROM t_documento d WHERE d.id_especie_normativa = ?1 and d.id_assunto_basico = ?2", nativeQuery = true) // -> Não está funcionando
//   @Query("SELECT d.id FROM Document d WHERE d.documentationType = ?1 AND d.basicSubject = ?2")
//   List<Document> findByDocumentationTypeAndBasicSubject(DocumentationType documentationType, BasicSubject basicSubject);

//   @EntityGraph(attributePaths = {"documentTextAttachment"})
   List<Document> findByDocumentationTypeAndBasicSubject(DocumentationType documentationType, BasicSubject basicSubject);

//   @Query("SELECT d FROM Document d LEFT JOIN FETCH d.documentAttachment da WHERE d.documentationType = :documentationType AND d.basicSubject = :basicSubject")
//   List<Document> findByDocumentationTypeAndBasicSubject(@Param("documentationType") DocumentationType documentationType, @Param("basicSubject") BasicSubject basicSubject);


   List<Document> findByDocumentationType(DocumentationType documentationType);
}
