
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ALI HAIDER
 */
public class tests_panel extends javax.swing.JPanel {

    /**
     * Creates new form tests_panel
     */
    Connection con;

    public tests_panel() {

        initComponents();
        // load_cat_box();
        AutoCompleteDecorator.decorate(category_Box);

        con = databaseconnetion.get_Connection();

        category_Box.requestFocus();

        try {
            loadTable();
        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void load_cat_box() {
        category_Box.removeAllItems();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tests_catagories");
            category_Box.addItem("Select Category");
            while (rs.next()) {
                String element = rs.getString(2);
                category_Box.addItem(element);
            }

        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    DefaultTableModel model;

    public void loadTable() throws SQLException {
        Connection con;

        con = databaseconnetion.get_Connection();
        this.model = (DefaultTableModel) pathology_tests_table.getModel();
        pathology_tests_table = new JTable(model);
        model.setRowCount(0);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pathology_tests");
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
            JOptionPane.showMessageDialog(null, "Cannot Create Statement Exception Catched    :    " + ex.getMessage() + "      ", "Tests Operation", JOptionPane.ERROR_MESSAGE);

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
            JOptionPane.showMessageDialog(null, "             Please Check Any Of the Given CheckBox To Search Accordingly . . .                             ", "Tests Operation", JOptionPane.WARNING_MESSAGE);

        } else if (chk1.isSelected()) {
            str = search.getText();

            try {
                loadTable("Test_Name", str);
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (chk2.isSelected()) {
            str = search.getText();

            try {
                loadTable("Test_category", str);
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void delete() {
        if (serial.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Tests Operation", JOptionPane.ERROR_MESSAGE);

        } else {
            try {
                Statement st = con.createStatement();
                st.executeUpdate("DELETE FROM pathology_tests  WHERE 	Test_Serial_ID  = '" + serial.getText() + "' ");
                serial.setText("00");
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                category_Box.setSelectedIndex(0);
                category_Box.grabFocus();
                loadTable();
                JOptionPane.showMessageDialog(null, " Patholog Test Information Deleted From DataBase SuccessFully . . .           \n          ", "Tests Operation", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Tests Operation", JOptionPane.WARNING_MESSAGE);

            }
        }

    }

    public void delete(int last) {

        int i = JOptionPane.showConfirmDialog(null, "Do you Really Want To Delete Last Patholog Test Information ? \n  ", "Tests Operation", JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {

            if (serial.getText().equals("00")) {

                JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Tests Operation", JOptionPane.ERROR_MESSAGE);

            } else {
                try {
                    Statement st = con.createStatement();
                    st.executeUpdate("DELETE FROM pathology_tests  WHERE 	Test_Serial_ID  = '" + serial.getText() + "' ");
                    serial.setText("00");
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    category_Box.setSelectedIndex(0);
                    category_Box.grabFocus();
                    loadTable();

                    JOptionPane.showMessageDialog(null, "Pathology Test Information Deleted From DataBase SuccessFully . . .           \n You May Insert New Tests Information From Add Tests Interface .              ", "Patholgy Lab Software", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Tests Operation", JOptionPane.WARNING_MESSAGE);

                }
            }

        }

    }

    public void loadTable(String databaseColumn, String givenargument) throws SQLException {
        this.model = (DefaultTableModel) pathology_tests_table.getModel();
        pathology_tests_table = new JTable(model);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pathology_tests WHERE " + databaseColumn + " LIKE '%" + givenargument + "%'");

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
            JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Tests Operation", JOptionPane.WARNING_MESSAGE);

        }
    }

    public void insert_test() {
        {

            // TODO add your handling code here:
            Statement st;
            double rate = Double.parseDouble(t4.getText());
            rate = Math.ceil(rate);
            try {
                st = con.createStatement();

                st.executeUpdate("INSERT INTO pathology_tests (Test_category,Test_Name,Test_Range,Test_Unit,Test_Rate) VALUES ('" + category_Box.getSelectedItem().toString() + "','" + t1.getText().trim() + "','" + t2.getText() + "','" + t3.getText() + "','" + rate + "')");
            } catch (Exception ex) {
                Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            }

            serial.setText("00");
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            search.setText("");
            category_Box.setSelectedIndex(0);
            category_Box.grabFocus();
            chk1.setSelected(false);
            chk2.setSelected(false);

            try {
                loadTable();
            } catch (Exception ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "          Pathology Tests  Information is Succesfully Inserted to DataBase     !              ", "Tests Operation", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void update_info() {

        category_Box.setSelectedItem(category_Box.getSelectedItem().toString().trim());
        if (category_Box.getSelectedIndex() == 0 || category_Box.getSelectedItem().toString().isEmpty() || category_Box.getSelectedItem().toString().equals(" ") || category_Box.getSelectedItem().toString().equals("  ")); else {
            Statement st;
            try {

                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tests_catagories WHERE 	Category_Name ='" + category_Box.getSelectedItem().toString() + "'");
                if (rs.next()) ; else {
                    try {
                        Statement stt = con.createStatement();
                        stt.executeUpdate("INSERT INTO  tests_catagories (Category_Name,Category_Code) VALUES ('" + (category_Box.getSelectedItem().toString()) + "','Update Required') ");

                    } catch (SQLException ex) {
                        Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        try {
            Statement st = con.createStatement();
            double rate = Double.parseDouble(t4.getText());
            rate = Math.ceil(rate);
            st.executeUpdate("UPDATE pathology_tests SET  Test_category='" + category_Box.getSelectedItem().toString() + "',Test_Name='" + t1.getText().trim() + "',Test_Range='" + t2.getText() + "',Test_Unit='" + t3.getText() + "',Test_Rate='" + rate + "' WHERE 	Test_Serial_ID   = '" + serial.getText() + "' ");
            serial.setText("00");
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            search.setText("");
            category_Box.setSelectedIndex(0);
            category_Box.grabFocus();
            chk1.setSelected(false);
            chk2.setSelected(false);
            try {
                loadTable();
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "           Tests  Information is Succesfully Updated with New information       !              ", "Tests Operation", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Tests Operation", JOptionPane.WARNING_MESSAGE);

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
        jLabel18 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        chk1 = new javax.swing.JCheckBox();
        chk2 = new javax.swing.JCheckBox();
        serial = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        t2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        t3 = new javax.swing.JTextField();
        category_Box = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        r = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pathology_tests_table = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        deletebtn = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        t4 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel1.setText("Add / Update Tests ");

        jLabel18.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jLabel18.setText("Search Tests ");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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
        chk2.setText("by Category");
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
        jLabel11.setText("Test's  Name");

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

        t2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t2.setSelectionColor(new java.awt.Color(19, 80, 161));
        t2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t2ActionPerformed(evt);
            }
        });
        t2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t2KeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel12.setText("Test's  Ranges");

        jLabel14.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel14.setText("Test's Category");

        jLabel15.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel15.setText("Test's  Unit");

        t3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t3.setSelectionColor(new java.awt.Color(19, 80, 161));
        t3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t3ActionPerformed(evt);
            }
        });
        t3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t3KeyPressed(evt);
            }
        });

        category_Box.setBackground(new java.awt.Color(19, 80, 161));
        category_Box.setEditable(true);
        category_Box.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        category_Box.setBorder(null);
        category_Box.setOpaque(false);
        category_Box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                category_BoxKeyPressed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/insert.png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.setContentAreaFilled(false);
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
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        r.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        r.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
        r.setText("Refresh");
        r.setContentAreaFilled(false);
        r.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        r.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(251, 152, 200));
        jPanel2.setOpaque(false);

        pathology_tests_table.setAutoCreateRowSorter(true);
        pathology_tests_table.setFont(new java.awt.Font("Rockwell", 0, 13)); // NOI18N
        pathology_tests_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Test Serial  ", "Test Category", "Test  Name", "Test Ranges ", "Test Unit", "Test Rate (Rs. )"
            }
        ));
        pathology_tests_table.setGridColor(new java.awt.Color(0, 0, 0));
        pathology_tests_table.setSelectionBackground(new java.awt.Color(19, 80, 161));
        pathology_tests_table.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pathology_tests_tableFocusLost(evt);
            }
        });
        pathology_tests_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pathology_tests_tableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(pathology_tests_table);

        jButton9.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/garbage-bin-png-10496.png"))); // NOI18N
        jButton9.setText("Delete All ");
        jButton9.setBorder(null);
        jButton9.setContentAreaFilled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        deletebtn.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        deletebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteIcon.png"))); // NOI18N
        deletebtn.setText("Delete Test");
        deletebtn.setBorder(null);
        deletebtn.setContentAreaFilled(false);
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton9)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 24, Short.MAX_VALUE))
        );

        jLabel17.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        jLabel17.setText(" Tests  Table");

        jLabel16.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel16.setText("Test's Rate (Rs. )");

        t4.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t4.setSelectionColor(new java.awt.Color(19, 80, 161));
        t4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t4ActionPerformed(evt);
            }
        });
        t4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t4KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t4KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chk2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chk1)
                                .addGap(24, 24, 24)))
                        .addGap(39, 39, 39))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(category_Box, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(serial, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(r))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(r, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(13, 13, 13)))
                        .addGap(41, 41, 41)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chk1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chk2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(serial, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(category_Box, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pathology_tests_tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pathology_tests_tableMouseReleased
        try {
            search.setText("");
            chk1.setSelected(false);
            chk2.setSelected(false);
            int i = pathology_tests_table.rowAtPoint(evt.getPoint());// TODO add your handling code here

            String srl = (String) pathology_tests_table.getValueAt(i, 0);
            serial.setText(srl);

            String cat = (String) pathology_tests_table.getValueAt(i, 1);
            category_Box.setSelectedItem(cat);
            String name = (pathology_tests_table.getValueAt(i, 2)).toString();
            t1.setText(name);

            String range = (String) pathology_tests_table.getValueAt(i, 3);
            t2.setText(range);

            String units = (String) pathology_tests_table.getValueAt(i, 4);
            t3.setText(units);

            String rate = (String) pathology_tests_table.getValueAt(i, 5);
            t4.setText(rate);

            try {
                loadTable();
                // TODO add your handling code here:
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "           Please  Select Only Single Row to Perform Operations  !          ", "Tests Operation", JOptionPane.WARNING_MESSAGE);
            try {
                loadTable();
                // TODO add your handling code here:
            } catch (SQLException ex1) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

    }//GEN-LAST:event_pathology_tests_tableMouseReleased

    private void pathology_tests_tableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pathology_tests_tableFocusLost
        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pathology_tests_tableFocusLost

    private void rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rActionPerformed
        load_cat_box();
        serial.setText("00");
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        search.setText("");
        category_Box.setSelectedIndex(0);
        category_Box.grabFocus();
        chk1.setSelected(false);
        chk2.setSelected(false);
        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_rActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            loadTable();
        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (pathology_tests_table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "All Tests Information Has Already Deleted From The DataBase .                  ", "Tests Operation", JOptionPane.INFORMATION_MESSAGE);

        } else {
            int i = JOptionPane.showConfirmDialog(null, "Do you Really Want To Delete All The Tests Informations       ?  ", "Tests Operation", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                try {
                    Statement st;
                    st = con.createStatement();
                    st.executeUpdate("DELETE FROM pathology_tests");
                    serial.setText("00");
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    search.setText("");
                    category_Box.setSelectedIndex(0);
                    category_Box.grabFocus();
                    chk1.setSelected(false);
                    chk2.setSelected(false);
                    load_cat_box();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "All Tests Information Has Been Deleted From The DataBase SuccessFully. \nYou May Insert New Tests  Information from Add Tests Interface.                 ", "Tests Operation", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else;
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        if (serial.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Tests Operation", JOptionPane.ERROR_MESSAGE);

        } else if (pathology_tests_table.getRowCount() == 1 && (serial.getText().equals("00") == false)) {
            delete(1);
            load_cat_box();
        } else {
            delete();
            load_cat_box();
        }
        // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_deletebtnActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        category_Box.setSelectedItem(category_Box.getSelectedItem().toString().trim());

        if (serial.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Update   . . .                   ", "Tests Operation", JOptionPane.ERROR_MESSAGE);

        } else {

            if (category_Box.getSelectedIndex() == 0 || category_Box.getSelectedItem().toString().isEmpty()) {
                JOptionPane.showMessageDialog(null, "          Please Select Category of the Test (if Not found then enlist Category From Add Category Interface    !              ", "Tests Operation", JOptionPane.ERROR_MESSAGE);
                category_Box.grabFocus();
            } else {

                if (serial.getText().equals("00")) {

                    JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Update   . . .                   ", "Tests Operation", JOptionPane.ERROR_MESSAGE);

                } else {

                    Boolean chk = false;

                    t4.setText(t4.getText().trim());

                    for (int i = 0; i < t4.getText().length(); i++) {
                        if (Character.isDigit(t4.getText().charAt(i)) || t4.getText().charAt(i) == '.'); else {
                            chk = true;
                            break;
                        }

                    }
                    if (chk == true) {
                        JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value for Test Rates   !              ", "Tests Operation", JOptionPane.ERROR_MESSAGE);
                        t4.setText("");
                        t4.grabFocus();
                        chk = true;
                    }
                    if (chk == false) {
                        if (t4.getText().equals("") || check_poiints() == true) {
                            if (t4.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "    Alert Test Rate Missing Please Enter Rates To Proceed   . . .                   ", "Tests Operation", JOptionPane.ERROR_MESSAGE);
                                t4.grabFocus();

                            } else {
                                JOptionPane.showMessageDialog(null, "    Number Format Exception in Tests Rates (Please Correct Tests rates)   . . .                   ", "Tests Operation", JOptionPane.ERROR_MESSAGE);
                                t4.grabFocus();
                            }

                        } else {

                            if (t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("")) {
                                int i = JOptionPane.showConfirmDialog(null, "                               Aleart ! Information Missing    !                            \n\nDo you want to Countinue    ?            ", "Tests Operation", JOptionPane.YES_NO_OPTION);
                                if (i == JOptionPane.YES_OPTION) {
                                    update_info();
                                    load_cat_box();
                                } else {
                                    category_Box.grabFocus();
                                }

                            } else {
                                update_info();
                                load_cat_box();
                            }

                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        category_Box.setSelectedItem(category_Box.getSelectedItem().toString().trim());
        if (category_Box.getSelectedIndex() == 0 || category_Box.getSelectedItem().toString().isEmpty() || category_Box.getSelectedItem().toString().equals(" ") || category_Box.getSelectedItem().toString().equals("  ")); else {
            Statement st;
            try {

                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tests_catagories WHERE 	Category_Name ='" + category_Box.getSelectedItem().toString() + "'");
                if (rs.next()) ; else {
                    try {
                        Statement stt = con.createStatement();
                        stt.executeUpdate("INSERT INTO  tests_catagories (Category_Name,Category_Code) VALUES ('" + (category_Box.getSelectedItem().toString()) + "','Update Required') ");

                    } catch (SQLException ex) {
                        Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (category_Box.getSelectedIndex() == 0 || category_Box.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "          Please Select Category of the Test (if Not found then enlist Category From Add Category Interface    !              ", "Tests Operation", JOptionPane.ERROR_MESSAGE);
            category_Box.grabFocus();

        } else {

            Boolean chk = false;

            t4.setText(t4.getText().trim());

            for (int i = 0; i < t4.getText().length(); i++) {
                if (Character.isDigit(t4.getText().charAt(i)) || t4.getText().charAt(i) == '.'); else {
                    chk = true;
                    break;
                }

            }
            if (chk == true) {
                JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value for Test Rates   !              ", "Tests Operation", JOptionPane.ERROR_MESSAGE);
                t4.setText("");
                t4.grabFocus();
                chk = true;
            }
            if (chk == false) {

                if (t4.getText().equals("") || check_poiints() == true) {
                    if (t4.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "    Alert Test Rate Missing Please Enter Rates To Proceed   . . .                   ", "Tests Operation", JOptionPane.ERROR_MESSAGE);
                        t4.grabFocus();

                    } else {
                        JOptionPane.showMessageDialog(null, "    Number Format Exception in Tests Rates (Please Correct Tests rates)   . . .                   ", "Tests Operation", JOptionPane.ERROR_MESSAGE);
                        t4.grabFocus();
                    }

                } else {

                    if (t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("")) {
                        int i = JOptionPane.showConfirmDialog(null, "           Aleart ! Information Missing    !    \n\nDo you want to Countinue    ?            ", "Tests Operation", JOptionPane.YES_NO_OPTION);
                        if (i == JOptionPane.YES_OPTION) {
                            insert_test();
                            load_cat_box();
                        } else {
                            category_Box.grabFocus();
                        }

                    } else {
                        insert_test();
                        load_cat_box();
                    }
                }
            }
        }

    }//GEN-LAST:event_jButton6ActionPerformed
    public boolean check_poiints() {

        boolean Check = false;
        {
            try {

                double rates = Double.parseDouble(t4.getText());
                Check = false;
            } catch (Exception ex) {
                Check = true;

            }
        }
        return Check;
    }
    private void category_BoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_category_BoxKeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t1.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_category_BoxKeyPressed

    private void t3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t3KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t4.grabFocus();      // TODO add your handling code here:
    }//GEN-LAST:event_t3KeyPressed

    private void t3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t3ActionPerformed

    private void t2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t2KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t3.grabFocus();     // TODO add your handling code here:
    }//GEN-LAST:event_t2KeyPressed

    private void t2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t2ActionPerformed

    private void t1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t1KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t2.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t1KeyPressed

    private void t1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1ActionPerformed

    private void chk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk2ActionPerformed

        chk1.setSelected(false);
        search.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_chk2ActionPerformed

    private void chk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk1ActionPerformed
        chk2.setSelected(false);
        search.grabFocus();
// TODO add your handling code here:
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
        t3.setText("");
        t4.setText("");
        try {
            loadTable();

            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchFocusGained

    private void t4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t4ActionPerformed

    private void t4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t4KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER) {
            category_Box.grabFocus();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_t4KeyPressed

    private void t4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t4KeyTyped

// TODO add your handling code here:
    }//GEN-LAST:event_t4KeyTyped

    private void t4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t4KeyReleased
        if (t4.getText().contains("."));
// TODO add your handling code here:
    }//GEN-LAST:event_t4KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> category_Box;
    private javax.swing.JCheckBox chk1;
    private javax.swing.JCheckBox chk2;
    private javax.swing.JButton deletebtn;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable pathology_tests_table;
    public static javax.swing.JButton r;
    private javax.swing.JTextField search;
    private javax.swing.JLabel serial;
    public static javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    public static javax.swing.JTextField t3;
    public static javax.swing.JTextField t4;
    // End of variables declaration//GEN-END:variables
}
