/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.controller;

import indooptik.dao.DAOFactory;
import indooptik.dao.DisplayTableDAO;
import indooptik.dao.FrameDAO;
import indooptik.internalframe.FrameInternalFrame;
import indooptik.model.DisplayTable;
import indooptik.model.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Yoeda H
 */
public class FrameController {

    private FrameInternalFrame frameInternalFrame;
    private FrameDAO frameDAO;
    private DefaultTableModel dtm;
    private JTable table;
    private TableRowSorter cek;
    private DisplayTableDAO displayTableDAO;
    private List<Frame> listfrFrames;

    public FrameController(FrameInternalFrame frameInternalFrame) {
        System.out.println("init");
        this.frameInternalFrame = frameInternalFrame;
        this.table = frameInternalFrame.getFrameTabel();
        this.frameDAO = DAOFactory.create().getFrameDAO();
        this.displayTableDAO = DAOFactory.create().getDisplayTableDAO();
        dtm = (DefaultTableModel) this.table.getModel();
        frameInternalFrame.getSearchTxt().getDocument().addDocumentListener(frameInternalFrame);
        cek = new TableRowSorter(dtm);
        frameInternalFrame.getFrameTabel().setRowSorter(cek);
        this.listfrFrames = new ArrayList<>();
        showDisplayTable();
        showChooseDisplayTable();
        showFrameTabel();
        frameInternalFrame.getChooseDisplayTableCmb().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {                
                showFrameTabel();
            }
        });
    }

    public void showChooseDisplayTable() {
        System.out.println("init show choose");

        frameInternalFrame.getChooseDisplayTableCmb().removeAllItems();
        frameInternalFrame.getChooseDisplayTableCmb().addItem("Semua Meja display");
        List<DisplayTable> listDisplayTables = displayTableDAO.retreiveALL();
        for (DisplayTable displayTable : listDisplayTables) {
            frameInternalFrame.getChooseDisplayTableCmb().addItem(displayTable.toString());
        }
        System.out.println("init show choose");
    }

    public void showDisplayTable() {
        frameInternalFrame.getIdDisplayTableCmb().removeAllItems();
        frameInternalFrame.getIdDisplayTableCmb().addItem("Pilih ID meja display");
        List<DisplayTable> listDisplayTables = displayTableDAO.retreiveALL();
        for (DisplayTable displayTable : listDisplayTables) {
            frameInternalFrame.getIdDisplayTableCmb().addItem(displayTable.toString());
        }
    }

    public void showFrameTabel() {
        dtm.setRowCount(0);
        int displayTableIndex = 0;
        if (frameInternalFrame.getChooseDisplayTableCmb().getItemCount() > -1) {
            displayTableIndex = frameInternalFrame.getChooseDisplayTableCmb().getSelectedIndex();
        }
        listfrFrames.clear();
        if (displayTableIndex == 0) {
            listfrFrames = frameDAO.retreiveALL();
        } else {
            int idDisplayTable = Integer.parseInt(frameInternalFrame.getChooseDisplayTableCmb().getSelectedItem().toString());
            listfrFrames = frameDAO.retreiveALLByIdDisplayTable(idDisplayTable);
        }

        for (Frame frame : listfrFrames) {
            Vector v = new Vector();
            v.add(frame.getIdFrame());
            v.add(frame.getIdDisplayTable());
            v.add(frame.getSeqNumber());
            v.add(frame.getMerk());
            v.add(frame.getFrameType());
            v.add(frame.getColor());
            v.add(frame.getPrice());
            v.add(frame.getSoldStatus());
            dtm.addRow(v);
        }
    }

    public void save() {
        if (frameInternalFrame.getMerkTxt().getText().trim().length() > 0 && frameInternalFrame.getIdDisplayTableCmb().getSelectedIndex() != 0 && frameInternalFrame.getSeqNumberTxt().getText().trim().length() > 0) {
            Frame frame = new Frame();
            frame.setIdDisplayTable(new Integer(frameInternalFrame.getIdDisplayTableCmb().getSelectedItem().toString()));
            frame.setSeqNumber(Integer.parseInt(frameInternalFrame.getSeqNumberTxt().getText()));
            frame.setMerk(frameInternalFrame.getMerkTxt().getText());
            frame.setFrameType(frameInternalFrame.getTypeTxt().getText());
            frame.setColor(frameInternalFrame.getColorTxt().getText());
            if (frameInternalFrame.getPriceTxt().getText().trim().length() > 0) {
                frame.setPrice(new BigDecimal(frameInternalFrame.getPriceTxt().getText()));
            }
            frame.setSoldStatus("Unsold");
            if (frameDAO.checkAvailableFrame(frame) == false) {
                if (frameDAO.insert(frame)) {
                    JOptionPane.showMessageDialog(frameInternalFrame, "Data berhasil disimpan");
                    reset();
                } else {
                    JOptionPane.showMessageDialog(frameInternalFrame, "Data gagal disimpan");
                }
            } else {
                JOptionPane.showMessageDialog(frameInternalFrame, "ID Display Table: " + frame.getIdDisplayTable() + " dan No Urut: " + frame.getSeqNumber() + " sudah digunakan");
            }

        } else {
            JOptionPane.showMessageDialog(frameInternalFrame, "Harap lengkapi data sebelum simpan data");
        }
    }

    public void reset() {
        frameInternalFrame.getIdDisplayTableCmb().setSelectedIndex(0);
        frameInternalFrame.getSeqNumberTxt().setText("");
        frameInternalFrame.getMerkTxt().setText("");
        frameInternalFrame.getTypeTxt().setText("");
        frameInternalFrame.getColorTxt().setText("");
        frameInternalFrame.getPriceTxt().setText("");
        frameInternalFrame.getChooseDisplayTableCmb().setSelectedIndex(0);
        frameInternalFrame.getSearchTxt().setText("");
        frameInternalFrame.getIdDisplayTableCmb().setEditable(true);
        frameInternalFrame.getSeqNumberTxt().setEditable(true);
        showFrameTabel();
    }

    public void search() {
        RowFilter rf = null;
        rf = RowFilter.regexFilter(frameInternalFrame.getSearchTxt().getText(), 1, 2, 3, 4, 5, 6, 7);
        cek.setRowFilter(rf);
    }

    public Frame chooseData() {
        int selectedRow = frameInternalFrame.getFrameTabel().getSelectedRow();
        Frame frame = null;
        if (selectedRow > -1) {
            frame = new Frame();
            frame.setIdFrame(frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 0).hashCode());
            frame.setIdDisplayTable(frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 1).hashCode());
            frame.setSeqNumber(frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 2).hashCode());
            frame.setMerk(frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 3).toString());
            frame.setFrameType(frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 4).toString());
            frame.setColor(frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 5).toString());
            frame.setPrice(new BigDecimal(frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 6).toString()));
            frame.setSoldStatus(frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 7).toString());

            frameInternalFrame.getIdDisplayTableCmb().setSelectedIndex(frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 1).hashCode());
            frameInternalFrame.getSeqNumberTxt().setText("" + frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 2).hashCode());
            frameInternalFrame.getMerkTxt().setText(frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 3).toString());
            frameInternalFrame.getTypeTxt().setText(frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 4).toString());
            frameInternalFrame.getColorTxt().setText(frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 5).toString());
            frameInternalFrame.getPriceTxt().setText(frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 6).toString());
            
            frameInternalFrame.getIdDisplayTableCmb().setEditable(false);
            frameInternalFrame.getSeqNumberTxt().setEditable(false);
            
            if("Sold".equalsIgnoreCase(frame.getSoldStatus())){
                frameInternalFrame.getDeleteBtn().setEnabled(false);
            }else{
                frameInternalFrame.getDeleteBtn().setEnabled(true);
            }

        }
        return frame;
    }

    public void delete() {
        Frame frame = chooseData();
        if (frame == null) {
            JOptionPane.showMessageDialog(frameInternalFrame, "Harap pilih data yang akan di hapus");
        } else {
            if (JOptionPane.showConfirmDialog(frameInternalFrame, "Anda yakin akan hapus data frame ini? ",
                    "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION) == 0) {

                if (frameDAO.delete(frame)) {
                    JOptionPane.showMessageDialog(frameInternalFrame, "Data berhasil dihapus");
                    reset();
                } else {
                    JOptionPane.showMessageDialog(frameInternalFrame, "Data gagal dihapus");
                }
            }
        }
    }

    public void edit() {
        int selectedRow = frameInternalFrame.getFrameTabel().getSelectedRow();
        if (selectedRow > -1) {
            if (frameInternalFrame.getMerkTxt().getText().trim().length() > 0 && frameInternalFrame.getIdDisplayTableCmb().getSelectedIndex() != 0 && frameInternalFrame.getSeqNumberTxt().getText().trim().length() > 0) {
                Frame frame = new Frame();                
                frame.setIdFrame(new Integer(frameInternalFrame.getFrameTabel().getValueAt(selectedRow, 0).toString()));
                frame.setIdDisplayTable(new Integer(frameInternalFrame.getIdDisplayTableCmb().getSelectedItem().toString()));
                frame.setSeqNumber(Integer.parseInt(frameInternalFrame.getSeqNumberTxt().getText()));
                frame.setMerk(frameInternalFrame.getMerkTxt().getText());
                frame.setFrameType(frameInternalFrame.getTypeTxt().getText());
                frame.setColor(frameInternalFrame.getColorTxt().getText());
                if (frameInternalFrame.getPriceTxt().getText().trim().length() > 0) {
                    frame.setPrice(new BigDecimal(frameInternalFrame.getPriceTxt().getText()));
                }

                if (frameDAO.update(frame)) {
                    JOptionPane.showMessageDialog(frameInternalFrame, "Data berhasil diubah");
                    reset();
                } else {
                    JOptionPane.showMessageDialog(frameInternalFrame, "Data gagal diubah");
                }
            } else {
                JOptionPane.showMessageDialog(frameInternalFrame, "Harap lengkapi data sebelum edit data");
            }
        } else {
            JOptionPane.showMessageDialog(frameInternalFrame, "Harap pilih data yang akan di ubah");
        }

    }
}
