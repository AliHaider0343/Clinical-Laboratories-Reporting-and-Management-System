
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ALI HAIDER
 */
public class add_cantagories_panel extends javax.swing.JPanel {

    /**
     * Creates new form add_cantagories_panel
     */
    Connection con;

    public add_cantagories_panel() {
        initComponents();

        con = databaseconnetion.get_Connection();

        t1.requestFocus();

        try {
            loadTable();
        } catch (SQLException ex) {
            Logger.getLogger(add_cantagories_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    DefaultTableModel model;

    public void loadTable() throws SQLException {
        Connection con;

        con = databaseconnetion.get_Connection();
        this.model = (DefaultTableModel) category_Table.getModel();
        category_Table = new JTable(model);
        model.setRowCount(0);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tests_catagories");
            while (rs.next()) {
                String[] data = new String[3];
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);

                model.addRow(data);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cannot Create Statement Exception Catched    :    " + ex.getMessage() + "      ", "Categories Opeartion", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void search_data() {

        model.setRowCount(0);
        ResultSet rs;
        String str = null;

        if (chk1.isSelected() == false && chk2.isSelected() == false) {
            search.setText("");
            try {
                loadTable();
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            search.grabFocus();
            JOptionPane.showMessageDialog(null, "             Please Check Any Of the Given CheckBox To Search Accordingly . . .                             ", "Categories Opeartion", JOptionPane.WARNING_MESSAGE);

        } else if (chk1.isSelected()) {
            str = search.getText();

            try {
                loadTable("Category_Name", str);
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (chk2.isSelected()) {
            str = search.getText();

            try {
                loadTable("Category_Code", str);
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void delete_tests_by_cat() {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tests_catagories WHERE Category_Serial_ID='" + serial.getText() + "' ");
            if (rs.next()) {

                String ca_name = rs.getString("Category_Name");
                Statement stt = con.createStatement();
                stt.executeUpdate("DELETE FROM pathology_tests  WHERE Test_category ='" + ca_name + "'");
            }

        } catch (SQLException ex) {
            Logger.getLogger(add_cantagories_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete_all_tests_by_cat() {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM pathology_tests ");
        } catch (SQLException ex) {
            Logger.getLogger(add_cantagories_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete() {
        if (serial.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Categories Opeartion", JOptionPane.ERROR_MESSAGE);

        } else {
            delete_tests_by_cat();
            try {
                Statement st = con.createStatement();

                st.executeUpdate("DELETE FROM tests_catagories  WHERE Category_Serial_ID  = '" + serial.getText() + "' ");

                serial.setText("00");
                t1.setText("");
                t2.setText("");
                t1.grabFocus();
                loadTable();
                JOptionPane.showMessageDialog(null, " Test Category with All Tests Information has been Deleted From DataBase SuccessFully . . .           \n          ", "Categories Opeartion", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Categories Opeartion", JOptionPane.WARNING_MESSAGE);

            }
        }

    }

    public void delete(int last) {

        int i = JOptionPane.showConfirmDialog(null, "Do you Really Want To Delete Last Test Category  Information with Tests informations? \n  ", "Categories Opeartion", JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {

            if (serial.getText().equals("00")) {

                JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Categories Opeartion", JOptionPane.ERROR_MESSAGE);

            } else {
                delete_tests_by_cat();
                try {

                    Statement st = con.createStatement();
                    st.executeUpdate("DELETE FROM tests_catagories  WHERE Category_Serial_ID  = '" + serial.getText() + "' ");

                    serial.setText("00");
                    t1.setText("");
                    t2.setText("");

                    t1.grabFocus();
                    loadTable();

                    JOptionPane.showMessageDialog(null, "Last Test Category with all Tests Information has been Deleted From DataBase SuccessFully . . .           \n You May Insert New  Test Categories From Add Tests Categories Interface .              ", "Categories Opeartion", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Categories Opeartion", JOptionPane.WARNING_MESSAGE);

                }
            }

        }

    }

    public void loadTable(String databaseColumn, String givenargument) throws SQLException {
        this.model = (DefaultTableModel) category_Table.getModel();
        category_Table = new JTable(model);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tests_catagories WHERE " + databaseColumn + " LIKE '%" + givenargument + "%'");

            while (rs.next()) {
                String[] data = new String[3];
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);

                model.addRow(data);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Categories Opeartion", JOptionPane.WARNING_MESSAGE);

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
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        chk1 = new javax.swing.JCheckBox();
        chk2 = new javax.swing.JCheckBox();
        serial = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        t2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        category_Table = new javax.swing.JTable();
        deletebtn = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel1.setText("Add / Update Categories ");

        jLabel17.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        jLabel17.setText("Categories  Table");

        search.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        search.setSelectionColor(new java.awt.Color(19, 80, 161));
        search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFocusLost(evt);
            }
        });
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        chk1.setBackground(new java.awt.Color(255, 255, 255));
        chk1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        chk1.setText("by Name");
        chk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk1ActionPerformed(evt);
            }
        });

        chk2.setBackground(new java.awt.Color(255, 255, 255));
        chk2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        chk2.setText("by  Code");
        chk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk2ActionPerformed(evt);
            }
        });

        serial.setFont(new java.awt.Font("Rockwell", 0, 20)); // NOI18N
        serial.setForeground(new java.awt.Color(19, 80, 161));
        serial.setText("00");

        jLabel13.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        jLabel13.setText("Serial Number ");

        jLabel11.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel11.setText("Category Name");

        t1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t1.setSelectionColor(new java.awt.Color(19, 80, 161));
        t1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ActionPerformed(evt);
            }
        });
        t1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t1KeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel12.setText("Category Code");

        t2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t2.setSelectionColor(new java.awt.Color(19, 80, 161));
        t2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t2KeyPressed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/insert.png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.setContentAreaFilled(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update1.png"))); // NOI18N
        jButton7.setBorder(null);
        jButton7.setContentAreaFilled(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(251, 152, 200));
        jPanel2.setOpaque(false);

        category_Table.setAutoCreateRowSorter(true);
        category_Table.setFont(new java.awt.Font("Rockwell", 0, 13)); // NOI18N
        category_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Category Serial Number ", "Category Name", "Category Code"
            }
        ));
        category_Table.setGridColor(new java.awt.Color(0, 0, 0));
        category_Table.setSelectionBackground(new java.awt.Color(19, 80, 161));
        category_Table.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                category_TableFocusLost(evt);
            }
        });
        category_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                category_TableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(category_Table);

        deletebtn.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        deletebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteIcon.png"))); // NOI18N
        deletebtn.setText("Delete");
        deletebtn.setBorder(null);
        deletebtn.setContentAreaFilled(false);
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/garbage-bin-png-10496.png"))); // NOI18N
        jButton9.setText("Delete All");
        jButton9.setBorder(null);
        jButton9.setContentAreaFilled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton9)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deletebtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9))
                .addContainerGap())
        );

        jLabel18.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jLabel18.setText("Search Category ");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
        jButton1.setText("Refresh");
        jButton1.setContentAreaFilled(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(serial, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(622, 622, 622))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(181, 181, 181)
                                        .addComponent(jLabel1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chk1)
                                            .addComponent(chk2))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel17)
                                .addGap(223, 223, 223))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chk1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(chk2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(search))
                        .addGap(2, 2, 2)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(serial))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
        serial.setText("00");
        t1.setText("");
        t2.setText("");
        try {
            loadTable();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(add_cantagories_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchFocusGained

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost

        chk2.setSelected(false);
        chk1.setSelected(false);
        search.setText("");


    }//GEN-LAST:event_searchFocusLost

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        search_data();
        // TODO add your handling code here:
    }//GEN-LAST:event_searchKeyReleased

    private void chk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk1ActionPerformed
        chk2.setSelected(false);
        search.grabFocus();// TODO add your handling code here:
    }//GEN-LAST:event_chk1ActionPerformed

    private void chk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk2ActionPerformed

        chk1.setSelected(false);
        search.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_chk2ActionPerformed

    private void t1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t1KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t2.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t1KeyPressed

    private void t2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t2KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t1.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t2KeyPressed

    private void t1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (t1.getText().equals("") || t2.getText().equals(""))
            JOptionPane.showMessageDialog(null, "           Please Fill All The Required Fields   !              ", "Categories Opeartion", JOptionPane.ERROR_MESSAGE);

        else {

            // TODO add your handling code here:
            Statement st;
            try {
                st = con.createStatement();
                st.executeUpdate("INSERT INTO tests_catagories (Category_Name,Category_Code) VALUES ('" + t1.getText() + "','" + t2.getText() + "')");

            } catch (Exception ex) {
                Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            }
            serial.setText("00");
            t1.setText("");
            t2.setText("");

            search.setText("");
            t1.grabFocus();
            chk1.setSelected(false);
            chk2.setSelected(false);
            try {
                loadTable();
            } catch (Exception ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "           Tests Categories Information is Succesfully Inserted to DataBase     !              ", "Categories Opeartion", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (serial.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Update   . . .                   ", "Categories Opeartion", JOptionPane.ERROR_MESSAGE);

        } else {
            if (t1.getText().equals("") || t2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "           Please Fill All The Required Fields   !              ", "Categories Opeartion", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Statement st = con.createStatement();

                    st.executeUpdate("UPDATE tests_catagories SET  Category_Name='" + t1.getText() + "',Category_Code='" + t2.getText() + "' WHERE Category_Serial_ID  = '" + serial.getText() + "' ");

                    serial.setText("00");
                    t1.setText("");
                    t2.setText("");
                    search.setText("");
                    t1.grabFocus();
                    chk1.setSelected(false);
                    chk2.setSelected(false);
                    try {
                        loadTable();
                    } catch (SQLException ex) {
                        Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "           Tests Categories Information is Succesfully Updated with New information       !              ", "Categories Opeartion", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Categories Opeartion", JOptionPane.WARNING_MESSAGE);

                }
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            loadTable();
        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (category_Table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "All Categories Information Has Already Deleted From The DataBase .                  ", "Categories Opeartion", JOptionPane.INFORMATION_MESSAGE);

        } else {
            int i = JOptionPane.showConfirmDialog(null, "you will lost all the Tests informations   \n\nDo you Really Want To Delete All The Categories        ?  ", "Categories Opeartion", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                try {
                    Statement st;
                    st = con.createStatement();
                    st.executeUpdate("DELETE FROM tests_catagories");
                    delete_all_tests_by_cat();
                    serial.setText("00");
                    t1.setText("");
                    t2.setText("");
                    search.setText("");
                    t1.grabFocus();
                    chk1.setSelected(false);
                    chk2.setSelected(false);

                    loadTable();
                    JOptionPane.showMessageDialog(null, "All Tests Categories Information Has Been Deleted From The DataBase SuccessFully. \nYou May Insert New Tests Categories Information from Add Categories Interface.                 ", "Categories Opeartion", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else;
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        if (serial.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Categories Opeartion", JOptionPane.ERROR_MESSAGE);

        } else if (category_Table.getRowCount() == 1 && (serial.getText().equals("00") == false)) {
            delete(1);
        } else {
            delete();
        }
        // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_deletebtnActionPerformed

    private void category_TableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_category_TableFocusLost
        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_category_TableFocusLost

    private void category_TableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_category_TableMouseReleased
        try {
            search.setText("");
            chk1.setSelected(false);
            chk2.setSelected(false);
            int i = category_Table.rowAtPoint(evt.getPoint());// TODO add your handling code here

            String srl = (String) category_Table.getValueAt(i, 0);
            serial.setText(srl);

            String name = (category_Table.getValueAt(i, 1)).toString();
            t1.setText(name);

            String specs = (String) category_Table.getValueAt(i, 2);
            t2.setText(specs);

            try {
                loadTable();
                // TODO add your handling code here:
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "           Please  Select Only Single Row to Perform Operations  !          ", "Categories Opeartion", JOptionPane.WARNING_MESSAGE);
            try {
                loadTable();
                // TODO add your handling code here:
            } catch (SQLException ex1) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

    }//GEN-LAST:event_category_TableMouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        serial.setText("00");
        t1.setText("");
        t2.setText("");
        search.setText("");
        t1.grabFocus();
        chk1.setSelected(false);
        chk2.setSelected(false);
        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable category_Table;
    private javax.swing.JCheckBox chk1;
    private javax.swing.JCheckBox chk2;
    private javax.swing.JButton deletebtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField search;
    private javax.swing.JLabel serial;
    public static javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    // End of variables declaration//GEN-END:variables
}
