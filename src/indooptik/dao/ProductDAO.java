/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.dao;

import indooptik.model.Customer;
import indooptik.model.Product;
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
public class ProductDAO {
    private Connection connection;
    ResultSet rs;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }
    
    public List<Product> retreiveALL() {
        String sql = "SELECT id, barcode, name, type, color,minus, price, stock, created_date FROM product ORDER BY name";
        PreparedStatement statement = null;
        List<Product> listprProducts = new ArrayList<>();
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setBarcode(rs.getString(2));
                product.setName(rs.getString(3));
                product.setType(rs.getString(4));
                product.setColor(rs.getString(5));
                product.setMinus(rs.getInt(6));
                product.setPrice(rs.getBigDecimal(7));
                product.setStock(rs.getInt(8));
                product.setCreatedDate(rs.getDate(9));
                listprProducts.add(product);
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
        return listprProducts;
    }
    
    public boolean insert(Product product) {
        PreparedStatement statement = null;
        String sql = "INSERT INTO product (barcode, name, type, color, minus, price, stock ,created_date )VALUES ('"
                 +product.getBarcode()+"', '" + product.getName() + "', '"
                +product.getType()+"', '"+product.getColor()
                +"',"+product.getMinus()+", "+product.getPrice()+", "+product.getStock()+",now());";
        try {            
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean update(Product product){
        PreparedStatement statement = null;        
        String sql= "UPDATE product SET barcode = '"+product.getBarcode()+"', name = '"
                +product.getName()+"', type = '"+product.getType()
                +"', color = '"+product.getColor()+"', minus = "+product.getMinus()
                +", price = "+product.getPrice()+", stock="+product.getStock()
                +" WHERE id = "+product.getId()+";";
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();           
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean delete(Product product){
        PreparedStatement statement = null;        
        String sql= "DELETE FROM product WHERE id = "+product.getId()+";";
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }        
}
