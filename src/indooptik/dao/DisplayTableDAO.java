/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.dao;

import indooptik.model.DisplayTable;
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
public class DisplayTableDAO {

    private Connection connection;
    ResultSet rs;

    public DisplayTableDAO(Connection connection) {
        this.connection = connection;
    }

    public List<DisplayTable> retreiveALL() {
        String sql = "SELECT id,display_name FROM display_table ";
        PreparedStatement statement = null;
        List<DisplayTable> listDisplayTables = new ArrayList<>();
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                DisplayTable displayTable = new DisplayTable();
                displayTable.setId(rs.getInt(1));
                displayTable.setDisplayName(rs.getString(2));
                listDisplayTables.add(displayTable);
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
        return listDisplayTables;
    }

    public boolean insertDisplayTable(DisplayTable displayTable) {
        PreparedStatement statement = null;
        String sql = "INSERT INTO display_table VALUES ("
                + displayTable.getId() + ", '" + displayTable.getDisplayName() + "')";
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(DisplayTableDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DisplayTableDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean updateDisplayTable(DisplayTable displayTable, int id){
        PreparedStatement statement = null;        
        String sql= "UPDATE display_table SET id = "+displayTable.getId()+", display_name = '"+displayTable.getDisplayName()+"' WHERE id = "+id;
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           System.out.println(sql);
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(DisplayTableDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DisplayTableDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean delete(DisplayTable displayTable){
        PreparedStatement statement = null;        
        String sql= "DELETE FROM display_table WHERE id = "+displayTable.getId();
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(DisplayTableDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DisplayTableDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public int cekID(int id) {
       String sql ="SELECT id FROM display_table WHERE " +
               "id = "+id;                
       PreparedStatement statement = null;
       int idDisplay = 0;
        try{
          statement = (PreparedStatement) connection.prepareStatement(sql);
          rs = statement.executeQuery();
          if(rs.first()){
              idDisplay = rs.getInt(1);
          }
        }catch (SQLException ex){
             System.out.println("SQL Execption :" + ex.getMessage());
        }
        return idDisplay;
    }
}
