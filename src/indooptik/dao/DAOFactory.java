/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yoeda H
 */
public class DAOFactory {

    private static DAOFactory daoFactory;

    public static DAOFactory create() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    private Connection connection;

    private DAOFactory() {
        try {

            Properties properties = new Properties();
            try {

                properties.load(new FileInputStream("config/connection.properties"));
                //System.out.println(properties.getProperty("url"));
                //JOptionPane.showMessageDialog(null, properties.getProperty("url"));
            } catch (IOException ex) {
                Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            }

            //tentukan driver jdbc yang digunakan
            Class.forName("com.mysql.jdbc.Driver");
            //tentukan koneksi url yang digunakan
            //String url = "jdbc:mysql://localhost:3306/sis";
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            //buat objek koneksi
            connection = DriverManager.getConnection(url, "root", "");
            System.out.println(username + connection);

        } catch (SQLException ex) {
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private UserInfoDAO userInfoDAO;
    private DisplayTableDAO displayTableDAO;
    private LensDAO lensDAO;
    private FrameDAO frameDAO;
    private CustomerDAO customerDAO;
    private PaymentProviderDAO paymentProviderDAO;
    private PaymentMethodDAO paymentMethodDAO;
    private ProductDAO productDAO;

    /**
     * @return the userInfoDAO
     */
    public UserInfoDAO getUserInfoDAO() {
        if (userInfoDAO == null) {
            userInfoDAO = new UserInfoDAO(connection);
        }
        return userInfoDAO;
    }

    /**
     * @return the displayTableDAO
     */
    public DisplayTableDAO getDisplayTableDAO() {
        if (displayTableDAO == null) {
            displayTableDAO = new DisplayTableDAO(connection);
        }
        return displayTableDAO;
    }

    /**
     * @return the lensDAO
     */
    public LensDAO getLensDAO() {
        if (lensDAO == null) {
            lensDAO = new LensDAO(connection);
        }
        return lensDAO;
    }

    /**
     * @return the frameDAO
     */
    public FrameDAO getFrameDAO() {
        if (frameDAO == null) {
            frameDAO = new FrameDAO(connection);
        }
        return frameDAO;
    }

    /**
     * @return the customerDAO
     */
    public CustomerDAO getCustomerDAO() {
        if (customerDAO == null) {
            customerDAO = new CustomerDAO(connection);
        }
        return customerDAO;
    }

    /**
     * @return the paymentProviderDAO
     */
    public PaymentProviderDAO getPaymentProviderDAO() {
        if (paymentProviderDAO == null) {
            paymentProviderDAO = new PaymentProviderDAO(connection);
        }
        return paymentProviderDAO;
    }

    /**
     * @return the paymentMethodDAO
     */
    public PaymentMethodDAO getPaymentMethodDAO() {
        if (paymentMethodDAO == null) {
            paymentMethodDAO = new PaymentMethodDAO(connection);
        }
        return paymentMethodDAO;
    }

    /**
     * @return the productDAO
     */
    public ProductDAO getProductDAO() {
        if (productDAO == null) {
            productDAO = new ProductDAO(connection);
        }
        return productDAO;
    }

}
