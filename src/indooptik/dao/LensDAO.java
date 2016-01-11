/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.dao;

import indooptik.model.Lens;
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
public class LensDAO {
    private Connection connection;
    ResultSet rs;

    public LensDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Lens> retreiveALL() {
        String sql = "SELECT id_lens, type, lens_variance, size_type, color, price  FROM lens ORDER BY id_lens DESC";
        PreparedStatement statement = null;
        List<Lens> listLens = new ArrayList<>();
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Lens lens = new Lens();
                lens.setIdLens(rs.getInt(1));
                lens.setType(rs.getString(2));
                lens.setLensVariance(rs.getString(3));
                lens.setSizeType(rs.getString(4));
                lens.setColor(rs.getString(5));
                lens.setPrice(rs.getBigDecimal(6));
                listLens.add(lens);
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
        return listLens;
    }

    public boolean insert(Lens lens) {
        PreparedStatement statement = null;
        String sql = "INSERT INTO lens VALUES (null"
                 + ", '" + lens.getType() + "', '"
                +lens.getLensVariance()+"', '"+lens.getSizeType()
                +"','"+lens.getColor()+"', "+lens.getPrice()+", now())";
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(LensDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LensDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean update(Lens lens){
        PreparedStatement statement = null;        
        String sql= "UPDATE lens SET type = '"+lens.getType()+"', lens_variance = '"
                +lens.getLensVariance()+"', size_type = '"+lens.getSizeType()
                +"', color = '"+lens.getColor()+"', price = "+lens.getPrice()+", created_date = now()    WHERE id_lens = "+lens.getIdLens();
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           System.out.println(sql);
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(LensDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LensDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean delete(Lens lens){
        PreparedStatement statement = null;        
        String sql= "DELETE FROM lens WHERE id_lens = "+lens.getIdLens();
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(LensDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LensDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
        
}
