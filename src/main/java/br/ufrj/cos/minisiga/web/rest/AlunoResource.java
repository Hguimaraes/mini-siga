package br.ufrj.cos.minisiga.web.rest;

import br.ufrj.cos.minisiga.domain.Turma;
import com.codahale.metrics.annotation.Timed;
import br.ufrj.cos.minisiga.domain.Aluno;
import br.ufrj.cos.minisiga.domain.User;

import br.ufrj.cos.minisiga.repository.AlunoRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.List;

/**
 * REST controller for managing Aluno.
 */
@RestController
@RequestMapping("/api")
public class AlunoResource {

    private final Logger log = LoggerFactory.getLogger(AlunoResource.class);

    private static final String ENTITY_NAME = "aluno";

    private final AlunoRepository alunoRepository;

    public AlunoResource(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    /**
     * POST  /alunos : Create a new aluno.
     *
     * @param aluno the aluno to create
     * @return the ResponseEntity with status 201 (Created) and with body the new aluno, or with status 400 (Bad Request) if the aluno has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/alunos")
    @Timed
    public ResponseEntity<Aluno> createAluno(@Valid @RequestBody Aluno aluno) throws URISyntaxException {
        log.debug("REST request to save Aluno : {}", aluno);
        if (aluno.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new aluno cannot already have an ID")).body(null);
        }
        Aluno result = alunoRepository.save(aluno);
        return ResponseEntity.created(new URI("/api/alunos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /alunos : Updates an existing aluno.
     *
     * @param aluno the aluno to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated aluno,
     * or with status 400 (Bad Request) if the aluno is not valid,
     * or with status 500 (Internal Server Error) if the aluno couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/alunos")
    @Timed
    public ResponseEntity<Aluno> updateAluno(@Valid @RequestBody Aluno aluno) throws URISyntaxException {
        log.debug("REST request to update Aluno : {}", aluno);
        if (aluno.getId() == null) {
            return createAluno(aluno);
        }
        Aluno result = alunoRepository.save(aluno);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, aluno.getId().toString()))
            .body(result);
    }

    /**
     * GET  /alunos : get all the alunos.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of alunos in body
     */
    @GetMapping("/alunos")
    @Timed
    public List<Aluno> getAllAlunos(@RequestParam(required = false) String filter) {
        if ("aluno-is-null".equals(filter)) {
            log.debug("REST request to get all Alunos where aluno is null");
            return StreamSupport
                .stream(alunoRepository.findAll().spliterator(), false)
                .filter(aluno -> aluno.getAluno() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Alunos");
        return alunoRepository.findAll();
    }

    /**
     * GET  /alunos/:id : get the "id" aluno.
     *
     * @param id the id of the aluno to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aluno, or with status 404 (Not Found)
     */
    @GetMapping("/alunos/{id}")
    @Timed
    public ResponseEntity<Aluno> getAluno(@PathVariable Long id) {
        log.debug("REST request to get Aluno : {}", id);
        Aluno aluno = alunoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(aluno));
    }

    /**
     * DELETE  /alunos/:id : delete the "id" aluno.
     *
     * @param id the id of the aluno to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/alunos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        log.debug("REST request to delete Aluno : {}", id);
        alunoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /alunos/:id : get the "id" aluno.
     *
     * @param id the id of the aluno to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aluno, or with status 404 (Not Found)
     */
    @GetMapping("/alunos/userid/{id}")
    @Timed
    public ResponseEntity<Aluno> getAlunoByUserId(@PathVariable Long id) {
        log.debug("REST request to get Aluno : {}", id);
        Aluno aluno = alunoRepository.findByUser(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(aluno));
    }

    /**
     * GET  /alunos/:id : get the "id" aluno.
     *
     * @param id the id of the aluno to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aluno, or with status 404 (Not Found)
     */
    @GetMapping("/alunos/turmas/{id}")
    @Timed
    public List<Turma> getAllTurmas(@PathVariable Long id) {
        log.debug("REST request to get Aluno : {}", id);
        return alunoRepository.getAllTurmas(id);
    }
}
