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
    
}
