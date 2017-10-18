package br.ufrj.cos.minisiga.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.ufrj.cos.minisiga.domain.Calendario;

import br.ufrj.cos.minisiga.repository.CalendarioRepository;
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
 * REST controller for managing Calendario.
 */
@RestController
@RequestMapping("/api")
public class CalendarioResource {

    private final Logger log = LoggerFactory.getLogger(CalendarioResource.class);

    private static final String ENTITY_NAME = "calendario";

    private final CalendarioRepository calendarioRepository;

    public CalendarioResource(CalendarioRepository calendarioRepository) {
        this.calendarioRepository = calendarioRepository;
    }

    /**
     * POST  /calendarios : Create a new calendario.
     *
     * @param calendario the calendario to create
     * @return the ResponseEntity with status 201 (Created) and with body the new calendario, or with status 400 (Bad Request) if the calendario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/calendarios")
    @Timed
    public ResponseEntity<Calendario> createCalendario(@Valid @RequestBody Calendario calendario) throws URISyntaxException {
        log.debug("REST request to save Calendario : {}", calendario);
        if (calendario.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new calendario cannot already have an ID")).body(null);
        }
        Calendario result = calendarioRepository.save(calendario);
        return ResponseEntity.created(new URI("/api/calendarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /calendarios : Updates an existing calendario.
     *
     * @param calendario the calendario to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated calendario,
     * or with status 400 (Bad Request) if the calendario is not valid,
     * or with status 500 (Internal Server Error) if the calendario couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/calendarios")
    @Timed
    public ResponseEntity<Calendario> updateCalendario(@Valid @RequestBody Calendario calendario) throws URISyntaxException {
        log.debug("REST request to update Calendario : {}", calendario);
        if (calendario.getId() == null) {
            return createCalendario(calendario);
        }
        Calendario result = calendarioRepository.save(calendario);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, calendario.getId().toString()))
            .body(result);
    }

    /**
     * GET  /calendarios : get all the calendarios.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of calendarios in body
     */
    @GetMapping("/calendarios")
    @Timed
    public List<Calendario> getAllCalendarios() {
        log.debug("REST request to get all Calendarios");
        return calendarioRepository.findAll();
    }

    /**
     * GET  /calendarios/:id : get the "id" calendario.
     *
     * @param id the id of the calendario to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the calendario, or with status 404 (Not Found)
     */
    @GetMapping("/calendarios/{id}")
    @Timed
    public ResponseEntity<Calendario> getCalendario(@PathVariable Long id) {
        log.debug("REST request to get Calendario : {}", id);
        Calendario calendario = calendarioRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(calendario));
    }

    /**
     * DELETE  /calendarios/:id : delete the "id" calendario.
     *
     * @param id the id of the calendario to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/calendarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteCalendario(@PathVariable Long id) {
        log.debug("REST request to delete Calendario : {}", id);
        calendarioRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
