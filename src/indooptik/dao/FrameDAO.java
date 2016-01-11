/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.dao;

import indooptik.model.Frame;
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
public class FrameDAO {
    private Connection connection;
    ResultSet rs;

    public FrameDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Frame> retreiveALL() {
        String sql = "SELECT id_frame, merk, type, color, id_display_table, seq_number, sold_status, price  FROM frame ORDER BY id_display_table, seq_number";
        PreparedStatement statement = null;
        List<Frame> listFrame = new ArrayList<>();
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Frame frame = new Frame();
                frame.setIdFrame(rs.getInt(1));
                frame.setMerk(rs.getString(2));
                frame.setFrameType(rs.getString(3));
                frame.setColor(rs.getString(4));
                frame.setIdDisplayTable(rs.getInt(5));
                frame.setSeqNumber(rs.getInt(6));
                frame.setSoldStatus(rs.getString(7));
                frame.setPrice(rs.getBigDecimal(8));
                listFrame.add(frame);
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
        return listFrame;
    }
    
    public Boolean checkAvailableFrame(Frame frame){
        String sql = "SELECT id_frame, merk, type, color, id_display_table, seq_number, sold_status, price  FROM frame WHERE id_display_table = "+frame.getIdDisplayTable()+ " AND seq_number = "+frame.getSeqNumber()+" ORDER BY id_display_table, seq_number";
        PreparedStatement statement = null;      
        
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs = statement.executeQuery();
            if (rs.first()) {                
                return  true;
            }else{
                return false;
            }            
        } catch (SQLException ex) {
            System.out.println("SQL Execption :" + ex.getMessage());
            return false;
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

    
    public List<Frame> retreiveALLByIdDisplayTable(int idDisplayTable) {
        String sql = "SELECT id_frame, merk, type, color, id_display_table, seq_number, sold_status, price  FROM frame WHERE id_display_table = "+idDisplayTable+ " ORDER BY id_display_table, seq_number";
        PreparedStatement statement = null;
        List<Frame> listFrame = new ArrayList<>();
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Frame frame = new Frame();
                frame.setIdFrame(rs.getInt(1));
                frame.setMerk(rs.getString(2));
                frame.setFrameType(rs.getString(3));
                frame.setColor(rs.getString(4));
                frame.setIdDisplayTable(rs.getInt(5));
                frame.setSeqNumber(rs.getInt(6));
                frame.setSoldStatus(rs.getString(7));
                frame.setPrice(rs.getBigDecimal(8));
                listFrame.add(frame);
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
        return listFrame;
    }

    public boolean insert(Frame frame) {
        PreparedStatement statement = null;
        String sql = "INSERT INTO frame VALUES ("
                + frame.getIdFrame()+ ", '" + frame.getMerk() + "', '"
                +frame.getFrameType()+"', '"+frame.getColor()
                +"', "+frame.getIdDisplayTable()+", "+frame.getSeqNumber()
                +", '"+frame.getSoldStatus()+"', "+frame.getPrice()+", created_date = now())";
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(FrameDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FrameDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
 
    public boolean update(Frame frame){
        PreparedStatement statement = null;        
        String sql= "UPDATE frame SET merk = '"+frame.getMerk()+"', type = '"
                +frame.getFrameType()+"', color = '"+frame.getColor()
                +"', id_display_table = "+frame.getIdDisplayTable()
                +", seq_number = "+frame.getSeqNumber()
                +", price= "+frame.getPrice()+" WHERE id_frame = "+frame.getIdFrame();
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           System.out.println(sql);
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(FrameDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FrameDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean delete(Frame frame){
        PreparedStatement statement = null;        
        String sql= "DELETE FROM frame WHERE id_frame = "+frame.getIdFrame();
        try {
           statement = (PreparedStatement) connection.prepareStatement(sql);
           statement.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(FrameDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FrameDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
        
}
