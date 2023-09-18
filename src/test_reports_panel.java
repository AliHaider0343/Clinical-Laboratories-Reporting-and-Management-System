
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
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ALI HAIDER
 */
public class test_reports_panel extends javax.swing.JPanel {

    /**
     * Creates new form test_reports_panel
     */
    Connection con;
    int test_counter;
    double price_counters = 0.0;
    double price_counters2 = 0.0;
    boolean issave = false;
    boolean getSpecificerror = false;
    int main_tests_count = 0;

    public test_reports_panel() {
        initComponents();
        getSpecificerror = false;
        previous.setMnemonic(KeyEvent.VK_LEFT);
        next.setMnemonic(KeyEvent.VK_RIGHT);
        jButton4.setMnemonic(KeyEvent.VK_P);
        jButton5.setMnemonic(KeyEvent.VK_S);
        jButton6.setMnemonic(KeyEvent.VK_N);
        Refresh.setMnemonic(KeyEvent.VK_R);
        issave = false;
        AutoCompleteDecorator.decorate(t7);
        AutoCompleteDecorator.decorate(t5);
        AutoCompleteDecorator.decorate(tests);

        AutoCompleteDecorator.decorate(category_Box);
        AutoCompleteDecorator.decorate(test_box);
        test_counter = 0;
        this.model = (DefaultTableModel) cart_tab.getModel();
        cart_tab = new JTable(model);

        this.model2 = (DefaultTableModel) main_test_table.getModel();
        main_test_table = new JTable(model2);

        con = databaseconnetion.get_Connection();

    }

    public void save_p1() {
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
                    if (t7.getSelectedItem().toString().equals("Self") || t7.getSelectedItem().toString().equals("OPD")); else {
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

        t14.setText(t14.getText().trim());
        String text3 = t14.getText();

        boolean chk1 = isvlaid(text1);
        boolean chk2 = isvlaid(text2);
        boolean chk3 = isvlaid(text3);
        if (chk1 == true || chk2 == true || chk3 == true) {
            String field = "";

            if (chk1 == true) {
                field = "Payed Amount";

            } else if (chk2 == true) {
                field = "Discount";

            } else if (chk3 == true) {
                field = "Total Amount";

            }

            JOptionPane.showMessageDialog(null, "           (    Please Correct   " + field + "    )   !              ", "Test Reports Generation", JOptionPane.WARNING_MESSAGE);

            if (chk1 == true) {
                t11.grabFocus();

            } else if (chk2 == true) {
                t10.grabFocus();

            } else if (chk3 == true) {
                t14.grabFocus();
            }
        } else if (t1.getText().equals("") || t2.getText().equals("") || t4.getText().equals("") || t5.getSelectedItem().equals("") || t8.getText().equals("") || t9.getText().equals("") || t10.getText().equals("") || t11.getText().equals("") || t12.getText().equals("") || t13.getText().equals("")) {

            int i = JOptionPane.showConfirmDialog(null, "                               Aleart ! Information Missing    !                            \n\nDo you want to Countinue    ?            ", "Test Reports Generation", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                save_part2();
            } else if (i == JOptionPane.NO_OPTION) {
                t1.requestFocus();
                getSpecificerror = true;
            }

        } else {

            save_part2();
        }

    }

    public void save_part2() {
        String date = "";
        String time = "";
        String name_title = "";

        if (t1.getText().startsWith("Mr", 0) || t1.getText().startsWith("Ms", 0) || t1.getText().startsWith("MR", 0) || t1.getText().startsWith("MS", 0) || t1.getText().startsWith("mR", 0) || t1.getText().startsWith("mS", 0)) {
            name_title = "";
        } else {
            if (t3.getSelectedItem().toString().equals("Male")) {

                name_title = "Mr ";
            } else if (t3.getSelectedItem().toString().equals("Female")) {
                name_title = "Ms ";
            }

        }

        time = Home.timer.getText();
        date = Home.date1.getText();

        String dr_title = "";
        try {

            if (t7.getSelectedItem().toString().equals("Self") || t7.getSelectedItem().toString().equals("OPD")); else {
                if (t7.getSelectedItem().toString().startsWith("Dr", 0) || t7.getSelectedItem().toString().startsWith("dr", 0) || t7.getSelectedItem().toString().startsWith("dR", 0) || t7.getSelectedItem().toString().startsWith("DR", 0)) {
                    dr_title = "";
                } else {
                    dr_title = "Dr ";
                }
            }

            String speciman = "[";
            for (int i = 0; i < model2.getRowCount(); i++) {
                if (!speciman.contains(model2.getValueAt(i, 2).toString())) {
                    if (i == model2.getRowCount() - 1) {
                        speciman += model2.getValueAt(i, 2) + "]";
                    } else {
                        speciman += model2.getValueAt(i, 2) + ",";
                    }

                }

            }
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO patients_reports (Patient_Name,Age,Sex,Phone_Number,Address,Specimen,Referred_By,Reporting_Date,Reporting_Time,Total_Tests,Total_Amount,discount,Grand_total,Payed_Amount,change_returned,remaining_amount) VALUES   ('" + (name_title + t1.getText()) + "','" + t2.getText() + "','" + (t3.getSelectedItem().toString()) + "','" + t4.getText() + "','" + t5.getSelectedItem().toString() + "','" + (speciman) + "','" + ((dr_title + t7.getSelectedItem().toString())) + "','" + (date) + "','" + (time) + "','" + model2.getRowCount() + "','" + t14.getText() + "','" + t10.getText() + "','" + t16.getText() + "','" + t11.getText() + "','" + t12.getText() + "','" + t13.getText() + "')");

            save_part3_update();
            // save_part3();
            issave = true;

            try {
                Statement st2 = con.createStatement();
                st2.executeUpdate("INSERT INTO  report_number(Last_report_number) VALUES ('" + (report_serial.getText()) + "')");
            } catch (SQLException ex) {
                Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "          Patient Information is Succesfully Inserted to DataBase     !              ", "Test Reports Generation", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Test Reports Generation", JOptionPane.WARNING_MESSAGE);

        }

    }

    public void save_part3_update() {

        for (int i = 0; i < model2.getRowCount(); i++) {
            try {
                Statement st = con.createStatement();

                st.executeUpdate("INSERT INTO individual_tests (Serial_no,Test_Name,report_id,speciman,Tests_rate) VALUES   ('" + model2.getValueAt(i, 0) + "','" + model2.getValueAt(i, 1) + "','" + (report_serial.getText()) + "','" + model2.getValueAt(i, 2) + "','" + model2.getValueAt(i, 3) + "')");
            } catch (SQLException ex) {
                Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void report_number() {

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM   report_number ORDER BY Last_report_number DESC LIMIT 0,1");

            if (rs.next()) {
                String report_id = rs.getString(1);
                int rpt = Integer.parseInt(report_id);
                rpt++;
                report_serial.setText(rpt + "");
            }

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

    public void suggest() {
        t5.removeAllItems();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM  suggestions");
            t5.addItem("Required Address");
            while (rs.next()) {
                String element = rs.getString(2);
                t5.addItem(element);
            }

        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void load_cat_box_of_docs() {

        t7.removeAllItems();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM  reference_doctors");

            t7.addItem("Self");
            t7.addItem("OPD");
            while (rs.next()) {
                String element = rs.getString(2);
                t7.addItem(element);
            }

        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void load_tests() {

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT Category_Code FROM tests_catagories p left join pathology_tests pt on p.Category_Name=pt.Test_category");

            while (rs.next()) {
                String element = rs.getString("Category_Code");
                tests.addItem(element);

            }

        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    DefaultTableModel model;
    DefaultTableModel model2;

    public void remove_all() {
        model.setRowCount(0);
        t8.setText("0");
        t14.setText("0.0");
        t10.setText("0.0");
        t11.setText("0.0");
        t12.setText("0.0");
        t13.setText("0.0");

        t16.setText("0.0");
        test_counter = 0;
        price_counters = 0.0;

    }

    public void remove_alll() {

        try {
            Statement st = con.createStatement();
            st.executeUpdate("delete from individual_tests where Report_ID='" + report_serial.getText() + "'   ");
        } catch (SQLException ex) {
            Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Statement st = con.createStatement();
            st.executeUpdate("delete from tests_cart_table where Report_ID='" + report_serial.getText() + "'   ");
        } catch (SQLException ex) {
            Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

        model2.setRowCount(0);
        try {
            Statement st = con.createStatement();
            st.executeUpdate("update patients_reports set  Total_Tests='" + model2.getRowCount() + "' where Report_Serial_ID ='" + report_serial.getText() + "' ");
        } catch (SQLException ex) {
            Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        t8.setText("0");
        t14.setText("0.0");
        t10.setText("0.0");
        t11.setText("0.0");
        t12.setText("0.0");
        t13.setText("0.0");
        t14.setText("0.0");
        t16.setText("0.0");
        main_tests_count = 0;
        price_counters = 0.0;

        price_counters2 = 0.0;

    }

    public void remove_item() {
        double val = 0.0;
        if (model.getRowCount() > 0) {
            int row = model.getRowCount() - 1;
            String price = model.getValueAt(row, 6).toString();
            val = Double.parseDouble(t9.getText()) - Double.parseDouble(price);

            model.removeRow(row);
            t9.setText(val + "");
            price_counters -= Double.parseDouble(price);
            t8.setText(model.getRowCount() + "");
            test_counter--;
        } else {
            JOptionPane.showMessageDialog(null, "     All Data From The Table is Alaedy Deleted  Already     ", "Test Reports Generation", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void remove_item2() {
        double val = 0.0;
        if (model2.getRowCount() > 0) {
            int row = model2.getRowCount() - 1;
            String price = model2.getValueAt(row, 3).toString();
            String name = model2.getValueAt(row, 1).toString();
            String spc = model2.getValueAt(row, 2).toString();
            String serial = model2.getValueAt(row, 0).toString();

            try {
                Statement st = con.createStatement();
                st.executeUpdate("delete from individual_tests where Report_ID='" + report_serial.getText() + "' and Test_Name='" + name + "' and Speciman='" + spc + "' and Tests_rate='" + price + "' and Serial_no='" + serial + "' ");

            } catch (SQLException ex) {
                Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Statement st = con.createStatement();
                st.executeUpdate("delete from tests_cart_table where Report_ID='" + report_serial.getText() + "' and Speciman='" + spc + "' and  Test_Category in (select Category_Name from tests_catagories where Category_Code='" + name + "') ");
            } catch (SQLException ex) {
                Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
            }

            model2.removeRow(row);
            try {
                Statement st = con.createStatement();
                st.executeUpdate("update patients_reports set  Total_Tests='" + model2.getRowCount() + "' where Report_Serial_ID ='" + report_serial.getText() + "' ");
            } catch (SQLException ex) {
                Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            val = Double.parseDouble(t14.getText()) - Double.parseDouble(price);

            t14.setText(val + "");
            price_counters2 -= Double.parseDouble(price);

            t8.setText(model2.getRowCount() + "");
            --main_tests_count;
        } else {
            JOptionPane.showMessageDialog(null, "     All Data From The Table is Alaedy Deleted  Already     ", "Test Reports Generation", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void remove_item(int r) {
        java.awt.event.MouseEvent evt = null;
        int row = cart_tab.rowAtPoint(evt.getPoint());
        String price = model.getValueAt(row, 6).toString();
        double val = Double.parseDouble(t9.getText()) - Double.parseDouble(price);
        model.removeRow(row);
        t9.setText(val + "");
        price_counters -= Double.parseDouble(price);
        t8.setText(model.getRowCount() + "");
        test_counter--;

    }

    public void set_parameters2(double price) {
        try {

            double dis = Double.parseDouble(t10.getText());

            double grand = price - dis;
            t16.setText("" + grand);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "         Exception Catched           ' " + ex + "'           ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
        }

        ///upfdates is on  table 
    }

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

                price_counters += Double.parseDouble(rates);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "         Exception Catched           ' " + ex + "'           ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);

            }
            double dis = Double.parseDouble(t10.getText());
            double grand = price_counters - dis;
            t16.setText("" + grand);
            t9.setText("" + price_counters);

            t8.setText(test_counter + "");
            model.addRow(data);
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "         Exception Catched           ' " + ex + "'           ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
        }

        ///upfdates is on  table 
    }

    public void load_tests_Box() {

        test_box.removeAllItems();
        test_box.addItem("Select Test");
        test_box.setSelectedIndex(0);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM  pathology_tests");

            while (rs.next()) {
                String element = rs.getString(3);
                test_box.addItem(element);
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
            JOptionPane.showMessageDialog(null, "Exception in  Load Tests By Category                          " + ex + "                            ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
        }

    }

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
                price_counters += Double.parseDouble(data[6]);
                double dis = Double.parseDouble(t10.getText());
                double grand = price_counters - dis;
                t16.setText("" + grand);
                t9.setText("" + price_counters);
                t8.setText(test_counter + "");
                model.addRow(data);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Exception in  Load Tests By Category                          " + ex + "                            ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
        }

    }

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enter_fresher = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        t9 = new javax.swing.JTextField();
        test_box = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cart_tab = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        category_Box = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        t5 = new javax.swing.JComboBox<>();
        t4 = new javax.swing.JTextField();
        t6 = new javax.swing.JComboBox<>();
        t7 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        report_serial = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        Refresh = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        t12 = new javax.swing.JTextField();
        t13 = new javax.swing.JTextField();
        t16 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        t11 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        t14 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        t10 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tests = new javax.swing.JComboBox<>();
        search_report_btn = new javax.swing.JButton();
        search_rpt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        t3 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        t2 = new javax.swing.JTextField();
        t1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        main_test_table = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        t8 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        date_lab = new javax.swing.JLabel();
        time_lab = new javax.swing.JLabel();
        previous = new javax.swing.JButton();
        next = new javax.swing.JButton();

        enter_fresher.setText("jButton7");
        enter_fresher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_fresherActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jLabel24.setText("Total Amount");
        jLabel24.setEnabled(false);
        jLabel24.setFocusable(false);

        t9.setEditable(false);
        t9.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        t9.setEnabled(false);
        t9.setFocusable(false);
        t9.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        t9.setSelectionColor(new java.awt.Color(251, 152, 200));
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

        test_box.setBackground(new java.awt.Color(251, 152, 200));
        test_box.setEditable(true);
        test_box.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        test_box.setBorder(null);
        test_box.setEnabled(false);
        test_box.setFocusable(false);
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

        jButton1.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jButton1.setText("Neglect Last Test");
        jButton1.setContentAreaFilled(false);
        jButton1.setEnabled(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jButton3.setText("Remove All ");
        jButton3.setContentAreaFilled(false);
        jButton3.setEnabled(false);
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(251, 152, 200));
        jPanel3.setEnabled(false);
        jPanel3.setFocusable(false);

        cart_tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Serial no", "Test Category", "Test Name", "Test Range", "Test Result", "Test Units", "Test Rate"
            }
        ));
        cart_tab.setEnabled(false);
        cart_tab.setFocusable(false);
        cart_tab.setSelectionBackground(new java.awt.Color(251, 152, 200));
        cart_tab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cart_tabMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(cart_tab);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jLabel13.setText("Test's  Name");
        jLabel13.setEnabled(false);
        jLabel13.setFocusable(false);

        jLabel20.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jLabel20.setText("Test's Category");
        jLabel20.setEnabled(false);
        jLabel20.setFocusable(false);

        category_Box.setBackground(new java.awt.Color(251, 152, 200));
        category_Box.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        category_Box.setBorder(null);
        category_Box.setEnabled(false);
        category_Box.setFocusable(false);
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

        jButton9.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jButton9.setText("Refresh Results");
        jButton9.setContentAreaFilled(false);
        jButton9.setEnabled(false);
        jButton9.setFocusable(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jButton2.setText("Add Single Test");
        jButton2.setContentAreaFilled(false);
        jButton2.setEnabled(false);
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jButton7.setText("Add Whole category");
        jButton7.setContentAreaFilled(false);
        jButton7.setEnabled(false);
        jButton7.setFocusable(false);
        jButton7.setHideActionText(true);
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jToggleButton1.setText("jToggleButton1");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(947, 786));

        jPanel1.setBackground(new java.awt.Color(243, 243, 243));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 686));

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 28)); // NOI18N
        jLabel1.setText(" Reports Interface");

        jLabel15.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel15.setText("Phone No ");

        jLabel16.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel16.setText("Address");

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
        });

        t6.setBackground(new java.awt.Color(19, 80, 161));
        t6.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Specimen", "Blood", "Urine", "Semen", "Stool", "None" }));
        t6.setBorder(null);
        t6.setOpaque(false);
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

        t7.setBackground(new java.awt.Color(19, 80, 161));
        t7.setEditable(true);
        t7.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OPD" }));
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

        jLabel18.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel18.setText("Referred By");

        jLabel19.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel19.setText("Specimen");

        report_serial.setFont(new java.awt.Font("Rockwell", 0, 20)); // NOI18N
        report_serial.setForeground(new java.awt.Color(19, 80, 161));
        report_serial.setText("00");

        jLabel21.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel21.setText("Report Number ");

        Refresh.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        Refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
        Refresh.setText("Refresh  ");
        Refresh.setContentAreaFilled(false);
        Refresh.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new.png"))); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/savereport.png"))); // NOI18N
        jButton5.setContentAreaFilled(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        t12.setEditable(false);
        t12.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t12.setSelectionColor(new java.awt.Color(19, 80, 161));
        t12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t12KeyPressed(evt);
            }
        });

        t13.setEditable(false);
        t13.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t13.setSelectionColor(new java.awt.Color(19, 80, 161));
        t13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t13KeyPressed(evt);
            }
        });

        t16.setEditable(false);
        t16.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t16.setSelectionColor(new java.awt.Color(19, 80, 161));
        t16.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                t16FocusLost(evt);
            }
        });
        t16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t16ActionPerformed(evt);
            }
        });
        t16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t16KeyPressed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel26.setText("Remaining Amount");

        jLabel30.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel30.setText("Total Amount");

        jLabel27.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel27.setText("Change");

        t11.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t11.setSelectionColor(new java.awt.Color(19, 80, 161));
        t11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t11FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t11FocusLost(evt);
            }
        });
        t11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t11ActionPerformed(evt);
            }
        });
        t11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t11KeyPressed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel28.setText("Payed Amount");

        jLabel25.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel25.setText("Discount");

        t14.setEditable(false);
        t14.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t14.setSelectionColor(new java.awt.Color(19, 80, 161));
        t14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                t14FocusLost(evt);
            }
        });
        t14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t14ActionPerformed(evt);
            }
        });
        t14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t14KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t14KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t14KeyTyped(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel29.setText("Grand Total");

        t10.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t10.setSelectionColor(new java.awt.Color(19, 80, 161));
        t10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t10FocusGained(evt);
            }
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jLabel25)
                    .addComponent(jLabel29))
                .addGap(0, 18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(t16, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addComponent(t14))
                    .addComponent(t10, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel27))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(t12)
                    .addComponent(t13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(t11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(t14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel25)
                    .addComponent(t10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel29)
                    .addComponent(t16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel17.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel17.setText("Test's  Name");

        tests.setBackground(new java.awt.Color(19, 80, 161));
        tests.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        tests.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Tests" }));
        tests.setBorder(null);
        tests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                testsMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                testsMouseReleased(evt);
            }
        });
        tests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testsActionPerformed(evt);
            }
        });
        tests.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                testsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                testsKeyReleased(evt);
            }
        });

        search_report_btn.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        search_report_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        search_report_btn.setText("Search Report");
        search_report_btn.setContentAreaFilled(false);
        search_report_btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        search_report_btn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        search_report_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_report_btnActionPerformed(evt);
            }
        });

        search_rpt.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        search_rpt.setText(" ");
        search_rpt.setSelectionColor(new java.awt.Color(19, 80, 161));
        search_rpt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_rptActionPerformed(evt);
            }
        });
        search_rpt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_rptKeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel14.setText("Gender");

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

        jLabel12.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel12.setText("Age");

        t2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        t2.setSelectionColor(new java.awt.Color(19, 80, 161));
        t2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t2KeyPressed(evt);
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

        jLabel11.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel11.setText("Patient's Name");

        jPanel6.setBackground(new java.awt.Color(19, 80, 161));
        jPanel6.setOpaque(false);

        main_test_table.setFont(new java.awt.Font("Rockwell", 0, 13)); // NOI18N
        main_test_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serial no", "Test Name", "Speciman", "price"
            }
        ));
        main_test_table.setGridColor(new java.awt.Color(51, 51, 51));
        main_test_table.setOpaque(false);
        main_test_table.setSelectionBackground(new java.awt.Color(19, 80, 161));
        main_test_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                main_test_tableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(main_test_table);

        jLabel3.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        jLabel3.setText("Total Tests");

        t8.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        t8.setForeground(new java.awt.Color(19, 80, 161));
        t8.setText("0");

        jButton10.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteIcon.png"))); // NOI18N
        jButton10.setText("Neglect Last Test");
        jButton10.setContentAreaFilled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/garbage-bin-png-10496.png"))); // NOI18N
        jButton8.setText("Remove All ");
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        date_lab.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        date_lab.setText("Date");

        time_lab.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        time_lab.setText("Time");

        previous.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/left-arrow.png"))); // NOI18N
        previous.setContentAreaFilled(false);
        previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionPerformed(evt);
            }
        });

        next.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right-arrow.png"))); // NOI18N
        next.setContentAreaFilled(false);
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(t8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(time_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 68, Short.MAX_VALUE)
                            .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(time_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tests, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(t4, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12)
                                .addComponent(jLabel14))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(report_serial, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(t2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .addComponent(t3, 0, 130, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(search_rpt)
                        .addComponent(search_report_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Refresh))))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {t1, t2, t3, t4, t5, t7});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_report_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(search_rpt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(report_serial))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t3)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(tests, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {t3, t5, t6, t7});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void test_boxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_test_boxKeyTyped

        // TODO add your handling code here:
    }//GEN-LAST:event_test_boxKeyTyped

    private void test_boxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_test_boxKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_test_boxKeyPressed

    private void test_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_test_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_test_boxActionPerformed

    private void test_boxMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_test_boxMouseReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_test_boxMouseReleased

    private void test_boxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_test_boxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_test_boxMouseClicked

    private void test_boxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_test_boxFocusLost
        // set_parameters();     // TODO add your handling code here:
    }//GEN-LAST:event_test_boxFocusLost

    private void test_boxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_test_boxFocusGained

        // TODO add your handling code here:
    }//GEN-LAST:event_test_boxFocusGained

    private void category_BoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_category_BoxKeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER) {

            test_box.grabFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_category_BoxKeyPressed

    private void category_BoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_category_BoxActionPerformed
        load_test_box_byt_cat();           // TODO add your handling code here:
    }//GEN-LAST:event_category_BoxActionPerformed

    private void category_BoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_category_BoxFocusLost

        //       refresher.doClick(); // TODO add your handling code here:
    }//GEN-LAST:event_category_BoxFocusLost

    private void t13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t13KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_t13KeyPressed

    private void t12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t12KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t13.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t12KeyPressed

    private void t11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t11KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t12.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t11KeyPressed

    private void t11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t11FocusLost
        t11.setText(t11.getText().trim());
        String text = t11.getText();
        if (t11.getText().equals(""))
            t11.setText("0.0");
        else {
            if (isvlaid(text) == true) {
                JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);

                }
            }

        }   // TODO add your handling code here:else if(isvlaid(text)==false)
    }//GEN-LAST:event_t11FocusLost

    private void t16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t16KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t11.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t16KeyPressed

    private void t16FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t16FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_t16FocusLost

    private void t10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t10KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_t10KeyTyped

    private void t10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t10KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_t10KeyReleased

    private void t10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t10KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER) {

            t16.grabFocus();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_t10KeyPressed

    private void t10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t10ActionPerformed
    public void pay() {
        t10.setText(t10.getText().trim());
        String text = t10.getText();
        if (t10.getText().equals("")) {
            t10.setText("0.0");
        } else {
            if (isvlaid(text) == true) {
                JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
                t10.setText("");
                t10.grabFocus();

            }// TODO add your handling code here:
            else if (isvlaid(text) == false) {
                try {
                    double dis;
                    double total;
                    t10.setText(t10.getText().toString().trim());
                    t14.setText(t14.getText().toString().trim());

                    if (t10.getText().isEmpty()) {
                        dis = 0.0;
                    } else {
                        dis = Double.parseDouble(t10.getText());
                    }

                    if (t14.getText().isEmpty()) {
                        total = 0.0;
                    } else {
                        total = Double.parseDouble(t14.getText());
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
                    JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);

                }
            }
        }

    }
    private void t10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t10FocusLost
        pay();
    }//GEN-LAST:event_t10FocusLost

    private void t7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t7KeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_TAB)
            t8.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t7KeyReleased

    private void t7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t7KeyPressed
        if (evt.getKeyChar() == evt.VK_TAB)
            t6.grabFocus();           // TODO add your handling code here:
    }//GEN-LAST:event_t7KeyPressed

    private void t6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t6KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            category_Box.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t6KeyPressed

    private void t2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t2KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t3.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t2KeyPressed

    private void t3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_t3KeyReleased

    private void t3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t3KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t4.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t3KeyPressed

    private void t4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t4KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t5.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t4KeyPressed

    private void t5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t5KeyPressed
        if (evt.getKeyChar() == evt.VK_TAB)
            t7.grabFocus();         // TODO add your handling code here:
    }//GEN-LAST:event_t5KeyPressed

    private void t5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t5FocusLost

        // TODO add your handling code here:
    }//GEN-LAST:event_t5FocusLost

    private void t1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t1KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER)
            t2.grabFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_t1KeyPressed

    private void t1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        remove_all();
        pay();

// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void t6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t6ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        Refresh.doClick();         // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        main_tests_count = 0;
        load_cat_box_of_docs();
        suggest();
        search_rpt.setText("");
        next.setEnabled(false);
        previous.setEnabled(true);
        jButton8.setEnabled(true);
        tests.setEnabled(true);
        jButton10.setEnabled(true);
        t1.setText("");
        t2.setText("");
        t3.setSelectedIndex(0);
        t4.setText("");
        t5.setSelectedIndex(0);
        t6.setSelectedIndex(0);
        t7.setSelectedIndex(0);
        tests.setSelectedIndex(0);

        t8.setText("0");
        t9.setText("0.0");
        t10.setText("0.0");
        price_counters2 = 0.0;

        t11.setText("0.0");
        t12.setText("0.0");
        t13.setText("0.0");
        t14.setText("0.0");
        t16.setText("0.0");
        test_counter = 0;
        price_counters = 0.0;
        date_lab.setText("");
        time_lab.setText("");
        issave = false;
        t1.grabFocus();
        category_Box.setSelectedIndex(0);
        test_box.setSelectedIndex(0);

        this.model = (DefaultTableModel) cart_tab.getModel();
        cart_tab = new JTable(model);
        model.setRowCount(0);
        this.model2 = (DefaultTableModel) main_test_table.getModel();
        main_test_table = new JTable(model2);
        model2.setRowCount(0);

        report_number();

// TODO add your handling code here:
    }//GEN-LAST:event_RefreshActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt("", i, 4);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (category_Box.getSelectedIndex() == 0 && test_box.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "     Kindly Select Category and Test Fields   . . .                   ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
            category_Box.grabFocus();
        } else if (category_Box.getSelectedIndex() == 0 && category_Box.getSelectedItem().toString().equals("Select Category")) {
            JOptionPane.showMessageDialog(null, "     Kindly Select Category    . . .                   ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
            category_Box.grabFocus();
        } else if (test_box.getSelectedIndex() == 0 && test_box.getSelectedItem().toString().trim().equals("Select Test")) {
            JOptionPane.showMessageDialog(null, "     Kindly Select  Test Fields   . . .                   ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
            test_box.grabFocus();
        } else {
            set_parameters();
            category_Box.setSelectedIndex(0);
            test_box.setSelectedIndex(0);
            category_Box.grabFocus();

        }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void enter_fresherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enter_fresherActionPerformed

        load_cat_box_of_docs();
        suggest();
        load_tests();
        load_tests_Box();
        load_cat_box();
        load_test_box_byt_cat();
        t1.setText("");
        t2.setText("");
        price_counters2 = 0.0;
        t3.setSelectedIndex(0);
        t4.setText("");
        next.setEnabled(false);
        t5.setSelectedIndex(0);
        t6.setSelectedIndex(0);
        t7.setSelectedIndex(0);
        category_Box.setSelectedIndex(0);
        test_box.setSelectedIndex(0);
        main_tests_count = 0;
        t8.setText("0");
        t9.setText("0.0");
        t10.setText("0.0");
        t11.setText("0.0");
        search_rpt.setText("");

        t12.setText("0.0");
        t13.setText("0.0");
        t14.setText("0.0");
        ;
        t16.setText("0.0");
        date_lab.setText("");
        time_lab.setText("");
        issave = false;
        t1.grabFocus();
        test_counter = 0;
        price_counters = 0.0;
        this.model = (DefaultTableModel) cart_tab.getModel();
        cart_tab = new JTable(model);
        model.setRowCount(0);
        this.model2 = (DefaultTableModel) main_test_table.getModel();
        main_test_table = new JTable(model2);
        model2.setRowCount(0);

        report_number();
        // TODO add your handling code here:
    }//GEN-LAST:event_enter_fresherActionPerformed

    private void t4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        //     java.awt.event.MouseEvent evtt = null;
        //if(cart_tab.rowAtPoint(evtt.getPoint()) > 0)
        //remove_item(1);
//else
        remove_item();
        pay();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void t10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t10FocusGained
        if (t10.getText().equals("0.0")) {
            t10.setText("");
        }

// TODO add your handling code here:
    }//GEN-LAST:event_t10FocusGained

    private void t11FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t11FocusGained
        if (t11.getText().equals("0.0"))
            t11.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_t11FocusGained

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (category_Box.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "     Kindly Select Category of the Tests   . . .                   ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
            category_Box.grabFocus();
        } else
            load_whole_category();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed


    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (issave) {
            update_p1();
            int id = Integer.parseInt(report_serial.getText().trim());
            get_specific(id + "");
        } else {
            pay();

            if (t7.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "     Kindly Fill  All the Required Refrence Field    . . .                   ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
                t7.grabFocus();
            } else {

                getSpecificerror = false;
                save_p1();
                int id = Integer.parseInt(report_serial.getText().trim());
                if (!getSpecificerror) {
                    get_specific(id + "");
                }
                getSpecificerror = false;

            }

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed
    public void print(String id) {
        HashMap parameter = new HashMap();
        parameter.put("report_Id", id);
        try {
            Report_View vr = new Report_View("src\\Reports\\tests_report.jasper", parameter);
            vr.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "          Exception Catched          ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);

        }
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        String report_ID;
        report_ID = report_serial.getText();

        if (issave) {
            print(report_ID);
        } else {
            JOptionPane.showMessageDialog(null, "          Please Save the Data First and Then Print the Information    !              ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void t16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t16ActionPerformed

    private void testsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testsActionPerformed

        boolean check = false;
        String test_name = tests.getSelectedItem().toString();

        for (int i = 0; i < model2.getRowCount(); i++) {

            if (model2.getValueAt(i, 1).equals(test_name) && model2.getValueAt(i, 2).equals(t6.getSelectedItem().toString()) && model2.getValueAt(i, 3).equals(load_prices(test_name) + "")) {
                check = true;
                break;
            }

        }

        if (check) {
            JOptionPane.showMessageDialog(null, "          Selected Test is Already Present in the Cart    !              ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
        } else {
            set_data();
        }


    }//GEN-LAST:event_testsActionPerformed
    public void set_data() {

        String test_name = tests.getSelectedItem().toString();
        if (test_name.equals("Select Tests")); else {
            ++main_tests_count;
            String[] data = new String[4];

            data[0] = main_tests_count + "";
            data[1] = test_name;
            data[2] = t6.getSelectedItem() + "";
            data[3] = load_prices(test_name) + "";

            model2.addRow(data);

            double price = 0;
            for (int i = 0; i < model2.getRowCount(); i++) {
                price += Double.parseDouble(model2.getValueAt(i, 3).toString());
            }

            price_counters2 = price;

            t14.setText(price + "");
            set_parameters2(price_counters2);
            t8.setText(main_tests_count + "");
        }

    }
    private void testsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_testsKeyReleased

// TODO add your handling code here:
    }//GEN-LAST:event_testsKeyReleased

    private void testsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_testsKeyPressed

        // TODO add your handling code here:
    }//GEN-LAST:event_testsKeyPressed

    private void t14FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t14FocusLost
        t14.setText(t14.getText().trim());
        String text = t14.getText();

        if (isvlaid(text) == true) {

            JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
            t14.setText("");
            t14.grabFocus();

        } else if (isvlaid(text) == false) {
            try {
                double dis;
                double total;
                t10.setText(t10.getText().toString().trim());
                t14.setText(t14.getText().toString().trim());

                if (t10.getText().isEmpty()) {
                    dis = 0.0;
                } else {
                    dis = Double.parseDouble(t10.getText());
                }

                if (t14.getText().isEmpty()) {
                    total = 0.0;
                } else {
                    total = Double.parseDouble(t14.getText());
                }

                t16.setText("" + (total - dis));

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);

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
                JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);

            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_t14FocusLost

    private void t14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t14ActionPerformed

    private void t14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t14KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER) {

            t10.grabFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_t14KeyPressed

    private void t14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t14KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_t14KeyReleased

    private void t14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t14KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_t14KeyTyped

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        remove_alll();
        pay();

// TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        if (main_test_table.getRowCount() == 1) {
            remove_alll();
        } else {
            remove_item2();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void t11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t11ActionPerformed

    private void t9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t9KeyTyped

    }//GEN-LAST:event_t9KeyTyped

    private void t9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t9KeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_t9KeyReleased

    private void t9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t9KeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER) {

            t10.grabFocus();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_t9KeyPressed

    private void t9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t9ActionPerformed

    private void t9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t9FocusLost

        t9.setText(t9.getText().trim());
        String text = t9.getText();

        if (isvlaid(text) == true) {

            JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);

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
                JOptionPane.showMessageDialog(null, "          Please Enter Only Digits Value    !              ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);

            }

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_t9FocusLost

    private void main_test_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_main_test_tableMouseClicked

        int i = main_test_table.rowAtPoint(evt.getPoint());// TODO add your handling code here
        if (issave) {
            individual_tests_report ob = new individual_tests_report();
            ob.setVisible(true);
            ob.refresh(report_serial.getText(), (main_test_table.getValueAt(i, 1)).toString(), (main_test_table.getValueAt(i, 2)).toString());

        } else {
            JOptionPane.showMessageDialog(null, "     Please Save Data nd Then Continue for Reporting Tests    . . .                   ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
        }

// TODO add your handling code here:
    }//GEN-LAST:event_main_test_tableMouseClicked

    private void cart_tabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cart_tabMouseReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_cart_tabMouseReleased

    private void testsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_testsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_testsMouseClicked

    private void testsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_testsMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_testsMouseReleased
    public void get_previous(String id) {
        int rid = Integer.parseInt(id);
        id = rid + "";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patients_reports WHERE Report_Serial_ID <'" + id + "' ORDER BY Report_Serial_ID desc LIMIT 1");

            if (rs.next()) {
                next.setEnabled(true);
                report_serial.setText(rs.getString(1));
                get_specific(report_serial.getText());

                /*     t1.setText(rs.getString(2));
                    t2.setText(rs.getString(3));
               String gen=rs.getString(4);
                  if(gen.equals("Male"))
        {
            t3.setSelectedIndex(1);
        }
        else if(gen.equals("Female"))
        {
            t3.setSelectedIndex(2);
        }
        else if(gen.equals("Select Gender"))
        t3.setSelectedIndex(0);
        else
            t3.setSelectedItem(gen);

                  
                  
                 
                  t4.setText(rs.getString(5));
                  
                  
                 t5.setSelectedItem(rs.getString(6));
                 
                 
                    t6.setSelectedIndex(0);
                 t7.setSelectedItem(rs.getString(8));
                date_lab.setText("[ "+rs.getString(9)+" ]");
                time_lab.setText("[ "+rs.getString(10)+" ]");
                t8.setText(rs.getString(11));
                    
                    
               main_tests_count= Integer.parseInt(t8.getText());
                t14.setText(rs.getString(12));
                price_counters2-=Double.parseDouble(t14.getText());
                  t10.setText(rs.getString(13));
                    t16.setText(rs.getString(14));
              t11.setText(rs.getString(15));
                  t12.setText(rs.getString(16));
                 t13.setText(rs.getString(17));
                   
                   model2.setRowCount(0);
                  try{
        Statement stt = con.createStatement();
        ResultSet rss = st.executeQuery("SELECT * FROM individual_tests where report_Id = '"+id+"'");

            while(rss.next())
            {
                  String[] dataa=new String[4];
                   dataa[0]=rss.getString(1);
                   dataa[1]=rss.getString(2);
                   dataa[2]=rss.getString(3);
                   dataa[3]=rss.getString(5);
               model2.addRow(dataa);
             }
       
       }
       catch(Exception ex)
       {
        JOptionPane.showMessageDialog(null," Exception         "+ex.getMessage()+"  Here         ","Test Reports Generation",JOptionPane.WARNING_MESSAGE);
       }
                      
                  
                 */
            } else {

                previous.setEnabled(false);
                next.setEnabled(true);
                JOptionPane.showMessageDialog(null, "           No More Data to Display                  ", "Test Reports Generation", JOptionPane.WARNING_MESSAGE);

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "  Here.         ", "Test Reports Generation", JOptionPane.WARNING_MESSAGE);

        }

    }
    private void previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionPerformed

        get_previous(report_serial.getText());

        issave = true;

// TODO add your handling code here:
    }//GEN-LAST:event_previousActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        get_next(report_serial.getText());

        issave = true;


    }//GEN-LAST:event_nextActionPerformed

    private void search_rptKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_rptKeyPressed
        if (evt.getKeyChar() == evt.VK_ENTER) {
            search_report_btn.doClick();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_search_rptKeyPressed

    private void search_report_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_report_btnActionPerformed
        try {

            int id = Integer.parseInt(search_rpt.getText().trim());
            get_specific(id + "");
            issave = true;
            search_rpt.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Please Enter Digits Only or Valid Numbers         ", "Test Reports Generation", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_search_report_btnActionPerformed

    private void search_rptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_rptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_rptActionPerformed

    public void get_next(String id) {
        int rid = Integer.parseInt(id);

        id = rid + "";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patients_reports WHERE Report_Serial_ID> '" + id + "' ORDER BY Report_Serial_ID Asc LIMIT 1");

            if (rs.next()) {
                previous.setEnabled(true);

                report_serial.setText(rs.getString(1));
                get_specific(report_serial.getText());
                /*
               t1.setText(rs.getString(2));
                    t2.setText(rs.getString(3));
               String gen=rs.getString(4);
                  if(gen.equals("Male"))
        {
            t3.setSelectedIndex(1);
        }
        else if(gen.equals("Female"))
        {
            t3.setSelectedIndex(2);
        }
        else if(gen.equals("Select Gender"))
        t3.setSelectedIndex(0);
        else
            t3.setSelectedItem(gen);

                  
                  
                 
                  t4.setText(rs.getString(5));
                  
                  
                 t5.setSelectedItem(rs.getString(6));
                 
                 
                    t6.setSelectedIndex(0);
                 t7.setSelectedItem(rs.getString(8));
                date_lab.setText("[ "+rs.getString(9)+" ]");
                time_lab.setText("[ "+rs.getString(10)+" ]");
                    
                    
                t8.setText(rs.getString(11));
                    
                main_tests_count= Integer.parseInt(t8.getText());
                    
                t14.setText(rs.getString(12));
                    
                    
             price_counters2=   Double.parseDouble(t14.getText());
                    
                    
                  t10.setText(rs.getString(13));
                    t16.setText(rs.getString(14));
              t11.setText(rs.getString(15));
                  t12.setText(rs.getString(16));
                 t13.setText(rs.getString(17));
                   
                   model2.setRowCount(0);
                      try{
        Statement stt = con.createStatement();
        ResultSet rss = st.executeQuery("SELECT * FROM individual_tests where report_Id = '"+rid+"'");

            while(rss.next())
            {
                  String[] dataa=new String[4];
                   dataa[0]=rss.getString(1);
                   dataa[1]=rss.getString(2);
                   dataa[2]=rss.getString(3);
                   dataa[3]=rss.getString(5);
               model2.addRow(dataa);
             }
       
       }
       catch(Exception ex)
       {
        JOptionPane.showMessageDialog(null," Exception         "+ex.getMessage()+"  Here         ","Test Reports Generation",JOptionPane.WARNING_MESSAGE);
       }
                      
                  
                      
                 */

            } else {

                next.setEnabled(false);
                previous.setEnabled(true);
                JOptionPane.showMessageDialog(null, "           No More Data to Display Create New Report Add New data                 ", "Test Reports Generation", JOptionPane.WARNING_MESSAGE);

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "  Here.         ", "Test Reports Generation", JOptionPane.WARNING_MESSAGE);

        }

    }

    public void get_specific(String id) {

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patients_reports WHERE Report_Serial_ID= '" + id + "'");

            if (rs.next()) {

                next.setEnabled(true);
                previous.setEnabled(true);
                report_serial.setText(rs.getString(1));
                t1.setText(rs.getString(2));
                t2.setText(rs.getString(3));
                String gen = rs.getString(4);
                if (gen.equals("Male")) {
                    t3.setSelectedIndex(1);
                } else if (gen.equals("Female")) {
                    t3.setSelectedIndex(2);
                } else if (gen.equals("Select Gender")) {
                    t3.setSelectedIndex(0);
                } else {
                    t3.setSelectedItem(gen);
                }

                t4.setText(rs.getString(5));

                t5.setSelectedItem(rs.getString(6));

                t6.setSelectedIndex(0);
                t7.setSelectedItem(rs.getString(8));
                date_lab.setText("[ " + rs.getString(9) + " ]");
                time_lab.setText("[ " + rs.getString(10) + " ]");
                t8.setText(rs.getString(11));
                t14.setText(rs.getString(12));
                main_tests_count = Integer.parseInt(t8.getText());

                price_counters2 = Double.parseDouble(t14.getText());

                t10.setText(rs.getString(13));
                t16.setText(rs.getString(14));
                t11.setText(rs.getString(15));
                t12.setText(rs.getString(16));
                t13.setText(rs.getString(17));

                model2.setRowCount(0);
                try {
                    Statement stt = con.createStatement();
                    ResultSet rss = st.executeQuery("SELECT * FROM individual_tests where report_Id = '" + id + "'");

                    while (rss.next()) {
                        String[] dataa = new String[4];
                        dataa[0] = rss.getString(1);
                        dataa[1] = rss.getString(2);
                        dataa[2] = rss.getString(3);
                        dataa[3] = rss.getString(5);
                        model2.addRow(dataa);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "  Here         ", "Test Reports Generation", JOptionPane.WARNING_MESSAGE);
                }

            } else {

                next.setEnabled(true);
                previous.setEnabled(true);
                JOptionPane.showMessageDialog(null, "           Report with Given ID Doesnot Exists                 ", "Test Reports Generation", JOptionPane.WARNING_MESSAGE);

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "  Here.         ", "Test Reports Generation", JOptionPane.WARNING_MESSAGE);

        }

    }

    public double load_prices(String test_name) {
        double price = 0.0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.Test_Rate  FROM pathology_tests p join tests_catagories pt on pt.Category_Name=p.Test_category where pt.Category_Code='" + test_name + "' ");
            while (rs.next()) {

                price += Double.parseDouble(rs.getString("Test_Rate"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(tests_panel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }

    public void update_p1() {
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
                    if (t7.getSelectedItem().toString().equals("Self") || t7.getSelectedItem().toString().equals("OPD")); else {
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

        t14.setText(t14.getText().trim());
        String text3 = t14.getText();

        boolean chk1 = isvlaid(text1);
        boolean chk2 = isvlaid(text2);
        boolean chk3 = isvlaid(text3);
        if (chk1 == true || chk2 == true || chk3 == true) {
            String field = "";

            if (chk1 == true) {
                field = "Payed Amount";

            } else if (chk2 == true) {
                field = "Discount";

            } else if (chk3 == true) {
                field = "Total Amount";

            }

            JOptionPane.showMessageDialog(null, "           (    Please Correct   " + field + "    )   !              ", "Test Reports Generation", JOptionPane.WARNING_MESSAGE);

            if (chk1 == true) {
                t11.grabFocus();

            } else if (chk2 == true) {
                t10.grabFocus();

            } else if (chk3 == true) {
                t14.grabFocus();
            }
        } else if (t1.getText().equals("") || t2.getText().equals("") || t4.getText().equals("") || t5.getSelectedItem().equals("") || t8.getText().equals("") || t9.getText().equals("") || t10.getText().equals("") || t11.getText().equals("") || t12.getText().equals("") || t13.getText().equals("")) {

            int i = JOptionPane.showConfirmDialog(null, "                               Aleart ! Information Missing    !                            \n\nDo you want to Countinue    ?            ", "Test Reports Generation", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                update_part2();
            } else {
                t1.grabFocus();
            }

        } else {

            update_part2();
        }

    }

    public void update_part2() {
        String date = "";
        String time = "";
        String name_title = "";

        if (t1.getText().startsWith("Mr", 0) || t1.getText().startsWith("Ms", 0) || t1.getText().startsWith("MR", 0) || t1.getText().startsWith("MS", 0) || t1.getText().startsWith("mR", 0) || t1.getText().startsWith("mS", 0)) {
            name_title = "";
        } else {
            if (t3.getSelectedItem().toString().equals("Male")) {

                name_title = "Mr ";
            } else if (t3.getSelectedItem().toString().equals("Female")) {
                name_title = "Ms ";
            }

        }

        time = Home.timer.getText();
        date = Home.date1.getText();

        String dr_title = "";
        try {

            if (t7.getSelectedItem().toString().equals("Self") || t7.getSelectedItem().toString().equals("OPD")); else {
                if (t7.getSelectedItem().toString().startsWith("Dr", 0) || t7.getSelectedItem().toString().startsWith("dr", 0) || t7.getSelectedItem().toString().startsWith("dR", 0) || t7.getSelectedItem().toString().startsWith("DR", 0)) {
                    dr_title = "";
                } else {
                    dr_title = "Dr ";
                }
            }

            String speciman = "[";
            for (int i = 0; i < model2.getRowCount(); i++) {
                if (!speciman.contains(model2.getValueAt(i, 2).toString())) {
                    if (i == model2.getRowCount() - 1) {
                        speciman += model2.getValueAt(i, 2) + "]";
                    } else {
                        speciman += model2.getValueAt(i, 2) + ",";
                    }

                }

            }
            Statement st = con.createStatement();

            st.executeUpdate("update  patients_reports  set Patient_Name='" + name_title + t1.getText() + "',Age='" + t2.getText() + "',Sex='" + (t3.getSelectedItem().toString()) + "',Phone_Number='" + t4.getText() + "',Address='" + t5.getSelectedItem().toString() + "',Specimen='" + (speciman) + "',Referred_By='" + ((dr_title + t7.getSelectedItem().toString())) + "',Reporting_Date='" + (date) + "',Reporting_Time='" + (time) + "',Total_Tests='" + model2.getRowCount() + "',Total_Amount='" + t14.getText() + "',discount='" + t10.getText() + "',Grand_total='" + t16.getText() + "',Payed_Amount='" + t11.getText() + "',change_returned='" + t12.getText() + "',remaining_amount='" + t13.getText() + "' Where Report_Serial_ID ='" + report_serial.getText() + "'  ");

            update_part3_update();
            // save_part3();

            issave = true;

            JOptionPane.showMessageDialog(null, "          Patient Information is Updated Successfully in DataBase     !              ", "Test Reports Generation", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Exception         " + ex.getMessage() + "           ", "Test Reports Generation", JOptionPane.WARNING_MESSAGE);

        }

    }

    public void update_part3_update() {
        Statement stt;
        try {
            stt = con.createStatement();
            stt.executeUpdate("delete from individual_tests where Report_ID='" + report_serial.getText() + "'");

        } catch (SQLException ex) {
            Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < model2.getRowCount(); i++) {
            try {
                Statement st = con.createStatement();
                st.executeUpdate("INSERT INTO individual_tests (Serial_no,Test_Name,report_id,speciman,Tests_rate) VALUES   ('" + model2.getValueAt(i, 0) + "','" + model2.getValueAt(i, 1) + "','" + (report_serial.getText()) + "','" + model2.getValueAt(i, 2) + "','" + model2.getValueAt(i, 3) + "')");
            } catch (SQLException ex) {
                Logger.getLogger(test_reports_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton Refresh;
    private javax.swing.JTable cart_tab;
    public static javax.swing.JComboBox<String> category_Box;
    private javax.swing.JLabel date_lab;
    public static javax.swing.JButton enter_fresher;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable main_test_table;
    private javax.swing.JButton next;
    private javax.swing.JButton previous;
    public static javax.swing.JLabel report_serial;
    private javax.swing.JButton search_report_btn;
    private javax.swing.JTextField search_rpt;
    public static javax.swing.JTextField t1;
    private javax.swing.JTextField t10;
    private javax.swing.JTextField t11;
    private javax.swing.JTextField t12;
    private javax.swing.JTextField t13;
    private javax.swing.JTextField t14;
    private javax.swing.JTextField t16;
    private javax.swing.JTextField t2;
    private javax.swing.JComboBox<String> t3;
    private javax.swing.JTextField t4;
    private javax.swing.JComboBox<String> t5;
    private javax.swing.JComboBox<String> t6;
    private javax.swing.JComboBox<String> t7;
    private javax.swing.JLabel t8;
    private javax.swing.JTextField t9;
    public static javax.swing.JComboBox<String> test_box;
    private javax.swing.JComboBox<String> tests;
    private javax.swing.JLabel time_lab;
    // End of variables declaration//GEN-END:variables
}
