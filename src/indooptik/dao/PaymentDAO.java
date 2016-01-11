/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.dao;

import indooptik.model.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yoeda H
 */
public class PaymentDAO {
    private Connection connection;
    ResultSet rs;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Payment> retreiveALL() {
        String sql = "SELECT id_payment, id_transaction, lens_price, frame_price, amount, dp, agency, variance FROM payment ";
        PreparedStatement statement = null;
        List<Payment> listpaPayments = new ArrayList<>();
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setIdPayment(rs.getInt(1));
                payment.setIdTransaction(rs.getInt(2));
                payment.setLensPrice(rs.getBigDecimal(3));
                payment.setFramePrice(rs.getBigDecimal(4));
                payment.setAmount(rs.getBigDecimal(5));
                payment.setDp(rs.getBigDecimal(6));
                payment.setAgency(rs.getBigDecimal(7));
                payment.setVariance(rs.getBigDecimal(8));                
                listpaPayments.add(payment);
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
        return listpaPayments;
    }

    public boolean insert(Payment payment) {
        PreparedStatement statement = null;
        String sql = "INSERT INTO payment VALUES ("
                + payment.getIdPayment()+ ", " + payment.getIdTransaction() + ", "
                + payment.getLensPrice()+", "+payment.getFramePrice()
                +", "+payment.getAmount()+", "+payment.getDp()
                +", "+payment.getAgency()+", "+payment.getVariance()+")";
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
 
    public boolean update(Payment payment){
        PreparedStatement statement = null;        
        String sql= "UPDATE payment SET id_transaction = "+payment.getIdTransaction()+", lens_price = "
                +payment.getLensPrice()+", frame_price = "+payment.getFramePrice()
                +", amount = "+payment.getAmount()
                +", dp = "+payment.getDp()+", agency = "+payment.getAgency()
                +", variance= "+payment.getVariance()+" WHERE id_payment = "+payment.getIdPayment();
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           System.out.println(sql);
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean delete(Payment payment){
        PreparedStatement statement = null;        
        String sql= "DELETE FROM payment WHERE id_payment = "+payment.getIdPayment();
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
        
}
