package com.flon.love.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.flon.love.web.rest.TestUtil;

public class ShopPerformanceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShopPerformance.class);
        ShopPerformance shopPerformance1 = new ShopPerformance();
        shopPerformance1.setId(1L);
        ShopPerformance shopPerformance2 = new ShopPerformance();
        shopPerformance2.setId(shopPerformance1.getId());
        assertThat(shopPerformance1).isEqualTo(shopPerformance2);
        shopPerformance2.setId(2L);
        assertThat(shopPerformance1).isNotEqualTo(shopPerformance2);
        shopPerformance1.setId(null);
        assertThat(shopPerformance1).isNotEqualTo(shopPerformance2);
    }
}
