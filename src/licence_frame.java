
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ALI HAIDER
 */
public class licence_frame extends javax.swing.JFrame {

    /**
     * Creates new form licence_frame
     */
    public static Connection get_Connection() throws ClassNotFoundException {
        String database_url = "";
        String username = "";
        String password = "";
        ResultSet rs = null;
        Statement stt;
        Connection conn = databaseconnetion.get_Connection();
        try {
            stt = conn.createStatement();
            rs = stt.executeQuery("SELECT * FROM remote_connection LIMIT 1");

        } catch (SQLException ex) {
            Logger.getLogger(licence_frame.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (rs.next()) {
                try {
                    database_url = "jdbc:mysql://" + rs.getString(2) + ":" + rs.getString(3) + "/" + rs.getString(4);
                    username = rs.getString(5);
                    password = rs.getString(6);
                } catch (SQLException ex) {
                    Logger.getLogger(licence_frame.class.getName()).log(Level.SEVERE, null, ex);
                }

                Connection con = null;

                Class.forName("com.mysql.jdbc.Driver");

                try {
                    con = (Connection) DriverManager.getConnection(database_url, username, password);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Exception here              " + ex + "     ", "License Activation", JOptionPane.ERROR_MESSAGE);
                }

                return con;
            }
        } catch (SQLException ex) {
            Logger.getLogger(licence_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, " Remote Connection Database Details Missing from Remote Connection Table in Xamp   ! ", "Database Error", JOptionPane.ERROR_MESSAGE);
        return null;

    }

    /*
    
        public static Connection get_Connection() throws ClassNotFoundException
{
    Connection con= null;
     String database_url="jdbc:mysql://127.0.0.1:3306/licenses";
    String user_name="root";
    String passward="";
    
    Class.forName("com.mysql.jdbc.Driver");
        try {
            con=(com.mysql.jdbc.Connection) DriverManager.getConnection(database_url,user_name,passward);
           //JOptionPane.showMessageDialog(null,"           Connecttion Established with Database    !              ","Patholgy Lab",JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Exception in Connection              "+ex+"     ","Pathology Lab Software",JOptionPane.ERROR_MESSAGE);
        }
    return con;
}
  
     */
    Connection con = null;

    public licence_frame() {
        initComponents();
        jTextField1.grabFocus();
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\images\\frameicon.png");
        this.setIconImage(icon);

        dc.setSelected(true);

        jTextArea1.setText("\n                 Software License Validation Agreement\n"
                + "\n"
                + "    Copyright (c) [2021] [Clinical Lab Solutions]\n"
                + "\n"
                + "    Permission is here by granted by the Software Developer Mr Ali\n"
                + "    Haider to any person  obtaining  a copy of this  software  and\n"
                + "    associated documentation files ( \" Clinical Lab Solutions \" ),\n"
                + "    to deal in the Software  without restriction , excluding copy, \n"
                + "    modify, merge, publish, distribute, sublicense, selling copies \n"
                + "    of the Software , and License Holders are not Allowed to Share \n"
                + "    this Software to whom Software is not Delivered  and  Approved\n"
                + "    by the Developer  Software Otherwise You may Lose your License \n"
                + "    as well. \n"
                + "\n"
                + "    The above copyright notice and this permission notice shall be\n"
                + "    included in all copies or substantial portions of the Software.\n"
                + "\n"
                + "\n"
                + "    \"THE SOFTWARE IS PROVIDED \" AS IT IS \",WITHOUT WARRANTY OF ANY \n"
                + "    KIND , EXPRESS OR IMPLIED , INCLUDING  BUT NOT  LIMITED TO THE\n"
                + "    WARRANTIES OF MERCHANTABILITY,FITNESS FOR A PARTICULAR PURPOSE\n"
                + "    AND NON  INFRINGEMENT IN NO EVENT SHALL THE  AUTHORS  OR  COPY\n"
                + "    RIGHT  HOLDERS BE  LIABLE  FOR ANY  CLAIM , DAMAGES  OR  OTHER\n"
                + "    LIABILITY , WHETHER IN AN ACTION OF CONTRACT,TORT OR OTHERWISE,\n"
                + "    ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE\n"
                + "    USE OR OTHER DEALINGS IN THE SOFTWARE\".\n"
                + "\n"
                + "\n"
                + "    License Holder must have to read and undertake all conditions.\n"
                + " \n"
                + "    By Acceting  this you will be stirck to this  Software License \n"
                + "    Validation Undertaking. \n"
                + "    Agreement which may be used further  against illegal action by\n"
                + "    the License Holder if essential.\n"
                + "\n"
                + "\n"
                + "\n"
                + "    Product          : Clinical Lab Solutions\n"
                + "    Email Address 01 : alihaider.ah00000@gmail.com\n"
                + "    Email Address 02 : alihaider.ah0000@gmail.com\n"
                + "    Phone Number  01 : +923068983139\n"
                + "    Phone Number  02 : +923143064902\n"
                + "\n"
                + "\n"
                + "                                              Regards Ali Haider\n\n");
        jTextArea1.setCaretPosition(0);

        try {
            con = get_Connection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(licence_frame.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        ac = new javax.swing.JRadioButton();
        dc = new javax.swing.JRadioButton();
        validatebtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clinical Lab Solutions (License Validation Agreement)");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo140.gif"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 22)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Clinical Lab Solutions");

        jLabel3.setFont(new java.awt.Font("Rockwell", 0, 25)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Software License Agreement ");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(226, 226, 226));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setToolTipText("");
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jTextArea1.setSelectionColor(new java.awt.Color(19, 80, 161));
        jScrollPane1.setViewportView(jTextArea1);

        ac.setBackground(new java.awt.Color(255, 255, 255));
        ac.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        ac.setText("I accept the License agreement");
        ac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acActionPerformed(evt);
            }
        });

        dc.setBackground(new java.awt.Color(255, 255, 255));
        dc.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        dc.setText("I do not accept the License agreement");
        dc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dcActionPerformed(evt);
            }
        });

        validatebtn.setBackground(new java.awt.Color(251, 152, 200));
        validatebtn.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        validatebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/validate.png"))); // NOI18N
        validatebtn.setContentAreaFilled(false);
        validatebtn.setEnabled(false);
        validatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validatebtnActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(251, 152, 200));
        jButton2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/quit.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        jTextField1.setSelectionColor(new java.awt.Color(19, 80, 161));

        jLabel4.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel4.setText("Enter License Validation Product Key");

        jLabel7.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel7.setText("Make sure to enter Prodcut key provided by the developer to validate the software for use.");

        jButton10.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
        jButton10.setText("Refresh ");
        jButton10.setBorder(null);
        jButton10.setContentAreaFilled(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gmail.png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/whatsapp.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fiverr.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/instagram.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/linkedin.png"))); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/facebook.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Software Developed and Licensed By ");

        jLabel14.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("ALI HAIDER");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ac, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dc, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(validatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ac)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dc)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton6)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addComponent(jLabel14)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void acActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acActionPerformed
        dc.setSelected(false);
        validatebtn.setEnabled(true);
// TODO add your handling code here:
    }//GEN-LAST:event_acActionPerformed

    private void dcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dcActionPerformed
        ac.setSelected(false);
        validatebtn.setEnabled(false);

// TODO add your handling code here:
    }//GEN-LAST:event_dcActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void validatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validatebtnActionPerformed
        try {
            String Licens_key = jTextField1.getText().trim();
            String ip = license_validation_machine_informations.get_ip();
            String mac = license_validation_machine_informations.get_mac();

            if (Licens_key.isEmpty() == true) {
                JOptionPane.showMessageDialog(null, "           Please enter Product Key that is Provided by Developer or Contact Develper to Purchase Product Key.           ", "License Activation (License Validtaion Agreement)", JOptionPane.INFORMATION_MESSAGE);
            } else if (Licens_key.isEmpty() == false && ac.isSelected() == true) {
                Statement st1;
                try {
                    st1 = con.createStatement();
                    ResultSet rs1 = st1.executeQuery("SELECT * FROM Licenses WHERE binary License_Activation_key = binary '" + Licens_key + "' AND binary ip_address = binary '" + ip + "' AND binary mac_address = binary'" + mac + "'");

                    if (rs1.next()) {
                        if (rs1.getString(11).trim().equalsIgnoreCase("Pending") || rs1.getString(11).trim().equalsIgnoreCase("Approved") && rs1.getString(5).equals(ip) && rs1.getString(6).equals(mac)) {
                            Statement st2;
                            st2 = con.createStatement();
                            st2.executeUpdate("UPDATE Licenses SET ip_address='" + ip + "',mac_address ='" + mac + "',License_current_status='Approved' WHERE binary License_Activation_key = binary '" + Licens_key + "' and ip_address='" + ip + "' and mac_address ='" + mac + "' ");
                            JOptionPane.showMessageDialog(null, "                     Congragulations your Clinical Lab Solutions License has been Activated.                    ", "License Activation (License Validtaion Agreement)", JOptionPane.INFORMATION_MESSAGE);
                            new login_frame().setVisible(true);
                            this.dispose();
                        } else if (rs1.getString(11).trim().equalsIgnoreCase("unApproved")) {
                            JOptionPane.showMessageDialog(null, "           Please enter the Approved Product Key or Contact Develper to Purchase Product Key.           ", "License Activation (License Validtaion Agreement)", JOptionPane.INFORMATION_MESSAGE);
                            jTextField1.grabFocus();

                        } else if (rs1.getString(11).trim().equalsIgnoreCase("Expired") || rs1.getString(11).trim().equalsIgnoreCase("Expire")) {
                            JOptionPane.showMessageDialog(null, "           Your License has been Expired (   according to the License Expiry Date i-e   " + rs1.getString(7).trim() + "   ) Contact Develper for further Queries or enter new product Key provided by the Developer.          ", "License Activation (License Validtaion Agreement)", JOptionPane.INFORMATION_MESSAGE);
                            jTextField1.grabFocus();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "           Please enter Valid Product Key that is  Provided by Developer or Contact Develper to Purchase Product Key.           ", "License Activation (License Validtaion Agreement)", JOptionPane.INFORMATION_MESSAGE);
                        jTextField1.grabFocus();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(licence_frame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "           Exception      " + ex + "           ", "License Activation (License Validtaion Agreement)", JOptionPane.ERROR_MESSAGE);

        }// TODO add your handling code here:
    }//GEN-LAST:event_validatebtnActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        new licence_frame().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        try {
            Desktop.getDesktop().browse(new URL("https://mail.google.com/mail/u/0/#inbox?compose=GTvVlcSHvZzbTTmffsmGTzxsdKmnJTrNQwwbthcNpMWWgDKkJfHRCcrPgcHBpbVTKBsgJLFkJVlrV").toURI());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        try {
            Desktop.getDesktop().browse(new URL("https://web.whatsapp.com/").toURI());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        try {
            Desktop.getDesktop().browse(new URL("https://www.fiverr.com/s2/0bf5a32b01").toURI());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        try {
            Desktop.getDesktop().browse(new URL("https://www.instagram.com/invites/contact/?i=c6k4gqbmmf9&utm_content=1upyagb").toURI());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }             // TODO add your handling code here:
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        try {
            Desktop.getDesktop().browse(new URL("https://www.facebook.com/itsme.sunni").toURI());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }            // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

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
            java.util.logging.Logger.getLogger(licence_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(licence_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(licence_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(licence_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new licence_frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ac;
    private javax.swing.JRadioButton dc;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton validatebtn;
    // End of variables declaration//GEN-END:variables
}
