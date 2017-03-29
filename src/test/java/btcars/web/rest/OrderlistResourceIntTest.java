package btcars.web.rest;

import btcars.BtcarsApp;

import btcars.domain.Orderlist;
import btcars.repository.OrderlistRepository;
import btcars.repository.search.OrderlistSearchRepository;
import btcars.web.rest.errors.ExceptionTranslator;

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

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_PHONENUMBER = 1;
    private static final Integer UPDATED_PHONENUMBER = 2;

    private static final Integer DEFAULT_TOTAL = 1;
    private static final Integer UPDATED_TOTAL = 2;

    @Autowired
    private OrderlistRepository orderlistRepository;

    @Autowired
    private OrderlistSearchRepository orderlistSearchRepository;

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
            OrderlistResource orderlistResource = new OrderlistResource(orderlistRepository, orderlistSearchRepository);
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
                .firstname(DEFAULT_FIRSTNAME)
                .lastname(DEFAULT_LASTNAME)
                .address(DEFAULT_ADDRESS)
                .phonenumber(DEFAULT_PHONENUMBER)
                .total(DEFAULT_TOTAL);
        return orderlist;
    }

    @Before
    public void initTest() {
        orderlistSearchRepository.deleteAll();
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
        assertThat(testOrderlist.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testOrderlist.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testOrderlist.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testOrderlist.getPhonenumber()).isEqualTo(DEFAULT_PHONENUMBER);
        assertThat(testOrderlist.getTotal()).isEqualTo(DEFAULT_TOTAL);

        // Validate the Orderlist in Elasticsearch
        Orderlist orderlistEs = orderlistSearchRepository.findOne(testOrderlist.getId());
        assertThat(orderlistEs).isEqualToComparingFieldByField(testOrderlist);
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
    public void checkFirstnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderlistRepository.findAll().size();
        // set the field null
        orderlist.setFirstname(null);

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
    public void checkLastnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderlistRepository.findAll().size();
        // set the field null
        orderlist.setLastname(null);

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
    public void checkPhonenumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderlistRepository.findAll().size();
        // set the field null
        orderlist.setPhonenumber(null);

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
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME.toString())))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].phonenumber").value(hasItem(DEFAULT_PHONENUMBER)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL)));
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
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME.toString()))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.phonenumber").value(DEFAULT_PHONENUMBER))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL));
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
        orderlistSearchRepository.save(orderlist);
        int databaseSizeBeforeUpdate = orderlistRepository.findAll().size();

        // Update the orderlist
        Orderlist updatedOrderlist = orderlistRepository.findOne(orderlist.getId());
        updatedOrderlist
                .firstname(UPDATED_FIRSTNAME)
                .lastname(UPDATED_LASTNAME)
                .address(UPDATED_ADDRESS)
                .phonenumber(UPDATED_PHONENUMBER)
                .total(UPDATED_TOTAL);

        restOrderlistMockMvc.perform(put("/api/orderlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrderlist)))
            .andExpect(status().isOk());

        // Validate the Orderlist in the database
        List<Orderlist> orderlistList = orderlistRepository.findAll();
        assertThat(orderlistList).hasSize(databaseSizeBeforeUpdate);
        Orderlist testOrderlist = orderlistList.get(orderlistList.size() - 1);
        assertThat(testOrderlist.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testOrderlist.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testOrderlist.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testOrderlist.getPhonenumber()).isEqualTo(UPDATED_PHONENUMBER);
        assertThat(testOrderlist.getTotal()).isEqualTo(UPDATED_TOTAL);

        // Validate the Orderlist in Elasticsearch
        Orderlist orderlistEs = orderlistSearchRepository.findOne(testOrderlist.getId());
        assertThat(orderlistEs).isEqualToComparingFieldByField(testOrderlist);
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
        orderlistSearchRepository.save(orderlist);
        int databaseSizeBeforeDelete = orderlistRepository.findAll().size();

        // Get the orderlist
        restOrderlistMockMvc.perform(delete("/api/orderlists/{id}", orderlist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean orderlistExistsInEs = orderlistSearchRepository.exists(orderlist.getId());
        assertThat(orderlistExistsInEs).isFalse();

        // Validate the database is empty
        List<Orderlist> orderlistList = orderlistRepository.findAll();
        assertThat(orderlistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchOrderlist() throws Exception {
        // Initialize the database
        orderlistRepository.saveAndFlush(orderlist);
        orderlistSearchRepository.save(orderlist);

        // Search the orderlist
        restOrderlistMockMvc.perform(get("/api/_search/orderlists?query=id:" + orderlist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderlist.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME.toString())))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].phonenumber").value(hasItem(DEFAULT_PHONENUMBER)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL)));
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Orderlist.class);
    }
}
