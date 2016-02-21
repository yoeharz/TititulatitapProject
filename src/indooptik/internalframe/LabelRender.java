/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.internalframe;

import indooptik.controller.FrameTransactionController;
import indooptik.main.MainFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Yoeda H
 */
public class LabelRender extends DefaultTableCellRenderer {

    MainFrame frame;

    public LabelRender(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setOpaque(true);
        label.setForeground(Color.BLUE);
        String text = new String((String) value);
        label.setText("<HTML><a href=''>" + text + "</a></html>");
        //label.setBackground(Color.RED);
        int rowNo = 0;
        if (isSelected) {
            System.out.println("rowNo awal=" + rowNo + " : row awal = " + row);
            if (rowNo != row) {

                rowNo = row;

                System.out.println(column + " : " + row);
                if (column == 6) {
                    JDesktopPane jDesktopPane = frame.getjDesktopPane1();
                    FrameTransactionInternalFrame frameTransactionInternalFrame = frame.getFrameTransactionInternalFrame();

                    if (!frameTransactionInternalFrame.isVisible()) {
                        jDesktopPane.add(frameTransactionInternalFrame);
                        frameTransactionInternalFrame.setVisible(true);
                        frameTransactionInternalFrame.setLocation(10, 10);
                        FrameTransactionController frameTransactionController = new FrameTransactionController(frameTransactionInternalFrame);
                        frameTransactionInternalFrame.setFrameTransactionController(frameTransactionController);
                    }
                    frameTransactionInternalFrame.moveToFront();
                    try {
                        frameTransactionInternalFrame.setSelected(true);
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(LabelRender.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {
                System.out.println("doNothing");
            }

        }

        return label;
    }

}
