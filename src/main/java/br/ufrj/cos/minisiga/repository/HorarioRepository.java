package br.ufrj.cos.minisiga.repository;

import br.ufrj.cos.minisiga.domain.Horario;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Horario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HorarioRepository extends JpaRepository<Horario,Long> {
    
    @Query("select distinct horario from Horario horario left join fetch horario.turmas")
    List<Horario> findAllWithEagerRelationships();

    @Query("select horario from Horario horario left join fetch horario.turmas where horario.id =:id")
    Horario findOneWithEagerRelationships(@Param("id") Long id);
    
}
