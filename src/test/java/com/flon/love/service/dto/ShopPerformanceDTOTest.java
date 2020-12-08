package com.flon.love.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.flon.love.web.rest.TestUtil;

public class ShopPerformanceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShopPerformanceDTO.class);
        ShopPerformanceDTO shopPerformanceDTO1 = new ShopPerformanceDTO();
        shopPerformanceDTO1.setId(1L);
        ShopPerformanceDTO shopPerformanceDTO2 = new ShopPerformanceDTO();
        assertThat(shopPerformanceDTO1).isNotEqualTo(shopPerformanceDTO2);
        shopPerformanceDTO2.setId(shopPerformanceDTO1.getId());
        assertThat(shopPerformanceDTO1).isEqualTo(shopPerformanceDTO2);
        shopPerformanceDTO2.setId(2L);
        assertThat(shopPerformanceDTO1).isNotEqualTo(shopPerformanceDTO2);
        shopPerformanceDTO1.setId(null);
        assertThat(shopPerformanceDTO1).isNotEqualTo(shopPerformanceDTO2);
    }
}
