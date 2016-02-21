/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.dao;

import indooptik.model.PaymentMethod;
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
public class PaymentMethodDAO {

    private Connection connection;
    ResultSet rs;

    public PaymentMethodDAO(Connection connection) {
        this.connection = connection;
    }

    public List<PaymentMethod> retreiveALL() {
        String sql = "SELECT id,type FROM payment_method ";
        PreparedStatement statement = null;
        List<PaymentMethod> listPaymentMethod = new ArrayList<>();
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                PaymentMethod paymentMethod = new PaymentMethod();
                paymentMethod.setId(rs.getInt(1));
                paymentMethod.setType(rs.getString(2));
                listPaymentMethod.add(paymentMethod);
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
        return listPaymentMethod;
    }
}
