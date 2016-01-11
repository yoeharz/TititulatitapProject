/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.main;

import indooptik.dao.DAOFactory;
import indooptik.dao.UserInfoDAO;
import indooptik.model.UserInfo;
import javax.swing.JFrame;

/**
 *
 * @author Yoeda H
 */
public class Main {
    public static void main(String[] args){
        
        UserInfoDAO userInfoDAO = DAOFactory.create().getUserInfoDAO();
        UserInfo userInfo = userInfoDAO.retreive();
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setExtendedState(mainFrame.getExtendedState()|JFrame.MAXIMIZED_BOTH);        
        mainFrame.setTitle(userInfo.getStoreName());
    }
}
