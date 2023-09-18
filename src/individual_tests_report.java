
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ALI HAIDER
 */
public class individual_tests_report extends javax.swing.JFrame {

    /**
     * Creates new form individual_tests_report
     */
    Connection con;
    int test_counter;
    String Categroy_name;
    String speciman;
    DefaultTableModel model;
    boolean issave = false;

    public individual_tests_report() {
        initComponents();

        Image icon = Toolkit.getDefaultToolkit().getImage("src\\images\\frameicon.png");
        this.setIconImage(icon);

        jButton2.setMnemonic(KeyEvent.VK_1);
        jButton4.setMnemonic(KeyEvent.VK_4);
        jButton5.setMnemonic(KeyEvent.VK_3);
        jButton7.setMnemonic(KeyEvent.VK_2);
        issave = false;
        this.model = (DefaultTableModel) cart_tab.getModel();
        cart_tab = new JTable(model);
        test_counter = 0;
        exclude_units.setSelected(false);
        exclude_ranges.setSelected(false);
        con = databaseconnetion.get_Connection();

    }

    public void load_cat_box(String name) {

        category_Box.removeAllItems();
        category_Box.addItem("Select Category");
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM  tests_catagories where Category_Code ='" + name + "'");
            while (rs.next()) {
                String element = rs.getString(2);
                category_Box.addItem(element);
            }

        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void load_test_box_byt_cat() {

        test_box.removeAllItems();
        test_box.addItem("Select Test");
        test_box.setSelectedIndex(0);
        try {
            Statement st = con.createStatement();
            String cat = category_Box.getSelectedItem().toString().trim();
            ResultSet rs = st.executeQuery("SELECT * FROM  pathology_tests WHERE Test_category='" + cat + "'");
            while (rs.next()) {
                String element = rs.getString(3).toString();

                test_box.addItem(element);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception in  Load Tests By Category                          " + ex + "                            ", "Individual Tests Reporting", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void loadTable(String name, String speciman) throws SQLException {

        Connection con;

        con = databaseconnetion.get_Connection();
        this.model = (DefaultTableModel) cart_tab.getModel();
        cart_tab = new JTable(model);
        model.setRowCount(0);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tests_cart_table WHERE Report_ID = '" + report_serial.getText() + "' and  Test_Category in (SELECT Category_Name from tests_catagories where Category_Code= '" + name + "') and Speciman='" + speciman + "'");

            if (rs.next()) {
                rs.previous();
                while (rs.next()) {
                    test_counter++;

                    t8.setText(test_counter + "");
                    String[] data = new String[7];
                    data[0] = rs.getString(3);
                    data[1] = rs.getString(4);
                    data[2] = rs.getString(5);
                    data[3] = rs.getString(6);
                    data[4] = rs.getString(7);
                    data[5] = rs.getString(8);
                    data[6] = rs.getString(9);

                    model.addRow(data);
                }
            } else
                   ;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cannot Create Statement Exception Catched    :    " + ex.getMessage() + "      ", "Individual Tests Reporting ", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void refresh(String report_id, String test_name, String speciman) {
        report_serial.setText(report_id);
        l22.setText(test_name + " REPORTING");
        this.Categroy_name = test_name;
        this.speciman = speciman;
        exclude_units.setSelected(false);
        exclude_ranges.setSelected(false);
        load_cat_box(test_name);
        try {
            loadTable(test_name, speciman);
        } catch (SQLException ex) {
            Logger.getLogger(individual_tests_report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        test_box = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        category_Box = new javax.swing.JComboBox<>();
        report_serial = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        l22 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cart_tab = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        t8 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        exclude_units = new javax.swing.JCheckBox();
        exclude_ranges = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporting");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel21.setText("Report Number ");

        jButton2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/appendone.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel20.setText("Test's Category");

        test_box.setBackground(new java.awt.Color(19, 80, 161));
        test_box.setEditable(true);
        test_box.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        test_box.setBorder(null);
        test_box.setOpaque(false);
        test_box.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                test_boxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                test_boxFocusLost(evt);
            }
        });
        test_box.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                test_boxMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                test_boxMouseReleased(evt);
            }
        });
        test_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                test_boxActionPerformed(evt);
            }
        });
        test_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                test_boxKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                test_boxKeyTyped(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reportingsave.png"))); // NOI18N
        jButton5.setContentAreaFilled(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/saveandprint.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        category_Box.setBackground(new java.awt.Color(19, 80, 161));
        category_Box.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        category_Box.setBorder(null);
        category_Box.setOpaque(false);
        category_Box.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                category_BoxFocusLost(evt);
            }
        });
        category_Box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                category_BoxActionPerformed(evt);
            }
        });
        category_Box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                category_BoxKeyPressed(evt);
            }
        });

        report_serial.setFont(new java.awt.Font("Rockwell", 0, 20)); // NOI18N
        report_serial.setForeground(new java.awt.Color(19, 80, 161));
        report_serial.setText("00");

        jButton7.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/appendall.png"))); // NOI18N
        jButton7.setContentAreaFilled(false);
        jButton7.setHideActionText(true);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        l22.setFont(new java.awt.Font("Rockwell", 0, 20)); // NOI18N
        l22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l22.setText("Test Name Results Entry");

        jButton1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteIcon.png"))); // NOI18N
        jButton1.setText("Neglect Last Test");
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/garbage-bin-png-10496.png"))); // NOI18N
        jButton3.setText("Remove All ");
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel13.setText("Test's  Name");

        jPanel3.setBackground(new java.awt.Color(251, 152, 200));
        jPanel3.setOpaque(false);

        cart_tab.setFont(new java.awt.Font("Rockwell", 0, 13)); // NOI18N
        cart_tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serial no", "Test Category", "Test Name", "Test Range", "Test Result", "Test Units", "Test Rate"
            }
        ));
        cart_tab.setSelectionBackground(new java.awt.Color(19, 80, 161));
        cart_tab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cart_tabMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(cart_tab);

        jLabel2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel2.setText("Selected Pathology Tests List");

        jButton9.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
        jButton9.setText("Refresh Results");
        jButton9.setContentAreaFilled(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        t8.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t8.setForeground(new java.awt.Color(19, 80, 161));
        t8.setText("0");

        jLabel23.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel23.setText("Total Tests");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(33, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(t8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addGap(22, 22, 22))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton9)
                    .addComponent(jLabel23)
                    .addComponent(t8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel22.setFont(new java.awt.Font("Rockwell", 0, 28)); // NOI18N
        jLabel22.setText("Reporting Interface");

        exclude_units.setBackground(new java.awt.Color(255, 255, 255));
        exclude_units.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        exclude_units.setText("Check to exclude Units in Report");

        exclude_ranges.setBackground(new java.awt.Color(255, 255, 255));
        exclude_ranges.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        exclude_ranges.setText("Check to exclude Normal Ranges in Report");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 21, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel13)
                                                    .addGap(30, 30, 30)
                                                    .addComponent(test_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel20)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(category_Box, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel21)
                                                .addGap(18, 18, 18)
                                                .addComponent(report_serial, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(exclude_ranges)
                                            .addComponent(exclude_units)))
                                    .addComponent(l22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(421, 421, 421))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(30, 30, 30)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(report_serial)
                    .addComponent(jLabel21))
                .addGap(60, 60, 60)
                .addComponent(l22)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(category_Box, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(test_box, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(exclude_units)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exclude_ranges)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        remove_item();
    }//GEN-LAST:event_jButton1ActionPerformed
    public void remove_item() {
        double val = 0.0;
        if (model.getRowCount() > 0) {
            int row = model.getRowCount() - 1;

            model.removeRow(row);
            t8.setText(model.getRowCount() + "");
            test_counter--;
        } else {
            JOptionPane.showMessageDialog(null, "     All Data From The Table is Alaedy Deleted  Already     ", "Individual Tests Reporting", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (category_Box.getSelectedIndex() == 0 && test_box.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "     Kindly Select Category and Test Fields   . . .                   ", "Individual Tests Reporting", JOptionPane.ERROR_MESSAGE);
            category_Box.grabFocus();
        } else if (category_Box.getSelectedIndex() == 0 && category_Box.getSelectedItem().toString().equals("Select Category")) {
            JOptionPane.showMessageDialog(null, "     Kindly Select Category    . . .                   ", "Individual Tests Reporting", JOptionPane.ERROR_MESSAGE);
            category_Box.grabFocus();
        } else if (test_box.getSelectedIndex() == 0 && test_box.getSelectedItem().toString().trim().equals("Select Test")) {
            JOptionPane.showMessageDialog(null, "     Kindly Select  Test Fields   . . .                   ", "Individual Tests Reporting", JOptionPane.ERROR_MESSAGE);
            test_box.grabFocus();
        } else {
            set_parameters();
            category_Box.setSelectedIndex(0);
            test_box.setSelectedIndex(0);
            category_Box.grabFocus();

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
    public void set_parameters() {
        try {
            String test_name = "";
            String ranges = "";
            String units = "";
            String rates = "";
            String cateegory;
            test_name = test_box.getSelectedItem().toString().trim();
            cateegory = category_Box.getSelectedItem().toString().trim();
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM  pathology_tests WHERE Test_Name='" + test_box.getSelectedItem().toString().trim() + "' AND Test_category='" + category_Box.getSelectedItem().toString().trim() + "'");
                if (rs.next()) {
                    ranges = rs.getString(4);
                    units = rs.getString(5);
                    rates = rs.getString(6);

                }

            } catch (SQLException ex) {
                Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

            test_counter++;
            String[] data = new String[7];
            data[0] = test_counter + "";
            data[1] = cateegory;
            data[2] = test_name;
            data[3] = ranges;
            data[4] = "";
            data[5] = units;
            data[6] = rates;

            try {

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "         Exception Catched           ' " + ex + "'           ", "Individual Tests Reporting", JOptionPane.ERROR_MESSAGE);

            }

            t8.setText(test_counter + "");
            model.addRow(data);
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "         Exception Catched           ' " + ex + "'           ", "Individual Tests Reporting", JOptionPane.ERROR_MESSAGE);
        }

        ///upfdates is on  table 
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        remove_all();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
    public void remove_all() {
        model.setRowCount(0);
        t8.setText("0");
        test_counter = 0;

    }

    private void cart_tabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cart_tabMouseReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_cart_tabMouseReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (category_Box.getSelectedIndex() == 0 && category_Box.getSelectedItem().toString().equals("Select Category")) {
            JOptionPane.showMessageDialog(null, "     Kindly Select Category    . . .                   ", "Individual Tests Reporting", JOptionPane.ERROR_MESSAGE);
            category_Box.grabFocus();
        } else {
            load_whole_category();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    public void load_whole_category() {

        try {
            Statement st = con.createStatement();
            String cat = category_Box.getSelectedItem().toString().trim();
            ResultSet rs = st.executeQuery("SELECT * FROM  pathology_tests WHERE Test_category='" + cat + "'");

            while (rs.next()) {
                test_counter++;

                String[] data = new String[7];
                data[0] = test_counter + "";
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = "";
                data[5] = rs.getString(5);
                data[6] = rs.getString(6);
                t8.setText(test_counter + "");
                model.addRow(data);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Exception in  Load Tests By Category                          " + ex + "                            ", "Individual Tests Reporting", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt("", i, 4);
        }

    }//GEN-LAST:event_jButton9ActionPerformed
    public void save_part3() {

        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                Statement st = con.createStatement();
                st.executeUpdate("INSERT INTO tests_cart_table (Report_ID,Serial_no,Test_Category,Test_Name,Test_Ranges,Test_Results,Test_Units,Test_Rates,speciman) VALUES   ('" + (report_serial.getText()) + "','" + model.getValueAt(i, 0) + "','" + model.getValueAt(i, 1) + "','" + model.getValueAt(i, 2) + "','" + model.getValueAt(i, 3) + "','" + model.getValueAt(i, 4) + "','" + model.getValueAt(i, 5) + "','" + model.getValueAt(i, 6) + "','" + speciman + "')");

            } catch (SQLException ex) {
                System.out.println("Error");
            }

        }

        issave = true;

    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (cart_tab.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "          Please Add Tests First and then Save (There is Nothing to Save )     !              ", "Individual Tests Reporting", JOptionPane.ERROR_MESSAGE);
        } else {

            if (issave) {
                try {
                    Statement st = con.createStatement();

                    st.executeUpdate("delete from tests_cart_table where Report_ID='" + report_serial.getText() + "' and  Test_Category in (SELECT Category_Name from tests_catagories where Category_Code= '" + Categroy_name + "')and Speciman='" + speciman + "'");
                } catch (SQLException ex) {
                    Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
                }
                save_part3();

                JOptionPane.showMessageDialog(null, "          Patient Information is Succesfully Updated in DataBase     !              ", "Individual Tests Reporting", JOptionPane.INFORMATION_MESSAGE);

            } else {
                try {
                    Statement st = con.createStatement();

                    st.executeUpdate("delete from tests_cart_table where Report_ID='" + report_serial.getText() + "' and  Test_Category in (SELECT Category_Name from tests_catagories where Category_Code= '" + Categroy_name + "') and Speciman='" + speciman + "'");
                } catch (SQLException ex) {
                    Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
                }
                save_part3();
                try {
                    Statement st = con.createStatement();
                    st.executeUpdate("Update individual_tests set tests_taken_from_category='" + model.getRowCount() + "' where Test_Name= '" + Categroy_name + "' and report_Id='" + report_serial.getText() + "' and Speciman='" + speciman + "'");
                } catch (SQLException ex) {
                    Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "          Patient Information is Succesfully Inserted to DataBase     !              ", "Individual Tests Reporting", JOptionPane.INFORMATION_MESSAGE);

            }

        }

        exclude_units.setSelected(false);
        exclude_ranges.setSelected(false);
    }//GEN-LAST:event_jButton5ActionPerformed
    public void print(String id, String code) {
        HashMap parameter = new HashMap();
        parameter.put("code", code);
        parameter.put("report_Id", id);
        parameter.put("speciman_code", speciman);

        if (exclude_units.isSelected() && exclude_ranges.isSelected()) {

            try {
                Report_View vr = new Report_View("src\\Reports\\tests_report _individual_without_units_and_ranges.jasper", parameter);
                vr.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "          Exception Catched          ", "Individual Tests Reporting", JOptionPane.ERROR_MESSAGE);

            }
        } else if (exclude_units.isSelected() == false && exclude_ranges.isSelected() == false) {
            try {
                Report_View vr = new Report_View("src\\Reports\\tests_report _individual.jasper", parameter);
                vr.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "          Exception Catched          ", "Individual Tests Reporting", JOptionPane.ERROR_MESSAGE);

            }

        } else if (exclude_units.isSelected() == true && exclude_ranges.isSelected() == false) {
            try {
                Report_View vr = new Report_View("src\\Reports\\tests_report _individual_without_units.jasper", parameter);
                vr.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "          Exception Catched          ", "Individual Tests Reporting", JOptionPane.ERROR_MESSAGE);

            }
        } else if (exclude_units.isSelected() == false && exclude_ranges.isSelected() == true) {
            try {
                Report_View vr = new Report_View("src\\Reports\\tests_report _individual_without_ranges.jasper", parameter);
                vr.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "          Exception Catched          ", "Individual Tests Reporting", JOptionPane.ERROR_MESSAGE);

            }
        }

    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (cart_tab.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "          Please Add Tests First and then Save (There is Nothing to Save )     !              ", "Individual Tests Reporting", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Statement st = con.createStatement();

                st.executeUpdate("delete from tests_cart_table where Report_ID='" + report_serial.getText() + "' and  Test_Category in (SELECT Category_Name from tests_catagories where Category_Code= '" + Categroy_name + "') and Speciman='" + speciman + "'");
            } catch (SQLException ex) {
                Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            save_part3();
            try {
                Statement st = con.createStatement();
                st.executeUpdate("Update individual_tests set tests_taken_from_category='" + model.getRowCount() + "' ");
            } catch (SQLException ex) {
                Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

            String report_ID;
            report_ID = report_serial.getText();
            print(report_ID, Categroy_name);
            exclude_units.setSelected(false);
            exclude_ranges.setSelected(false);
            // TODO add your handling code here:
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void category_BoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_category_BoxFocusLost

        //       refresher.doClick(); // TODO add your handling code here:
    }//GEN-LAST:event_category_BoxFocusLost

    private void category_BoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_category_BoxActionPerformed
        load_test_box_byt_cat();
     }//GEN-LAST:event_category_BoxActionPerformed

    private void category_BoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_category_BoxKeyPressed

        // TODO add your handling code here:
    }//GEN-LAST:event_category_BoxKeyPressed

    private void test_boxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_test_boxFocusGained

        // TODO add your handling code here:
    }//GEN-LAST:event_test_boxFocusGained

    private void test_boxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_test_boxFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_test_boxFocusLost

    private void test_boxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_test_boxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_test_boxMouseClicked

    private void test_boxMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_test_boxMouseReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_test_boxMouseReleased

    private void test_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_test_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_test_boxActionPerformed

    private void test_boxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_test_boxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_test_boxKeyPressed

    private void test_boxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_test_boxKeyTyped

        // TODO add your handling code here:
    }//GEN-LAST:event_test_boxKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(individual_tests_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(individual_tests_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(individual_tests_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(individual_tests_report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new individual_tests_report().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable cart_tab;
    public static javax.swing.JComboBox<String> category_Box;
    private javax.swing.JCheckBox exclude_ranges;
    private javax.swing.JCheckBox exclude_units;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l22;
    public static javax.swing.JLabel report_serial;
    private javax.swing.JLabel t8;
    public static javax.swing.JComboBox<String> test_box;
    // End of variables declaration//GEN-END:variables
}
