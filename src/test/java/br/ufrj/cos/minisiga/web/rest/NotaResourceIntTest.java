package br.ufrj.cos.minisiga.web.rest;

import br.ufrj.cos.minisiga.MinisigaApp;

import br.ufrj.cos.minisiga.domain.Nota;
import br.ufrj.cos.minisiga.repository.NotaRepository;
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

import br.ufrj.cos.minisiga.domain.enumeration.CONCEITO;
/**
 * Test class for the NotaResource REST controller.
 *
 * @see NotaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MinisigaApp.class)
public class NotaResourceIntTest {

    private static final CONCEITO DEFAULT_CONCEITO = CONCEITO.A;
    private static final CONCEITO UPDATED_CONCEITO = CONCEITO.B;

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNotaMockMvc;

    private Nota nota;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        NotaResource notaResource = new NotaResource(notaRepository);
        this.restNotaMockMvc = MockMvcBuilders.standaloneSetup(notaResource)
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
    public static Nota createEntity(EntityManager em) {
        Nota nota = new Nota()
            .conceito(DEFAULT_CONCEITO);
        return nota;
    }

    @Before
    public void initTest() {
        nota = createEntity(em);
    }

    @Test
    @Transactional
    public void createNota() throws Exception {
        int databaseSizeBeforeCreate = notaRepository.findAll().size();

        // Create the Nota
        restNotaMockMvc.perform(post("/api/notas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nota)))
            .andExpect(status().isCreated());

        // Validate the Nota in the database
        List<Nota> notaList = notaRepository.findAll();
        assertThat(notaList).hasSize(databaseSizeBeforeCreate + 1);
        Nota testNota = notaList.get(notaList.size() - 1);
        assertThat(testNota.getConceito()).isEqualTo(DEFAULT_CONCEITO);
    }

    @Test
    @Transactional
    public void createNotaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notaRepository.findAll().size();

        // Create the Nota with an existing ID
        nota.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotaMockMvc.perform(post("/api/notas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nota)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Nota> notaList = notaRepository.findAll();
        assertThat(notaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkConceitoIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaRepository.findAll().size();
        // set the field null
        nota.setConceito(null);

        // Create the Nota, which fails.

        restNotaMockMvc.perform(post("/api/notas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nota)))
            .andExpect(status().isBadRequest());

        List<Nota> notaList = notaRepository.findAll();
        assertThat(notaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNotas() throws Exception {
        // Initialize the database
        notaRepository.saveAndFlush(nota);

        // Get all the notaList
        restNotaMockMvc.perform(get("/api/notas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nota.getId().intValue())))
            .andExpect(jsonPath("$.[*].conceito").value(hasItem(DEFAULT_CONCEITO.toString())));
    }

    @Test
    @Transactional
    public void getNota() throws Exception {
        // Initialize the database
        notaRepository.saveAndFlush(nota);

        // Get the nota
        restNotaMockMvc.perform(get("/api/notas/{id}", nota.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nota.getId().intValue()))
            .andExpect(jsonPath("$.conceito").value(DEFAULT_CONCEITO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNota() throws Exception {
        // Get the nota
        restNotaMockMvc.perform(get("/api/notas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNota() throws Exception {
        // Initialize the database
        notaRepository.saveAndFlush(nota);
        int databaseSizeBeforeUpdate = notaRepository.findAll().size();

        // Update the nota
        Nota updatedNota = notaRepository.findOne(nota.getId());
        updatedNota
            .conceito(UPDATED_CONCEITO);

        restNotaMockMvc.perform(put("/api/notas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNota)))
            .andExpect(status().isOk());

        // Validate the Nota in the database
        List<Nota> notaList = notaRepository.findAll();
        assertThat(notaList).hasSize(databaseSizeBeforeUpdate);
        Nota testNota = notaList.get(notaList.size() - 1);
        assertThat(testNota.getConceito()).isEqualTo(UPDATED_CONCEITO);
    }

    @Test
    @Transactional
    public void updateNonExistingNota() throws Exception {
        int databaseSizeBeforeUpdate = notaRepository.findAll().size();

        // Create the Nota

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNotaMockMvc.perform(put("/api/notas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nota)))
            .andExpect(status().isCreated());

        // Validate the Nota in the database
        List<Nota> notaList = notaRepository.findAll();
        assertThat(notaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteNota() throws Exception {
        // Initialize the database
        notaRepository.saveAndFlush(nota);
        int databaseSizeBeforeDelete = notaRepository.findAll().size();

        // Get the nota
        restNotaMockMvc.perform(delete("/api/notas/{id}", nota.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Nota> notaList = notaRepository.findAll();
        assertThat(notaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Nota.class);
        Nota nota1 = new Nota();
        nota1.setId(1L);
        Nota nota2 = new Nota();
        nota2.setId(nota1.getId());
        assertThat(nota1).isEqualTo(nota2);
        nota2.setId(2L);
        assertThat(nota1).isNotEqualTo(nota2);
        nota1.setId(null);
        assertThat(nota1).isNotEqualTo(nota2);
    }
}
