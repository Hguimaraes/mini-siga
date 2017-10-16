package br.ufrj.cos.minisiga.web.rest;

import br.ufrj.cos.minisiga.MinisigaApp;

import br.ufrj.cos.minisiga.domain.Horario;
import br.ufrj.cos.minisiga.repository.HorarioRepository;
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
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the HorarioResource REST controller.
 *
 * @see HorarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MinisigaApp.class)
public class HorarioResourceIntTest {

    private static final LocalDate DEFAULT_DIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIA = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_HORA_INICIO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_HORA_INICIO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_HORA_FIM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_HORA_FIM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DESC_EXTENSO = "AAAAAAAAAA";
    private static final String UPDATED_DESC_EXTENSO = "BBBBBBBBBB";

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHorarioMockMvc;

    private Horario horario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        HorarioResource horarioResource = new HorarioResource(horarioRepository);
        this.restHorarioMockMvc = MockMvcBuilders.standaloneSetup(horarioResource)
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
    public static Horario createEntity(EntityManager em) {
        Horario horario = new Horario()
            .dia(DEFAULT_DIA)
            .horaInicio(DEFAULT_HORA_INICIO)
            .horaFim(DEFAULT_HORA_FIM)
            .descExtenso(DEFAULT_DESC_EXTENSO);
        return horario;
    }

    @Before
    public void initTest() {
        horario = createEntity(em);
    }

    @Test
    @Transactional
    public void createHorario() throws Exception {
        int databaseSizeBeforeCreate = horarioRepository.findAll().size();

        // Create the Horario
        restHorarioMockMvc.perform(post("/api/horarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(horario)))
            .andExpect(status().isCreated());

        // Validate the Horario in the database
        List<Horario> horarioList = horarioRepository.findAll();
        assertThat(horarioList).hasSize(databaseSizeBeforeCreate + 1);
        Horario testHorario = horarioList.get(horarioList.size() - 1);
        assertThat(testHorario.getDia()).isEqualTo(DEFAULT_DIA);
        assertThat(testHorario.getHoraInicio()).isEqualTo(DEFAULT_HORA_INICIO);
        assertThat(testHorario.getHoraFim()).isEqualTo(DEFAULT_HORA_FIM);
        assertThat(testHorario.getDescExtenso()).isEqualTo(DEFAULT_DESC_EXTENSO);
    }

    @Test
    @Transactional
    public void createHorarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = horarioRepository.findAll().size();

        // Create the Horario with an existing ID
        horario.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHorarioMockMvc.perform(post("/api/horarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(horario)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Horario> horarioList = horarioRepository.findAll();
        assertThat(horarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDiaIsRequired() throws Exception {
        int databaseSizeBeforeTest = horarioRepository.findAll().size();
        // set the field null
        horario.setDia(null);

        // Create the Horario, which fails.

        restHorarioMockMvc.perform(post("/api/horarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(horario)))
            .andExpect(status().isBadRequest());

        List<Horario> horarioList = horarioRepository.findAll();
        assertThat(horarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoraInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = horarioRepository.findAll().size();
        // set the field null
        horario.setHoraInicio(null);

        // Create the Horario, which fails.

        restHorarioMockMvc.perform(post("/api/horarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(horario)))
            .andExpect(status().isBadRequest());

        List<Horario> horarioList = horarioRepository.findAll();
        assertThat(horarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoraFimIsRequired() throws Exception {
        int databaseSizeBeforeTest = horarioRepository.findAll().size();
        // set the field null
        horario.setHoraFim(null);

        // Create the Horario, which fails.

        restHorarioMockMvc.perform(post("/api/horarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(horario)))
            .andExpect(status().isBadRequest());

        List<Horario> horarioList = horarioRepository.findAll();
        assertThat(horarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescExtensoIsRequired() throws Exception {
        int databaseSizeBeforeTest = horarioRepository.findAll().size();
        // set the field null
        horario.setDescExtenso(null);

        // Create the Horario, which fails.

        restHorarioMockMvc.perform(post("/api/horarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(horario)))
            .andExpect(status().isBadRequest());

        List<Horario> horarioList = horarioRepository.findAll();
        assertThat(horarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHorarios() throws Exception {
        // Initialize the database
        horarioRepository.saveAndFlush(horario);

        // Get all the horarioList
        restHorarioMockMvc.perform(get("/api/horarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(horario.getId().intValue())))
            .andExpect(jsonPath("$.[*].dia").value(hasItem(DEFAULT_DIA.toString())))
            .andExpect(jsonPath("$.[*].horaInicio").value(hasItem(DEFAULT_HORA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].horaFim").value(hasItem(DEFAULT_HORA_FIM.toString())))
            .andExpect(jsonPath("$.[*].descExtenso").value(hasItem(DEFAULT_DESC_EXTENSO.toString())));
    }

    @Test
    @Transactional
    public void getHorario() throws Exception {
        // Initialize the database
        horarioRepository.saveAndFlush(horario);

        // Get the horario
        restHorarioMockMvc.perform(get("/api/horarios/{id}", horario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(horario.getId().intValue()))
            .andExpect(jsonPath("$.dia").value(DEFAULT_DIA.toString()))
            .andExpect(jsonPath("$.horaInicio").value(DEFAULT_HORA_INICIO.toString()))
            .andExpect(jsonPath("$.horaFim").value(DEFAULT_HORA_FIM.toString()))
            .andExpect(jsonPath("$.descExtenso").value(DEFAULT_DESC_EXTENSO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHorario() throws Exception {
        // Get the horario
        restHorarioMockMvc.perform(get("/api/horarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHorario() throws Exception {
        // Initialize the database
        horarioRepository.saveAndFlush(horario);
        int databaseSizeBeforeUpdate = horarioRepository.findAll().size();

        // Update the horario
        Horario updatedHorario = horarioRepository.findOne(horario.getId());
        updatedHorario
            .dia(UPDATED_DIA)
            .horaInicio(UPDATED_HORA_INICIO)
            .horaFim(UPDATED_HORA_FIM)
            .descExtenso(UPDATED_DESC_EXTENSO);

        restHorarioMockMvc.perform(put("/api/horarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHorario)))
            .andExpect(status().isOk());

        // Validate the Horario in the database
        List<Horario> horarioList = horarioRepository.findAll();
        assertThat(horarioList).hasSize(databaseSizeBeforeUpdate);
        Horario testHorario = horarioList.get(horarioList.size() - 1);
        assertThat(testHorario.getDia()).isEqualTo(UPDATED_DIA);
        assertThat(testHorario.getHoraInicio()).isEqualTo(UPDATED_HORA_INICIO);
        assertThat(testHorario.getHoraFim()).isEqualTo(UPDATED_HORA_FIM);
        assertThat(testHorario.getDescExtenso()).isEqualTo(UPDATED_DESC_EXTENSO);
    }

    @Test
    @Transactional
    public void updateNonExistingHorario() throws Exception {
        int databaseSizeBeforeUpdate = horarioRepository.findAll().size();

        // Create the Horario

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restHorarioMockMvc.perform(put("/api/horarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(horario)))
            .andExpect(status().isCreated());

        // Validate the Horario in the database
        List<Horario> horarioList = horarioRepository.findAll();
        assertThat(horarioList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteHorario() throws Exception {
        // Initialize the database
        horarioRepository.saveAndFlush(horario);
        int databaseSizeBeforeDelete = horarioRepository.findAll().size();

        // Get the horario
        restHorarioMockMvc.perform(delete("/api/horarios/{id}", horario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Horario> horarioList = horarioRepository.findAll();
        assertThat(horarioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Horario.class);
        Horario horario1 = new Horario();
        horario1.setId(1L);
        Horario horario2 = new Horario();
        horario2.setId(horario1.getId());
        assertThat(horario1).isEqualTo(horario2);
        horario2.setId(2L);
        assertThat(horario1).isNotEqualTo(horario2);
        horario1.setId(null);
        assertThat(horario1).isNotEqualTo(horario2);
    }
}
