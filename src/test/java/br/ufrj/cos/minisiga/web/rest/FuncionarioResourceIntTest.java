package br.ufrj.cos.minisiga.web.rest;

import br.ufrj.cos.minisiga.MinisigaApp;

import br.ufrj.cos.minisiga.domain.Funcionario;
import br.ufrj.cos.minisiga.repository.FuncionarioRepository;
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
 * Test class for the FuncionarioResource REST controller.
 *
 * @see FuncionarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MinisigaApp.class)
public class FuncionarioResourceIntTest {

    private static final String DEFAULT_CPF = "AAAAAAAAAA";
    private static final String UPDATED_CPF = "BBBBBBBBBB";

    private static final String DEFAULT_SIAP = "AAAAAAAAAA";
    private static final String UPDATED_SIAP = "BBBBBBBBBB";

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFuncionarioMockMvc;

    private Funcionario funcionario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FuncionarioResource funcionarioResource = new FuncionarioResource(funcionarioRepository);
        this.restFuncionarioMockMvc = MockMvcBuilders.standaloneSetup(funcionarioResource)
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
    public static Funcionario createEntity(EntityManager em) {
        Funcionario funcionario = new Funcionario()
            .cpf(DEFAULT_CPF)
            .siap(DEFAULT_SIAP);
        return funcionario;
    }

    @Before
    public void initTest() {
        funcionario = createEntity(em);
    }

    @Test
    @Transactional
    public void createFuncionario() throws Exception {
        int databaseSizeBeforeCreate = funcionarioRepository.findAll().size();

        // Create the Funcionario
        restFuncionarioMockMvc.perform(post("/api/funcionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(funcionario)))
            .andExpect(status().isCreated());

        // Validate the Funcionario in the database
        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeCreate + 1);
        Funcionario testFuncionario = funcionarioList.get(funcionarioList.size() - 1);
        assertThat(testFuncionario.getCpf()).isEqualTo(DEFAULT_CPF);
        assertThat(testFuncionario.getSiap()).isEqualTo(DEFAULT_SIAP);
    }

    @Test
    @Transactional
    public void createFuncionarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = funcionarioRepository.findAll().size();

        // Create the Funcionario with an existing ID
        funcionario.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFuncionarioMockMvc.perform(post("/api/funcionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(funcionario)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCpfIsRequired() throws Exception {
        int databaseSizeBeforeTest = funcionarioRepository.findAll().size();
        // set the field null
        funcionario.setCpf(null);

        // Create the Funcionario, which fails.

        restFuncionarioMockMvc.perform(post("/api/funcionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(funcionario)))
            .andExpect(status().isBadRequest());

        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSiapIsRequired() throws Exception {
        int databaseSizeBeforeTest = funcionarioRepository.findAll().size();
        // set the field null
        funcionario.setSiap(null);

        // Create the Funcionario, which fails.

        restFuncionarioMockMvc.perform(post("/api/funcionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(funcionario)))
            .andExpect(status().isBadRequest());

        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFuncionarios() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get all the funcionarioList
        restFuncionarioMockMvc.perform(get("/api/funcionarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(funcionario.getId().intValue())))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF.toString())))
            .andExpect(jsonPath("$.[*].siap").value(hasItem(DEFAULT_SIAP.toString())));
    }

    @Test
    @Transactional
    public void getFuncionario() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);

        // Get the funcionario
        restFuncionarioMockMvc.perform(get("/api/funcionarios/{id}", funcionario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(funcionario.getId().intValue()))
            .andExpect(jsonPath("$.cpf").value(DEFAULT_CPF.toString()))
            .andExpect(jsonPath("$.siap").value(DEFAULT_SIAP.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFuncionario() throws Exception {
        // Get the funcionario
        restFuncionarioMockMvc.perform(get("/api/funcionarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFuncionario() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);
        int databaseSizeBeforeUpdate = funcionarioRepository.findAll().size();

        // Update the funcionario
        Funcionario updatedFuncionario = funcionarioRepository.findOne(funcionario.getId());
        updatedFuncionario
            .cpf(UPDATED_CPF)
            .siap(UPDATED_SIAP);

        restFuncionarioMockMvc.perform(put("/api/funcionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFuncionario)))
            .andExpect(status().isOk());

        // Validate the Funcionario in the database
        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeUpdate);
        Funcionario testFuncionario = funcionarioList.get(funcionarioList.size() - 1);
        assertThat(testFuncionario.getCpf()).isEqualTo(UPDATED_CPF);
        assertThat(testFuncionario.getSiap()).isEqualTo(UPDATED_SIAP);
    }

    @Test
    @Transactional
    public void updateNonExistingFuncionario() throws Exception {
        int databaseSizeBeforeUpdate = funcionarioRepository.findAll().size();

        // Create the Funcionario

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFuncionarioMockMvc.perform(put("/api/funcionarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(funcionario)))
            .andExpect(status().isCreated());

        // Validate the Funcionario in the database
        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFuncionario() throws Exception {
        // Initialize the database
        funcionarioRepository.saveAndFlush(funcionario);
        int databaseSizeBeforeDelete = funcionarioRepository.findAll().size();

        // Get the funcionario
        restFuncionarioMockMvc.perform(delete("/api/funcionarios/{id}", funcionario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Funcionario> funcionarioList = funcionarioRepository.findAll();
        assertThat(funcionarioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Funcionario.class);
        Funcionario funcionario1 = new Funcionario();
        funcionario1.setId(1L);
        Funcionario funcionario2 = new Funcionario();
        funcionario2.setId(funcionario1.getId());
        assertThat(funcionario1).isEqualTo(funcionario2);
        funcionario2.setId(2L);
        assertThat(funcionario1).isNotEqualTo(funcionario2);
        funcionario1.setId(null);
        assertThat(funcionario1).isNotEqualTo(funcionario2);
    }
}
