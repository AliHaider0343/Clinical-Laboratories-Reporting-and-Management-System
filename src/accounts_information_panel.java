
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ALI HAIDER
 */
public class accounts_information_panel extends javax.swing.JPanel {

    /**
     * Creates new form accounts_information_panel
     */
    DefaultTableModel model;

    public void loadTable() throws SQLException {
        Connection con;

        con = databaseconnetion.get_Connection();
        this.model = (DefaultTableModel) accounts_table.getModel();
        accounts_table = new JTable(model);
        model.setRowCount(0);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM accounts");
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
            JOptionPane.showMessageDialog(null, "Cannot Create Statement Exception Catched    :    " + ex.getMessage() + "      ", "Accounts Information", JOptionPane.ERROR_MESSAGE);

        }

    }
    Connection con;

    public accounts_information_panel() {

        initComponents();
        con = databaseconnetion.get_Connection();
        t2.requestFocus();
        try {
            loadTable();
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
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
            JOptionPane.showMessageDialog(null, "             Please Check Any Of the Given CheckBox To Search Accordingly . . .                             ", "Accounts Information", JOptionPane.WARNING_MESSAGE);

        } else if (chk1.isSelected()) {
            str = search.getText();

            try {
                loadTable("User_name", str);
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (chk2.isSelected()) {
            str = search.getText();

            try {
                loadTable("Good_Name", str);
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void delete() {
        if (t1.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Accounts Information", JOptionPane.ERROR_MESSAGE);

        } else {
            try {
                Statement st = con.createStatement();
                st.executeUpdate("DELETE FROM accounts  WHERE Serial_Number = '" + t1.getText() + "' ");
                t1.setText("00");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");

                t2.grabFocus();
                loadTable();
                JOptionPane.showMessageDialog(null, "Account Deleted From DataBase SuccessFully . . .           \n          ", "Accounts Information", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Accounts Information", JOptionPane.WARNING_MESSAGE);

            }
        }

    }

    public void delete(int last) {

        int i = JOptionPane.showConfirmDialog(null, "Do you Really Want To Delete Last Account ? \n  ", "Accounts Information", JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {

            if (t1.getText().equals("00")) {

                JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Accounts Information", JOptionPane.ERROR_MESSAGE);

            } else {
                try {
                    Statement st = con.createStatement();
                    st.executeUpdate("DELETE FROM accounts  WHERE Serial_Number = '" + t1.getText() + "' ");
                    t1.setText("00");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    t6.setText("");
                    t2.grabFocus();
                    loadTable();
                    Home.jButton6.doClick();
                    JOptionPane.showMessageDialog(null, "Last Account Deleted From DataBase SuccessFully . . .           \nPlease Create New Accounts and then Login To Access Software.              ", "Accounts Information", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Accounts Information", JOptionPane.WARNING_MESSAGE);

                }
            }

        }

    }

    public void loadTable(String databaseColumn, String givenargument) throws SQLException {
        this.model = (DefaultTableModel) accounts_table.getModel();
        accounts_table = new JTable(model);
        try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM accounts WHERE Binary " + databaseColumn + " LIKE Binary'%" + givenargument + "%'");

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
            JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Accounts Information", JOptionPane.WARNING_MESSAGE);

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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        t2 = new javax.swing.JTextField();
        t3 = new javax.swing.JTextField();
        t4 = new javax.swing.JTextField();
        t5 = new javax.swing.JTextField();
        t6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        chk2 = new javax.swing.JCheckBox();
        chk1 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        t1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        accounts_table = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1002, 688));

        jPanel1.setBackground(new java.awt.Color(243, 243, 243));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 686));

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 28)); // NOI18N
        jLabel1.setText("Accounts Interface");

        jLabel2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel2.setText("Good Name");

        jLabel3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel3.setText("Username");

        jLabel4.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel4.setText("Serial Number ");

        jLabel5.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel5.setText("Passward");

        jLabel6.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel6.setText("Confirm Passward");

        jLabel7.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel7.setText("Backup PIN");

        t2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t2.setSelectionColor(new java.awt.Color(19, 80, 161));
        t2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t2KeyPressed(evt);
            }
        });

        t3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t3.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        t3.setSelectionColor(new java.awt.Color(19, 80, 161));
        t3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t3KeyPressed(evt);
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

        t6.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t6.setSelectionColor(new java.awt.Color(19, 80, 161));
        t6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t6ActionPerformed(evt);
            }
        });
        t6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t6KeyPressed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/register acciybr.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteaccounts.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteall.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setContentAreaFilled(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
        jButton5.setText("Refresh ");
        jButton5.setBorder(null);
        jButton5.setContentAreaFilled(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jLabel8.setText("Search User Accounts");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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

        chk2.setBackground(new java.awt.Color(243, 243, 243));
        chk2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        chk2.setText("Search by Goodname");
        chk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk2ActionPerformed(evt);
            }
        });

        chk1.setBackground(new java.awt.Color(243, 243, 243));
        chk1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        chk1.setText("Search by  Username");
        chk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel9.setText("Accounts Database Table");

        t1.setFont(new java.awt.Font("Rockwell", 0, 20)); // NOI18N
        t1.setForeground(new java.awt.Color(19, 80, 161));
        t1.setText("00");

        jPanel2.setBackground(new java.awt.Color(251, 152, 200));
        jPanel2.setOpaque(false);

        accounts_table.setAutoCreateRowSorter(true);
        accounts_table.setFont(new java.awt.Font("Rockwell", 0, 13)); // NOI18N
        accounts_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Serial Number ", "Good Name", "Username", "Passward", "Confirm Code", "Backup PIN"
            }
        ));
        accounts_table.setGridColor(new java.awt.Color(0, 0, 0));
        accounts_table.setSelectionBackground(new java.awt.Color(19, 80, 161));
        accounts_table.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                accounts_tableFocusLost(evt);
            }
        });
        accounts_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                accounts_tableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(accounts_table);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(t6, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                    .addComponent(t5)
                                    .addComponent(t4)
                                    .addComponent(t3)
                                    .addComponent(t2)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel8))
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chk2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chk1))))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(184, 184, 184))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(58, 58, 58)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(chk1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chk2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(t1))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 957, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        t1.setText("00");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        search.setText("");
        t2.grabFocus();
        chk1.setSelected(false);
        chk2.setSelected(false);
        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals("") || t6.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "           Please Fill All The Required Fields   !              ", "Accounts Information", JOptionPane.WARNING_MESSAGE);
        } else {
            if (t4.getText().equals(t5.getText())) {// TODO add your handling code here:

                Statement stt;
                ResultSet rs = null;
                try {
                    stt = con.createStatement();
                    rs = stt.executeQuery("SELECT * From accounts where Good_Name='" + t2.getText() + "' OR User_Name='" + t3.getText() + "' ");

                } catch (Exception ex) {
                    Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    if (rs.next()) {

                        JOptionPane.showMessageDialog(null, "\t\tAccount with Such Creadetials Already Exits Please Try with other Credentials!\t\t", "Accounts Information", JOptionPane.ERROR_MESSAGE);

                    } else {

                        Statement st;

                        try {
                            st = con.createStatement();
                            st.executeUpdate("INSERT INTO accounts (Good_Name,User_Name,Passward,Confirm_Passward,Backup_pin) VALUES  ('" + t2.getText() + "','" + t3.getText() + "','" + t4.getText() + "','" + t5.getText() + "','" + t6.getText() + "')");

                        } catch (Exception ex) {
                            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        t1.setText("00");
                        t2.setText("");
                        t3.setText("");
                        t4.setText("");
                        t5.setText("");
                        t6.setText("");
                        t2.grabFocus();
                        try {
                            loadTable();
                        } catch (SQLException ex) {
                            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(null, "           Congratulations you has been Registered  !              ", "Accounts Information", JOptionPane.INFORMATION_MESSAGE);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "           Passward Confiramtion Error  !              ", "Accounts Information", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void accounts_tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accounts_tableMouseReleased
        try {
            search.setText("");
            chk1.setSelected(false);
            chk2.setSelected(false);
            int i = accounts_table.rowAtPoint(evt.getPoint());// TODO add your handling code here       

            String serial = (String) accounts_table.getValueAt(i, 0);
            String name = (accounts_table.getValueAt(i, 1)).toString();
            String username = (String) accounts_table.getValueAt(i, 2);
            String pass = (String) accounts_table.getValueAt(i, 3);
            String confirmpass = (String) accounts_table.getValueAt(i, 4);
            String backupin = (String) accounts_table.getValueAt(i, 5);

            t1.setText(serial);
            t2.setText(name);
            t3.setText(username);
            t4.setText(pass);
            t5.setText(confirmpass);
            t6.setText(backupin);
            try {
                loadTable();
                // TODO add your handling code here:
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "           Please  Select Only Single Row to Perform Operations  !          ", "Accounts Information", JOptionPane.WARNING_MESSAGE);
            try {
                loadTable();
                // TODO add your handling code here:
            } catch (SQLException ex1) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }


    }//GEN-LAST:event_accounts_tableMouseReleased

    private void t2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t2KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t3.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t2KeyPressed

    private void t3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t3KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t4.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t3KeyPressed

    private void t4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t4KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t5.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t4KeyPressed

    private void t5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t5KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t6.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t5KeyPressed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        search_data();
// TODO add your handling code here:
    }//GEN-LAST:event_searchKeyReleased

    private void chk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk2ActionPerformed
        chk1.setSelected(false);
        search.grabFocus();// TODO add your handling code here:
    }//GEN-LAST:event_chk2ActionPerformed

    private void chk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk1ActionPerformed

        chk2.setSelected(false);
        search.grabFocus();
// TODO add your handling code here:
    }//GEN-LAST:event_chk1ActionPerformed

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost

        chk2.setSelected(false);
        chk1.setSelected(false);
        search.setText("");

    }//GEN-LAST:event_searchFocusLost

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
        t1.setText("00");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        try {
            loadTable();

// TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchFocusGained

    private void accounts_tableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_accounts_tableFocusLost
        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_accounts_tableFocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (t1.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Update   . . .                   ", "Accounts Information", JOptionPane.ERROR_MESSAGE);

        } else {
            if (t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals("") || t6.getText().equals(""))
                JOptionPane.showMessageDialog(null, "           Please Fill All The Required Fields   !              ", "Accounts Information", JOptionPane.ERROR_MESSAGE);
            else {
                try {
                    Statement st = con.createStatement();
                    st.executeUpdate("UPDATE accounts SET Good_Name='" + t2.getText() + "',User_name='" + t3.getText() + "',Passward='" + t4.getText() + "',Confirm_Passward='" + t5.getText() + "',Backup_pin='" + t6.getText() + "' WHERE Serial_Number ='" + t1.getText() + "'");
                    t1.setText("00");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    t6.setText("");
                    t2.grabFocus();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "           Congratulations your Account Has Been Updated With New Inforamtion   !              ", "Accounts Information", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Accounts Information", JOptionPane.WARNING_MESSAGE);

                }

        }       }         }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (t1.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Accounts Information", JOptionPane.ERROR_MESSAGE);

        } else if (accounts_table.getRowCount() == 1 && (t1.getText().equals("00") == false)) {
            delete(1);
        } else {
            delete();
        }

        // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            loadTable();
        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (accounts_table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "All  Accounts Information Has Already Deleted From The DataBase .                  ", "Accounts Information", JOptionPane.INFORMATION_MESSAGE);

        } else {
            int i = JOptionPane.showConfirmDialog(null, "Do you Really Want To Delete All The Account       ?  ", "Accounts Information", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                try {
                    Statement st;
                    st = con.createStatement();
                    st.executeUpdate("DELETE FROM accounts");
                    t1.setText("00");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    t6.setText("");
                    search.setText("");
                    t2.grabFocus();
                    chk1.setSelected(false);
                    chk2.setSelected(false);
                    loadTable();
                    Home.loggedin.setText("Logged in as '      '");
                    Home.jButton6.doClick();
                    JOptionPane.showMessageDialog(null, "All Accounts Has Been Deleted From The DataBase SuccessFully. \nPlease Create New Accounts and then Login To Access Software.                 ", "Accounts Information", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else;
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void t6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t6KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t2.grabFocus();   // TODO add your handling code here:
    }//GEN-LAST:event_t6KeyPressed

    private void t6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable accounts_table;
    private javax.swing.JCheckBox chk1;
    private javax.swing.JCheckBox chk2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search;
    private javax.swing.JLabel t1;
    public static javax.swing.JTextField t2;
    private javax.swing.JTextField t3;
    private javax.swing.JTextField t4;
    private javax.swing.JTextField t5;
    private javax.swing.JTextField t6;
    // End of variables declaration//GEN-END:variables
}
