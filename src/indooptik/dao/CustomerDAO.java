/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.dao;

import indooptik.model.Customer;
import indooptik.model.Lens;
import java.sql.Connection;
import java.sql.Date;
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
public class CustomerDAO {
    private Connection connection;
    ResultSet rs;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }
    
    public List<Customer> retreiveALL() {
        String sql = "SELECT id, name, telp, hp, birth_date,created_date FROM customer ORDER BY LENGTH(id),id ";
        PreparedStatement statement = null;
        List<Customer> listCustomer = new ArrayList<>();
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setIdCustomer(rs.getString(1));
                customer.setName(rs.getString(2));
                customer.setTelp(rs.getString(3));
                customer.setHp(rs.getString(4));
                customer.setHut(rs.getDate(5));
                listCustomer.add(customer);
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
        return listCustomer;
    }
    
    public boolean insert(Customer customer) {
        PreparedStatement statement = null;
        String sql = "INSERT INTO customer (id, name, telp, hp, birth_date,created_date )VALUES ('"
                 +customer.getIdCustomer()+"', '" + customer.getName() + "', '"
                +customer.getTelp()+"', '"+customer.getHp()
                +"','"+new Date(customer.getHut().getTime())+"',now());";
        try {            
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean update(Customer customer){
        PreparedStatement statement = null;        
        String sql= "UPDATE customer SET name = '"+customer.getName()+"', telp = '"
                +customer.getTelp()+"', hp = '"+customer.getHp()
                +"', birth_date = '"+new Date(customer.getHut().getTime())+"' WHERE id = '"+customer.getIdCustomer()+"';";
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();           
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean delete(Customer customer){
        PreparedStatement statement = null;        
        String sql= "DELETE FROM customer WHERE id = '"+customer.getIdCustomer()+"';";
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public int cekID(String initial){
        String sql ="SELECT id,created_date FROM Customer WHERE id LIKE '"+initial+"%' ORDER BY created_date DESC LIMIT 0,1";
        System.out.print(sql);
        PreparedStatement statement = null;
        int id = 0;
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setIdCustomer(rs.getString(1));    
                System.out.print("ieww"+customer.getIdCustomer());
                id = new Integer(customer.getIdCustomer().substring(1, customer.getIdCustomer().length()));                
            }            
            rs.close();
            System.out.print(id);
            return id;
        } catch (SQLException ex) {
            System.out.println("SQL Execption :" + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }        
    }
}
