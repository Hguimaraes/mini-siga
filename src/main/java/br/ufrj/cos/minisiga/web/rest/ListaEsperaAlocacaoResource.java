package br.ufrj.cos.minisiga.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.ufrj.cos.minisiga.domain.ListaEsperaAlocacao;

import br.ufrj.cos.minisiga.repository.ListaEsperaAlocacaoRepository;
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
 * REST controller for managing ListaEsperaAlocacao.
 */
@RestController
@RequestMapping("/api")
public class ListaEsperaAlocacaoResource {

    private final Logger log = LoggerFactory.getLogger(ListaEsperaAlocacaoResource.class);

    private static final String ENTITY_NAME = "listaEsperaAlocacao";

    private final ListaEsperaAlocacaoRepository listaEsperaAlocacaoRepository;

    public ListaEsperaAlocacaoResource(ListaEsperaAlocacaoRepository listaEsperaAlocacaoRepository) {
        this.listaEsperaAlocacaoRepository = listaEsperaAlocacaoRepository;
    }

    /**
     * POST  /lista-espera-alocacaos : Create a new listaEsperaAlocacao.
     *
     * @param listaEsperaAlocacao the listaEsperaAlocacao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new listaEsperaAlocacao, or with status 400 (Bad Request) if the listaEsperaAlocacao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/lista-espera-alocacaos")
    @Timed
    public ResponseEntity<ListaEsperaAlocacao> createListaEsperaAlocacao(@Valid @RequestBody ListaEsperaAlocacao listaEsperaAlocacao) throws URISyntaxException {
        log.debug("REST request to save ListaEsperaAlocacao : {}", listaEsperaAlocacao);
        if (listaEsperaAlocacao.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new listaEsperaAlocacao cannot already have an ID")).body(null);
        }
        ListaEsperaAlocacao result = listaEsperaAlocacaoRepository.save(listaEsperaAlocacao);
        return ResponseEntity.created(new URI("/api/lista-espera-alocacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /lista-espera-alocacaos : Updates an existing listaEsperaAlocacao.
     *
     * @param listaEsperaAlocacao the listaEsperaAlocacao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated listaEsperaAlocacao,
     * or with status 400 (Bad Request) if the listaEsperaAlocacao is not valid,
     * or with status 500 (Internal Server Error) if the listaEsperaAlocacao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/lista-espera-alocacaos")
    @Timed
    public ResponseEntity<ListaEsperaAlocacao> updateListaEsperaAlocacao(@Valid @RequestBody ListaEsperaAlocacao listaEsperaAlocacao) throws URISyntaxException {
        log.debug("REST request to update ListaEsperaAlocacao : {}", listaEsperaAlocacao);
        if (listaEsperaAlocacao.getId() == null) {
            return createListaEsperaAlocacao(listaEsperaAlocacao);
        }
        ListaEsperaAlocacao result = listaEsperaAlocacaoRepository.save(listaEsperaAlocacao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, listaEsperaAlocacao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /lista-espera-alocacaos : get all the listaEsperaAlocacaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of listaEsperaAlocacaos in body
     */
    @GetMapping("/lista-espera-alocacaos")
    @Timed
    public List<ListaEsperaAlocacao> getAllListaEsperaAlocacaos() {
        log.debug("REST request to get all ListaEsperaAlocacaos");
        return listaEsperaAlocacaoRepository.findAll();
    }

    /**
     * GET  /lista-espera-alocacaos/:id : get the "id" listaEsperaAlocacao.
     *
     * @param id the id of the listaEsperaAlocacao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the listaEsperaAlocacao, or with status 404 (Not Found)
     */
    @GetMapping("/lista-espera-alocacaos/{id}")
    @Timed
    public ResponseEntity<ListaEsperaAlocacao> getListaEsperaAlocacao(@PathVariable Long id) {
        log.debug("REST request to get ListaEsperaAlocacao : {}", id);
        ListaEsperaAlocacao listaEsperaAlocacao = listaEsperaAlocacaoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(listaEsperaAlocacao));
    }

    /**
     * DELETE  /lista-espera-alocacaos/:id : delete the "id" listaEsperaAlocacao.
     *
     * @param id the id of the listaEsperaAlocacao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/lista-espera-alocacaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteListaEsperaAlocacao(@PathVariable Long id) {
        log.debug("REST request to delete ListaEsperaAlocacao : {}", id);
        listaEsperaAlocacaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
