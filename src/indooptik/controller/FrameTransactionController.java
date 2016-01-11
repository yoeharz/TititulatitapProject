/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.controller;

import indooptik.internalframe.FrameTransactionInternalFrame;
import indooptik.jdialog.LensDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

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
    
    public void test(){
        LensDialog dialog = new LensDialog();
        dialog.setVisible(true);
        dialog.setLocation(frameTransactionInternalFrame.getWidth()/2,frameTransactionInternalFrame.getHeight()/2);
        dialog.setFrameTransactionInternalFrame(frameTransactionInternalFrame);
    }
    
}
