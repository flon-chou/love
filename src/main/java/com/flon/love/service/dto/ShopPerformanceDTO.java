package com.flon.love.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.flon.love.domain.ShopPerformance} entity.
 */
@ApiModel(description = "门店业绩")
public class ShopPerformanceDTO implements Serializable {
    
    private Long id;

    /**
     * 门店名
     */
    @Size(max = 50)
    @ApiModelProperty(value = "门店名")
    private String shopName;

    /**
     * 今日初诊
     */
    @ApiModelProperty(value = "今日初诊")
    private Integer primeraConsulta;

    /**
     * 留下初诊
     */
    @ApiModelProperty(value = "留下初诊")
    private Integer primeraConsultaLeave;

    /**
     * 今日复诊
     */
    @ApiModelProperty(value = "今日复诊")
    private Integer nuevaConsulta;

    /**
     * 今日业绩
     */
    @ApiModelProperty(value = "今日业绩")
    private Integer todayPerformance;

    /**
     * 累计业绩
     */
    @ApiModelProperty(value = "累计业绩")
    private Integer totalPerformance;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    /**
     * 数据有效（0：无效 ， 1：有效）
     */
    @Max(value = 1)
    @ApiModelProperty(value = "数据有效（0：无效 ， 1：有效）")
    private Integer del;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getPrimeraConsulta() {
        return primeraConsulta;
    }

    public void setPrimeraConsulta(Integer primeraConsulta) {
        this.primeraConsulta = primeraConsulta;
    }

    public Integer getPrimeraConsultaLeave() {
        return primeraConsultaLeave;
    }

    public void setPrimeraConsultaLeave(Integer primeraConsultaLeave) {
        this.primeraConsultaLeave = primeraConsultaLeave;
    }

    public Integer getNuevaConsulta() {
        return nuevaConsulta;
    }

    public void setNuevaConsulta(Integer nuevaConsulta) {
        this.nuevaConsulta = nuevaConsulta;
    }

    public Integer getTodayPerformance() {
        return todayPerformance;
    }

    public void setTodayPerformance(Integer todayPerformance) {
        this.todayPerformance = todayPerformance;
    }

    public Integer getTotalPerformance() {
        return totalPerformance;
    }

    public void setTotalPerformance(Integer totalPerformance) {
        this.totalPerformance = totalPerformance;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShopPerformanceDTO)) {
            return false;
        }

        return id != null && id.equals(((ShopPerformanceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShopPerformanceDTO{" +
            "id=" + getId() +
            ", shopName='" + getShopName() + "'" +
            ", primeraConsulta=" + getPrimeraConsulta() +
            ", primeraConsultaLeave=" + getPrimeraConsultaLeave() +
            ", nuevaConsulta=" + getNuevaConsulta() +
            ", todayPerformance=" + getTodayPerformance() +
            ", totalPerformance=" + getTotalPerformance() +
            ", createTime='" + getCreateTime() + "'" +
            ", del=" + getDel() +
            "}";
    }
}
