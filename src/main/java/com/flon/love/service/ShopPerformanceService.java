package com.flon.love.service;

import com.flon.love.domain.ShopPerformance;
import com.flon.love.repository.ShopPerformanceRepository;
import com.flon.love.service.dto.ShopPerformanceDTO;
import com.flon.love.service.mapper.ShopPerformanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ShopPerformance}.
 */
@Service
@Transactional
public class ShopPerformanceService {

    private final Logger log = LoggerFactory.getLogger(ShopPerformanceService.class);

    private final ShopPerformanceRepository shopPerformanceRepository;

    private final ShopPerformanceMapper shopPerformanceMapper;

    public ShopPerformanceService(ShopPerformanceRepository shopPerformanceRepository, ShopPerformanceMapper shopPerformanceMapper) {
        this.shopPerformanceRepository = shopPerformanceRepository;
        this.shopPerformanceMapper = shopPerformanceMapper;
    }

    /**
     * Save a shopPerformance.
     *
     * @param shopPerformanceDTO the entity to save.
     * @return the persisted entity.
     */
    public ShopPerformanceDTO save(ShopPerformanceDTO shopPerformanceDTO) {
        log.debug("Request to save ShopPerformance : {}", shopPerformanceDTO);
        ShopPerformance shopPerformance = shopPerformanceMapper.toEntity(shopPerformanceDTO);
        shopPerformance = shopPerformanceRepository.save(shopPerformance);
        return shopPerformanceMapper.toDto(shopPerformance);
    }

    /**
     * Get all the shopPerformances.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ShopPerformanceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ShopPerformances");
        return shopPerformanceRepository.findAll(pageable)
            .map(shopPerformanceMapper::toDto);
    }


    /**
     * Get one shopPerformance by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ShopPerformanceDTO> findOne(Long id) {
        log.debug("Request to get ShopPerformance : {}", id);
        return shopPerformanceRepository.findById(id)
            .map(shopPerformanceMapper::toDto);
    }

    /**
     * Delete the shopPerformance by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ShopPerformance : {}", id);
        shopPerformanceRepository.deleteById(id);
    }
}
