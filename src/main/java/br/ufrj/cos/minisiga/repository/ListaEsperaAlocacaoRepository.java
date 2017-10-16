package br.ufrj.cos.minisiga.repository;

import br.ufrj.cos.minisiga.domain.ListaEsperaAlocacao;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ListaEsperaAlocacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListaEsperaAlocacaoRepository extends JpaRepository<ListaEsperaAlocacao,Long> {
    
}
