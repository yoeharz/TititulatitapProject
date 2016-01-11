/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.controller;

import indooptik.dao.DAOFactory;
import indooptik.dao.LensDAO;
import indooptik.internalframe.LensInternalFrame;
import indooptik.model.Lens;
import java.math.BigDecimal;
import java.util.Date;
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
public class LensController {

    private DefaultTableModel dtm;
    private JTable table;
    private TableRowSorter cek;
    LensInternalFrame lensInternalFrame;
    LensDAO lensDAO;

    public LensController(LensInternalFrame lensInternalFrame) {
        this.lensInternalFrame = lensInternalFrame;
        this.lensDAO = DAOFactory.create().getLensDAO();
        this.table = lensInternalFrame.getLensTabel();
        dtm = (DefaultTableModel) this.table.getModel();
        lensInternalFrame.getSearchTxt().getDocument().addDocumentListener(lensInternalFrame);
        cek = new TableRowSorter(dtm);
        lensInternalFrame.getLensTabel().setRowSorter(cek);
        showData();
    }

    public void showData() {
        List<Lens> listLens = lensDAO.retreiveALL();
        dtm.setRowCount(0);
        for (Lens lens : listLens) {
            Vector v = new Vector();
            v.add(lens.getIdLens());
            v.add(lens.getType());
            v.add(lens.getLensVariance());
            v.add(lens.getSizeType());
            v.add(lens.getColor());
            v.add(lens.getPrice());
            dtm.addRow(v);
        }
    }

    public void reset() {
        lensInternalFrame.getLensTypeTxt().setText("");
        lensInternalFrame.getLensVarianceTxt().setText("");
        lensInternalFrame.getLensSizeTxt().setText("");
        lensInternalFrame.getLensColorTxt().setText("");
        lensInternalFrame.getLensPriceTxt().setText("");
        lensInternalFrame.getSearchTxt().setText("");
        showData();
    }

    public void save() {
        if (lensInternalFrame.getLensTypeTxt().getText().trim().length() > 0 || lensInternalFrame.getLensVarianceTxt().getText().trim().length() > 0) {
            Lens lens = new Lens();
            lens.setType(lensInternalFrame.getLensTypeTxt().getText());
            lens.setLensVariance(lensInternalFrame.getLensVarianceTxt().getText());
            lens.setSizeType(lensInternalFrame.getLensSizeTxt().getText());
            lens.setColor(lensInternalFrame.getLensColorTxt().getText());
            if (lensInternalFrame.getLensPriceTxt().getText().trim().length() > 0) {
                lens.setPrice(new BigDecimal(lensInternalFrame.getLensPriceTxt().getText()));
            }
            lens.setCreatedDate(new Date());
            if (lensDAO.insert(lens)) {
                JOptionPane.showMessageDialog(lensInternalFrame, "Data berhasil disimpan");
                reset();
            } else {
                JOptionPane.showMessageDialog(lensInternalFrame, "Data gagal disimpan");
            }
        } else {
            JOptionPane.showMessageDialog(lensInternalFrame, "Harap lengkapi data sebelum simpan data");
        }
    }

    public Lens chooseData() {
        int selectedRow = lensInternalFrame.getLensTabel().getSelectedRow();
        Lens lens = null;
        if (selectedRow > -1) {
            lens = new Lens();
            lens.setIdLens(lensInternalFrame.getLensTabel().getValueAt(selectedRow, 0).hashCode());
            lens.setType(lensInternalFrame.getLensTabel().getValueAt(selectedRow, 1).toString());
            lens.setLensVariance(lensInternalFrame.getLensTabel().getValueAt(selectedRow, 2).toString());
            lens.setSizeType(lensInternalFrame.getLensTabel().getValueAt(selectedRow, 3).toString());
            lens.setColor(lensInternalFrame.getLensTabel().getValueAt(selectedRow, 4).toString());
            lens.setPrice(new BigDecimal(lensInternalFrame.getLensTabel().getValueAt(selectedRow, 5).toString()));

            lensInternalFrame.getLensTypeTxt().setText(lens.getType());
            lensInternalFrame.getLensVarianceTxt().setText(lens.getLensVariance());
            lensInternalFrame.getLensSizeTxt().setText(lens.getSizeType());
            lensInternalFrame.getLensColorTxt().setText(lens.getColor());
            lensInternalFrame.getLensPriceTxt().setText("" + lens.getPrice());
        }
        return lens;
    }

    public void edit() {
        int selectedRow = lensInternalFrame.getLensTabel().getSelectedRow();
        if (selectedRow > -1) {
            if (lensInternalFrame.getLensTypeTxt().getText().trim().length() > 0 || lensInternalFrame.getLensVarianceTxt().getText().trim().length() > 0) {
                Lens lens = new Lens();
                lens.setIdLens(lensInternalFrame.getLensTabel().getValueAt(selectedRow, 0).hashCode());
                lens.setType(lensInternalFrame.getLensTypeTxt().getText());
                lens.setLensVariance(lensInternalFrame.getLensVarianceTxt().getText());
                lens.setSizeType(lensInternalFrame.getLensSizeTxt().getText());
                lens.setColor(lensInternalFrame.getLensColorTxt().getText());
                lens.setPrice(new BigDecimal(lensInternalFrame.getLensPriceTxt().getText()));
                lens.setCreatedDate(new Date());
                if (lensDAO.update(lens)) {
                    JOptionPane.showMessageDialog(lensInternalFrame, "Data berhasil diubah");
                    reset();
                } else {
                    JOptionPane.showMessageDialog(lensInternalFrame, "Data gagal diubah");
                }
            }else{
                JOptionPane.showMessageDialog(lensInternalFrame, "Harap lengkapi data sebelum edit data");
            }
        } else {
            JOptionPane.showMessageDialog(lensInternalFrame, "Harap pilih data yang akan di ubah");
        }
    }

    public void delete() {
        Lens lens = chooseData();
        if (lens == null) {
            JOptionPane.showMessageDialog(lensInternalFrame, "Harap pilih data yang akan di hapus");
        } else {
            if (JOptionPane.showConfirmDialog(lensInternalFrame, "Anda yakin akan hapus data lensa tipe " + lens.getType() + "? ",
                    "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION) == 0) {
                if (lensDAO.delete(lens)) {
                    JOptionPane.showMessageDialog(lensInternalFrame, "Data berhasil dihapus");
                    reset();
                } else {
                    JOptionPane.showMessageDialog(lensInternalFrame, "Data gagal dihapus");
                }
            }
        }
    }

    public void search() {
        RowFilter rf = null;
        rf = RowFilter.regexFilter(lensInternalFrame.getSearchTxt().getText(), 1, 2, 3, 4, 5);
        cek.setRowFilter(rf);
    }
}
