/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.dao;

import indooptik.model.DisplayTable;
import indooptik.model.UserInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yoeda H
 */
public class UserInfoDAO{

    private Connection connection;
    ResultSet rs;

    public UserInfoDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean updateUserInfo(UserInfo userInfo) {
        PreparedStatement statement = null;
        String sql = "UPDATE user_info SET owner_name = '" + userInfo.getOwnerName()
                + "', store_name = '" + userInfo.getStoreName() + "', telp_no='" + userInfo.getTelpNo()
                + "', address = '" + userInfo.getAddress() + "', image_url = '" + userInfo.getImageUrl() + "' WHERE id = 1";
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

    public UserInfo retreive() {
        String sql = "SELECT id,owner_name, store_name, telp_no, address, image_url FROM user_info ";
        PreparedStatement statement = null;
        UserInfo userInfo = new UserInfo();
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs = statement.executeQuery();
            if (rs.first()) {
                userInfo.setId(rs.getInt(1));
                userInfo.setOwnerName(rs.getString(2));
                userInfo.setStoreName(rs.getString(3));
                userInfo.setTelpNo(rs.getString(4));
                userInfo.setAddress(rs.getString(5));
                userInfo.setImageUrl(rs.getString(6));
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return userInfo;
    }
}
