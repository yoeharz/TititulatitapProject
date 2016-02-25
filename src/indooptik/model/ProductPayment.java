/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.model;

import java.math.BigDecimal;

/**
 *
 * @author Yoeda H
 */
public class ProductPayment {
    private int id;
    private int idPaymentMethod;
    private int idPaymentProvider;
    private BigDecimal amount = new BigDecimal(0.0);
    private BigDecimal variance = new BigDecimal(0.0);
    private String batchNo;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idPaymentMethod
     */
    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }

    /**
     * @param idPaymentMethod the idPaymentMethod to set
     */
    public void setIdPaymentMethod(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    /**
     * @return the idPaymentProvider
     */
    public int getIdPaymentProvider() {
        return idPaymentProvider;
    }

    /**
     * @param idPaymentProvider the idPaymentProvider to set
     */
    public void setIdPaymentProvider(int idPaymentProvider) {
        this.idPaymentProvider = idPaymentProvider;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the variance
     */
    public BigDecimal getVariance() {
        return variance;
    }

    /**
     * @param variance the variance to set
     */
    public void setVariance(BigDecimal variance) {
        this.variance = variance;
    }

    /**
     * @return the batchNo
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * @param batchNo the batchNo to set
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    
}
