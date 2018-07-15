package com.unicredit.isd.web.rest;

import com.unicredit.isd.IsdtoolsApp;

import com.unicredit.isd.domain.Isdcalc;
import com.unicredit.isd.repository.IsdcalcRepository;
import com.unicredit.isd.web.rest.errors.ExceptionTranslator;

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


import static com.unicredit.isd.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the IsdcalcResource REST controller.
 *
 * @see IsdcalcResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IsdtoolsApp.class)
public class IsdcalcResourceIntTest {

    @Autowired
    private IsdcalcRepository isdcalcRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restIsdcalcMockMvc;

    private Isdcalc isdcalc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IsdcalcResource isdcalcResource = new IsdcalcResource(isdcalcRepository);
        this.restIsdcalcMockMvc = MockMvcBuilders.standaloneSetup(isdcalcResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Isdcalc createEntity(EntityManager em) {
        Isdcalc isdcalc = new Isdcalc();
        return isdcalc;
    }

    @Before
    public void initTest() {
        isdcalc = createEntity(em);
    }

    @Test
    @Transactional
    public void createIsdcalc() throws Exception {
        int databaseSizeBeforeCreate = isdcalcRepository.findAll().size();

        // Create the Isdcalc
        restIsdcalcMockMvc.perform(post("/api/isdcalcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(isdcalc)))
            .andExpect(status().isCreated());

        // Validate the Isdcalc in the database
        List<Isdcalc> isdcalcList = isdcalcRepository.findAll();
        assertThat(isdcalcList).hasSize(databaseSizeBeforeCreate + 1);
        Isdcalc testIsdcalc = isdcalcList.get(isdcalcList.size() - 1);
    }

    @Test
    @Transactional
    public void createIsdcalcWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = isdcalcRepository.findAll().size();

        // Create the Isdcalc with an existing ID
        isdcalc.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIsdcalcMockMvc.perform(post("/api/isdcalcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(isdcalc)))
            .andExpect(status().isBadRequest());

        // Validate the Isdcalc in the database
        List<Isdcalc> isdcalcList = isdcalcRepository.findAll();
        assertThat(isdcalcList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllIsdcalcs() throws Exception {
        // Initialize the database
        isdcalcRepository.saveAndFlush(isdcalc);

        // Get all the isdcalcList
        restIsdcalcMockMvc.perform(get("/api/isdcalcs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(isdcalc.getId().intValue())));
    }
    

    @Test
    @Transactional
    public void getIsdcalc() throws Exception {
        // Initialize the database
        isdcalcRepository.saveAndFlush(isdcalc);

        // Get the isdcalc
        restIsdcalcMockMvc.perform(get("/api/isdcalcs/{id}", isdcalc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(isdcalc.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingIsdcalc() throws Exception {
        // Get the isdcalc
        restIsdcalcMockMvc.perform(get("/api/isdcalcs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIsdcalc() throws Exception {
        // Initialize the database
        isdcalcRepository.saveAndFlush(isdcalc);

        int databaseSizeBeforeUpdate = isdcalcRepository.findAll().size();

        // Update the isdcalc
        Isdcalc updatedIsdcalc = isdcalcRepository.findById(isdcalc.getId()).get();
        // Disconnect from session so that the updates on updatedIsdcalc are not directly saved in db
        em.detach(updatedIsdcalc);

        restIsdcalcMockMvc.perform(put("/api/isdcalcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIsdcalc)))
            .andExpect(status().isOk());

        // Validate the Isdcalc in the database
        List<Isdcalc> isdcalcList = isdcalcRepository.findAll();
        assertThat(isdcalcList).hasSize(databaseSizeBeforeUpdate);
        Isdcalc testIsdcalc = isdcalcList.get(isdcalcList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingIsdcalc() throws Exception {
        int databaseSizeBeforeUpdate = isdcalcRepository.findAll().size();

        // Create the Isdcalc

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restIsdcalcMockMvc.perform(put("/api/isdcalcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(isdcalc)))
            .andExpect(status().isBadRequest());

        // Validate the Isdcalc in the database
        List<Isdcalc> isdcalcList = isdcalcRepository.findAll();
        assertThat(isdcalcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIsdcalc() throws Exception {
        // Initialize the database
        isdcalcRepository.saveAndFlush(isdcalc);

        int databaseSizeBeforeDelete = isdcalcRepository.findAll().size();

        // Get the isdcalc
        restIsdcalcMockMvc.perform(delete("/api/isdcalcs/{id}", isdcalc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Isdcalc> isdcalcList = isdcalcRepository.findAll();
        assertThat(isdcalcList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Isdcalc.class);
        Isdcalc isdcalc1 = new Isdcalc();
        isdcalc1.setId(1L);
        Isdcalc isdcalc2 = new Isdcalc();
        isdcalc2.setId(isdcalc1.getId());
        assertThat(isdcalc1).isEqualTo(isdcalc2);
        isdcalc2.setId(2L);
        assertThat(isdcalc1).isNotEqualTo(isdcalc2);
        isdcalc1.setId(null);
        assertThat(isdcalc1).isNotEqualTo(isdcalc2);
    }
}
