package com.flon.love.web.rest;

import com.flon.love.LoveApp;
import com.flon.love.domain.ShopPerformance;
import com.flon.love.repository.ShopPerformanceRepository;
import com.flon.love.service.ShopPerformanceService;
import com.flon.love.service.dto.ShopPerformanceDTO;
import com.flon.love.service.mapper.ShopPerformanceMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ShopPerformanceResource} REST controller.
 */
@SpringBootTest(classes = LoveApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ShopPerformanceResourceIT {

    private static final String DEFAULT_SHOP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHOP_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRIMERA_CONSULTA = 1;
    private static final Integer UPDATED_PRIMERA_CONSULTA = 2;

    private static final Integer DEFAULT_PRIMERA_CONSULTA_LEAVE = 1;
    private static final Integer UPDATED_PRIMERA_CONSULTA_LEAVE = 2;

    private static final Integer DEFAULT_NUEVA_CONSULTA = 1;
    private static final Integer UPDATED_NUEVA_CONSULTA = 2;

    private static final Integer DEFAULT_TODAY_PERFORMANCE = 1;
    private static final Integer UPDATED_TODAY_PERFORMANCE = 2;

    private static final Integer DEFAULT_TOTAL_PERFORMANCE = 1;
    private static final Integer UPDATED_TOTAL_PERFORMANCE = 2;

    private static final String DEFAULT_CREATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_TIME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DEL = 1;
    private static final Integer UPDATED_DEL = 0;

    @Autowired
    private ShopPerformanceRepository shopPerformanceRepository;

    @Autowired
    private ShopPerformanceMapper shopPerformanceMapper;

    @Autowired
    private ShopPerformanceService shopPerformanceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restShopPerformanceMockMvc;

    private ShopPerformance shopPerformance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShopPerformance createEntity(EntityManager em) {
        ShopPerformance shopPerformance = new ShopPerformance()
            .shopName(DEFAULT_SHOP_NAME)
            .primeraConsulta(DEFAULT_PRIMERA_CONSULTA)
            .primeraConsultaLeave(DEFAULT_PRIMERA_CONSULTA_LEAVE)
            .nuevaConsulta(DEFAULT_NUEVA_CONSULTA)
            .todayPerformance(DEFAULT_TODAY_PERFORMANCE)
            .totalPerformance(DEFAULT_TOTAL_PERFORMANCE)
            .createTime(DEFAULT_CREATE_TIME)
            .del(DEFAULT_DEL);
        return shopPerformance;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShopPerformance createUpdatedEntity(EntityManager em) {
        ShopPerformance shopPerformance = new ShopPerformance()
            .shopName(UPDATED_SHOP_NAME)
            .primeraConsulta(UPDATED_PRIMERA_CONSULTA)
            .primeraConsultaLeave(UPDATED_PRIMERA_CONSULTA_LEAVE)
            .nuevaConsulta(UPDATED_NUEVA_CONSULTA)
            .todayPerformance(UPDATED_TODAY_PERFORMANCE)
            .totalPerformance(UPDATED_TOTAL_PERFORMANCE)
            .createTime(UPDATED_CREATE_TIME)
            .del(UPDATED_DEL);
        return shopPerformance;
    }

    @BeforeEach
    public void initTest() {
        shopPerformance = createEntity(em);
    }

    @Test
    @Transactional
    public void createShopPerformance() throws Exception {
        int databaseSizeBeforeCreate = shopPerformanceRepository.findAll().size();
        // Create the ShopPerformance
        ShopPerformanceDTO shopPerformanceDTO = shopPerformanceMapper.toDto(shopPerformance);
        restShopPerformanceMockMvc.perform(post("/api/shop-performances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopPerformanceDTO)))
            .andExpect(status().isCreated());

        // Validate the ShopPerformance in the database
        List<ShopPerformance> shopPerformanceList = shopPerformanceRepository.findAll();
        assertThat(shopPerformanceList).hasSize(databaseSizeBeforeCreate + 1);
        ShopPerformance testShopPerformance = shopPerformanceList.get(shopPerformanceList.size() - 1);
        assertThat(testShopPerformance.getShopName()).isEqualTo(DEFAULT_SHOP_NAME);
        assertThat(testShopPerformance.getPrimeraConsulta()).isEqualTo(DEFAULT_PRIMERA_CONSULTA);
        assertThat(testShopPerformance.getPrimeraConsultaLeave()).isEqualTo(DEFAULT_PRIMERA_CONSULTA_LEAVE);
        assertThat(testShopPerformance.getNuevaConsulta()).isEqualTo(DEFAULT_NUEVA_CONSULTA);
        assertThat(testShopPerformance.getTodayPerformance()).isEqualTo(DEFAULT_TODAY_PERFORMANCE);
        assertThat(testShopPerformance.getTotalPerformance()).isEqualTo(DEFAULT_TOTAL_PERFORMANCE);
        assertThat(testShopPerformance.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testShopPerformance.getDel()).isEqualTo(DEFAULT_DEL);
    }

    @Test
    @Transactional
    public void createShopPerformanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shopPerformanceRepository.findAll().size();

        // Create the ShopPerformance with an existing ID
        shopPerformance.setId(1L);
        ShopPerformanceDTO shopPerformanceDTO = shopPerformanceMapper.toDto(shopPerformance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShopPerformanceMockMvc.perform(post("/api/shop-performances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopPerformanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShopPerformance in the database
        List<ShopPerformance> shopPerformanceList = shopPerformanceRepository.findAll();
        assertThat(shopPerformanceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllShopPerformances() throws Exception {
        // Initialize the database
        shopPerformanceRepository.saveAndFlush(shopPerformance);

        // Get all the shopPerformanceList
        restShopPerformanceMockMvc.perform(get("/api/shop-performances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shopPerformance.getId().intValue())))
            .andExpect(jsonPath("$.[*].shopName").value(hasItem(DEFAULT_SHOP_NAME)))
            .andExpect(jsonPath("$.[*].primeraConsulta").value(hasItem(DEFAULT_PRIMERA_CONSULTA)))
            .andExpect(jsonPath("$.[*].primeraConsultaLeave").value(hasItem(DEFAULT_PRIMERA_CONSULTA_LEAVE)))
            .andExpect(jsonPath("$.[*].nuevaConsulta").value(hasItem(DEFAULT_NUEVA_CONSULTA)))
            .andExpect(jsonPath("$.[*].todayPerformance").value(hasItem(DEFAULT_TODAY_PERFORMANCE)))
            .andExpect(jsonPath("$.[*].totalPerformance").value(hasItem(DEFAULT_TOTAL_PERFORMANCE)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME)))
            .andExpect(jsonPath("$.[*].del").value(hasItem(DEFAULT_DEL)));
    }
    
    @Test
    @Transactional
    public void getShopPerformance() throws Exception {
        // Initialize the database
        shopPerformanceRepository.saveAndFlush(shopPerformance);

        // Get the shopPerformance
        restShopPerformanceMockMvc.perform(get("/api/shop-performances/{id}", shopPerformance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(shopPerformance.getId().intValue()))
            .andExpect(jsonPath("$.shopName").value(DEFAULT_SHOP_NAME))
            .andExpect(jsonPath("$.primeraConsulta").value(DEFAULT_PRIMERA_CONSULTA))
            .andExpect(jsonPath("$.primeraConsultaLeave").value(DEFAULT_PRIMERA_CONSULTA_LEAVE))
            .andExpect(jsonPath("$.nuevaConsulta").value(DEFAULT_NUEVA_CONSULTA))
            .andExpect(jsonPath("$.todayPerformance").value(DEFAULT_TODAY_PERFORMANCE))
            .andExpect(jsonPath("$.totalPerformance").value(DEFAULT_TOTAL_PERFORMANCE))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME))
            .andExpect(jsonPath("$.del").value(DEFAULT_DEL));
    }
    @Test
    @Transactional
    public void getNonExistingShopPerformance() throws Exception {
        // Get the shopPerformance
        restShopPerformanceMockMvc.perform(get("/api/shop-performances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShopPerformance() throws Exception {
        // Initialize the database
        shopPerformanceRepository.saveAndFlush(shopPerformance);

        int databaseSizeBeforeUpdate = shopPerformanceRepository.findAll().size();

        // Update the shopPerformance
        ShopPerformance updatedShopPerformance = shopPerformanceRepository.findById(shopPerformance.getId()).get();
        // Disconnect from session so that the updates on updatedShopPerformance are not directly saved in db
        em.detach(updatedShopPerformance);
        updatedShopPerformance
            .shopName(UPDATED_SHOP_NAME)
            .primeraConsulta(UPDATED_PRIMERA_CONSULTA)
            .primeraConsultaLeave(UPDATED_PRIMERA_CONSULTA_LEAVE)
            .nuevaConsulta(UPDATED_NUEVA_CONSULTA)
            .todayPerformance(UPDATED_TODAY_PERFORMANCE)
            .totalPerformance(UPDATED_TOTAL_PERFORMANCE)
            .createTime(UPDATED_CREATE_TIME)
            .del(UPDATED_DEL);
        ShopPerformanceDTO shopPerformanceDTO = shopPerformanceMapper.toDto(updatedShopPerformance);

        restShopPerformanceMockMvc.perform(put("/api/shop-performances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopPerformanceDTO)))
            .andExpect(status().isOk());

        // Validate the ShopPerformance in the database
        List<ShopPerformance> shopPerformanceList = shopPerformanceRepository.findAll();
        assertThat(shopPerformanceList).hasSize(databaseSizeBeforeUpdate);
        ShopPerformance testShopPerformance = shopPerformanceList.get(shopPerformanceList.size() - 1);
        assertThat(testShopPerformance.getShopName()).isEqualTo(UPDATED_SHOP_NAME);
        assertThat(testShopPerformance.getPrimeraConsulta()).isEqualTo(UPDATED_PRIMERA_CONSULTA);
        assertThat(testShopPerformance.getPrimeraConsultaLeave()).isEqualTo(UPDATED_PRIMERA_CONSULTA_LEAVE);
        assertThat(testShopPerformance.getNuevaConsulta()).isEqualTo(UPDATED_NUEVA_CONSULTA);
        assertThat(testShopPerformance.getTodayPerformance()).isEqualTo(UPDATED_TODAY_PERFORMANCE);
        assertThat(testShopPerformance.getTotalPerformance()).isEqualTo(UPDATED_TOTAL_PERFORMANCE);
        assertThat(testShopPerformance.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testShopPerformance.getDel()).isEqualTo(UPDATED_DEL);
    }

    @Test
    @Transactional
    public void updateNonExistingShopPerformance() throws Exception {
        int databaseSizeBeforeUpdate = shopPerformanceRepository.findAll().size();

        // Create the ShopPerformance
        ShopPerformanceDTO shopPerformanceDTO = shopPerformanceMapper.toDto(shopPerformance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShopPerformanceMockMvc.perform(put("/api/shop-performances")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shopPerformanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShopPerformance in the database
        List<ShopPerformance> shopPerformanceList = shopPerformanceRepository.findAll();
        assertThat(shopPerformanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShopPerformance() throws Exception {
        // Initialize the database
        shopPerformanceRepository.saveAndFlush(shopPerformance);

        int databaseSizeBeforeDelete = shopPerformanceRepository.findAll().size();

        // Delete the shopPerformance
        restShopPerformanceMockMvc.perform(delete("/api/shop-performances/{id}", shopPerformance.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShopPerformance> shopPerformanceList = shopPerformanceRepository.findAll();
        assertThat(shopPerformanceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
