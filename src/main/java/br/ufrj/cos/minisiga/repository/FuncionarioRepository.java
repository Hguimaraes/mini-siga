package br.ufrj.cos.minisiga.repository;

import br.ufrj.cos.minisiga.domain.Funcionario;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.Optional;


/**
 * Spring Data JPA repository for the Funcionario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
    Optional<Funcionario> findOneByCpf(String cpf);
}
