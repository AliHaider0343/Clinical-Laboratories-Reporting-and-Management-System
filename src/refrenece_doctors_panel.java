
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
public class refrenece_doctors_panel extends javax.swing.JPanel {

    /**
     * Creates new form refrenece_doctors
     */
    Connection con;
    String drname;

    public refrenece_doctors_panel() {
        try {
            initComponents();

            con = databaseconnetion.get_Connection();

            t1.requestFocus();

            loadTable();
        } catch (SQLException ex) {
            Logger.getLogger(refrenece_doctors_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    DefaultTableModel model;

    public void loadTable() throws SQLException {
        Connection con;

        con = databaseconnetion.get_Connection();
        this.model = (DefaultTableModel) doctors_reference_Table.getModel();
        doctors_reference_Table = new JTable(model);
        model.setRowCount(0);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reference_doctors");
            while (rs.next()) {
                String[] data = new String[6];
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
                data[5] = rs.getString(6);
                model.addRow(data);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cannot Create Statement Exception Catched    :    " + ex.getMessage() + "      ", "Refrence Doctors ", JOptionPane.ERROR_MESSAGE);

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
            JOptionPane.showMessageDialog(null, "             Please Check Any Of the Given CheckBox To Search Accordingly . . .                             ", "Refrence Doctors ", JOptionPane.WARNING_MESSAGE);

        } else if (chk1.isSelected()) {
            str = search.getText();

            try {
                loadTable("Doctor_Name", str);
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (chk2.isSelected()) {
            str = search.getText();

            try {
                loadTable("Phone_Number", str);
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void delete() {
        if (serial.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Refrence Doctors ", JOptionPane.ERROR_MESSAGE);

        } else {
            try {
                Statement st = con.createStatement();
                st.executeUpdate("DELETE FROM reference_doctors  WHERE Serial_Number = '" + serial.getText() + "' ");
                serial.setText("00");
                t1.setText("");
                t2.setText("");
                t3.setSelectedIndex(0);
                t4.setText("");
                t5.setText("");

                t1.grabFocus();
                loadTable();
                JOptionPane.showMessageDialog(null, " Reference Doctor Information Deleted From DataBase SuccessFully . . .           \n          ", "Refrence Doctors ", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Refrence Doctors ", JOptionPane.WARNING_MESSAGE);

            }
        }

    }

    public void delete(int last) {

        int i = JOptionPane.showConfirmDialog(null, "Do you Really Want To Delete Last Reference Doctor Information ? \n  ", "Refrence Doctors ", JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {

            if (serial.getText().equals("00")) {

                JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Refrence Doctors ", JOptionPane.ERROR_MESSAGE);

            } else {
                try {
                    Statement st = con.createStatement();
                    st.executeUpdate("DELETE FROM reference_doctors  WHERE Serial_Number = '" + serial.getText() + "' ");
                    serial.setText("00");
                    t1.setText("");
                    t2.setText("");
                    t3.setSelectedIndex(0);
                    t4.setText("");
                    t5.setText("");
                    t1.grabFocus();
                    loadTable();

                    JOptionPane.showMessageDialog(null, "Last Reference Doctor Information Deleted From DataBase SuccessFully . . .           \n You May Insert New References From Referrals Doctor Interface .              ", "Patholgy Lab Software", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Refrence Doctors ", JOptionPane.WARNING_MESSAGE);

                }
            }

        }

    }

    public void loadTable(String databaseColumn, String givenargument) throws SQLException {
        this.model = (DefaultTableModel) doctors_reference_Table.getModel();
        doctors_reference_Table = new JTable(model);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reference_doctors WHERE Binary " + databaseColumn + " LIKE Binary'%" + givenargument + "%'");

            while (rs.next()) {
                String[] data = new String[6];
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
                data[5] = rs.getString(6);
                model.addRow(data);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Refrence Doctors ", JOptionPane.WARNING_MESSAGE);

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

        jScrollPane3 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jSpinner1 = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        t2 = new javax.swing.JTextField();
        t4 = new javax.swing.JTextField();
        t5 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        chk1 = new javax.swing.JCheckBox();
        chk2 = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        serial = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        t3 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        doctors_reference_Table = new javax.swing.JTable();
        deletebtn = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        jScrollPane3.setViewportView(jEditorPane1);

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1002, 688));

        jPanel1.setBackground(new java.awt.Color(226, 226, 226));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 686));

        jPanel3.setBackground(new java.awt.Color(243, 243, 243));
        jPanel3.setPreferredSize(new java.awt.Dimension(0, 686));

        jLabel11.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel11.setText("Doctore's Name");

        jLabel12.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel12.setText("Specialization");

        jLabel13.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel13.setText("Serial Number ");

        jLabel14.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel14.setText("Sex");

        jLabel15.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel15.setText("Hospital's Name");

        jLabel16.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel16.setText("Phone Number ");

        t1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t1.setSelectionColor(new java.awt.Color(19, 80, 161));
        t1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t1KeyPressed(evt);
            }
        });

        t2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t2.setSelectionColor(new java.awt.Color(19, 80, 161));
        t2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t2KeyPressed(evt);
            }
        });

        t4.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t4.setSelectionColor(new java.awt.Color(19, 80, 161));
        t4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t4KeyPressed(evt);
            }
        });

        t5.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t5.setSelectionColor(new java.awt.Color(19, 80, 161));
        t5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t5KeyPressed(evt);
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

        jButton10.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
        jButton10.setText("Refresh");
        jButton10.setBorder(null);
        jButton10.setContentAreaFilled(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jLabel17.setText("Search Doctors From Database");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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

        chk1.setBackground(new java.awt.Color(243, 243, 243));
        chk1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        chk1.setText("by Name");
        chk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk1ActionPerformed(evt);
            }
        });

        chk2.setBackground(new java.awt.Color(243, 243, 243));
        chk2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        chk2.setText("by Phone");
        chk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk2ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel18.setText("Referral Doctors Database Table");

        serial.setFont(new java.awt.Font("Rockwell", 0, 20)); // NOI18N
        serial.setForeground(new java.awt.Color(19, 80, 161));
        serial.setText("00");

        jLabel19.setFont(new java.awt.Font("Rockwell", 0, 28)); // NOI18N
        jLabel19.setText("Referral Doctors Record ");

        t3.setBackground(new java.awt.Color(19, 80, 161));
        t3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Gender", "Male", "Female" }));
        t3.setBorder(null);
        t3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t3KeyReleased(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(251, 152, 200));
        jPanel2.setOpaque(false);

        doctors_reference_Table.setAutoCreateRowSorter(true);
        doctors_reference_Table.setFont(new java.awt.Font("Rockwell", 0, 13)); // NOI18N
        doctors_reference_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Serial Number ", "Doctor's Name", "Specialization", "Sex", "Hospital Name", "Phone Number"
            }
        ));
        doctors_reference_Table.setGridColor(new java.awt.Color(0, 0, 0));
        doctors_reference_Table.setSelectionBackground(new java.awt.Color(19, 80, 161));
        doctors_reference_Table.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                doctors_reference_TableFocusLost(evt);
            }
        });
        doctors_reference_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                doctors_reference_TableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(doctors_reference_Table);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE))
        );

        deletebtn.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        deletebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteone.png"))); // NOI18N
        deletebtn.setBorder(null);
        deletebtn.setContentAreaFilled(false);
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteall.png"))); // NOI18N
        jButton9.setBorder(null);
        jButton9.setContentAreaFilled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(search, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chk2)
                                    .addComponent(chk1)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(156, 156, 156)
                                .addComponent(serial, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(t4)
                                    .addComponent(t3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton6)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deletebtn)))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel18)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chk2)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chk1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(serial))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(t2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addGap(60, 60, 60))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 954, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void t3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_t3KeyReleased

    private void t3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t3KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t4.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t3KeyPressed

    private void chk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk2ActionPerformed

        chk1.setSelected(false);
        search.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_chk2ActionPerformed

    private void chk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk1ActionPerformed
        chk2.setSelected(false);
        search.grabFocus();// TODO add your handling code here:
    }//GEN-LAST:event_chk1ActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        search_data();
        // TODO add your handling code here:
    }//GEN-LAST:event_searchKeyReleased

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost
        chk2.setSelected(false);
        chk1.setSelected(false);
        search.setText("");


    }//GEN-LAST:event_searchFocusLost

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
        serial.setText("00");
        t1.setText("");
        t2.setText("");
        t3.setSelectedIndex(0);
        t4.setText("");
        t5.setText("");
        try {
            loadTable();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(refrenece_doctors_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchFocusGained

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        serial.setText("00");
        t1.setText("");
        t2.setText("");
        t3.setSelectedIndex(0);
        t4.setText("");
        t5.setText("");
        search.setText("");
        t1.grabFocus();
        chk1.setSelected(false);
        chk2.setSelected(false);
        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        try {
            loadTable();
        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (doctors_reference_Table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "All Reference Doctors Information Has Already Deleted From The DataBase .                  ", "Refrence Doctors ", JOptionPane.INFORMATION_MESSAGE);

        } else {
            int i = JOptionPane.showConfirmDialog(null, "Do you Really Want To Delete All The References       ?  ", "Refrence Doctors ", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                try {
                    Statement st;
                    st = con.createStatement();
                    st.executeUpdate("DELETE FROM reference_doctors");
                    serial.setText("00");
                    t1.setText("");
                    t2.setText("");
                    t3.setSelectedIndex(0);
                    t4.setText("");
                    t5.setText("");
                    search.setText("");
                    t1.grabFocus();
                    chk1.setSelected(false);
                    chk2.setSelected(false);
                    loadTable();
                    JOptionPane.showMessageDialog(null, "All Doctors References  Has Been Deleted From The DataBase SuccessFully. \nYou May Insert New References From Referrals Doctor Interface.                 ", "Refrence Doctors ", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else;
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        if (serial.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Refrence Doctors ", JOptionPane.ERROR_MESSAGE);

        } else if (doctors_reference_Table.getRowCount() == 1 && (serial.getText().equals("00") == false)) {
            delete(1);
        } else {
            delete();
        }
        // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_deletebtnActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        if (serial.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Update   . . .                   ", "Refrence Doctors ", JOptionPane.ERROR_MESSAGE);

        } else {
            if (t1.getText().equals("") || t2.getText().equals("") || t4.getText().equals("") || t5.getText().equals("") || t3.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "           Please Fill All The Required Fields   !              ", "Refrence Doctors ", JOptionPane.ERROR_MESSAGE);
            } else {

                String name_title = "";
                if (t1.getText().startsWith("Dr", 0)) {
                    name_title = "";
                } else {
                    name_title = "Dr ";
                }

                try {
                    Statement stt = con.createStatement();

                    stt.executeUpdate("UPDATE patients_reports SET  Referred_By='" + ((name_title + t1.getText())) + "' WHERE Referred_By='" + drname + "'");
                    Statement st = con.createStatement();
                    st.executeUpdate("UPDATE reference_doctors SET  Doctor_Name='" + (name_title + t1.getText()) + "',Speicalization='" + t2.getText() + "',Sex='" + t3.getSelectedItem().toString() + "',Hospital_Name='" + t4.getText() + "',Phone_Number='" + t5.getText() + "' WHERE Serial_Number ='" + serial.getText() + "'");
                    serial.setText("00");
                    t1.setText("");
                    t2.setText("");
                    t3.setSelectedIndex(0);
                    t4.setText("");
                    t5.setText("");
                    search.setText("");
                    t1.grabFocus();
                    chk1.setSelected(false);
                    chk2.setSelected(false);
                    try {
                        loadTable();
                    } catch (Exception ex) {
                        Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "           Reference Doctor Information is Succesfully Updated with New information       !              ", "Refrence Doctors ", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Refrence Doctors ", JOptionPane.WARNING_MESSAGE);

                }
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (t1.getText().equals("") || t2.getText().equals("") || t4.getText().equals("") || t5.getText().equals("") || t3.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "           Please Fill All The Required Fields   !              ", "Refrence Doctors ", JOptionPane.ERROR_MESSAGE);
        } else {

            // TODO add your handling code here:
            String name_title = "";
            t1.setText(t1.getText().trim());
            if (t1.getText().startsWith("Dr", 0) || t1.getText().startsWith("dr", 0) || t1.getText().startsWith("dR", 0) || t1.getText().startsWith("DR", 0)) {
                name_title = "";
            } else {
                name_title = "Dr ";
            }
            Statement st;
            try {
                st = con.createStatement();
                st.executeUpdate("INSERT INTO reference_doctors (Doctor_Name,Speicalization,Sex,Hospital_Name,Phone_Number) VALUES ('" + (name_title + t1.getText()) + "','" + t2.getText() + "','" + (t3.getSelectedItem().toString()) + "','" + t4.getText() + "','" + t5.getText() + "')");

            } catch (Exception ex) {
                Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            }
            serial.setText("00");
            t1.setText("");
            t2.setText("");
            t3.setSelectedIndex(0);
            t4.setText("");
            t5.setText("");
            search.setText("");
            t1.grabFocus();
            chk1.setSelected(false);
            chk2.setSelected(false);
            try {
                loadTable();
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "           Reference Doctor Information is Succesfully Inserted to DataBase     !              ", "Refrence Doctors ", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void doctors_reference_TableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctors_reference_TableMouseReleased
        try {
            search.setText("");
            chk1.setSelected(false);
            chk2.setSelected(false);
            int i = doctors_reference_Table.rowAtPoint(evt.getPoint());// TODO add your handling code here

            String srl = (String) doctors_reference_Table.getValueAt(i, 0);
            serial.setText(srl);

            String name = (doctors_reference_Table.getValueAt(i, 1)).toString();
            drname = name;
            t1.setText(name);

            String specs = (String) doctors_reference_Table.getValueAt(i, 2);
            t2.setText(specs);

            String gen = (String) doctors_reference_Table.getValueAt(i, 3);
            if (gen.equals("Male")) {
                t3.setSelectedIndex(1);
            } else if (gen.equals("Female")) {
                t3.setSelectedIndex(2);
            } else {
                t3.setSelectedIndex(0);
            }
            String hname = (String) doctors_reference_Table.getValueAt(i, 4);
            t4.setText(hname);

            String phone = (String) doctors_reference_Table.getValueAt(i, 5);
            t5.setText(phone);

            try {
                loadTable();
                // TODO add your handling code here:
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "           Please  Select Only Single Row to Perform Operations  !          ", "Refrence Doctors ", JOptionPane.WARNING_MESSAGE);
            try {
                loadTable();
                // TODO add your handling code here:
            } catch (SQLException ex1) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

    }//GEN-LAST:event_doctors_reference_TableMouseReleased

    private void doctors_reference_TableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_doctors_reference_TableFocusLost
        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_doctors_reference_TableFocusLost

    private void t4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t4KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t5.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t4KeyPressed

    private void t2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t2KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t3.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t2KeyPressed

    private void t1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t1KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t2.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t1KeyPressed

    private void t5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t5KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t1.grabFocus();    // TODO add your handling code here:
    }//GEN-LAST:event_t5KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chk1;
    private javax.swing.JCheckBox chk2;
    private javax.swing.JButton deletebtn;
    public static javax.swing.JTable doctors_reference_Table;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField search;
    private javax.swing.JLabel serial;
    public static javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    private javax.swing.JComboBox<String> t3;
    private javax.swing.JTextField t4;
    private javax.swing.JTextField t5;
    // End of variables declaration//GEN-END:variables
}
