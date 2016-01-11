/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.controller;

import indooptik.dao.DAOFactory;
import indooptik.dao.UserInfoDAO;
import indooptik.internalframe.UserInfoInternalFrame;
import indooptik.model.UserInfo;
import javax.swing.JOptionPane;

/**
 *
 * @author Yoeda H
 */
public class UserInfoController {
    UserInfoInternalFrame userInfoInternalFrame;
    UserInfoDAO userInfoDAO;

    public UserInfoController(UserInfoInternalFrame userInfoInternalFrame) {
        this.userInfoInternalFrame = userInfoInternalFrame;
        userInfoDAO = DAOFactory.create().getUserInfoDAO();
        showData();
    }
    
    public void showData(){
        UserInfo userInfo = userInfoDAO.retreive();
        userInfoInternalFrame.getAddressArea().setText(userInfo.getAddress());
        userInfoInternalFrame.getOwnerTxt().setText(userInfo.getOwnerName());
        userInfoInternalFrame.getStoreNameTxt().setText(userInfo.getStoreName());
        userInfoInternalFrame.getTelpTxt().setText(userInfo.getTelpNo());
    }
    
    public void editData(){
        UserInfo userInfo = new UserInfo();
        userInfo.setOwnerName(userInfoInternalFrame.getOwnerTxt().getText());
        userInfo.setTelpNo(userInfoInternalFrame.getTelpTxt().getText());
        userInfo.setAddress(userInfoInternalFrame.getAddressArea().getText());
        userInfo.setStoreName(userInfoInternalFrame.getStoreNameTxt().getText());
        
        if(userInfoDAO.updateUserInfo(userInfo) == true){
            JOptionPane.showMessageDialog(userInfoInternalFrame, "Update success");
            showData();
        }else{
            JOptionPane.showMessageDialog(userInfoInternalFrame, "Update failed");
            showData();
        }
        
    }
    
    
}
