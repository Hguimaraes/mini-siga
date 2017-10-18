package br.ufrj.cos.minisiga.repository;

import br.ufrj.cos.minisiga.domain.Calendario;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Calendario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CalendarioRepository extends JpaRepository<Calendario,Long> {
    
}
