/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.controller;

import indooptik.dao.CustomerDAO;
import indooptik.dao.DAOFactory;
import indooptik.internalframe.CustomerInternalFrame;
import indooptik.internalframe.LabelEditor;
import indooptik.internalframe.LabelRender;
import indooptik.main.MainFrame;
import indooptik.model.Customer;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Yoeda H
 */
public class CustomerController {

    public CustomerInternalFrame customerInternalFrame;
    CustomerDAO customerDAO;
    private DefaultTableModel dtm;
    public JTable table;
    private TableRowSorter cek;
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    MainFrame frame;

    public CustomerController(CustomerInternalFrame customerInternalFrame, MainFrame frame) {
        this.customerInternalFrame = customerInternalFrame;
        this.customerDAO = DAOFactory.create().getCustomerDAO();
        this.table = customerInternalFrame.getCustomerTabel();
        //this.table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //this.table.setColumnSelectionAllowed(true);
        //this.table.setRowSelectionAllowed(true);
        dtm = (DefaultTableModel) this.table.getModel();
        customerInternalFrame.getSearchTxt().getDocument().addDocumentListener(customerInternalFrame);
        cek = new TableRowSorter(dtm);
        customerInternalFrame.getCustomerTabel().setRowSorter(cek);
        this.frame = frame;
        showData();
    }

    public void showData() {
        List<Customer> listCustomer = customerDAO.retreiveALL();
        dtm.setRowCount(0);
        int no = 1;
        for (Customer customer : listCustomer) {
            Vector v = new Vector();
            v.add(no);
            v.add(customer.getIdCustomer());
            v.add(customer.getName());
            v.add(customer.getTelp());
            v.add(customer.getHp());
            v.add(dateFormat.format(customer.getHut()));
            v.add("Ke Transaksi");
            v.add("Ke Histori");
            no++;
            dtm.addRow(v);
        }
        //customerInternalFrame.getCustomerTabel().getColumnModel().getColumn(5).setCellRenderer(null);
        customerInternalFrame.getCustomerTabel().getColumnModel().getColumn(6).setCellRenderer(new LabelRender(this.frame, this));
        customerInternalFrame.getCustomerTabel().getColumnModel().getColumn(7).setCellRenderer(new LabelRender(this.frame, this));
        //customerInternalFrame.getCustomerTabel().getColumnModel().getColumn(6).setCellEditor(new LabelEditor());
    }

    public void search() {
        RowFilter rf = null;
        rf = RowFilter.regexFilter(customerInternalFrame.getSearchTxt().getText(), 1, 2, 3, 4, 5);
        cek.setRowFilter(rf);
    }

    public void save() {
        if (customerInternalFrame.getNameTxt().getText().trim().length() > 0 && customerInternalFrame.getBdayDate().getDate() != null) {
            Customer customer = new Customer();
            String initial = customerInternalFrame.getNameTxt().getText().substring(0, 1).toUpperCase();
            int idSequence = customerDAO.cekID(initial);
            idSequence += 1;
            System.out.println(initial + idSequence);
            customer.setIdCustomer(initial + idSequence);
            customer.setName(customerInternalFrame.getNameTxt().getText());
            customer.setTelp(customerInternalFrame.getTelpTxt().getText());
            customer.setHp(customerInternalFrame.getNoHPTxt().getText());
            customer.setHut(customerInternalFrame.getBdayDate().getDate());

            if (customerDAO.insert(customer)) {
                JOptionPane.showMessageDialog(customerInternalFrame, "Data berhasil disimpan");
                reset();
            } else {
                JOptionPane.showMessageDialog(customerInternalFrame, "Data gagal disimpan");
            }
        } else {
            JOptionPane.showMessageDialog(customerInternalFrame, "Harap lengkapi data sebelum simpan data");
        }
    }

    public void reset() {
        customerInternalFrame.getNameTxt().setText("");
        customerInternalFrame.getTelpTxt().setText("");
        customerInternalFrame.getNoHPTxt().setText("");
        customerInternalFrame.getBdayDate().setDate(null);
        showData();
    }

    public Customer chooseData() {
        int selectedRow = customerInternalFrame.getCustomerTabel().getSelectedRow();
        Customer customer = null;
        if (selectedRow > -1) {
            customer = new Customer();
            customer.setIdCustomer(customerInternalFrame.getCustomerTabel().getValueAt(selectedRow, 1).toString());
            customer.setName(customerInternalFrame.getCustomerTabel().getValueAt(selectedRow, 2).toString());
            customer.setTelp(customerInternalFrame.getCustomerTabel().getValueAt(selectedRow, 3).toString());
            customer.setHp(customerInternalFrame.getCustomerTabel().getValueAt(selectedRow, 4).toString());
            System.out.print(customerInternalFrame.getCustomerTabel().getValueAt(selectedRow, 5).toString());
            try {
                customer.setHut(dateFormat.parse(customerInternalFrame.getCustomerTabel().getValueAt(selectedRow, 5).toString()));
            } catch (ParseException ex) {
                Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }

            customerInternalFrame.getNameTxt().setText(customer.getName());
            customerInternalFrame.getTelpTxt().setText(customer.getTelp());
            customerInternalFrame.getNoHPTxt().setText(customer.getHp());
            customerInternalFrame.getBdayDate().setDate(customer.getHut());
        }
        return customer;
    }

    public void edit() {
        int selectedRow = customerInternalFrame.getCustomerTabel().getSelectedRow();
        if (selectedRow > -1) {
            if (customerInternalFrame.getNameTxt().getText().trim().length() > 0 && customerInternalFrame.getBdayDate().getDate() != null) {
                Customer customer = new Customer();
                customer.setIdCustomer(customerInternalFrame.getCustomerTabel().getValueAt(selectedRow, 1).toString());
                customer.setName(customerInternalFrame.getNameTxt().getText());
                customer.setTelp(customerInternalFrame.getTelpTxt().getText());
                customer.setHp(customerInternalFrame.getNoHPTxt().getText());
                customer.setHut(customerInternalFrame.getBdayDate().getDate());
                if (customerDAO.update(customer)) {
                    JOptionPane.showMessageDialog(customerInternalFrame, "Data berhasil diubah");
                    reset();
                } else {
                    JOptionPane.showMessageDialog(customerInternalFrame, "Data gagal diubah");
                }
            } else {
                JOptionPane.showMessageDialog(customerInternalFrame, "Harap lengkapi data sebelum edit data");
            }
        } else {
            JOptionPane.showMessageDialog(customerInternalFrame, "Harap pilih data yang akan di ubah");
        }
    }

    public void delete() {
        Customer customer = chooseData();
        if (customer == null) {
            JOptionPane.showMessageDialog(customerInternalFrame, "Harap pilih data yang akan di hapus");
        } else {
            if (JOptionPane.showConfirmDialog(customerInternalFrame, "Anda yakin akan hapus data dengan Nama " + customer.getName() + "? ",
                    "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION) == 0) {
                if (customerDAO.delete(customer)) {
                    JOptionPane.showMessageDialog(customerInternalFrame, "Data berhasil dihapus");
                    reset();
                } else {
                    JOptionPane.showMessageDialog(customerInternalFrame, "Data gagal dihapus");
                }
            }
        }
    }
}
