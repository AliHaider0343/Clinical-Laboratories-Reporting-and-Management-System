
import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
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
public class record_panel extends javax.swing.JPanel {

    /**
     * Creates new form record_panel
     */
    Connection con;
    int val = 0;

    public record_panel() {
        initComponents();
        AutoCompleteDecorator.decorate(t7);
        AutoCompleteDecorator.decorate(t5);

        con = databaseconnetion.get_Connection();

        t1.grabFocus();
        try {
            loadTable();
        } catch (SQLException ex) {
            Logger.getLogger(record_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void suggest() {
        t5.removeAllItems();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM  suggestions");
            t5.addItem("Patient Address");
            while (rs.next()) {
                String element = rs.getString(2);
                t5.addItem(element);
            }

        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void load_cat_box() {

        t7.removeAllItems();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM  reference_doctors");

            t7.addItem("Self");
            while (rs.next()) {
                String element = rs.getString(2);
                t7.addItem(element);
            }

        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    DefaultTableModel model;

    public void loadTable() throws SQLException {
        Connection con;

        con = databaseconnetion.get_Connection();
        this.model = (DefaultTableModel) patients_records_table.getModel();
        patients_records_table = new JTable(model);
        model.setRowCount(0);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patients_reports");
            while (rs.next()) {
                String[] data = new String[17];
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
                data[5] = rs.getString(6);
                data[6] = rs.getString(7);
                data[7] = rs.getString(8);
                data[8] = rs.getString(9);
                data[9] = rs.getString(10);
                data[10] = rs.getString(11);
                data[11] = rs.getString(12);
                data[12] = rs.getString(13);
                data[13] = rs.getString(14);
                data[14] = rs.getString(15);
                data[15] = rs.getString(16);
                data[16] = rs.getString(17);
                model.addRow(data);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cannot Create Statement Exception Catched    :    " + ex.getMessage() + "      ", "Pathology Lab Software ", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void update() {
        String date = "";
        String time = "";
        String name_title = "";
        t14.setText(t14.getText().trim());
        t15.setText(t15.getText().trim());
        if (t1.getText().startsWith("Mr", 0) || t1.getText().startsWith("Ms", 0) || t1.getText().startsWith("MR", 0) || t1.getText().startsWith("MS", 0) || t1.getText().startsWith("mR", 0) || t1.getText().startsWith("mS", 0)) {
            name_title = "";
        } else {
            if (t3.getSelectedItem().toString().equals("Male")) {

                name_title = "Mr ";
            } else if (t3.getSelectedItem().toString().equals("Female")) {
                name_title = "Ms ";
            }

        }

        if (t14.getText().equals("") && t15.getText().equals("") || (t14.getText().equals(" ") && t15.getText().equals(" "))) {
            time = Home.timer.getText();
            date = Home.date1.getText();
        } else if (t14.getText().equals("") || t14.getText().equals(" ")) {
            date = Home.date1.getText();
            time = t15.getText();
        } else if (t15.getText().equals("") || t15.getText().equals(" ")) {
            time = Home.timer.getText();
            date = t14.getText();
        } else {
            time = t15.getText();
            date = t14.getText();
        }
        String dr_title = "";
        try {

            if (t7.getSelectedItem().toString().equals("Self")); else {
                if (t7.getSelectedItem().toString().startsWith("Dr", 0) || t7.getSelectedItem().toString().startsWith("dr", 0) || t7.getSelectedItem().toString().startsWith("dR", 0) || t7.getSelectedItem().toString().startsWith("DR", 0)) {
                    dr_title = "";
                } else {
                    dr_title = "Dr ";
                }
            }
            pay();
            Statement st = con.createStatement();

            st.executeUpdate("UPDATE patients_reports SET  Patient_Name='" + (name_title + t1.getText()) + "',Age='" + t2.getText() + "',Sex='" + t3.getSelectedItem().toString() + "',Phone_Number='" + t4.getText() + "',Address='" + t5.getSelectedItem().toString() + "',Specimen='" + t6.getText().toString() + "',Referred_By='" + ((dr_title + t7.getSelectedItem().toString())) + "',Reporting_Date='" + date + "',Reporting_Time='" + time + "',Total_Tests='" + t8.getText() + "',Total_Amount='" + t9.getText() + "',discount='" + t10.getText() + "',Grand_total='" + t16.getText() + "',Payed_Amount='" + t11.getText() + "',change_returned='" + t12.getText() + "',remaining_amount='" + t13.getText() + "' WHERE Report_Serial_ID ='" + serial.getText() + "'");

            serial.setText("00");
            t1.setText("");
            t2.setText("");
            t3.setSelectedIndex(0);
            t4.setText("");
            t5.setSelectedIndex(0);
            t6.setText("");
            t7.setSelectedIndex(0);
            t8.setText("");
            t9.setText("");
            t10.setText("");
            t11.setText("");
            t12.setText("");
            t13.setText("");
            t14.setText("");
            t15.setText("");
            t16.setText("");
            search.setText("");
            suggest();
            load_cat_box();
            t1.grabFocus();
            chk1.setSelected(false);
            chk2.setSelected(false);
            try {
                loadTable();        // TODO add your handling code here:
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "           Patient Information is Succesfully Updated with New information       !              ", "Previous Records", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Previous Records", JOptionPane.WARNING_MESSAGE);

        }

    }

    public void save() {
        String date = "";
        String time = "";
        String name_title = "";
        t14.setText(t14.getText().trim());
        t15.setText(t15.getText().trim());
        if (t1.getText().startsWith("Mr", 0) || t1.getText().startsWith("Ms", 0) || t1.getText().startsWith("MR", 0) || t1.getText().startsWith("MS", 0) || t1.getText().startsWith("mR", 0) || t1.getText().startsWith("mS", 0)) {
            name_title = "";
        } else {
            if (t3.getSelectedItem().toString().equals("Male")) {

                name_title = "Mr ";
            } else if (t3.getSelectedItem().toString().equals("Female")) {
                name_title = "Ms ";
            }

        }

        if (t14.getText().equals("") && t15.getText().equals("") || (t14.getText().equals(" ") && t15.getText().equals(" "))) {
            time = Home.timer.getText();
            date = Home.date1.getText();
        } else if (t14.getText().equals("") || t14.getText().equals(" ")) {
            date = Home.date1.getText();
            time = t15.getText();
        } else if (t15.getText().equals("") || t15.getText().equals(" ")) {
            time = Home.timer.getText();
            date = t14.getText();
        } else {
            time = t15.getText();
            date = t14.getText();
        }
        String dr_title = "";

        if (t7.getSelectedItem().toString().equals("Self")); else {
            if (t7.getSelectedItem().toString().startsWith("Dr", 0) || t7.getSelectedItem().toString().startsWith("dr", 0) || t7.getSelectedItem().toString().startsWith("dR", 0) || t7.getSelectedItem().toString().startsWith("DR", 0)) {
                dr_title = "";
            } else {
                dr_title = "Dr ";
            }
        }
        Statement st;
        try {
            st = con.createStatement();

            st.executeUpdate("INSERT INTO patients_reports (Patient_Name,Age,Sex,Phone_Number,Address,Specimen,Referred_By,Reporting_Date,Reporting_Time,Total_Tests,Total_Amount,discount,Grand_total,Payed_Amount,change_returned,remaining_amount) VALUES ('" + (name_title + t1.getText()) + "','" + t2.getText() + "','" + (t3.getSelectedItem().toString()) + "','" + t4.getText() + "','" + t5.getSelectedItem().toString() + "','" + (t6.getText().toString()) + "','" + ((dr_title + t7.getSelectedItem().toString())) + "','" + (date) + "','" + (time) + "','" + t8.getText() + "','" + t9.getText() + "','" + t10.getText() + "','" + t16.getText() + "','" + t11.getText() + "','" + t12.getText() + "','" + t13.getText() + "')");
        } catch (Exception ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
        serial.setText("00");
        t1.setText("");
        t2.setText("");
        t3.setSelectedIndex(0);
        t4.setText("");
        t5.setSelectedIndex(0);
        t6.setText("");
        t7.setSelectedIndex(0);
        t8.setText("");
        t9.setText("");
        t10.setText("");
        t11.setText("");
        t12.setText("");
        t13.setText("");
        t14.setText("");
        t15.setText("");
        t16.setText("");
        search.setText("");
        suggest();
        load_cat_box();
        t1.grabFocus();
        chk1.setSelected(false);
        chk2.setSelected(false);
        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "          Patient Information is Succesfully Inserted to DataBase     !              ", "Previous Records", JOptionPane.INFORMATION_MESSAGE);

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
            JOptionPane.showMessageDialog(null, "             Please Check Any Of the Given CheckBox To Search Accordingly . . .                             ", "Previous Records", JOptionPane.WARNING_MESSAGE);

        } else if (chk1.isSelected()) {
            str = search.getText();

            try {
                loadTable("Patient_Name", str);
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (chk2.isSelected()) {
            str = search.getText();

            try {
                loadTable("Report_Serial_ID ", str);
            } catch (SQLException ex) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void delete() {
        if (serial.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Previous Records", JOptionPane.ERROR_MESSAGE);

        } else {
            try {
                Statement st = con.createStatement();
                st.executeUpdate("DELETE FROM patients_reports  WHERE Report_Serial_ID  = '" + serial.getText() + "' ");

                Statement st1 = con.createStatement();
                st1.executeUpdate("DELETE FROM tests_cart_table  WHERE Report_ID  = '" + serial.getText() + "' ");
                Statement st12 = con.createStatement();
                st12.executeUpdate("DELETE FROM individual_tests  WHERE Report_ID  = '" + serial.getText() + "' ");

                serial.setText("00");
                t1.setText("");
                t2.setText("");
                t3.setSelectedIndex(0);
                t4.setText("");
                t5.setSelectedIndex(0);
                t6.setText("");
                t7.setSelectedIndex(0);
                t8.setText("");
                t9.setText("");
                t10.setText("");
                t11.setText("");
                t12.setText("");
                t13.setText("");
                t14.setText("");
                t16.setText("");
                suggest();
                load_cat_box();
                t15.setText("");
                search.setText("");
                t1.grabFocus();
                chk1.setSelected(false);
                chk2.setSelected(false);
                try {
                    loadTable();        // TODO add your handling code here:
                } catch (SQLException ex) {
                    Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, " Patient Information Deleted From DataBase SuccessFully . . .           \n          ", "Previous Records", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Previous Records", JOptionPane.WARNING_MESSAGE);

            }
        }

    }

    public void delete(int last) {

        int i = JOptionPane.showConfirmDialog(null, "Do you Really Want To Delete Last Patient Information ? \n  ", "Previous Records", JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {

            if (serial.getText().equals("00")) {

                JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Previous Records", JOptionPane.ERROR_MESSAGE);

            } else {
                try {
                    Statement st = con.createStatement();
                    st.executeUpdate("DELETE FROM patients_reports  WHERE Report_Serial_ID = '" + serial.getText() + "' ");
                    Statement st1 = con.createStatement();
                    st1.executeUpdate("DELETE FROM tests_cart_table  WHERE Report_ID  = '" + serial.getText() + "' ");
                    Statement st12 = con.createStatement();
                    st12.executeUpdate("DELETE FROM individual_tests  WHERE Report_ID  = '" + serial.getText() + "' ");

                    serial.setText("00");
                    t1.setText("");
                    t2.setText("");
                    t3.setSelectedIndex(0);
                    t4.setText("");
                    t5.setSelectedIndex(0);
                    t6.setText("");
                    t7.setSelectedIndex(0);
                    t8.setText("");
                    t9.setText("");
                    t10.setText("");
                    t11.setText("");
                    t12.setText("");
                    t13.setText("");
                    t14.setText("");
                    t16.setText("");
                    suggest();
                    load_cat_box();
                    t15.setText("");
                    search.setText("");
                    t1.grabFocus();
                    chk1.setSelected(false);
                    chk2.setSelected(false);
                    try {
                        loadTable();        // TODO add your handling code here:
                    } catch (SQLException ex) {
                        Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JOptionPane.showMessageDialog(null, "Last Patient Information Deleted From DataBase SuccessFully . . .           \n You May Insert New Patients Inforamtion form ADD reports Panel            ", "Previous Records", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Previous Records", JOptionPane.WARNING_MESSAGE);

                }
            }

        }

    }

    public void loadTable(String databaseColumn, String givenargument) throws SQLException {
        this.model = (DefaultTableModel) patients_records_table.getModel();
        patients_records_table = new JTable(model);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patients_reports WHERE Binary " + databaseColumn + " LIKE Binary '%" + givenargument + "%'");

            while (rs.next()) {
                String[] data = new String[17];
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
                data[5] = rs.getString(6);
                data[6] = rs.getString(7);
                data[7] = rs.getString(8);
                data[8] = rs.getString(9);
                data[9] = rs.getString(10);
                data[10] = rs.getString(11);
                data[11] = rs.getString(12);
                data[12] = rs.getString(13);
                data[13] = rs.getString(14);
                data[14] = rs.getString(15);
                data[15] = rs.getString(16);
                data[16] = rs.getString(17);
                model.addRow(data);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Previous Records", JOptionPane.WARNING_MESSAGE);

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

        load = new javax.swing.JButton();
        mouse_events = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        t9 = new javax.swing.JTextField();
        t1 = new javax.swing.JTextField();
        t4 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        t11 = new javax.swing.JTextField();
        t12 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        t5 = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        t8 = new javax.swing.JTextField();
        serial = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        t16 = new javax.swing.JTextField();
        t7 = new javax.swing.JComboBox<>();
        t2 = new javax.swing.JTextField();
        t10 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        t3 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        t6 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        patients_records_table = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        refresh_record = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        chk2 = new javax.swing.JCheckBox();
        chk1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        t13 = new javax.swing.JTextField();
        t14 = new javax.swing.JTextField();
        t15 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        load.setText("jButton1");
        load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadActionPerformed(evt);
            }
        });

        mouse_events.setText("jButton1");
        mouse_events.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mouse_eventsActionPerformed(evt);
            }
        });

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(243, 243, 243));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 686));

        jPanel4.setBackground(new java.awt.Color(243, 243, 243));
        jPanel4.setPreferredSize(new java.awt.Dimension(968, 600));

        jLabel19.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel19.setText("Specimen");

        t9.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t9.setSelectionColor(new java.awt.Color(19, 80, 161));
        t9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                t9FocusLost(evt);
            }
        });
        t9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t9ActionPerformed(evt);
            }
        });
        t9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t9KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t9KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t9KeyTyped(evt);
            }
        });

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

        t4.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t4.setSelectionColor(new java.awt.Color(19, 80, 161));
        t4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t4KeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel15.setText("Phone No ");

        jLabel12.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel12.setText("Age");

        t11.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t11.setSelectionColor(new java.awt.Color(19, 80, 161));
        t11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                t11FocusLost(evt);
            }
        });
        t11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t11KeyPressed(evt);
            }
        });

        t12.setEditable(false);
        t12.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t12.setSelectionColor(new java.awt.Color(19, 80, 161));
        t12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t12KeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel11.setText("Patient's Name");

        jLabel14.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel14.setText("Gender");

        jLabel27.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel27.setText("Change");

        t5.setBackground(new java.awt.Color(19, 80, 161));
        t5.setEditable(true);
        t5.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Local" }));
        t5.setBorder(null);
        t5.setOpaque(false);
        t5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                t5FocusLost(evt);
            }
        });
        t5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t5KeyPressed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel25.setText("Discount");

        t8.setEditable(false);
        t8.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t8.setSelectionColor(new java.awt.Color(19, 80, 161));
        t8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t8KeyPressed(evt);
            }
        });

        serial.setFont(new java.awt.Font("Rockwell", 0, 20)); // NOI18N
        serial.setForeground(new java.awt.Color(19, 80, 161));
        serial.setText("00");

        jLabel13.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel13.setText("Report Number ");

        jLabel28.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel28.setText("Payed Amount");

        t16.setEditable(false);
        t16.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t16.setSelectionColor(new java.awt.Color(19, 80, 161));
        t16.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                t16FocusLost(evt);
            }
        });
        t16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t16KeyPressed(evt);
            }
        });

        t7.setBackground(new java.awt.Color(19, 80, 161));
        t7.setEditable(true);
        t7.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Referred By" }));
        t7.setBorder(null);
        t7.setOpaque(false);
        t7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t7KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t7KeyReleased(evt);
            }
        });

        t2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t2.setSelectionColor(new java.awt.Color(19, 80, 161));
        t2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t2KeyPressed(evt);
            }
        });

        t10.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t10.setSelectionColor(new java.awt.Color(19, 80, 161));
        t10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                t10FocusLost(evt);
            }
        });
        t10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t10ActionPerformed(evt);
            }
        });
        t10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t10KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t10KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t10KeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel16.setText("Address");

        jLabel29.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel29.setText("Grand Total");

        jLabel24.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel24.setText("Total Amount");

        t3.setBackground(new java.awt.Color(19, 80, 161));
        t3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Gender", "Male", "Female" }));
        t3.setBorder(null);
        t3.setOpaque(false);
        t3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t3KeyReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel23.setText("Total Tests");

        jLabel18.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel18.setText("Referred By");

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

        jPanel2.setBackground(new java.awt.Color(251, 152, 200));
        jPanel2.setOpaque(false);

        patients_records_table.setAutoCreateRowSorter(true);
        patients_records_table.setFont(new java.awt.Font("Rockwell", 0, 13)); // NOI18N
        patients_records_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Rep No ", " Name", "Age", "Sex", "Phone", "Address", "Specimen", "Ref By", " Date", " Time", "T Tests", "Amount", "Discount", "G Total", "Payed", "Change", "Bal"
            }
        ));
        patients_records_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        patients_records_table.setGridColor(new java.awt.Color(0, 0, 0));
        patients_records_table.setSelectionBackground(new java.awt.Color(19, 80, 161));
        patients_records_table.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                patients_records_tableFocusLost(evt);
            }
        });
        patients_records_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                patients_records_tableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(patients_records_table);

        jButton3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteIcon.png"))); // NOI18N
        jButton3.setText("Delete Selected");
        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/garbage-bin-png-10496.png"))); // NOI18N
        jButton4.setText("Delete All");
        jButton4.setBorder(null);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        refresh_record.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        refresh_record.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/re.png"))); // NOI18N
        refresh_record.setBorder(null);
        refresh_record.setContentAreaFilled(false);
        refresh_record.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        refresh_record.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh_recordActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/up.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pr.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vt.png"))); // NOI18N
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(refresh_record, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addGap(71, 71, 71)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(refresh_record, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))))
        );

        jLabel17.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jLabel17.setText("Search Report From Database");
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

        chk2.setBackground(new java.awt.Color(243, 243, 243));
        chk2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        chk2.setText("by Report ID");
        chk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk2ActionPerformed(evt);
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

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 28)); // NOI18N
        jLabel1.setText("Previous Record");

        t13.setEditable(false);
        t13.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t13.setSelectionColor(new java.awt.Color(19, 80, 161));
        t13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t13KeyPressed(evt);
            }
        });

        t14.setEditable(false);
        t14.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t14.setSelectionColor(new java.awt.Color(19, 80, 161));
        t14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t14KeyPressed(evt);
            }
        });

        t15.setEditable(false);
        t15.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t15.setSelectionColor(new java.awt.Color(19, 80, 161));
        t15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t15KeyPressed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel26.setText("Remaining  ");

        jLabel21.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel21.setText("Reporting Date   ");

        jLabel20.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel20.setText("Reporting Time  ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel16)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(t1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(t2)
                            .addComponent(t3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t5, 0, 164, Short.MAX_VALUE)
                            .addComponent(t4)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(28, 28, 28)
                        .addComponent(serial, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addGap(41, 41, 41)
                            .addComponent(t16))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel25)
                                .addComponent(jLabel24)
                                .addComponent(jLabel23)
                                .addComponent(jLabel19)
                                .addComponent(jLabel27)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28)
                                .addComponent(jLabel26)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(8, 8, 8)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(t15)
                                .addComponent(t14)
                                .addComponent(t13)
                                .addComponent(t11, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(t12)
                                .addComponent(t9)
                                .addComponent(t10)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(t8, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(chk2)
                                .addGap(15, 15, 15)
                                .addComponent(chk1))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chk2)
                            .addComponent(chk1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(serial))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(t8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(t9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(t10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel29)
                                    .addComponent(t16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28)
                                    .addComponent(t11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(t12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {t3, t5});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 961, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refresh_recordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_recordActionPerformed
        serial.setText("00");
        t1.setText("");
        t2.setText("");
        t3.setSelectedIndex(0);
        t4.setText("");
        t5.setSelectedIndex(0);
        t6.setText("");
        t7.setSelectedIndex(0);
        t8.setText("");
        t9.setText("");
        t10.setText("");
        t11.setText("");
        t12.setText("");
        t13.setText("");
        t14.setText("");
        t15.setText("");
        t16.setText("");

        search.setText("");
        t1.grabFocus();
        chk1.setSelected(false);
        chk2.setSelected(false);
        load_cat_box();
        suggest();
        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_refresh_recordActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        /*if(t6.getSelectedIndex()==0)
        {
            JOptionPane.showMessageDialog(null,"           Please Fill The Required Specimen Fields   !              ","Patholgy Lab Software",JOptionPane.ERROR_MESSAGE);
            t6.grabFocus();
        }
        else if(t1.getText().equals("")||t2.getText().equals("")||t4.getText().equals("")||t5.getText().equals("")||(t6.getSelectedIndex()==0)||t7.getText().equals("")||t8.getText().equals("")||t9.getText().equals("")||t10.getText().equals("")||t11.getText().equals("")||t12.getText().equals("")||t13.getText().equals(""))
        {

            int i= JOptionPane.showConfirmDialog(null,"                               Aleart ! Information Missing    !                            \n\nDo you want to Countinue    ?            ","Patholgy Lab Software",JOptionPane.YES_NO_OPTION);
            if(i==JOptionPane.YES_OPTION)
            {
                save();
            }
            else
            {
                t1.grabFocus();
            }

        }
        else
        {

            save();
        }    */
        val = Integer.parseInt(serial.getText());
        new tests_records_frame().setVisible(true);
        tests_records_frame.fresh.doClick();
        tests_records_frame.category_Box.grabFocus();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            loadTable();
        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (patients_records_table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "All Patients Information Has Already Deleted From The DataBase .                  ", "Previous Records", JOptionPane.INFORMATION_MESSAGE);

        } else {
            int i = JOptionPane.showConfirmDialog(null, "Do you Really Want To Delete All The Patients  Informations     ?  ", "Previous Records", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                try {
                    Statement st;
                    st = con.createStatement();
                    st.executeUpdate("DELETE FROM patients_reports");
                    Statement st1 = con.createStatement();
                    st1.executeUpdate("DELETE FROM tests_cart_table ");
                    Statement st12 = con.createStatement();
                    st12.executeUpdate("DELETE FROM individual_tests   ");

                    serial.setText("00");
                    t1.setText("");
                    t2.setText("");
                    t3.setSelectedIndex(0);
                    t4.setText("");
                    t5.setSelectedIndex(0);
                    t6.setText("");
                    t7.setSelectedIndex(0);
                    t8.setText("");
                    t9.setText("");
                    t10.setText("");
                    t16.setText("");

                    suggest();
                    load_cat_box();
                    t11.setText("");
                    t12.setText("");
                    t13.setText("");
                    t14.setText("");
                    t15.setText("");
                    search.setText("");
                    t1.grabFocus();
                    chk1.setSelected(false);
                    chk2.setSelected(false);
                    try {
                        loadTable();        // TODO add your handling code here:
                    } catch (SQLException ex) {
                        Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "All Patients Information  Has Been Deleted From The DataBase SuccessFully. \nYou May Insert Patients Inforamtion from Add reports Interface               ", "Previous Records", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (serial.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Previous Records", JOptionPane.ERROR_MESSAGE);

        } else if (patients_records_table.getRowCount() == 1 && (t1.getText().equals("00") == false)) {
            delete(1);
        } else {
            delete();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
    public void pay() {
        t10.setText(t10.getText().trim());
        String text = t10.getText();
        if (t10.getText().equals("")) {
            t10.setText("0.0");
        } else {
            if (isvlaid(text) == true) {
                JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Previous Records", JOptionPane.ERROR_MESSAGE);
                t10.setText("");
                t10.grabFocus();

            }// TODO add your handling code here:
            else if (isvlaid(text) == false) {
                try {
                    double dis;
                    double total;
                    t10.setText(t10.getText().toString().trim());
                    t9.setText(t9.getText().toString().trim());

                    if (t10.getText().isEmpty()) {
                        dis = 0.0;
                    } else {
                        dis = Double.parseDouble(t10.getText());
                    }

                    if (t9.getText().isEmpty()) {
                        total = 0.0;
                    } else {
                        total = Double.parseDouble(t9.getText());
                    }

                    t16.setText("" + (total - dis));

                    double GTOTAL;
                    double PAYED;
                    t16.setText(t16.getText().toString().trim());
                    t11.setText(t11.getText().toString().trim());

                    if (t16.getText().isEmpty()) {
                        GTOTAL = 0.0;
                    } else {
                        GTOTAL = Double.parseDouble(t16.getText());
                    }

                    if (t11.getText().isEmpty()) {
                        PAYED = 0.0;
                    } else {
                        PAYED = Double.parseDouble(t11.getText());
                    }

                    if (GTOTAL == PAYED) {
                        t12.setText("0.0");
                        t13.setText("0.0");
                    } else if (GTOTAL > PAYED) {
                        t12.setText("0.0");
                        t13.setText("" + Math.abs(GTOTAL - PAYED));
                    } else if (GTOTAL < PAYED) {
                        t13.setText("0.0");
                        t12.setText("" + Math.abs(PAYED - GTOTAL));
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Previous Records", JOptionPane.ERROR_MESSAGE);

                }
            }
        }

    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        t5.setSelectedItem(t5.getSelectedItem().toString().trim());
        if (t5.getSelectedItem().toString().isEmpty() || t5.getSelectedItem().toString().equals(" ") || t5.getSelectedItem().toString().equals("  ")); else {
            Statement st;
            try {

                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM suggestions WHERE word ='" + t5.getSelectedItem().toString() + "'");
                if (rs.next())
        ; else {
                    try {
                        Statement stt = con.createStatement();
                        stt.executeUpdate("INSERT INTO  suggestions (word) VALUES ('" + (t5.getSelectedItem().toString()) + "') ");

                    } catch (SQLException ex) {
                        Logger.getLogger(record_panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(record_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        t7.setSelectedItem(t7.getSelectedItem().toString().trim());

        if (t7.getSelectedItem().toString().isEmpty() || t7.getSelectedItem().toString().equals(" ") || t7.getSelectedItem().toString().equals("  ")); else {
            Statement st;

            try {
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM reference_doctors WHERE Doctor_Name ='" + t7.getSelectedItem().toString() + "'");
                if (rs.next()) ; else {
                    String name_title = "";
                    if (t7.getSelectedItem().toString().equals("Self")); else {
                        if (t7.getSelectedItem().toString().startsWith("Dr", 0) || t7.getSelectedItem().toString().startsWith("dr", 0) || t7.getSelectedItem().toString().startsWith("dR", 0) || t7.getSelectedItem().toString().startsWith("DR", 0)) {
                            name_title = "";
                        } else {
                            name_title = "Dr ";
                        }

                        Statement stt;
                        try {
                            stt = con.createStatement();
                            stt.executeUpdate("INSERT INTO reference_doctors (Doctor_Name,Speicalization,Sex,Hospital_Name,Phone_Number) VALUES ('" + (name_title + t7.getSelectedItem().toString()) + "','Update Required','Select Gender','Update Required','Update Required')");

                        } catch (Exception ex) {
                            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(record_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        t11.setText(t11.getText().trim());
        String text1 = t11.getText();

        t10.setText(t10.getText().trim());
        String text2 = t10.getText();

        t9.setText(t9.getText().trim());
        String text3 = t9.getText();

        boolean chk1 = isvlaid(text1);
        boolean chk2 = isvlaid(text2);
        boolean chk3 = isvlaid(text3);

        if (serial.getText().equals("00")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select Some Data Row From The Table To Delete   . . .                   ", "Previous Records", JOptionPane.ERROR_MESSAGE);

        } else {

            t6.setText(t6.getText().trim());
            if (t6.getText().isEmpty() || t6.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "           Please Fill The Required Specimen Fields   !              ", "Previous Records", JOptionPane.ERROR_MESSAGE);
                t6.grabFocus();
            } else if (chk1 == true || chk2 == true || chk3 == true) {
                String field = "";

                if (chk1 == true) {
                    field = "Payed Amount";

                } else if (chk2 == true) {
                    field = "Discount";

                } else if (chk3 == true) {
                    field = "Total Amount";

                }

                JOptionPane.showMessageDialog(null, "           (    Please Correct   " + field + "    )   !              ", "Previous Records", JOptionPane.WARNING_MESSAGE);
                t6.setText(t6.getText().trim());

                if (chk1 == true) {
                    t11.grabFocus();

                } else if (chk2 == true) {
                    t10.grabFocus();

                } else if (chk3 == true) {
                    t9.grabFocus();
                }
            } else if (t1.getText().equals("") || t2.getText().equals("") || t4.getText().equals("") || t5.getSelectedItem().equals("") || (t6.getText().equals("")) || t6.getText().isEmpty() || (t7.getSelectedItem().toString().equals("")) || (t7.getSelectedItem().toString().equals(" ")) || t8.getText().equals("") || t9.getText().equals("") || t10.getText().equals("") || t11.getText().equals("") || t12.getText().equals("") || t13.getText().equals("")) {

                int i = JOptionPane.showConfirmDialog(null, "                               Aleart ! Information Missing    !                            \n\nDo you want to Countinue    ?            ", "Previous Records", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    update();
                } else {
                    t1.grabFocus();
                }

            } else {

                update();
            }
        }

        /*
        

         */
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void t15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t15KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t1.grabFocus();  // TODO add your handling code here:
    }//GEN-LAST:event_t15KeyPressed

    private void t10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t10KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER) {

            t16.grabFocus();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_t10KeyPressed

    private void t10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t10ActionPerformed

    private void t12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t12KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t13.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t12KeyPressed

    private void t13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t13KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t14.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t13KeyPressed

    private void t14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t14KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t15.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t14KeyPressed

    private void t11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t11KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t12.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t11KeyPressed

    private void t9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t9KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER) {

            t10.grabFocus();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_t9KeyPressed

    private void t8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t8KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t9.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t8KeyPressed
    public void mouse_release(int i) {
        String srl = (String) patients_records_table.getValueAt(i, 0);
        serial.setText(srl);

        String name = (patients_records_table.getValueAt(i, 1)).toString();
        t1.setText(name);

        String age = (String) patients_records_table.getValueAt(i, 2);
        t2.setText(age);

        String gen = (String) patients_records_table.getValueAt(i, 3);

        if (gen.equals("Male")) {
            t3.setSelectedIndex(1);
        } else if (gen.equals("Female")) {
            t3.setSelectedIndex(2);
        } else if (gen.equals("Select Gender")) {
            t3.setSelectedIndex(0);
        } else {
            t3.setSelectedItem(gen);
        }

        String phone = (String) patients_records_table.getValueAt(i, 4);
        t4.setText(phone);

        String add = (String) patients_records_table.getValueAt(i, 5);
        t5.setSelectedItem(add);

        String spec = (String) patients_records_table.getValueAt(i, 6);

        t6.setText(spec);

        String ref = (String) patients_records_table.getValueAt(i, 7);
        t7.setSelectedItem(ref);

        String date = (String) patients_records_table.getValueAt(i, 8);
        t14.setText(date);

        String time = (String) patients_records_table.getValueAt(i, 9);
        t15.setText(time);

        String Total_tests = (String) patients_records_table.getValueAt(i, 10);
        t8.setText(Total_tests);

        String amount = (String) patients_records_table.getValueAt(i, 11);
        t9.setText(amount);

        String discount = (String) patients_records_table.getValueAt(i, 12);
        t10.setText(discount);

        String gtotoal = (String) patients_records_table.getValueAt(i, 13);
        t16.setText(gtotoal);

        String payed = (String) patients_records_table.getValueAt(i, 14);
        t11.setText(payed);

        String chnage = (String) patients_records_table.getValueAt(i, 15);
        t12.setText(chnage);

        String remainig = (String) patients_records_table.getValueAt(i, 16);
        t13.setText(remainig);

        try {
            loadTable();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void patients_records_tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patients_records_tableMouseReleased
        try {

            search.setText("");
            chk1.setSelected(false);
            chk2.setSelected(false);
            int i = patients_records_table.rowAtPoint(evt.getPoint());// TODO add your handling code here
            mouse_release(i);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "           Please  Select Only Single Row to Perform Operations  !          ", "Previous Records", JOptionPane.WARNING_MESSAGE);
            try {
                loadTable();
                // TODO add your handling code here:
            } catch (SQLException ex1) {
                Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }


    }//GEN-LAST:event_patients_records_tableMouseReleased

    private void patients_records_tableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patients_records_tableFocusLost
        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_patients_records_tableFocusLost

    private void t4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t4KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t5.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t4KeyPressed

    private void t3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_t3KeyReleased

    private void t3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t3KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t4.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t3KeyPressed

    private void t2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t2KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t3.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t2KeyPressed

    private void t1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t1KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t2.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t1KeyPressed

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
        t5.setSelectedIndex(0);
        t6.setText("");
        t7.setSelectedIndex(0);
        t8.setText("");
        t9.setText("");
        t10.setText("");
        t11.setText("");
        t16.setText("");
        suggest();
        load_cat_box();
        t12.setText("");
        t13.setText("");
        t14.setText("");
        t15.setText("");
        search.setText("");

        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_searchFocusGained

    private void t7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t7KeyPressed
        if (evt.getKeyChar() == evt.VK_TAB)
            t8.grabFocus();           // TODO add your handling code here:
    }//GEN-LAST:event_t7KeyPressed

    private void t9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t9FocusLost

        t9.setText(t9.getText().trim());
        String text = t9.getText();

        if (isvlaid(text) == true) {

            JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Previous Records", JOptionPane.ERROR_MESSAGE);
            t9.setText("");
            t9.grabFocus();

        } else if (isvlaid(text) == false) {
            try {
                double dis;
                double total;
                t10.setText(t10.getText().toString().trim());
                t9.setText(t9.getText().toString().trim());

                if (t10.getText().isEmpty()) {
                    dis = 0.0;
                } else {
                    dis = Double.parseDouble(t10.getText());
                }

                if (t9.getText().isEmpty()) {
                    total = 0.0;
                } else {
                    total = Double.parseDouble(t9.getText());
                }

                t16.setText("" + (total - dis));

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Previous Records", JOptionPane.ERROR_MESSAGE);

            }
            try {
                double GTOTAL;
                double PAYED;
                t16.setText(t16.getText().toString().trim());
                t11.setText(t11.getText().toString().trim());

                if (t16.getText().isEmpty()) {
                    GTOTAL = 0.0;
                } else {
                    GTOTAL = Double.parseDouble(t16.getText());
                }

                if (t11.getText().isEmpty()) {
                    PAYED = 0.0;
                } else {
                    PAYED = Double.parseDouble(t11.getText());
                }

                if (GTOTAL == PAYED) {
                    t12.setText("0.0");
                    t13.setText("0.0");
                } else if (GTOTAL > PAYED) {
                    t12.setText("0.0");
                    t13.setText("" + Math.abs(GTOTAL - PAYED));
                } else if (GTOTAL < PAYED) {
                    t13.setText("0.0");
                    t12.setText("" + Math.abs(PAYED - GTOTAL));
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Previous Records", JOptionPane.ERROR_MESSAGE);

            }

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_t9FocusLost
    public boolean isvlaid(String text) {
        Boolean chk = false;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i)) || text.charAt(i) == '.'); else {
                chk = true;
                break;
            }

        }
        return chk;
    }

    private void t10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t10FocusLost
        t10.setText(t10.getText().trim());
        String text = t10.getText();

        if (isvlaid(text) == true) {
            JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Previous Records", JOptionPane.ERROR_MESSAGE);
            t10.setText("");
            t10.grabFocus();

        }// TODO add your handling code here:
        else if (isvlaid(text) == false) {
            try {
                double dis;
                double total;
                t10.setText(t10.getText().toString().trim());
                t9.setText(t9.getText().toString().trim());

                if (t10.getText().isEmpty()) {
                    dis = 0.0;
                } else {
                    dis = Double.parseDouble(t10.getText());
                }

                if (t9.getText().isEmpty()) {
                    total = 0.0;
                } else {
                    total = Double.parseDouble(t9.getText());
                }

                t16.setText("" + (total - dis));

                double GTOTAL;
                double PAYED;
                t16.setText(t16.getText().toString().trim());
                t11.setText(t11.getText().toString().trim());

                if (t16.getText().isEmpty()) {
                    GTOTAL = 0.0;
                } else {
                    GTOTAL = Double.parseDouble(t16.getText());
                }

                if (t11.getText().isEmpty()) {
                    PAYED = 0.0;
                } else {
                    PAYED = Double.parseDouble(t11.getText());
                }

                if (GTOTAL == PAYED) {
                    t12.setText("0.0");
                    t13.setText("0.0");
                } else if (GTOTAL > PAYED) {
                    t12.setText("0.0");
                    t13.setText("" + Math.abs(GTOTAL - PAYED));
                } else if (GTOTAL < PAYED) {
                    t13.setText("0.0");
                    t12.setText("" + Math.abs(PAYED - GTOTAL));
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Previous Records", JOptionPane.ERROR_MESSAGE);

            }
        }
    }//GEN-LAST:event_t10FocusLost

    private void t11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t11FocusLost
        t11.setText(t11.getText().trim());
        String text = t11.getText();

        if (isvlaid(text) == true) {
            JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Previous Records", JOptionPane.ERROR_MESSAGE);
            t11.setText("");
            t11.grabFocus();

        } else if (isvlaid(text) == false) {
            try {
                double GTOTAL;
                double PAYED;
                t16.setText(t16.getText().toString().trim());
                t11.setText(t11.getText().toString().trim());

                if (t16.getText().isEmpty()) {
                    GTOTAL = 0.0;
                } else {
                    GTOTAL = Double.parseDouble(t16.getText());
                }

                if (t11.getText().isEmpty()) {
                    PAYED = 0.0;
                } else {
                    PAYED = Double.parseDouble(t11.getText());
                }

                if (GTOTAL == PAYED) {
                    t12.setText("0.0");
                    t13.setText("0.0");
                } else if (GTOTAL > PAYED) {
                    t12.setText("0.0");
                    t13.setText("" + Math.abs(GTOTAL - PAYED));
                } else if (GTOTAL < PAYED) {
                    t13.setText("0.0");
                    t12.setText("" + Math.abs(PAYED - GTOTAL));
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Previous Records", JOptionPane.ERROR_MESSAGE);

            }
        }

// TODO add your handling code here:else if(isvlaid(text)==false)

    }//GEN-LAST:event_t11FocusLost

    private void t9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t9ActionPerformed

    private void t7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t7KeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_TAB)
            t8.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t7KeyReleased

    private void t16FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t16FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_t16FocusLost

    private void t16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t16KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t11.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t16KeyPressed

    private void t9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t9KeyTyped

    }//GEN-LAST:event_t9KeyTyped

    private void t10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t10KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_t10KeyReleased

    private void t9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t9KeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_t9KeyReleased

    private void t10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t10KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_t10KeyTyped

    private void t5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t5KeyPressed
        if (evt.getKeyChar() == evt.VK_TAB)
            t6.grabFocus();         // TODO add your handling code here:
    }//GEN-LAST:event_t5KeyPressed

    private void t5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t5FocusLost

// TODO add your handling code here:
    }//GEN-LAST:event_t5FocusLost

    private void t1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1ActionPerformed

    private void loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadActionPerformed

        try {
            loadTable();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(accounts_information_panel.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_loadActionPerformed

    private void mouse_eventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mouse_eventsActionPerformed

        mouse_release(val);
        pay();// TODO add your handling code here:
    }//GEN-LAST:event_mouse_eventsActionPerformed
    public void print(String id) {
        HashMap parameter = new HashMap();
        parameter.put("report_Id", id);
        try {
            Report_View vr = new Report_View("src\\Reports\\tests_report.jasper", parameter);
            vr.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "          Exception Catched          ", "Previous Records", JOptionPane.ERROR_MESSAGE);

        }

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String id = serial.getText();
        t6.setText(t6.getText().trim());

        if (id.equals("00") || id.equals("")) {

            JOptionPane.showMessageDialog(null, "     Kindly Select any Report to Print    . . .                   ", "Previous Records", JOptionPane.ERROR_MESSAGE);

        } else if (t6.getText().equals("") || t6.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "     Kindly Fill  All the Required Specimen Field    . . .                   ", "Previous Records", JOptionPane.ERROR_MESSAGE);
            t6.grabFocus();
        } else {

            print(id);

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void t6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_t6KeyPressed

    private void t6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chk1;
    private javax.swing.JCheckBox chk2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JButton load;
    public static javax.swing.JButton mouse_events;
    public static javax.swing.JTable patients_records_table;
    public static javax.swing.JButton refresh_record;
    private javax.swing.JTextField search;
    public static javax.swing.JLabel serial;
    public static javax.swing.JTextField t1;
    private javax.swing.JTextField t10;
    private javax.swing.JTextField t11;
    private javax.swing.JTextField t12;
    private javax.swing.JTextField t13;
    private javax.swing.JTextField t14;
    private javax.swing.JTextField t15;
    private javax.swing.JTextField t16;
    private javax.swing.JTextField t2;
    private javax.swing.JComboBox<String> t3;
    private javax.swing.JTextField t4;
    private javax.swing.JComboBox<String> t5;
    private javax.swing.JTextField t6;
    private javax.swing.JComboBox<String> t7;
    private javax.swing.JTextField t8;
    private javax.swing.JTextField t9;
    // End of variables declaration//GEN-END:variables
}
