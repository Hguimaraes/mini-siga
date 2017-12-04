package br.ufrj.cos.minisiga.repository;

import br.ufrj.cos.minisiga.domain.Horario;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Horario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HorarioRepository extends JpaRepository<Horario,Long> {
    
}
