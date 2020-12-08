package com.flon.love.repository;

import com.flon.love.domain.ShopPerformance;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ShopPerformance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShopPerformanceRepository extends JpaRepository<ShopPerformance, Long> {
}
