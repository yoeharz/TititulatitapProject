/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.model;

import java.util.Date;

/**
 *
 * @author Yoeda H
 */
public class Transaction {
    private int idTransaction;
    private int idCostumer;
    private int idLens;
    private int idFrame;
    private String name;
    private String Telp;
    private String HP;
    private Date birthDate;
    private Date transactionDate;
    private String lens;
    private String lensColor;
    private String frame;
    private String desc;

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
     * @return the idCostumer
     */
    public int getIdCostumer() {
        return idCostumer;
    }

    /**
     * @param idCostumer the idCostumer to set
     */
    public void setIdCostumer(int idCostumer) {
        this.idCostumer = idCostumer;
    }

    /**
     * @return the idLens
     */
    public int getIdLens() {
        return idLens;
    }

    /**
     * @param idLens the idLens to set
     */
    public void setIdLens(int idLens) {
        this.idLens = idLens;
    }

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the Telp
     */
    public String getTelp() {
        return Telp;
    }

    /**
     * @param Telp the Telp to set
     */
    public void setTelp(String Telp) {
        this.Telp = Telp;
    }

    /**
     * @return the HP
     */
    public String getHP() {
        return HP;
    }

    /**
     * @param HP the HP to set
     */
    public void setHP(String HP) {
        this.HP = HP;
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the transactionDate
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * @return the lens
     */
    public String getLens() {
        return lens;
    }

    /**
     * @param lens the lens to set
     */
    public void setLens(String lens) {
        this.lens = lens;
    }

    /**
     * @return the lensColor
     */
    public String getLensColor() {
        return lensColor;
    }

    /**
     * @param lensColor the lensColor to set
     */
    public void setLensColor(String lensColor) {
        this.lensColor = lensColor;
    }

    /**
     * @return the frame
     */
    public String getFrame() {
        return frame;
    }

    /**
     * @param frame the frame to set
     */
    public void setFrame(String frame) {
        this.frame = frame;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
}
