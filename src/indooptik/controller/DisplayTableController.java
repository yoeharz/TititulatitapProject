/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.controller;

import indooptik.dao.DAOFactory;
import indooptik.dao.DisplayTableDAO;
import indooptik.internalframe.DisplayTableInternalFrame;
import indooptik.model.DisplayTable;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yoeda H
 */
public class DisplayTableController {
    DisplayTableInternalFrame displayTableInternalFrame;
    DisplayTableDAO displayTableDAO;
    private DefaultTableModel dtm;
    private JTable table;    

    public DisplayTableController(DisplayTableInternalFrame displayTableInternalFrame) {
        this.displayTableInternalFrame = displayTableInternalFrame;
        this.displayTableDAO = DAOFactory.create().getDisplayTableDAO();
        this.table = displayTableInternalFrame.getDisplayTabel();
        dtm = (DefaultTableModel) this.table.getModel();
        showData();
    }
    
    public void showData(){
        List<DisplayTable> listDisplayTables = displayTableDAO.retreiveALL();
        dtm.setRowCount(0);
        for(DisplayTable displayTable : listDisplayTables){
            Vector v = new Vector();
            v.add(displayTable.getId());
            v.add(displayTable.getDisplayName());
            dtm.addRow(v);
        }
    }
    
    public void reset(){
        displayTableInternalFrame.getIdTxt().setText("");
        displayTableInternalFrame.getDisplayNameTxt().setText("");
        displayTableInternalFrame.setIdDisplay(0);
        displayTableInternalFrame.getIdTxt().requestFocus();
        showData();
    }
    
    public void save(){
        if (validation() == false) {
            JOptionPane.showMessageDialog(displayTableInternalFrame, "Harap lengkapi data sebeludisplayTable.setId(new Integer(displayTableInternalFrame.getIdTxt().getText()));\n" +
"        displayTable.setDisplayName(displayTableInternalFrame.getDisplayNameTxt().getText());m simpan");
            reset();
            return;
        }

        DisplayTable displayTable = new DisplayTable();
        displayTable.setId(new Integer(displayTableInternalFrame.getIdTxt().getText()));
        displayTable.setDisplayName(displayTableInternalFrame.getDisplayNameTxt().getText());

        if (displayTableDAO.insertDisplayTable(displayTable)) {
            JOptionPane.showMessageDialog(displayTableInternalFrame, "Simpan data berhasil");
            reset();
        } else {
            JOptionPane.showMessageDialog(displayTableInternalFrame, "Simpan data gagal");
        }
    }
    
    public DisplayTable chooseData(){
        int barisTerpilih = displayTableInternalFrame.getDisplayTabel().getSelectedRow();
        DisplayTable displayTable = null;
        if(barisTerpilih > -1){
            displayTable = new DisplayTable();
            displayTableInternalFrame.getIdTxt().setText(""+displayTableInternalFrame.getDisplayTabel().getValueAt(barisTerpilih, 0).hashCode());
            displayTableInternalFrame.getDisplayNameTxt().setText(displayTableInternalFrame.getDisplayTabel().getValueAt(barisTerpilih, 1).toString());
            displayTableInternalFrame.setIdDisplay(displayTableInternalFrame.getDisplayTabel().getValueAt(barisTerpilih, 0).hashCode());
            
            
            displayTable.setId(displayTableInternalFrame.getDisplayTabel().getValueAt(barisTerpilih, 0).hashCode());
            displayTable.setDisplayName(displayTableInternalFrame.getDisplayTabel().getValueAt(barisTerpilih, 1).toString());                        
        }
        return displayTable;
    }
    
    public boolean validation(){        
        if(displayTableInternalFrame.getIdTxt().getText().trim().length() != 0 ) return true;
        else return false;        
    }
    
    public void edit(){        
        if(displayTableInternalFrame.getIdDisplay() == 0) {
            JOptionPane.showMessageDialog(displayTableInternalFrame, "Harap pilih data pada tabel untuk diubah");
        } else {
            DisplayTable displayTable = new DisplayTable();
            displayTable.setId(new Integer(displayTableInternalFrame.getIdTxt().getText()));
            displayTable.setDisplayName(displayTableInternalFrame.getDisplayNameTxt().getText());
            System.out.println(displayTableInternalFrame.getIdDisplay() + displayTableInternalFrame.getDisplayNameTxt().getText());
            if (displayTableDAO.updateDisplayTable(displayTable, displayTableInternalFrame.getIdDisplay())) {
                JOptionPane.showMessageDialog(displayTableInternalFrame, "Ubah data berhasil");
                reset();
            } else {
                JOptionPane.showMessageDialog(displayTableInternalFrame, "Ubah data gagal");
            }
        }    
    }
    
    public void delete(){
        DisplayTable displayTable = chooseData();
        if(displayTable == null){
            JOptionPane.showMessageDialog(displayTableInternalFrame, "Harap pilih data pada tabel untuk dihapus");
        }else{            
            if (JOptionPane.showConfirmDialog(displayTableInternalFrame, "Anda yakin akan hapus data " + displayTable.getDisplayName() + "? ",
                    "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION) == 0) {

                if (displayTableDAO.delete(displayTable)) {
                    JOptionPane.showMessageDialog(displayTableInternalFrame, "Data berhasil dihapus");
                    reset();
                } else {
                    JOptionPane.showMessageDialog(displayTableInternalFrame, "Data gagal dihapus");
                }
            }
        }
    }
}
