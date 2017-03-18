package com.btcars.web.rest;

import com.btcars.BtcarsApp;

import com.btcars.domain.Recommend;
import com.btcars.repository.RecommendRepository;
import com.btcars.web.rest.errors.ExceptionTranslator;

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
 * Test class for the RecommendResource REST controller.
 *
 * @see RecommendResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BtcarsApp.class)
public class RecommendResourceIntTest {

    @Autowired
    private RecommendRepository recommendRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRecommendMockMvc;

    private Recommend recommend;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
            RecommendResource recommendResource = new RecommendResource(recommendRepository);
        this.restRecommendMockMvc = MockMvcBuilders.standaloneSetup(recommendResource)
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
    public static Recommend createEntity(EntityManager em) {
        Recommend recommend = new Recommend();
        return recommend;
    }

    @Before
    public void initTest() {
        recommend = createEntity(em);
    }

    @Test
    @Transactional
    public void createRecommend() throws Exception {
        int databaseSizeBeforeCreate = recommendRepository.findAll().size();

        // Create the Recommend

        restRecommendMockMvc.perform(post("/api/recommends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recommend)))
            .andExpect(status().isCreated());

        // Validate the Recommend in the database
        List<Recommend> recommendList = recommendRepository.findAll();
        assertThat(recommendList).hasSize(databaseSizeBeforeCreate + 1);
        Recommend testRecommend = recommendList.get(recommendList.size() - 1);
    }

    @Test
    @Transactional
    public void createRecommendWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recommendRepository.findAll().size();

        // Create the Recommend with an existing ID
        Recommend existingRecommend = new Recommend();
        existingRecommend.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecommendMockMvc.perform(post("/api/recommends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingRecommend)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Recommend> recommendList = recommendRepository.findAll();
        assertThat(recommendList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRecommends() throws Exception {
        // Initialize the database
        recommendRepository.saveAndFlush(recommend);

        // Get all the recommendList
        restRecommendMockMvc.perform(get("/api/recommends?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recommend.getId().intValue())));
    }

    @Test
    @Transactional
    public void getRecommend() throws Exception {
        // Initialize the database
        recommendRepository.saveAndFlush(recommend);

        // Get the recommend
        restRecommendMockMvc.perform(get("/api/recommends/{id}", recommend.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(recommend.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRecommend() throws Exception {
        // Get the recommend
        restRecommendMockMvc.perform(get("/api/recommends/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRecommend() throws Exception {
        // Initialize the database
        recommendRepository.saveAndFlush(recommend);
        int databaseSizeBeforeUpdate = recommendRepository.findAll().size();

        // Update the recommend
        Recommend updatedRecommend = recommendRepository.findOne(recommend.getId());

        restRecommendMockMvc.perform(put("/api/recommends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRecommend)))
            .andExpect(status().isOk());

        // Validate the Recommend in the database
        List<Recommend> recommendList = recommendRepository.findAll();
        assertThat(recommendList).hasSize(databaseSizeBeforeUpdate);
        Recommend testRecommend = recommendList.get(recommendList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingRecommend() throws Exception {
        int databaseSizeBeforeUpdate = recommendRepository.findAll().size();

        // Create the Recommend

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRecommendMockMvc.perform(put("/api/recommends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recommend)))
            .andExpect(status().isCreated());

        // Validate the Recommend in the database
        List<Recommend> recommendList = recommendRepository.findAll();
        assertThat(recommendList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRecommend() throws Exception {
        // Initialize the database
        recommendRepository.saveAndFlush(recommend);
        int databaseSizeBeforeDelete = recommendRepository.findAll().size();

        // Get the recommend
        restRecommendMockMvc.perform(delete("/api/recommends/{id}", recommend.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Recommend> recommendList = recommendRepository.findAll();
        assertThat(recommendList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Recommend.class);
    }
}
