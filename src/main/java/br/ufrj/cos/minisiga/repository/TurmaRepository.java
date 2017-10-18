package br.ufrj.cos.minisiga.repository;

import br.ufrj.cos.minisiga.domain.Turma;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Turma entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TurmaRepository extends JpaRepository<Turma,Long> {
    
    @Query("select distinct turma from Turma turma left join fetch turma.inscritos")
    List<Turma> findAllWithEagerRelationships();

    @Query("select turma from Turma turma left join fetch turma.inscritos where turma.id =:id")
    Turma findOneWithEagerRelationships(@Param("id") Long id);
    
}
