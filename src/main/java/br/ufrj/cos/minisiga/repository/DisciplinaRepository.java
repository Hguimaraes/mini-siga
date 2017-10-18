package br.ufrj.cos.minisiga.repository;

import br.ufrj.cos.minisiga.domain.Disciplina;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Disciplina entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina,Long> {
    
}
