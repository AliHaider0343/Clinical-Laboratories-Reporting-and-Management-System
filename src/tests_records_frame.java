
import java.awt.Image;
import java.awt.Toolkit;
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
public class tests_records_frame extends javax.swing.JFrame {

    /**
     * Creates new form tests_records_frame
     */
    Connection con;

    public tests_records_frame() {
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\images\\frameicon.png");
        this.setIconImage(icon);
        AutoCompleteDecorator.decorate(category_Box);
        AutoCompleteDecorator.decorate(t1);

        report.setText(record_panel.serial.getText());

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
        category_Box.addItem("Select Category");
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM  tests_catagories");

            while (rs.next()) {
                String element = rs.getString(2);
                category_Box.addItem(element);
            }

        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void set_parameters() {
        String ranges = "";
        String units = "";
        String rates = "";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM  pathology_tests WHERE Test_Name='" + t1.getSelectedItem().toString().trim() + "' AND Test_category='" + category_Box.getSelectedItem().toString().trim() + "'");
            if (rs.next()) {
                ranges = rs.getString(4);
                units = rs.getString(5);
                rates = rs.getString(6);

            }

        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        t2.setText(ranges);
        t3.setText(units);
        t4.setText(rates);
    }

    public void load_tests_Box() {

        t1.removeAllItems();
        t1.addItem("Select Test");
        t1.setSelectedIndex(0);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM  pathology_tests");

            while (rs.next()) {
                String element = rs.getString(3);
                t1.addItem(element);
            }

        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void load_test_box_byt_cat() {

        t1.removeAllItems();
        t1.addItem("Select Test");
        t1.setSelectedIndex(0);
        try {
            Statement st = con.createStatement();
            String cat = category_Box.getSelectedItem().toString().trim();
            ResultSet rs = st.executeQuery("SELECT * FROM  pathology_tests WHERE Test_category='" + cat + "'");

            while (rs.next()) {
                String element = rs.getString(3).toString();

                t1.addItem(element);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception in  Load Tests By Category                          " + ex + "                            ", "Previoes Record (Selected Tests)", JOptionPane.ERROR_MESSAGE);
        }

    }

    DefaultTableModel model;

    public void loadTable() throws SQLException {

        Connection con;

        con = databaseconnetion.get_Connection();
        this.model = (DefaultTableModel) patients_tests_display_table.getModel();
        patients_tests_display_table = new JTable(model);
        model.setRowCount(0);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tests_cart_table WHERE Report_ID = '" + report.getText() + "'");
            while (rs.next()) {
                String[] data = new String[9];
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
                data[5] = rs.getString(6);
                data[6] = rs.getString(7);
                data[7] = rs.getString(8);
                data[8] = rs.getString(9);
                model.addRow(data);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cannot Create Statement Exception Catched    :    " + ex.getMessage() + "      ", "Previoes Record (Selected Tests)", JOptionPane.ERROR_MESSAGE);

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
            JOptionPane.showMessageDialog(null, "             Please Check Any Of the Given CheckBox To Search Accordingly . . .                             ", "Previoes Record (Selected Tests)", JOptionPane.WARNING_MESSAGE);

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

        this.model = (DefaultTableModel) patients_tests_display_table.getModel();
        if (model.getRowCount() == 0) {

            JOptionPane.showMessageDialog(null, "     There is no Data in The Table To Delete   . . .                   ", "Previoes Record (Selected Tests)", JOptionPane.ERROR_MESSAGE);

        } else {

            if (model.getRowCount() > 0) {

                int row = model.getRowCount() - 1;
                int sl = Integer.parseInt(model.getValueAt(row, 2).toString());
                try {
                    Statement st = con.createStatement();
                    st.executeUpdate("DELETE FROM tests_cart_table  WHERE Serial_no  = '" + sl + "' ");
                    serial.setText("00");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    search.setText("");
                    chk1.setSelected(false);
                    chk2.setSelected(false);
                    category_Box.setSelectedIndex(0);
                    t1.setSelectedIndex(0);
                    update_tests_count();

                    category_Box.grabFocus();

                    try {
                        loadTable();        // TODO add your handling code here:
                    } catch (SQLException ex) {
                        Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, " Patholog Test Information Deleted From DataBase SuccessFully . . .           \n          ", "Previoes Record (Selected Tests)", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Previoes Record (Selected Tests)", JOptionPane.WARNING_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(null, "     All Data From The Table is Alaedy Deleted  Already     ", "Previoes Record (Selected Tests)", JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }

    public void update_tests_count() {
        try {
            Statement stt = con.createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM tests_cart_table WHERE Report_ID = '" + report.getText() + "'");
            int counter = 0;
            while (rs.next()) {
                counter++;
            }
            Statement sttt = con.createStatement();
            sttt.executeUpdate("UPDATE patients_reports SET  Total_Tests='" + counter + "' WHERE  Report_Serial_ID  ='" + report.getText() + "' ");

        } catch (SQLException ex) {
            Logger.getLogger(tests_records_frame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update_amount() {
        try {
            Statement stt = con.createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM tests_cart_table WHERE Report_ID = '" + report.getText() + "'");
            double amount = 0;
            while (rs.next()) {
                double amo = Double.parseDouble(rs.getString(9));
                amount += amo;
            }
            Statement sttt = con.createStatement();
            sttt.executeUpdate("UPDATE patients_reports SET  Total_Amount='" + amount + "' WHERE  Report_Serial_ID  ='" + report.getText() + "' ");

        } catch (SQLException ex) {
            Logger.getLogger(tests_records_frame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updates_all_amounts() {
        double total = 0.0;
        double dis = 0.0;
        double PAYED = 0.0;

        try {
            Statement sttt = con.createStatement();
            ResultSet rs = sttt.executeQuery("SELECT * FROM patients_reports WHERE Report_Serial_ID = '" + report.getText() + "'");

            if (rs.next()) {

                total = Double.parseDouble(rs.getString(12));
                dis = Double.parseDouble(rs.getString(13));
                PAYED = Double.parseDouble(rs.getString(15));
            }

            double GTOTAL = total - dis;
            double change = 0.0;
            double remaiming = 0.0;

            if (GTOTAL == PAYED) {
                change = 0.0;
                remaiming = 0.0;
            } else if (GTOTAL > PAYED) {
                change = 0.0;

                remaiming = Math.abs(GTOTAL - PAYED);

            } else if (GTOTAL < PAYED) {
                remaiming = 0.0;
                change = Math.abs(PAYED - GTOTAL);

            }

            Statement stt = con.createStatement();
            stt.executeUpdate("UPDATE patients_reports SET  Grand_total='" + GTOTAL + "' ,change_returned='" + change + "',remaining_amount='" + remaiming + "' WHERE  Report_Serial_ID  ='" + report.getText() + "' ");

        } catch (SQLException ex) {
            Logger.getLogger(tests_records_frame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(int last) {
        this.model = (DefaultTableModel) patients_tests_display_table.getModel();

        int i = JOptionPane.showConfirmDialog(null, "Do you Really Want To Delete Last Patholog Test Information ? \n  ", "Previoes Record (Selected Tests)", JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {

            if (model.getRowCount() == 0) {

                JOptionPane.showMessageDialog(null, "    There is no data int The Table To Delete   . . .                   ", "Previoes Record (Selected Tests)", JOptionPane.ERROR_MESSAGE);

            } else {
                if (model.getRowCount() > 0) {

                    int row = model.getRowCount() - 1;
                    int sl = Integer.parseInt(model.getValueAt(row, 2).toString());
                    try {
                        Statement st = con.createStatement();
                        st.executeUpdate("DELETE FROM tests_cart_table  WHERE 	Serial_no  = '" + sl + "' ");

                        serial.setText("00");
                        t2.setText("");
                        t3.setText("");
                        t4.setText("");
                        t5.setText("");
                        search.setText("");
                        chk1.setSelected(false);
                        chk2.setSelected(false);
                        category_Box.setSelectedIndex(0);
                        t1.setSelectedIndex(0);
                        category_Box.grabFocus();
                        update_tests_count();

                        try {
                            loadTable();        // TODO add your handling code here:
                        } catch (SQLException ex) {
                            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        JOptionPane.showMessageDialog(null, "Pathology Test Information Deleted From DataBase SuccessFully . . .           \n You May Insert New Tests Information From Add Tests Interface .              ", "Previoes Record (Selected Tests)", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Previoes Record (Selected Tests)", JOptionPane.WARNING_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "     All Data From The Table is Alaedy Deleted  Already     ", "Previoes Record (Selected Tests)", JOptionPane.INFORMATION_MESSAGE);
                }

            }

        }

    }

    public void loadTable(String databaseColumn, String givenargument) throws SQLException {
        this.model = (DefaultTableModel) patients_tests_display_table.getModel();
        patients_tests_display_table = new JTable(model);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tests_cart_table WHERE " + databaseColumn + " LIKE '%" + givenargument + "%' AND Report_ID = '" + report.getText() + "'");

            while (rs.next()) {
                String[] data = new String[9];
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
                data[5] = rs.getString(6);
                data[6] = rs.getString(7);
                data[7] = rs.getString(8);
                data[8] = rs.getString(9);

                model.addRow(data);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Previoes Record (Selected Tests)", JOptionPane.WARNING_MESSAGE);

        }
    }

    public void insert_test() {

        // TODO add your handling code here:
        Statement st;
        try {
            int Serial_num = model.getRowCount() + 1;
            st = con.createStatement();
            st.executeUpdate("INSERT INTO tests_cart_table (Report_ID,Serial_no,Test_category,Test_Name,Test_Ranges,Test_Results,Test_Units,Test_Rates) VALUES ('" + report.getText() + "','" + ("" + Serial_num) + "','" + category_Box.getSelectedItem().toString() + "','" + t1.getSelectedItem().toString() + "','" + t2.getText() + "','" + t5.getText() + "','" + t3.getText() + "','" + t4.getText().trim() + "')");
        } catch (Exception ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }

        serial.setText("00");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        search.setText("");
        chk1.setSelected(false);
        chk2.setSelected(false);
        category_Box.setSelectedIndex(0);
        t1.setSelectedIndex(0);
        category_Box.grabFocus();
        update_tests_count();
        r.doClick();

        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            loadTable();
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(null, "           Tests  Information is Succesfully Added in Database       !              ", "Previoes Record (Selected Tests)", JOptionPane.INFORMATION_MESSAGE);

    }

    public void update_info() {

        try {
            Statement st = con.createStatement();

            st.executeUpdate("UPDATE tests_cart_table SET  Test_category='" + category_Box.getSelectedItem().toString() + "',Test_Name='" + t1.getSelectedItem().toString() + "',Test_Ranges='" + t2.getText() + "',Test_Results='" + t5.getText() + "',Test_Units='" + t3.getText() + "',Test_Rates='" + t4.getText().trim() + "' WHERE Serial_no   = '" + serial.getText() + "' AND Report_ID ='" + report.getText() + "' ");
            serial.setText("00");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            search.setText("");
            chk1.setSelected(false);
            chk2.setSelected(false);
            category_Box.setSelectedIndex(0);
            t1.setSelectedIndex(0);
            category_Box.grabFocus();
            update_tests_count();

            r.doClick();
            try {
                loadTable();        // TODO add your handling code here:
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                loadTable();
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "           Tests  Information is Succesfully Updated with New information       !              ", "Previoes Record (Selected Tests)", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Previoes Record (Selected Tests)", JOptionPane.WARNING_MESSAGE);

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

        fresh = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        serial = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        category_Box = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        t2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        t3 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        t4 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        t5 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        patients_tests_display_table = new javax.swing.JTable();
        deletebtn = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        chk1 = new javax.swing.JCheckBox();
        search = new javax.swing.JTextField();
        chk2 = new javax.swing.JCheckBox();
        jLabel19 = new javax.swing.JLabel();
        report = new javax.swing.JLabel();
        report_id = new javax.swing.JLabel();
        r = new javax.swing.JButton();
        t1 = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();

        fresh.setText("jButton1");
        fresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                freshActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tests Information");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 28)); // NOI18N
        jLabel1.setText("Tests Information");

        jLabel13.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel13.setText("Test Serial Number ");

        serial.setFont(new java.awt.Font("Rockwell", 0, 20)); // NOI18N
        serial.setForeground(new java.awt.Color(19, 80, 161));
        serial.setText("00");

        jLabel14.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel14.setText("Test's Category");

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

        jLabel11.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel11.setText("Test's  Name");

        jLabel12.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel12.setText("Test's  Ranges");

        t2.setEditable(false);
        t2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t2.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        t2.setSelectionColor(new java.awt.Color(19, 80, 161));
        t2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t2FocusGained(evt);
            }
        });
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

        jLabel15.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel15.setText("Test's  Unit");

        t3.setEditable(false);
        t3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t3.setSelectedTextColor(new java.awt.Color(0, 0, 0));
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

        jLabel16.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel16.setText("Test's Rate (Rs. )");

        t4.setEditable(false);
        t4.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t4.setSelectedTextColor(new java.awt.Color(0, 0, 0));
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

        jLabel17.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel17.setText("Test's  Result");

        t5.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t5.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        t5.setSelectionColor(new java.awt.Color(19, 80, 161));
        t5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t5FocusGained(evt);
            }
        });
        t5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t5ActionPerformed(evt);
            }
        });
        t5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t5KeyPressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(251, 152, 200));
        jPanel2.setOpaque(false);

        patients_tests_display_table.setAutoCreateRowSorter(true);
        patients_tests_display_table.setFont(new java.awt.Font("Rockwell", 0, 13)); // NOI18N
        patients_tests_display_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Test Cart ID", "Report Serial", "Test Serial  ", "Test Category", "Test  Name", "Test Ranges ", "Test  Result", "Test Unit", "Test Rate (Rs.)"
            }
        ));
        patients_tests_display_table.setGridColor(new java.awt.Color(0, 0, 0));
        patients_tests_display_table.setSelectionBackground(new java.awt.Color(19, 80, 161));
        patients_tests_display_table.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                patients_tests_display_tableFocusLost(evt);
            }
        });
        patients_tests_display_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                patients_tests_display_tableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(patients_tests_display_table);

        deletebtn.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        deletebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteIcon.png"))); // NOI18N
        deletebtn.setText("Delete Latest Test");
        deletebtn.setBorder(null);
        deletebtn.setContentAreaFilled(false);
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addGap(42, 42, 42)
                        .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deletebtn)
                    .addComponent(jButton9))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jButton7.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update1.png"))); // NOI18N
        jButton7.setBorder(null);
        jButton7.setContentAreaFilled(false);
        jButton7.setEnabled(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
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

        search.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        search.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        search.setSelectionColor(new java.awt.Color(251, 152, 200));
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

        chk2.setBackground(new java.awt.Color(255, 255, 255));
        chk2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        chk2.setText("by Category");
        chk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk2ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jLabel19.setText("Search Tests From Database");
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel19.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        report.setFont(new java.awt.Font("Rockwell", 0, 20)); // NOI18N
        report.setForeground(new java.awt.Color(19, 80, 161));
        report.setText("00");

        report_id.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        report_id.setText("Report Number ");

        r.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        r.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
        r.setText("Refresh");
        r.setContentAreaFilled(false);
        r.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rActionPerformed(evt);
            }
        });

        t1.setBackground(new java.awt.Color(19, 80, 161));
        t1.setEditable(true);
        t1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t1.setBorder(null);
        t1.setOpaque(false);
        t1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t1FocusLost(evt);
            }
        });
        t1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                t1MouseReleased(evt);
            }
        });
        t1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ActionPerformed(evt);
            }
        });
        t1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t1KeyTyped(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/insert.png"))); // NOI18N
        jButton8.setBorder(null);
        jButton8.setContentAreaFilled(false);
        jButton8.setEnabled(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel19)))
                        .addGap(265, 265, 265)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(r))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(report_id)
                                .addGap(18, 18, 18)
                                .addComponent(report, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chk1)
                                .addGap(18, 18, 18)
                                .addComponent(chk2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(serial, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel12))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(category_Box, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel17)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel15)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(27, 27, 27)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(r, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chk1)
                            .addComponent(chk2))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(report_id)
                            .addComponent(report))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(serial, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(category_Box, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton7, jButton8});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void category_BoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_category_BoxKeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER) {

            t1.grabFocus();
        }
// TODO add your handling code here:
    }//GEN-LAST:event_category_BoxKeyPressed

    private void t2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t2ActionPerformed

    private void t2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t2KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t5.grabFocus();     // TODO add your handling code here:
    }//GEN-LAST:event_t2KeyPressed

    private void t3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t3ActionPerformed

    private void t3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t3KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t4.grabFocus();      // TODO add your handling code here:
    }//GEN-LAST:event_t3KeyPressed

    private void t4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t4ActionPerformed

    private void t4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t4KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER) {
            category_Box.grabFocus();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_t4KeyPressed

    private void t4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t4KeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_t4KeyReleased

    private void t4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t4KeyTyped

        // TODO add your handling code here:
    }//GEN-LAST:event_t4KeyTyped

    private void t5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t5ActionPerformed

    private void t5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t5KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t3.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t5KeyPressed

    private void patients_tests_display_tableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patients_tests_display_tableFocusLost
        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_patients_tests_display_tableFocusLost

    private void patients_tests_display_tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patients_tests_display_tableMouseReleased
        try {
            search.setText("");
            chk1.setSelected(false);
            chk2.setSelected(false);
            int i = patients_tests_display_table.rowAtPoint(evt.getPoint());// TODO add your handling code here
            String rsrl = (String) patients_tests_display_table.getValueAt(i, 1);
            report.setText(rsrl);

            String srl = (String) patients_tests_display_table.getValueAt(i, 2);
            serial.setText(srl);

            String cat = (String) patients_tests_display_table.getValueAt(i, 3);
            category_Box.setSelectedItem(cat);

            String name = (patients_tests_display_table.getValueAt(i, 4)).toString();
            t1.setSelectedItem(name);

            String range = (String) patients_tests_display_table.getValueAt(i, 5);
            t2.setText(range);

            String res = (String) patients_tests_display_table.getValueAt(i, 6);
            t5.setText(res);
            String units = (String) patients_tests_display_table.getValueAt(i, 7);
            t3.setText(units);

            String rate = (String) patients_tests_display_table.getValueAt(i, 8);
            t4.setText(rate);

            try {
                loadTable();
                // TODO add your handling code here:
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "           Please  Select Only Single Row to Perform Operations  !          ", "Previoes Record (Selected Tests)", JOptionPane.WARNING_MESSAGE);
            try {
                loadTable();
                // TODO add your handling code here:
            } catch (SQLException ex1) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

    }//GEN-LAST:event_patients_tests_display_tableMouseReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        set_parameters();
        t1.setSelectedItem(t1.getSelectedItem().toString().trim());
        if (serial.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Update   . . .                   ", "Previoes Record (Selected Tests)", JOptionPane.ERROR_MESSAGE);

        } else {

            if (category_Box.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "          Please Select Category of the Test (if Not found then enlist Category From Add Category Interface)    !              ", "Previoes Record (Selected Tests)", JOptionPane.ERROR_MESSAGE);
                category_Box.grabFocus();
            } else if (t1.getSelectedIndex() == 0 || t1.getSelectedItem().toString().isEmpty()) {
                JOptionPane.showMessageDialog(null, "          Please Select Tests               ", "Previoes Record (Selected Tests)", JOptionPane.ERROR_MESSAGE);

                t1.grabFocus();
            } else {

                if (serial.getText().equals("00")) {

                    JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Update   . . .                   ", "Previoes Record (Selected Tests)", JOptionPane.ERROR_MESSAGE);

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
                        JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value for Test Rates   !              ", "Previoes Record (Selected Tests)", JOptionPane.ERROR_MESSAGE);
                        t4.setText("");
                        t4.grabFocus();
                        chk = true;
                    }
                    if (chk == false) {
                        if (t4.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "    Alert Test Rate Missing Please Enter Rates To Proceed   . . .                   ", "Previoes Record (Selected Tests)", JOptionPane.ERROR_MESSAGE);
                            t4.grabFocus();
                        } else {

                            if (t1.getSelectedItem().toString().equals("") || t2.getText().equals("") || t3.getText().equals("")) {
                                int i = JOptionPane.showConfirmDialog(null, "                               Aleart ! Information Missing    !                            \n\nDo you want to Countinue    ?            ", "Previoes Record (Selected Tests)", JOptionPane.YES_NO_OPTION);
                                if (i == JOptionPane.YES_OPTION) {
                                    update_info();

                                    update_amount();
                                    updates_all_amounts();
                                } else {
                                    category_Box.grabFocus();
                                }

                            } else {
                                update_info();

                                update_amount();
                                updates_all_amounts();
                            }

                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed

        if (model.getRowCount() == 0) {

            JOptionPane.showMessageDialog(null, "     There is no  data in The Table To Delete   . . .                   ", " Patholgy Lab Software ", JOptionPane.ERROR_MESSAGE);

        } else if (patients_tests_display_table.getRowCount() == 1 && model.getRowCount() > 0) {
            delete(1);
            update_tests_count();
            update_amount();
            updates_all_amounts();
        } else {
            delete();
            update_tests_count();
            update_amount();

            updates_all_amounts();

        }
        // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_deletebtnActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            loadTable();
        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (patients_tests_display_table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "All Tests Information Has Already Deleted From The DataBase .                  ", "Previoes Record (Selected Tests)", JOptionPane.INFORMATION_MESSAGE);

        } else {
            int i = JOptionPane.showConfirmDialog(null, "Do you Really Want To Delete All The Tests Informations       ?  ", "Previoes Record (Selected Tests)", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                try {
                    Statement st;
                    st = con.createStatement();
                    st.executeUpdate("DELETE FROM tests_cart_table WHERE Report_ID='" + report.getText() + "'");
                    serial.setText("00");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    search.setText("");
                    chk1.setSelected(false);
                    chk2.setSelected(false);
                    category_Box.setSelectedIndex(0);
                    t1.setSelectedIndex(0);
                    category_Box.grabFocus();
                    update_tests_count();
                    update_amount();
                    updates_all_amounts();

                    try {
                        loadTable();        // TODO add your handling code here:
                    } catch (SQLException ex) {
                        Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "All Tests Information Has Been Deleted From The DataBase SuccessFully. \nYou May Insert New Tests  Information from Add Reports Interface.                 ", "Previoes Record (Selected Tests)", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else;
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void chk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk1ActionPerformed
        chk2.setSelected(false);
        search.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_chk1ActionPerformed

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
        serial.setText("00");
        t1.setSelectedIndex(0);
        t2.setText("");
        t3.setText("");
        t4.setText("");
        try {
            loadTable();

            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(tests_records_frame.class.getName()).log(Level.SEVERE, null, ex);
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

    private void chk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk2ActionPerformed

        chk1.setSelected(false);
        search.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_chk2ActionPerformed

    private void rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rActionPerformed
        serial.setText("00");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        search.setText("");
        chk1.setSelected(false);
        chk2.setSelected(false);
        category_Box.setSelectedIndex(0);
        t1.setSelectedIndex(0);
        category_Box.grabFocus();
        update_tests_count();
        update_amount();
        updates_all_amounts();

        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_rActionPerformed

    private void t1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t1KeyPressed
// TODO add your handling code here:
    }//GEN-LAST:event_t1KeyPressed

    private void t1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t1FocusLost
        // set_parameters();     // TODO add your handling code here:
    }//GEN-LAST:event_t1FocusLost

    private void t1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t1KeyTyped

// TODO add your handling code here:
    }//GEN-LAST:event_t1KeyTyped

    private void t1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_t1MouseClicked

    private void t2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t2FocusGained
        set_parameters();  // TODO add your handling code here:
    }//GEN-LAST:event_t2FocusGained

    private void category_BoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_category_BoxFocusLost

//       refresher.doClick(); // TODO add your handling code here:
    }//GEN-LAST:event_category_BoxFocusLost

    private void t1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t1FocusGained

        // TODO add your handling code here:
    }//GEN-LAST:event_t1FocusGained

    private void t1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t1MouseReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_t1MouseReleased

    private void freshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_freshActionPerformed
        serial.setText("00");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        search.setText("");
        chk1.setSelected(false);
        chk2.setSelected(false);
        update_tests_count();

        load_cat_box();
        category_Box.setSelectedIndex(0);

        category_Box.grabFocus();

        update_amount();
        load_tests_Box();
        updates_all_amounts();
        load_test_box_byt_cat();

        t1.setSelectedIndex(0);

        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }             // TODO add your handling code here:
    }//GEN-LAST:event_freshActionPerformed

    private void t1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1ActionPerformed

    private void category_BoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_category_BoxActionPerformed
        load_test_box_byt_cat();           // TODO add your handling code here:
    }//GEN-LAST:event_category_BoxActionPerformed

    private void t5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t5FocusGained
        set_parameters();         // TODO add your handling code here:
    }//GEN-LAST:event_t5FocusGained

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        record_panel.load.doClick();
// TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        set_parameters();
        t1.setSelectedItem(t1.getSelectedItem().toString().trim());

        if (category_Box.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "          Please Select Category of the Test (if Not found then enlist Category From Add Category Interface)    !              ", "Previoes Record (Selected Tests)", JOptionPane.ERROR_MESSAGE);
            category_Box.grabFocus();
        } else if (t1.getSelectedIndex() == 0 || t1.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "          Please Select Tests               ", "Previoes Record (Selected Tests)", JOptionPane.ERROR_MESSAGE);

            t1.grabFocus();
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
                JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value for Test Rates   !              ", "Previoes Record (Selected Tests)", JOptionPane.ERROR_MESSAGE);
                t4.setText("");
                t4.grabFocus();
                chk = true;
            }
            if (chk == false) {
                if (t4.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "    Alert Test Rate Missing Please Enter Rates To Proceed   . . .                   ", "Previoes Record (Selected Tests)", JOptionPane.ERROR_MESSAGE);
                    t4.grabFocus();
                } else {

                    if (t1.getSelectedItem().toString().equals("") || t2.getText().equals("") || t3.getText().equals("")) {
                        int i = JOptionPane.showConfirmDialog(null, "                               Aleart ! Information Missing    !                            \n\nDo you want to Countinue    ?            ", "Previoes Record (Selected Tests)", JOptionPane.YES_NO_OPTION);
                        if (i == JOptionPane.YES_OPTION) {
                            insert_test();

                            update_amount();
                            updates_all_amounts();
                        } else {
                            category_Box.grabFocus();
                        }

                    } else {
                        insert_test();

                        update_amount();
                        updates_all_amounts();

                    }

                }
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(tests_records_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tests_records_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tests_records_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tests_records_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tests_records_frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> category_Box;
    private javax.swing.JCheckBox chk1;
    private javax.swing.JCheckBox chk2;
    private javax.swing.JButton deletebtn;
    public static javax.swing.JButton fresh;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable patients_tests_display_table;
    public static javax.swing.JButton r;
    public static javax.swing.JLabel report;
    private javax.swing.JLabel report_id;
    private javax.swing.JTextField search;
    public static javax.swing.JLabel serial;
    public static javax.swing.JComboBox<String> t1;
    private javax.swing.JTextField t2;
    public static javax.swing.JTextField t3;
    public static javax.swing.JTextField t4;
    private javax.swing.JTextField t5;
    // End of variables declaration//GEN-END:variables
}
