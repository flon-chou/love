package com.flon.love.web.rest;

import com.flon.love.service.ShopPerformanceService;
import com.flon.love.web.rest.errors.BadRequestAlertException;
import com.flon.love.service.dto.ShopPerformanceDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.flon.love.domain.ShopPerformance}.
 */
@RestController
@RequestMapping("/api")
public class ShopPerformanceResource {

    private final Logger log = LoggerFactory.getLogger(ShopPerformanceResource.class);

    private static final String ENTITY_NAME = "shopPerformance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShopPerformanceService shopPerformanceService;

    public ShopPerformanceResource(ShopPerformanceService shopPerformanceService) {
        this.shopPerformanceService = shopPerformanceService;
    }

    /**
     * {@code POST  /shop-performances} : Create a new shopPerformance.
     *
     * @param shopPerformanceDTO the shopPerformanceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shopPerformanceDTO, or with status {@code 400 (Bad Request)} if the shopPerformance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shop-performances")
    public ResponseEntity<ShopPerformanceDTO> createShopPerformance(@Valid @RequestBody ShopPerformanceDTO shopPerformanceDTO) throws URISyntaxException {
        log.debug("REST request to save ShopPerformance : {}", shopPerformanceDTO);
        if (shopPerformanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new shopPerformance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShopPerformanceDTO result = shopPerformanceService.save(shopPerformanceDTO);
        return ResponseEntity.created(new URI("/api/shop-performances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shop-performances} : Updates an existing shopPerformance.
     *
     * @param shopPerformanceDTO the shopPerformanceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shopPerformanceDTO,
     * or with status {@code 400 (Bad Request)} if the shopPerformanceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shopPerformanceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shop-performances")
    public ResponseEntity<ShopPerformanceDTO> updateShopPerformance(@Valid @RequestBody ShopPerformanceDTO shopPerformanceDTO) throws URISyntaxException {
        log.debug("REST request to update ShopPerformance : {}", shopPerformanceDTO);
        if (shopPerformanceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShopPerformanceDTO result = shopPerformanceService.save(shopPerformanceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shopPerformanceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shop-performances} : get all the shopPerformances.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shopPerformances in body.
     */
    @GetMapping("/shop-performances")
    public ResponseEntity<List<ShopPerformanceDTO>> getAllShopPerformances(Pageable pageable) {
        log.debug("REST request to get a page of ShopPerformances");
        Page<ShopPerformanceDTO> page = shopPerformanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /shop-performances/:id} : get the "id" shopPerformance.
     *
     * @param id the id of the shopPerformanceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shopPerformanceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shop-performances/{id}")
    public ResponseEntity<ShopPerformanceDTO> getShopPerformance(@PathVariable Long id) {
        log.debug("REST request to get ShopPerformance : {}", id);
        Optional<ShopPerformanceDTO> shopPerformanceDTO = shopPerformanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shopPerformanceDTO);
    }

    /**
     * {@code DELETE  /shop-performances/:id} : delete the "id" shopPerformance.
     *
     * @param id the id of the shopPerformanceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shop-performances/{id}")
    public ResponseEntity<Void> deleteShopPerformance(@PathVariable Long id) {
        log.debug("REST request to delete ShopPerformance : {}", id);
        shopPerformanceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
