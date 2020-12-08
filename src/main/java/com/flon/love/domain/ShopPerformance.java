package com.flon.love.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * 门店业绩
 */
@Entity
@Table(name = "shop_performance")
public class ShopPerformance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 门店名
     */
    @Size(max = 50)
    @Column(name = "shop_name", length = 50)
    private String shopName;

    /**
     * 今日初诊
     */
    @Column(name = "primera_consulta")
    private Integer primeraConsulta;

    /**
     * 留下初诊
     */
    @Column(name = "primera_consulta_leave")
    private Integer primeraConsultaLeave;

    /**
     * 今日复诊
     */
    @Column(name = "nueva_consulta")
    private Integer nuevaConsulta;

    /**
     * 今日业绩
     */
    @Column(name = "today_performance")
    private Integer todayPerformance;

    /**
     * 累计业绩
     */
    @Column(name = "total_performance")
    private Integer totalPerformance;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 数据有效（0：无效 ， 1：有效）
     */
    @Max(value = 1)
    @Column(name = "del")
    private Integer del;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public ShopPerformance shopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getPrimeraConsulta() {
        return primeraConsulta;
    }

    public ShopPerformance primeraConsulta(Integer primeraConsulta) {
        this.primeraConsulta = primeraConsulta;
        return this;
    }

    public void setPrimeraConsulta(Integer primeraConsulta) {
        this.primeraConsulta = primeraConsulta;
    }

    public Integer getPrimeraConsultaLeave() {
        return primeraConsultaLeave;
    }

    public ShopPerformance primeraConsultaLeave(Integer primeraConsultaLeave) {
        this.primeraConsultaLeave = primeraConsultaLeave;
        return this;
    }

    public void setPrimeraConsultaLeave(Integer primeraConsultaLeave) {
        this.primeraConsultaLeave = primeraConsultaLeave;
    }

    public Integer getNuevaConsulta() {
        return nuevaConsulta;
    }

    public ShopPerformance nuevaConsulta(Integer nuevaConsulta) {
        this.nuevaConsulta = nuevaConsulta;
        return this;
    }

    public void setNuevaConsulta(Integer nuevaConsulta) {
        this.nuevaConsulta = nuevaConsulta;
    }

    public Integer getTodayPerformance() {
        return todayPerformance;
    }

    public ShopPerformance todayPerformance(Integer todayPerformance) {
        this.todayPerformance = todayPerformance;
        return this;
    }

    public void setTodayPerformance(Integer todayPerformance) {
        this.todayPerformance = todayPerformance;
    }

    public Integer getTotalPerformance() {
        return totalPerformance;
    }

    public ShopPerformance totalPerformance(Integer totalPerformance) {
        this.totalPerformance = totalPerformance;
        return this;
    }

    public void setTotalPerformance(Integer totalPerformance) {
        this.totalPerformance = totalPerformance;
    }

    public String getCreateTime() {
        return createTime;
    }

    public ShopPerformance createTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getDel() {
        return del;
    }

    public ShopPerformance del(Integer del) {
        this.del = del;
        return this;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShopPerformance)) {
            return false;
        }
        return id != null && id.equals(((ShopPerformance) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShopPerformance{" +
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
