/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.controller;

import indooptik.dao.DAOFactory;
import indooptik.dao.ProductDAO;
import indooptik.internalframe.ProductInternalFrame;
import indooptik.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author yoeda
 */
public class ProductController {

    private DefaultTableModel dtm;
    private JTable table;
    ProductInternalFrame productInternalFrame;
    ProductDAO productDAO;
    private TableRowSorter cek;

    public ProductController(ProductInternalFrame productInternalFrame) {
        this.productInternalFrame = productInternalFrame;
        this.productDAO = DAOFactory.create().getProductDAO();
        this.table = productInternalFrame.getProductTabel();
        this.dtm = (DefaultTableModel) this.table.getModel();
        productInternalFrame.getSearchTxt().getDocument().addDocumentListener(productInternalFrame);
        cek = new TableRowSorter(dtm);
        productInternalFrame.getProductTabel().setRowSorter(cek);
        showData();
    }

    public void showData() {
        List<Product> listProducts = productDAO.retreiveALL();
        dtm.setRowCount(0);
        for (Product product : listProducts) {
            Vector v = new Vector();
            v.add(product.getId());
            v.add(product.getName());
            v.add(product.getType());
            v.add(product.getColor());
            v.add(product.getMinus());
            v.add(product.getBarcode());
            v.add(product.getStock());
            v.add(product.getPrice());
            v.add(product.getCreatedDate());
            dtm.addRow(v);
        }
    }

    public void reset() {
        productInternalFrame.getNameTxt().setText("");
        productInternalFrame.getTypeTxt().setText("");
        productInternalFrame.getColorTxt().setText("");
        productInternalFrame.getMinusTxt().setText("");
        productInternalFrame.getBarcodeTxt().setText("");
        productInternalFrame.getStockTxt().setText("");
        productInternalFrame.getPriceTxt().setText("");
        productInternalFrame.getSearchTxt().setText("");
        showData();
    }

    public void search() {
        RowFilter rf = null;
        rf = RowFilter.regexFilter(productInternalFrame.getSearchTxt().getText(), 1, 2, 3, 4, 5, 6, 7);
        cek.setRowFilter(rf);
    }

    public Product chooseData() {
        int selectedRow = productInternalFrame.getProductTabel().getSelectedRow();
        Product product = null;
        if (selectedRow > -1) {
            product = new Product();
            product.setId(productInternalFrame.getProductTabel().getValueAt(0, 0).hashCode());
            product.setName(productInternalFrame.getProductTabel().getValueAt(0, 1).toString());
            product.setType(productInternalFrame.getProductTabel().getValueAt(0, 2).toString());
            product.setColor(productInternalFrame.getProductTabel().getValueAt(0, 3).toString());
            product.setMinus(productInternalFrame.getProductTabel().getValueAt(0, 4).hashCode());
            product.setBarcode(productInternalFrame.getProductTabel().getValueAt(0, 5).toString());
            product.setStock(productInternalFrame.getProductTabel().getValueAt(0, 6).hashCode());
            product.setPrice(new BigDecimal(productInternalFrame.getProductTabel().getValueAt(0, 7).toString()));

            productInternalFrame.getNameTxt().setText(product.getName());
            productInternalFrame.getTypeTxt().setText(product.getType());
            productInternalFrame.getColorTxt().setText(product.getColor());
            productInternalFrame.getMinusTxt().setText("" + product.getMinus());
            productInternalFrame.getBarcodeTxt().setText(product.getBarcode());
            productInternalFrame.getStockTxt().setText("" + product.getStock());
            productInternalFrame.getPriceTxt().setText("" + product.getPrice());
        }
        return product;
    }

    public void save() {
        if (productInternalFrame.getNameTxt().getText().trim().length() > 0) {
            Product product = new Product();
            product.setName(productInternalFrame.getNameTxt().getText());
            product.setType(productInternalFrame.getTypeTxt().getText());
            product.setColor(productInternalFrame.getColorTxt().getText());
            product.setMinus(new Integer(productInternalFrame.getNameTxt().getText()));
            product.setBarcode(productInternalFrame.getBarcodeTxt().getText());
            product.setStock(new Integer(productInternalFrame.getStockTxt().getText()));
            product.setPrice(new BigDecimal(productInternalFrame.getNameTxt().getText()));

            if (productDAO.insert(product)) {
                JOptionPane.showMessageDialog(productInternalFrame, "Data berhasil disimpan");
                reset();
            } else {
                JOptionPane.showMessageDialog(productInternalFrame, "Data gagal disimpan");
            }
        } else {
            JOptionPane.showMessageDialog(productInternalFrame, "Harap lengkapi data sebelum simpan data");
        }
    }

    public void edit() {
        int selectedRow = productInternalFrame.getProductTabel().getSelectedRow();
        if (selectedRow > -1) {
            Product product = new Product();
            product.setId(productInternalFrame.getProductTabel().getValueAt(0, 0).hashCode());
            product.setName(productInternalFrame.getNameTxt().getText());
            product.setType(productInternalFrame.getTypeTxt().getText());
            product.setColor(productInternalFrame.getColorTxt().getText());
            product.setMinus(new Integer(productInternalFrame.getNameTxt().getText()));
            product.setBarcode(productInternalFrame.getBarcodeTxt().getText());
            product.setStock(new Integer(productInternalFrame.getStockTxt().getText()));
            product.setPrice(new BigDecimal(productInternalFrame.getNameTxt().getText()));

            if (productDAO.update(product)) {
                JOptionPane.showMessageDialog(productInternalFrame, "Data berhasil diubah");
                reset();
            } else {
                JOptionPane.showMessageDialog(productInternalFrame, "Data gagal diubah");
            }
        } else {
            JOptionPane.showMessageDialog(productInternalFrame, "Harap pilih data yang akan di ubah");
        }
    }

    public void delete() {
        Product product = chooseData();
        if(product == null){
            JOptionPane.showMessageDialog(productInternalFrame, "Harap pilih data yang akan di hapus");
        }else{
            if (JOptionPane.showConfirmDialog(productInternalFrame, "Anda yakin akan hapus data dengan Nama " + product.getName() + "? ",
                    "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION) == 0) {
                if (productDAO.delete(product)) {
                    JOptionPane.showMessageDialog(productInternalFrame, "Data berhasil dihapus");
                    reset();
                } else {
                    JOptionPane.showMessageDialog(productInternalFrame, "Data gagal dihapus");
                }
            }
        }
    }
}
