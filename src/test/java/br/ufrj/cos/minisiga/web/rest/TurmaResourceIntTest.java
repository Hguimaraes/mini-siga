package br.ufrj.cos.minisiga.web.rest;

import br.ufrj.cos.minisiga.MinisigaApp;

import br.ufrj.cos.minisiga.domain.Turma;
import br.ufrj.cos.minisiga.repository.TurmaRepository;
import br.ufrj.cos.minisiga.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TurmaResource REST controller.
 *
 * @see TurmaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MinisigaApp.class)
public class TurmaResourceIntTest {

    private static final Integer DEFAULT_MAX_INSCRITOS = 1;
    private static final Integer UPDATED_MAX_INSCRITOS = 2;

    private static final String DEFAULT_SALA = "AAAAAAAAAA";
    private static final String UPDATED_SALA = "BBBBBBBBBB";

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTurmaMockMvc;

    private Turma turma;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TurmaResource turmaResource = new TurmaResource(turmaRepository);
        this.restTurmaMockMvc = MockMvcBuilders.standaloneSetup(turmaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Turma createEntity(EntityManager em) {
        Turma turma = new Turma()
            .maxInscritos(DEFAULT_MAX_INSCRITOS)
            .sala(DEFAULT_SALA);
        return turma;
    }

    @Before
    public void initTest() {
        turma = createEntity(em);
    }

    @Test
    @Transactional
    public void createTurma() throws Exception {
        int databaseSizeBeforeCreate = turmaRepository.findAll().size();

        // Create the Turma
        restTurmaMockMvc.perform(post("/api/turmas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(turma)))
            .andExpect(status().isCreated());

        // Validate the Turma in the database
        List<Turma> turmaList = turmaRepository.findAll();
        assertThat(turmaList).hasSize(databaseSizeBeforeCreate + 1);
        Turma testTurma = turmaList.get(turmaList.size() - 1);
        assertThat(testTurma.getMaxInscritos()).isEqualTo(DEFAULT_MAX_INSCRITOS);
        assertThat(testTurma.getSala()).isEqualTo(DEFAULT_SALA);
    }

    @Test
    @Transactional
    public void createTurmaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = turmaRepository.findAll().size();

        // Create the Turma with an existing ID
        turma.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTurmaMockMvc.perform(post("/api/turmas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(turma)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Turma> turmaList = turmaRepository.findAll();
        assertThat(turmaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkMaxInscritosIsRequired() throws Exception {
        int databaseSizeBeforeTest = turmaRepository.findAll().size();
        // set the field null
        turma.setMaxInscritos(null);

        // Create the Turma, which fails.

        restTurmaMockMvc.perform(post("/api/turmas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(turma)))
            .andExpect(status().isBadRequest());

        List<Turma> turmaList = turmaRepository.findAll();
        assertThat(turmaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSalaIsRequired() throws Exception {
        int databaseSizeBeforeTest = turmaRepository.findAll().size();
        // set the field null
        turma.setSala(null);

        // Create the Turma, which fails.

        restTurmaMockMvc.perform(post("/api/turmas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(turma)))
            .andExpect(status().isBadRequest());

        List<Turma> turmaList = turmaRepository.findAll();
        assertThat(turmaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTurmas() throws Exception {
        // Initialize the database
        turmaRepository.saveAndFlush(turma);

        // Get all the turmaList
        restTurmaMockMvc.perform(get("/api/turmas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(turma.getId().intValue())))
            .andExpect(jsonPath("$.[*].maxInscritos").value(hasItem(DEFAULT_MAX_INSCRITOS)))
            .andExpect(jsonPath("$.[*].sala").value(hasItem(DEFAULT_SALA.toString())));
    }

    @Test
    @Transactional
    public void getTurma() throws Exception {
        // Initialize the database
        turmaRepository.saveAndFlush(turma);

        // Get the turma
        restTurmaMockMvc.perform(get("/api/turmas/{id}", turma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(turma.getId().intValue()))
            .andExpect(jsonPath("$.maxInscritos").value(DEFAULT_MAX_INSCRITOS))
            .andExpect(jsonPath("$.sala").value(DEFAULT_SALA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTurma() throws Exception {
        // Get the turma
        restTurmaMockMvc.perform(get("/api/turmas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTurma() throws Exception {
        // Initialize the database
        turmaRepository.saveAndFlush(turma);
        int databaseSizeBeforeUpdate = turmaRepository.findAll().size();

        // Update the turma
        Turma updatedTurma = turmaRepository.findOne(turma.getId());
        updatedTurma
            .maxInscritos(UPDATED_MAX_INSCRITOS)
            .sala(UPDATED_SALA);

        restTurmaMockMvc.perform(put("/api/turmas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTurma)))
            .andExpect(status().isOk());

        // Validate the Turma in the database
        List<Turma> turmaList = turmaRepository.findAll();
        assertThat(turmaList).hasSize(databaseSizeBeforeUpdate);
        Turma testTurma = turmaList.get(turmaList.size() - 1);
        assertThat(testTurma.getMaxInscritos()).isEqualTo(UPDATED_MAX_INSCRITOS);
        assertThat(testTurma.getSala()).isEqualTo(UPDATED_SALA);
    }

    @Test
    @Transactional
    public void updateNonExistingTurma() throws Exception {
        int databaseSizeBeforeUpdate = turmaRepository.findAll().size();

        // Create the Turma

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTurmaMockMvc.perform(put("/api/turmas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(turma)))
            .andExpect(status().isCreated());

        // Validate the Turma in the database
        List<Turma> turmaList = turmaRepository.findAll();
        assertThat(turmaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTurma() throws Exception {
        // Initialize the database
        turmaRepository.saveAndFlush(turma);
        int databaseSizeBeforeDelete = turmaRepository.findAll().size();

        // Get the turma
        restTurmaMockMvc.perform(delete("/api/turmas/{id}", turma.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Turma> turmaList = turmaRepository.findAll();
        assertThat(turmaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Turma.class);
        Turma turma1 = new Turma();
        turma1.setId(1L);
        Turma turma2 = new Turma();
        turma2.setId(turma1.getId());
        assertThat(turma1).isEqualTo(turma2);
        turma2.setId(2L);
        assertThat(turma1).isNotEqualTo(turma2);
        turma1.setId(null);
        assertThat(turma1).isNotEqualTo(turma2);
    }
}
