/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.model;

/**
 *
 * @author Yoeda H
 */
public class PaymentProvider {
    private int id;
    private String paymentProviderName;

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
     * @return the paymentProviderName
     */
    public String getPaymentProviderName() {
        return paymentProviderName;
    }

    /**
     * @param paymentProviderName the paymentProviderName to set
     */
    public void setPaymentProviderName(String paymentProviderName) {
        this.paymentProviderName = paymentProviderName;
    }
}
