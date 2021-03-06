package br.ufrj.cos.minisiga.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.ufrj.cos.minisiga.domain.Funcionario;

import br.ufrj.cos.minisiga.repository.FuncionarioRepository;
import br.ufrj.cos.minisiga.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Funcionario.
 */
@RestController
@RequestMapping("/api")
public class FuncionarioResource {

    private final Logger log = LoggerFactory.getLogger(FuncionarioResource.class);

    private static final String ENTITY_NAME = "funcionario";

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioResource(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    /**
     * POST  /funcionarios : Create a new funcionario.
     *
     * @param funcionario the funcionario to create
     * @return the ResponseEntity with status 201 (Created) and with body the new funcionario, or with status 400 (Bad Request) if the funcionario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/funcionarios")
    @Timed
    public ResponseEntity<Funcionario> createFuncionario(@Valid @RequestBody Funcionario funcionario) throws URISyntaxException {
        log.debug("REST request to save Funcionario : {}", funcionario);
        if (funcionario.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new funcionario cannot already have an ID")).body(null);
        }
        Funcionario result = funcionarioRepository.save(funcionario);
        return ResponseEntity.created(new URI("/api/funcionarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /funcionarios : Updates an existing funcionario.
     *
     * @param funcionario the funcionario to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated funcionario,
     * or with status 400 (Bad Request) if the funcionario is not valid,
     * or with status 500 (Internal Server Error) if the funcionario couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/funcionarios")
    @Timed
    public ResponseEntity<Funcionario> updateFuncionario(@Valid @RequestBody Funcionario funcionario) throws URISyntaxException {
        log.debug("REST request to update Funcionario : {}", funcionario);
        if (funcionario.getId() == null) {
            return createFuncionario(funcionario);
        }
        Funcionario result = funcionarioRepository.save(funcionario);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, funcionario.getId().toString()))
            .body(result);
    }

    /**
     * GET  /funcionarios : get all the funcionarios.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of funcionarios in body
     */
    @GetMapping("/funcionarios")
    @Timed
    public List<Funcionario> getAllFuncionarios() {
        log.debug("REST request to get all Funcionarios");
        return funcionarioRepository.findAll();
    }

    /**
     * GET  /funcionarios/:id : get the "id" funcionario.
     *
     * @param id the id of the funcionario to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the funcionario, or with status 404 (Not Found)
     */
    @GetMapping("/funcionarios/{id}")
    @Timed
    public ResponseEntity<Funcionario> getFuncionario(@PathVariable Long id) {
        log.debug("REST request to get Funcionario : {}", id);
        Funcionario funcionario = funcionarioRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(funcionario));
    }

    /**
     * DELETE  /funcionarios/:id : delete the "id" funcionario.
     *
     * @param id the id of the funcionario to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/funcionarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        log.debug("REST request to delete Funcionario : {}", id);
        funcionarioRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
