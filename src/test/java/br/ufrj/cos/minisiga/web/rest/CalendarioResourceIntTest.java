package br.ufrj.cos.minisiga.web.rest;

import br.ufrj.cos.minisiga.MinisigaApp;

import br.ufrj.cos.minisiga.domain.Calendario;
import br.ufrj.cos.minisiga.repository.CalendarioRepository;
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
 * Test class for the CalendarioResource REST controller.
 *
 * @see CalendarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MinisigaApp.class)
public class CalendarioResourceIntTest {

    private static final String DEFAULT_PERIODO = "AAAAAAAAAA";
    private static final String UPDATED_PERIODO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_INI_PERIODO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_INI_PERIODO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FIM_PERIODO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FIM_PERIODO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_INI_INSC_DISC = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_INI_INSC_DISC = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FIM_INS_DISC = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FIM_INS_DISC = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_INI_TRANC_DISC = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_INI_TRANC_DISC = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FIM_TRANC_DISC = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FIM_TRANC_DISC = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private CalendarioRepository calendarioRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCalendarioMockMvc;

    private Calendario calendario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CalendarioResource calendarioResource = new CalendarioResource(calendarioRepository);
        this.restCalendarioMockMvc = MockMvcBuilders.standaloneSetup(calendarioResource)
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
    public static Calendario createEntity(EntityManager em) {
        Calendario calendario = new Calendario()
            .periodo(DEFAULT_PERIODO)
            .iniPeriodo(DEFAULT_INI_PERIODO)
            .fimPeriodo(DEFAULT_FIM_PERIODO)
            .iniInscDisc(DEFAULT_INI_INSC_DISC)
            .fimInsDisc(DEFAULT_FIM_INS_DISC)
            .iniTrancDisc(DEFAULT_INI_TRANC_DISC)
            .fimTrancDisc(DEFAULT_FIM_TRANC_DISC);
        return calendario;
    }

    @Before
    public void initTest() {
        calendario = createEntity(em);
    }

    @Test
    @Transactional
    public void createCalendario() throws Exception {
        int databaseSizeBeforeCreate = calendarioRepository.findAll().size();

        // Create the Calendario
        restCalendarioMockMvc.perform(post("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendario)))
            .andExpect(status().isCreated());

        // Validate the Calendario in the database
        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeCreate + 1);
        Calendario testCalendario = calendarioList.get(calendarioList.size() - 1);
        assertThat(testCalendario.getPeriodo()).isEqualTo(DEFAULT_PERIODO);
        assertThat(testCalendario.getIniPeriodo()).isEqualTo(DEFAULT_INI_PERIODO);
        assertThat(testCalendario.getFimPeriodo()).isEqualTo(DEFAULT_FIM_PERIODO);
        assertThat(testCalendario.getIniInscDisc()).isEqualTo(DEFAULT_INI_INSC_DISC);
        assertThat(testCalendario.getFimInsDisc()).isEqualTo(DEFAULT_FIM_INS_DISC);
        assertThat(testCalendario.getIniTrancDisc()).isEqualTo(DEFAULT_INI_TRANC_DISC);
        assertThat(testCalendario.getFimTrancDisc()).isEqualTo(DEFAULT_FIM_TRANC_DISC);
    }

    @Test
    @Transactional
    public void createCalendarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = calendarioRepository.findAll().size();

        // Create the Calendario with an existing ID
        calendario.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCalendarioMockMvc.perform(post("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendario)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPeriodoIsRequired() throws Exception {
        int databaseSizeBeforeTest = calendarioRepository.findAll().size();
        // set the field null
        calendario.setPeriodo(null);

        // Create the Calendario, which fails.

        restCalendarioMockMvc.perform(post("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendario)))
            .andExpect(status().isBadRequest());

        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIniPeriodoIsRequired() throws Exception {
        int databaseSizeBeforeTest = calendarioRepository.findAll().size();
        // set the field null
        calendario.setIniPeriodo(null);

        // Create the Calendario, which fails.

        restCalendarioMockMvc.perform(post("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendario)))
            .andExpect(status().isBadRequest());

        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFimPeriodoIsRequired() throws Exception {
        int databaseSizeBeforeTest = calendarioRepository.findAll().size();
        // set the field null
        calendario.setFimPeriodo(null);

        // Create the Calendario, which fails.

        restCalendarioMockMvc.perform(post("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendario)))
            .andExpect(status().isBadRequest());

        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIniInscDiscIsRequired() throws Exception {
        int databaseSizeBeforeTest = calendarioRepository.findAll().size();
        // set the field null
        calendario.setIniInscDisc(null);

        // Create the Calendario, which fails.

        restCalendarioMockMvc.perform(post("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendario)))
            .andExpect(status().isBadRequest());

        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFimInsDiscIsRequired() throws Exception {
        int databaseSizeBeforeTest = calendarioRepository.findAll().size();
        // set the field null
        calendario.setFimInsDisc(null);

        // Create the Calendario, which fails.

        restCalendarioMockMvc.perform(post("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendario)))
            .andExpect(status().isBadRequest());

        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIniTrancDiscIsRequired() throws Exception {
        int databaseSizeBeforeTest = calendarioRepository.findAll().size();
        // set the field null
        calendario.setIniTrancDisc(null);

        // Create the Calendario, which fails.

        restCalendarioMockMvc.perform(post("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendario)))
            .andExpect(status().isBadRequest());

        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFimTrancDiscIsRequired() throws Exception {
        int databaseSizeBeforeTest = calendarioRepository.findAll().size();
        // set the field null
        calendario.setFimTrancDisc(null);

        // Create the Calendario, which fails.

        restCalendarioMockMvc.perform(post("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendario)))
            .andExpect(status().isBadRequest());

        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCalendarios() throws Exception {
        // Initialize the database
        calendarioRepository.saveAndFlush(calendario);

        // Get all the calendarioList
        restCalendarioMockMvc.perform(get("/api/calendarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(calendario.getId().intValue())))
            .andExpect(jsonPath("$.[*].periodo").value(hasItem(DEFAULT_PERIODO.toString())))
            .andExpect(jsonPath("$.[*].iniPeriodo").value(hasItem(sameInstant(DEFAULT_INI_PERIODO))))
            .andExpect(jsonPath("$.[*].fimPeriodo").value(hasItem(sameInstant(DEFAULT_FIM_PERIODO))))
            .andExpect(jsonPath("$.[*].iniInscDisc").value(hasItem(sameInstant(DEFAULT_INI_INSC_DISC))))
            .andExpect(jsonPath("$.[*].fimInsDisc").value(hasItem(sameInstant(DEFAULT_FIM_INS_DISC))))
            .andExpect(jsonPath("$.[*].iniTrancDisc").value(hasItem(sameInstant(DEFAULT_INI_TRANC_DISC))))
            .andExpect(jsonPath("$.[*].fimTrancDisc").value(hasItem(sameInstant(DEFAULT_FIM_TRANC_DISC))));
    }

    @Test
    @Transactional
    public void getCalendario() throws Exception {
        // Initialize the database
        calendarioRepository.saveAndFlush(calendario);

        // Get the calendario
        restCalendarioMockMvc.perform(get("/api/calendarios/{id}", calendario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(calendario.getId().intValue()))
            .andExpect(jsonPath("$.periodo").value(DEFAULT_PERIODO.toString()))
            .andExpect(jsonPath("$.iniPeriodo").value(sameInstant(DEFAULT_INI_PERIODO)))
            .andExpect(jsonPath("$.fimPeriodo").value(sameInstant(DEFAULT_FIM_PERIODO)))
            .andExpect(jsonPath("$.iniInscDisc").value(sameInstant(DEFAULT_INI_INSC_DISC)))
            .andExpect(jsonPath("$.fimInsDisc").value(sameInstant(DEFAULT_FIM_INS_DISC)))
            .andExpect(jsonPath("$.iniTrancDisc").value(sameInstant(DEFAULT_INI_TRANC_DISC)))
            .andExpect(jsonPath("$.fimTrancDisc").value(sameInstant(DEFAULT_FIM_TRANC_DISC)));
    }

    @Test
    @Transactional
    public void getNonExistingCalendario() throws Exception {
        // Get the calendario
        restCalendarioMockMvc.perform(get("/api/calendarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCalendario() throws Exception {
        // Initialize the database
        calendarioRepository.saveAndFlush(calendario);
        int databaseSizeBeforeUpdate = calendarioRepository.findAll().size();

        // Update the calendario
        Calendario updatedCalendario = calendarioRepository.findOne(calendario.getId());
        updatedCalendario
            .periodo(UPDATED_PERIODO)
            .iniPeriodo(UPDATED_INI_PERIODO)
            .fimPeriodo(UPDATED_FIM_PERIODO)
            .iniInscDisc(UPDATED_INI_INSC_DISC)
            .fimInsDisc(UPDATED_FIM_INS_DISC)
            .iniTrancDisc(UPDATED_INI_TRANC_DISC)
            .fimTrancDisc(UPDATED_FIM_TRANC_DISC);

        restCalendarioMockMvc.perform(put("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCalendario)))
            .andExpect(status().isOk());

        // Validate the Calendario in the database
        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeUpdate);
        Calendario testCalendario = calendarioList.get(calendarioList.size() - 1);
        assertThat(testCalendario.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testCalendario.getIniPeriodo()).isEqualTo(UPDATED_INI_PERIODO);
        assertThat(testCalendario.getFimPeriodo()).isEqualTo(UPDATED_FIM_PERIODO);
        assertThat(testCalendario.getIniInscDisc()).isEqualTo(UPDATED_INI_INSC_DISC);
        assertThat(testCalendario.getFimInsDisc()).isEqualTo(UPDATED_FIM_INS_DISC);
        assertThat(testCalendario.getIniTrancDisc()).isEqualTo(UPDATED_INI_TRANC_DISC);
        assertThat(testCalendario.getFimTrancDisc()).isEqualTo(UPDATED_FIM_TRANC_DISC);
    }

    @Test
    @Transactional
    public void updateNonExistingCalendario() throws Exception {
        int databaseSizeBeforeUpdate = calendarioRepository.findAll().size();

        // Create the Calendario

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCalendarioMockMvc.perform(put("/api/calendarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(calendario)))
            .andExpect(status().isCreated());

        // Validate the Calendario in the database
        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCalendario() throws Exception {
        // Initialize the database
        calendarioRepository.saveAndFlush(calendario);
        int databaseSizeBeforeDelete = calendarioRepository.findAll().size();

        // Get the calendario
        restCalendarioMockMvc.perform(delete("/api/calendarios/{id}", calendario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Calendario> calendarioList = calendarioRepository.findAll();
        assertThat(calendarioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Calendario.class);
        Calendario calendario1 = new Calendario();
        calendario1.setId(1L);
        Calendario calendario2 = new Calendario();
        calendario2.setId(calendario1.getId());
        assertThat(calendario1).isEqualTo(calendario2);
        calendario2.setId(2L);
        assertThat(calendario1).isNotEqualTo(calendario2);
        calendario1.setId(null);
        assertThat(calendario1).isNotEqualTo(calendario2);
    }
}
