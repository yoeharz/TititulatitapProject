/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.dao;

import indooptik.model.Frame;
import indooptik.model.Transaction;
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
public class TransactionDAO {
    private Connection connection;
    ResultSet rs;

    public TransactionDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Transaction> retreiveALL() {
        String sql = "SELECT id_transaction, id_customer, id_lens, id_frame, name, telp, hp, birth_date, trx_date, lens, lens_color, frame, desc FROM transaction ";
        PreparedStatement statement = null;
        List<Transaction> listTransaction = new ArrayList<>();
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setIdTransaction(rs.getString(1));
                transaction.setIdCostumer(rs.getInt(2));
                transaction.setIdLens(rs.getInt(3));
                transaction.setIdFrame(rs.getInt(4));
                transaction.setName(rs.getString(5));
                transaction.setTelp(rs.getString(6));
                transaction.setHP(rs.getString(7));
                transaction.setBirthDate(rs.getDate(8));
                transaction.setTransactionDate(rs.getDate(9));
                transaction.setBirthDate(rs.getDate(8));
                transaction.setLens(rs.getString(10));
                transaction.setLensColor(rs.getString(11));
                transaction.setFrame(rs.getString(12));
                transaction.setDesc(rs.getString(13));
                listTransaction.add(transaction);
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
        return listTransaction;
    }

    public boolean insert(Transaction transaction) {
        PreparedStatement statement = null;
        String sql = "INSERT INTO transaction VALUES ("
                + transaction.getIdTransaction()+ ", " + transaction.getIdCostumer() + ", "
                +transaction.getIdLens()+", "+transaction.getIdFrame()
                +", '"+transaction.getName()+"', '"+transaction.getTelp()
                +"', '"+transaction.getHP()+"', '"+transaction.getBirthDate()+"', '"+
                transaction.getTransactionDate()+"', '"+transaction.getLens()+"', '"+
                transaction.getLensColor()+"','"+transaction.getFrame()+"','"+transaction.getDesc()+"')";
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
 
    public boolean update(Transaction transaction){
        PreparedStatement statement = null;        
        String sql= "UPDATE transaction SET id_costumer = "+transaction.getIdCostumer()+", id_lens = '"
                +transaction.getIdLens()+", id_frame = "+transaction.getIdFrame()
                +", name = '"+transaction.getName()
                +"', telp = '"+transaction.getTelp()+"', hp = '"+transaction.getHP()
                +"', birth_date= '"+transaction.getBirthDate()+"', trx_date='"+transaction.getTransactionDate()
                +"', lens= '"+transaction.getLens()+"', lens_color='"+transaction.getLensColor()
                +"', frame='"+transaction.getFrame()+"', desc='"+transaction.getDesc()
                +"' WHERE id_transaction = "+transaction.getIdTransaction();
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           System.out.println(sql);
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean delete(Transaction transaction){
        PreparedStatement statement = null;        
        String sql= "DELETE FROM transaction WHERE id_transaction = "+transaction.getIdTransaction();
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
        
}
