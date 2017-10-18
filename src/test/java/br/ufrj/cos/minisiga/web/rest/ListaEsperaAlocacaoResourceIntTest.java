package br.ufrj.cos.minisiga.web.rest;

import br.ufrj.cos.minisiga.MinisigaApp;

import br.ufrj.cos.minisiga.domain.ListaEsperaAlocacao;
import br.ufrj.cos.minisiga.repository.ListaEsperaAlocacaoRepository;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static br.ufrj.cos.minisiga.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ListaEsperaAlocacaoResource REST controller.
 *
 * @see ListaEsperaAlocacaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MinisigaApp.class)
public class ListaEsperaAlocacaoResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ListaEsperaAlocacaoRepository listaEsperaAlocacaoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restListaEsperaAlocacaoMockMvc;

    private ListaEsperaAlocacao listaEsperaAlocacao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ListaEsperaAlocacaoResource listaEsperaAlocacaoResource = new ListaEsperaAlocacaoResource(listaEsperaAlocacaoRepository);
        this.restListaEsperaAlocacaoMockMvc = MockMvcBuilders.standaloneSetup(listaEsperaAlocacaoResource)
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
    public static ListaEsperaAlocacao createEntity(EntityManager em) {
        ListaEsperaAlocacao listaEsperaAlocacao = new ListaEsperaAlocacao()
            .data(DEFAULT_DATA);
        return listaEsperaAlocacao;
    }

    @Before
    public void initTest() {
        listaEsperaAlocacao = createEntity(em);
    }

    @Test
    @Transactional
    public void createListaEsperaAlocacao() throws Exception {
        int databaseSizeBeforeCreate = listaEsperaAlocacaoRepository.findAll().size();

        // Create the ListaEsperaAlocacao
        restListaEsperaAlocacaoMockMvc.perform(post("/api/lista-espera-alocacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listaEsperaAlocacao)))
            .andExpect(status().isCreated());

        // Validate the ListaEsperaAlocacao in the database
        List<ListaEsperaAlocacao> listaEsperaAlocacaoList = listaEsperaAlocacaoRepository.findAll();
        assertThat(listaEsperaAlocacaoList).hasSize(databaseSizeBeforeCreate + 1);
        ListaEsperaAlocacao testListaEsperaAlocacao = listaEsperaAlocacaoList.get(listaEsperaAlocacaoList.size() - 1);
        assertThat(testListaEsperaAlocacao.getData()).isEqualTo(DEFAULT_DATA);
    }

    @Test
    @Transactional
    public void createListaEsperaAlocacaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listaEsperaAlocacaoRepository.findAll().size();

        // Create the ListaEsperaAlocacao with an existing ID
        listaEsperaAlocacao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListaEsperaAlocacaoMockMvc.perform(post("/api/lista-espera-alocacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listaEsperaAlocacao)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ListaEsperaAlocacao> listaEsperaAlocacaoList = listaEsperaAlocacaoRepository.findAll();
        assertThat(listaEsperaAlocacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = listaEsperaAlocacaoRepository.findAll().size();
        // set the field null
        listaEsperaAlocacao.setData(null);

        // Create the ListaEsperaAlocacao, which fails.

        restListaEsperaAlocacaoMockMvc.perform(post("/api/lista-espera-alocacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listaEsperaAlocacao)))
            .andExpect(status().isBadRequest());

        List<ListaEsperaAlocacao> listaEsperaAlocacaoList = listaEsperaAlocacaoRepository.findAll();
        assertThat(listaEsperaAlocacaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListaEsperaAlocacaos() throws Exception {
        // Initialize the database
        listaEsperaAlocacaoRepository.saveAndFlush(listaEsperaAlocacao);

        // Get all the listaEsperaAlocacaoList
        restListaEsperaAlocacaoMockMvc.perform(get("/api/lista-espera-alocacaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listaEsperaAlocacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(sameInstant(DEFAULT_DATA))));
    }

    @Test
    @Transactional
    public void getListaEsperaAlocacao() throws Exception {
        // Initialize the database
        listaEsperaAlocacaoRepository.saveAndFlush(listaEsperaAlocacao);

        // Get the listaEsperaAlocacao
        restListaEsperaAlocacaoMockMvc.perform(get("/api/lista-espera-alocacaos/{id}", listaEsperaAlocacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listaEsperaAlocacao.getId().intValue()))
            .andExpect(jsonPath("$.data").value(sameInstant(DEFAULT_DATA)));
    }

    @Test
    @Transactional
    public void getNonExistingListaEsperaAlocacao() throws Exception {
        // Get the listaEsperaAlocacao
        restListaEsperaAlocacaoMockMvc.perform(get("/api/lista-espera-alocacaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListaEsperaAlocacao() throws Exception {
        // Initialize the database
        listaEsperaAlocacaoRepository.saveAndFlush(listaEsperaAlocacao);
        int databaseSizeBeforeUpdate = listaEsperaAlocacaoRepository.findAll().size();

        // Update the listaEsperaAlocacao
        ListaEsperaAlocacao updatedListaEsperaAlocacao = listaEsperaAlocacaoRepository.findOne(listaEsperaAlocacao.getId());
        updatedListaEsperaAlocacao
            .data(UPDATED_DATA);

        restListaEsperaAlocacaoMockMvc.perform(put("/api/lista-espera-alocacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedListaEsperaAlocacao)))
            .andExpect(status().isOk());

        // Validate the ListaEsperaAlocacao in the database
        List<ListaEsperaAlocacao> listaEsperaAlocacaoList = listaEsperaAlocacaoRepository.findAll();
        assertThat(listaEsperaAlocacaoList).hasSize(databaseSizeBeforeUpdate);
        ListaEsperaAlocacao testListaEsperaAlocacao = listaEsperaAlocacaoList.get(listaEsperaAlocacaoList.size() - 1);
        assertThat(testListaEsperaAlocacao.getData()).isEqualTo(UPDATED_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingListaEsperaAlocacao() throws Exception {
        int databaseSizeBeforeUpdate = listaEsperaAlocacaoRepository.findAll().size();

        // Create the ListaEsperaAlocacao

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restListaEsperaAlocacaoMockMvc.perform(put("/api/lista-espera-alocacaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listaEsperaAlocacao)))
            .andExpect(status().isCreated());

        // Validate the ListaEsperaAlocacao in the database
        List<ListaEsperaAlocacao> listaEsperaAlocacaoList = listaEsperaAlocacaoRepository.findAll();
        assertThat(listaEsperaAlocacaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteListaEsperaAlocacao() throws Exception {
        // Initialize the database
        listaEsperaAlocacaoRepository.saveAndFlush(listaEsperaAlocacao);
        int databaseSizeBeforeDelete = listaEsperaAlocacaoRepository.findAll().size();

        // Get the listaEsperaAlocacao
        restListaEsperaAlocacaoMockMvc.perform(delete("/api/lista-espera-alocacaos/{id}", listaEsperaAlocacao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ListaEsperaAlocacao> listaEsperaAlocacaoList = listaEsperaAlocacaoRepository.findAll();
        assertThat(listaEsperaAlocacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListaEsperaAlocacao.class);
        ListaEsperaAlocacao listaEsperaAlocacao1 = new ListaEsperaAlocacao();
        listaEsperaAlocacao1.setId(1L);
        ListaEsperaAlocacao listaEsperaAlocacao2 = new ListaEsperaAlocacao();
        listaEsperaAlocacao2.setId(listaEsperaAlocacao1.getId());
        assertThat(listaEsperaAlocacao1).isEqualTo(listaEsperaAlocacao2);
        listaEsperaAlocacao2.setId(2L);
        assertThat(listaEsperaAlocacao1).isNotEqualTo(listaEsperaAlocacao2);
        listaEsperaAlocacao1.setId(null);
        assertThat(listaEsperaAlocacao1).isNotEqualTo(listaEsperaAlocacao2);
    }
}
