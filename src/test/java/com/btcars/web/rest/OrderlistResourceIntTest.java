package com.btcars.web.rest;

import com.btcars.BtcarsApp;

import com.btcars.domain.Orderlist;
import com.btcars.repository.OrderlistRepository;
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
 * Test class for the OrderlistResource REST controller.
 *
 * @see OrderlistResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BtcarsApp.class)
public class OrderlistResourceIntTest {

    private static final String DEFAULT_HO = "AAAAAAAAAA";
    private static final String UPDATED_HO = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Long DEFAULT_TOTAL = 1L;
    private static final Long UPDATED_TOTAL = 2L;

    @Autowired
    private OrderlistRepository orderlistRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrderlistMockMvc;

    private Orderlist orderlist;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
            OrderlistResource orderlistResource = new OrderlistResource(orderlistRepository);
        this.restOrderlistMockMvc = MockMvcBuilders.standaloneSetup(orderlistResource)
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
    public static Orderlist createEntity(EntityManager em) {
        Orderlist orderlist = new Orderlist()
                .ho(DEFAULT_HO)
                .ten(DEFAULT_TEN)
                .address(DEFAULT_ADDRESS)
                .total(DEFAULT_TOTAL);
        return orderlist;
    }

    @Before
    public void initTest() {
        orderlist = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderlist() throws Exception {
        int databaseSizeBeforeCreate = orderlistRepository.findAll().size();

        // Create the Orderlist

        restOrderlistMockMvc.perform(post("/api/orderlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderlist)))
            .andExpect(status().isCreated());

        // Validate the Orderlist in the database
        List<Orderlist> orderlistList = orderlistRepository.findAll();
        assertThat(orderlistList).hasSize(databaseSizeBeforeCreate + 1);
        Orderlist testOrderlist = orderlistList.get(orderlistList.size() - 1);
        assertThat(testOrderlist.getHo()).isEqualTo(DEFAULT_HO);
        assertThat(testOrderlist.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testOrderlist.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testOrderlist.getTotal()).isEqualTo(DEFAULT_TOTAL);
    }

    @Test
    @Transactional
    public void createOrderlistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderlistRepository.findAll().size();

        // Create the Orderlist with an existing ID
        Orderlist existingOrderlist = new Orderlist();
        existingOrderlist.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderlistMockMvc.perform(post("/api/orderlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingOrderlist)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Orderlist> orderlistList = orderlistRepository.findAll();
        assertThat(orderlistList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkHoIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderlistRepository.findAll().size();
        // set the field null
        orderlist.setHo(null);

        // Create the Orderlist, which fails.

        restOrderlistMockMvc.perform(post("/api/orderlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderlist)))
            .andExpect(status().isBadRequest());

        List<Orderlist> orderlistList = orderlistRepository.findAll();
        assertThat(orderlistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTenIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderlistRepository.findAll().size();
        // set the field null
        orderlist.setTen(null);

        // Create the Orderlist, which fails.

        restOrderlistMockMvc.perform(post("/api/orderlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderlist)))
            .andExpect(status().isBadRequest());

        List<Orderlist> orderlistList = orderlistRepository.findAll();
        assertThat(orderlistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderlistRepository.findAll().size();
        // set the field null
        orderlist.setAddress(null);

        // Create the Orderlist, which fails.

        restOrderlistMockMvc.perform(post("/api/orderlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderlist)))
            .andExpect(status().isBadRequest());

        List<Orderlist> orderlistList = orderlistRepository.findAll();
        assertThat(orderlistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderlistRepository.findAll().size();
        // set the field null
        orderlist.setTotal(null);

        // Create the Orderlist, which fails.

        restOrderlistMockMvc.perform(post("/api/orderlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderlist)))
            .andExpect(status().isBadRequest());

        List<Orderlist> orderlistList = orderlistRepository.findAll();
        assertThat(orderlistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrderlists() throws Exception {
        // Initialize the database
        orderlistRepository.saveAndFlush(orderlist);

        // Get all the orderlistList
        restOrderlistMockMvc.perform(get("/api/orderlists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderlist.getId().intValue())))
            .andExpect(jsonPath("$.[*].ho").value(hasItem(DEFAULT_HO.toString())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.intValue())));
    }

    @Test
    @Transactional
    public void getOrderlist() throws Exception {
        // Initialize the database
        orderlistRepository.saveAndFlush(orderlist);

        // Get the orderlist
        restOrderlistMockMvc.perform(get("/api/orderlists/{id}", orderlist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderlist.getId().intValue()))
            .andExpect(jsonPath("$.ho").value(DEFAULT_HO.toString()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOrderlist() throws Exception {
        // Get the orderlist
        restOrderlistMockMvc.perform(get("/api/orderlists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderlist() throws Exception {
        // Initialize the database
        orderlistRepository.saveAndFlush(orderlist);
        int databaseSizeBeforeUpdate = orderlistRepository.findAll().size();

        // Update the orderlist
        Orderlist updatedOrderlist = orderlistRepository.findOne(orderlist.getId());
        updatedOrderlist
                .ho(UPDATED_HO)
                .ten(UPDATED_TEN)
                .address(UPDATED_ADDRESS)
                .total(UPDATED_TOTAL);

        restOrderlistMockMvc.perform(put("/api/orderlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrderlist)))
            .andExpect(status().isOk());

        // Validate the Orderlist in the database
        List<Orderlist> orderlistList = orderlistRepository.findAll();
        assertThat(orderlistList).hasSize(databaseSizeBeforeUpdate);
        Orderlist testOrderlist = orderlistList.get(orderlistList.size() - 1);
        assertThat(testOrderlist.getHo()).isEqualTo(UPDATED_HO);
        assertThat(testOrderlist.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testOrderlist.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testOrderlist.getTotal()).isEqualTo(UPDATED_TOTAL);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderlist() throws Exception {
        int databaseSizeBeforeUpdate = orderlistRepository.findAll().size();

        // Create the Orderlist

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOrderlistMockMvc.perform(put("/api/orderlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderlist)))
            .andExpect(status().isCreated());

        // Validate the Orderlist in the database
        List<Orderlist> orderlistList = orderlistRepository.findAll();
        assertThat(orderlistList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOrderlist() throws Exception {
        // Initialize the database
        orderlistRepository.saveAndFlush(orderlist);
        int databaseSizeBeforeDelete = orderlistRepository.findAll().size();

        // Get the orderlist
        restOrderlistMockMvc.perform(delete("/api/orderlists/{id}", orderlist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Orderlist> orderlistList = orderlistRepository.findAll();
        assertThat(orderlistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Orderlist.class);
    }
}
