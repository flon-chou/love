package com.flon.love.service.mapper;


import com.flon.love.domain.*;
import com.flon.love.service.dto.ShopPerformanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShopPerformance} and its DTO {@link ShopPerformanceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ShopPerformanceMapper extends EntityMapper<ShopPerformanceDTO, ShopPerformance> {



    default ShopPerformance fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShopPerformance shopPerformance = new ShopPerformance();
        shopPerformance.setId(id);
        return shopPerformance;
    }
}
