package br.com.danielchipolesch.infrastructure.repositories;

import br.com.danielchipolesch.domain.entities.estruturaDocumento.Documento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@Repository
public interface DocumentoRepository extends MongoRepository<Documento, String> {

    List<Documento> findByEspecieNormativaAndAssuntoBasico(
            @Param("documentationTypeName") String documentationTypeAcronym,
            @Param("basicSubjectName") String basicSubjectName
    );
}
