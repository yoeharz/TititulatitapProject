/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Yoeda H
 */
public class Lens {
    private int IdLens;
    private String type;
    private String lensVariance;
    private String sizeType;
    private String color;
    private BigDecimal price = new BigDecimal(0.0);
    private Date createdDate;

    /**
     * @return the IdLens
     */
    public int getIdLens() {
        return IdLens;
    }

    /**
     * @param IdLens the IdLens to set
     */
    public void setIdLens(int IdLens) {
        this.IdLens = IdLens;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the lensVariance
     */
    public String getLensVariance() {
        return lensVariance;
    }

    /**
     * @param lensVariance the lensVariance to set
     */
    public void setLensVariance(String lensVariance) {
        this.lensVariance = lensVariance;
    }

    /**
     * @return the sizeType
     */
    public String getSizeType() {
        return sizeType;
    }

    /**
     * @param sizeType the sizeType to set
     */
    public void setSizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
}
