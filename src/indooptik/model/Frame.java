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
public class Frame {
    private int idFrame;
    private String merk;
    private String frameType;
    private String color;
    private int idDisplayTable;
    private int seqNumber;
    private String soldStatus;
    private BigDecimal price = new BigDecimal(0.0);    

    /**
     * @return the idFrame
     */
    public int getIdFrame() {
        return idFrame;
    }

    /**
     * @param idFrame the idFrame to set
     */
    public void setIdFrame(int idFrame) {
        this.idFrame = idFrame;
    }

    /**
     * @return the merk
     */
    public String getMerk() {
        return merk;
    }

    /**
     * @param merk the merk to set
     */
    public void setMerk(String merk) {
        this.merk = merk;
    }

    /**
     * @return the frameType
     */
    public String getFrameType() {
        return frameType;
    }

    /**
     * @param frameType the frameType to set
     */
    public void setFrameType(String frameType) {
        this.frameType = frameType;
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
     * @return the idDisplayTable
     */
    public int getIdDisplayTable() {
        return idDisplayTable;
    }

    /**
     * @param idDisplayTable the idDisplayTable to set
     */
    public void setIdDisplayTable(int idDisplayTable) {
        this.idDisplayTable = idDisplayTable;
    }

    /**
     * @return the seqNumber
     */
    public int getSeqNumber() {
        return seqNumber;
    }

    /**
     * @param seqNumber the seqNumber to set
     */
    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    /**
     * @return the soldStatus
     */
    public String getSoldStatus() {
        return soldStatus;
    }

    /**
     * @param soldStatus the soldStatus to set
     */
    public void setSoldStatus(String soldStatus) {
        this.soldStatus = soldStatus;
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
    
}
