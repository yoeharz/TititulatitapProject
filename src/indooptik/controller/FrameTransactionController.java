/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.controller;

import indooptik.internalframe.FrameTransactionInternalFrame;
import indooptik.jdialog.LensDialog;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author Yoeda H
 */
public class FrameTransactionController {
    private FrameTransactionInternalFrame frameTransactionInternalFrame;

    public FrameTransactionController(FrameTransactionInternalFrame frameTransactionInternalFrame) {
        this.frameTransactionInternalFrame = frameTransactionInternalFrame;
    }
    
    void getInfo(){
        System.out.print(frameTransactionInternalFrame.getLensTxt());
    }
    
    public void show(JInternalFrame parent, JDialog child){
        child.setVisible(true);
        child.setLocation(parent.getWidth()/2,parent.getHeight()/2);
    }
    
}
