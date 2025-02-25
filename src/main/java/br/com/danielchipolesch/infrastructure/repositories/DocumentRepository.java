package br.com.danielchipolesch.infrastructure.repositories;

import br.com.danielchipolesch.domain.entities.estruturaDocumento.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

//   @Query("SELECT d.id, d.documentStatus from Document d where d.documentationType = 3 and d.basicSubject = 5") // -> Não está funcionando
//   @Query(value = "SELECT d.id_documento, d.nm_titulo_documento, d.id_especie_normativa, d.id_assunto_basico, d.nr_numero_secundario, d.st_documento FROM t_documento d WHERE d.id_especie_normativa = ?1 and d.id_assunto_basico = ?2", nativeQuery = true) // -> Não está funcionando
//   @Query("SELECT d.id FROM Document d WHERE d.documentationType = ?1 AND d.basicSubject = ?2")
//   List<Document> findByDocumentationTypeAndBasicSubject(DocumentationType documentationType, BasicSubject basicSubject);

//   @EntityGraph(attributePaths = {"documentTextAttachment"})
//   List<Document> findByDocumentationTypeAndBasicSubject(DocumentationType documentationType, BasicSubject basicSubject);

//   @EntityGraph(attributePaths = {"documentationType", "basicSubject"})
//   @Query("SELECT d FROM Document d WHERE d.documentationType = :documentationType AND d.basicSubject = :basicSubject")
//   List<DocumentResponseDto> findDocumentsWithoutRegulatoryAct(
//           @Param("documentationType") DocumentationType documentationType,
//           @Param("basicSubject") BasicSubject basicSubject
//   );

//   @Query("SELECT d FROM Document d WHERE d.documentationType = :documentationType AND d.basicSubject = :basicSubject")
//   List<Document> findDocumentsWithoutRegulatoryAct(
//           @Param("documentationType") DocumentationType documentationType,
//           @Param("basicSubject") BasicSubject basicSubject
//   );

//   @Query("SELECT d FROM Document d " +
//           "LEFT JOIN FETCH d.documentationType " + // Carregar documentationType (se necessário)
//           "LEFT JOIN FETCH d.basicSubject " +      // Carregar basicSubject (se necessário)
//           "WHERE d.documentationType = :documentationType AND d.basicSubject = :basicSubject")
//   List<Document> findDocumentsWithoutRegulatoryAct(
//           @Param("documentationType") DocumentationType documentationType,
//           @Param("basicSubject") BasicSubject basicSubject
//   );

//   @Query(value = "SELECT d.id_documento, d.nm_titulo_documento, dt.sg_especie_normativa, bs.cd_assunto_basico " +
//           "FROM t_documento d " +
//           "JOIN t_especie_normativa dt ON dt.id_especie_normativa = d.id_especie_normativa " +
//           "JOIN t_assunto_basico bs ON bs.id_assunto_basico = d.id_assunto_basico " +
//           "WHERE d.id_especie_normativa = :documentationTypeId " +
//           "AND d.id_assunto_basico = :basicSubjectId", nativeQuery = true)
//   List<Document> findDocumentosWithoutPortaria(
//           @Param("documentationTypeId") Long documentationTypeId,
//           @Param("basicSubjectId") Long basicSubjectId
//   );
//   @Query("SELECT d FROM Document d LEFT JOIN FETCH d.documentAttachment da WHERE d.documentationType = :documentationType AND d.basicSubject = :basicSubject")
//   List<Document> findByDocumentationTypeAndBasicSubject(@Param("documentationType") DocumentationType documentationType, @Param("basicSubject") BasicSubject basicSubject);


//   List<Document> findByEspecieNormativa(EspecieNormativa especieNormativa);
}
