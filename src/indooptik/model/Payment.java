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
public class Payment {
    private int idPayment;
    private int idTransaction;
    private BigDecimal lensPrice = new BigDecimal(0.0);
    private BigDecimal framePrice = new BigDecimal(0.0);
    private BigDecimal amount = new BigDecimal(0.0);
    private BigDecimal dp = new BigDecimal(0.0);
    private BigDecimal agency = new BigDecimal(0.0);
    private BigDecimal variance = new BigDecimal(0.0);
    private BigDecimal discount = new BigDecimal(0.0);
    private String batchNo;
    private int idPaymentMethod;
    private int idPaymentProvider;

    /**
     * @return the idPayment
     */
    public int getIdPayment() {
        return idPayment;
    }

    /**
     * @param idPayment the idPayment to set
     */
    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    /**
     * @return the idTransaction
     */
    public int getIdTransaction() {
        return idTransaction;
    }

    /**
     * @param idTransaction the idTransaction to set
     */
    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    /**
     * @return the lensPrice
     */
    public BigDecimal getLensPrice() {
        return lensPrice;
    }

    /**
     * @param lensPrice the lensPrice to set
     */
    public void setLensPrice(BigDecimal lensPrice) {
        this.lensPrice = lensPrice;
    }

    /**
     * @return the framePrice
     */
    public BigDecimal getFramePrice() {
        return framePrice;
    }

    /**
     * @param framePrice the framePrice to set
     */
    public void setFramePrice(BigDecimal framePrice) {
        this.framePrice = framePrice;
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
     * @return the dp
     */
    public BigDecimal getDp() {
        return dp;
    }

    /**
     * @param dp the dp to set
     */
    public void setDp(BigDecimal dp) {
        this.dp = dp;
    }

    /**
     * @return the agency
     */
    public BigDecimal getAgency() {
        return agency;
    }

    /**
     * @param agency the agency to set
     */
    public void setAgency(BigDecimal agency) {
        this.agency = agency;
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
     * @return the discount
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
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

    
}
