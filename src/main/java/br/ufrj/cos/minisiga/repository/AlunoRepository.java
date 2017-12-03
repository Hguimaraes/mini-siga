package br.ufrj.cos.minisiga.repository;

import br.ufrj.cos.minisiga.domain.Aluno;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.Optional;


/**
 * Spring Data JPA repository for the Aluno entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Long> {
    Optional<Aluno> findOneByCpf(String cpf);
}
