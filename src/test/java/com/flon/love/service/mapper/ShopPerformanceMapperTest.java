package com.flon.love.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ShopPerformanceMapperTest {

    private ShopPerformanceMapper shopPerformanceMapper;

    @BeforeEach
    public void setUp() {
        shopPerformanceMapper = new ShopPerformanceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(shopPerformanceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(shopPerformanceMapper.fromId(null)).isNull();
    }
}
