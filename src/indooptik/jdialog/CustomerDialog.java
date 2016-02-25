/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.jdialog;

import indooptik.controller.CustomerController;
import indooptik.dao.CustomerDAO;
import indooptik.dao.DAOFactory;
import indooptik.dao.FrameDAO;
import indooptik.internalframe.FrameTransactionInternalFrame;
import indooptik.model.Customer;
import indooptik.model.Frame;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author msacool
 */
public class CustomerDialog extends javax.swing.JDialog implements DocumentListener{

    private FrameTransactionInternalFrame frameTransactionInternalFrame;
    private DefaultTableModel dtm;
    private JTable table;
    private TableRowSorter cek;
    private CustomerDAO customerDAO;
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    /**
     * Creates new form FrameDialog
     */
    public CustomerDialog(JInternalFrame parent) {
        initComponents();
        
        frameTransactionInternalFrame = (FrameTransactionInternalFrame) parent;
        customerDAO = DAOFactory.create().getCustomerDAO();
        table = this.frameTbl;
        dtm = (DefaultTableModel) this.table.getModel();
        searchTxt.getDocument().addDocumentListener(this);
        cek = new TableRowSorter(dtm);
        table.setRowSorter(cek);
        
        showData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new indooptik.utility.Panel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        frameTbl = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pilih Customer");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cari");

        frameTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Id", "Nama", "Telp", "HP", "HUT", "Ke Penjualan", "Histori Transaksi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(frameTbl);
        if (frameTbl.getColumnModel().getColumnCount() > 0) {
            frameTbl.getColumnModel().getColumn(0).setMinWidth(50);
            frameTbl.getColumnModel().getColumn(0).setMaxWidth(50);
            frameTbl.getColumnModel().getColumn(1).setMinWidth(70);
            frameTbl.getColumnModel().getColumn(1).setMaxWidth(70);
            frameTbl.getColumnModel().getColumn(3).setMinWidth(100);
            frameTbl.getColumnModel().getColumn(3).setMaxWidth(100);
            frameTbl.getColumnModel().getColumn(4).setMinWidth(100);
            frameTbl.getColumnModel().getColumn(4).setMaxWidth(100);
            frameTbl.getColumnModel().getColumn(5).setMinWidth(100);
            frameTbl.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        jButton1.setText("Pilih");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchTxt))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 643, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(panel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        chooseData();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable frameTbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private indooptik.utility.Panel panel1;
    private javax.swing.JTextField searchTxt;
    // End of variables declaration//GEN-END:variables

    void showData(){
        List<Customer> customerList = customerDAO.retreiveALL();
        dtm.setRowCount(0);
        int count = 1;
        for (Customer customer : customerList) {
            Vector v = new Vector();
            v.add(count);
            v.add(customer.getIdCustomer());
            v.add(customer.getName());
            v.add(customer.getTelp());
            v.add(customer.getHp());
            v.add(dateFormat.format(customer.getHut()));
            v.add("Ke Transaksi");
            v.add("Ke Histori");
            dtm.addRow(v);
            count = count++;
        }
    }
    
    public void search() {
        RowFilter rf = RowFilter.regexFilter(this.searchTxt.getText(), 1, 2, 3, 4, 5);
        cek.setRowFilter(rf);
    }
    
    public Customer chooseData(){
        int selectedRow = this.table.getSelectedRow();
        Customer customer = null;
        if (selectedRow > -1) {
            customer = new Customer();
            customer.setIdCustomer(this.table.getValueAt(selectedRow, 1).toString());
            customer.setName(this.table.getValueAt(selectedRow, 2).toString());
            customer.setTelp(this.table.getValueAt(selectedRow, 3).toString());
            customer.setHp(this.table.getValueAt(selectedRow, 4).toString());
            System.out.print(this.table.getValueAt(selectedRow, 5).toString());
            try {
                customer.setHut(dateFormat.parse(this.table.getValueAt(selectedRow, 5).toString()));
            } catch (ParseException ex) {
                Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            setData(customer);
        }
        return customer;
    }
    
    public void setData(Customer customer){
        frameTransactionInternalFrame.getNameTxt().setText(customer.getName());
        frameTransactionInternalFrame.getPhoneTxt().setText(customer.getTelp());
        frameTransactionInternalFrame.getMobileTxt().setText(customer.getHp());
        frameTransactionInternalFrame.getBodTxt().setDate(customer.getHut());
    }
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        search();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        search();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        search();
    }
}
