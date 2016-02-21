/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.dao;


import indooptik.model.PaymentMethod;
import indooptik.model.PaymentProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yoeda H
 */
public class PaymentProviderDAO {

    private Connection connection;
    ResultSet rs;

    public PaymentProviderDAO(Connection connection) {
        this.connection = connection;
    }

    public List<PaymentProvider> retreiveALL() {
        String sql = "SELECT id,provider_name FROM payment_provider ";
        PreparedStatement statement = null;
        List<PaymentProvider> listPaymentProviders = new ArrayList<>();
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                PaymentProvider paymentProvider = new PaymentProvider();
                paymentProvider.setId(rs.getInt(1));
                paymentProvider.setPaymentProviderName(rs.getString(2));
                listPaymentProviders.add(paymentProvider);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("SQL Execption :" + ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return listPaymentProviders;
    }
}
