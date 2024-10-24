package br.com.danielchipolesch.infrastructure.repositories;

import br.com.danielchipolesch.domain.entities.documentationNumbering.BasicSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicSubjectRepository extends JpaRepository<BasicSubject, Long> {
    boolean existsByCode(String code);
    BasicSubject findByCode(String code);
}
