/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wow
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class ReportView extends JFrame {

    public ReportView() {
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\images\\frameicon.png");
        this.setIconImage(icon);

    }

    public ReportView(String fileName) {

        this(fileName, null);
    }

    public ReportView(String fileName, HashMap para) {

        super("Clinical Lab Soltuions (Report Viewer)");
        super.setBackground(Color.white);

        Connection con = null;

        con = databaseconnetion.get_Connection();

//        
//        Dbcon dba;
//        dba = new Dbcon();
//        java.sql.Connection con;
//        con = Dbcon.mycon();
//       
        try {
            JasperPrint print = JasperFillManager.fillReport(fileName, para, con);

            JRViewer viewer = new JRViewer(print);
            viewer.setBackground(Color.WHITE);
            Container c = getContentPane();
            c.setBackground(Color.WHITE);
            c.add(viewer);
        } catch (JRException jRException) {
            System.out.println("Exception Here ");
        }
        setBounds(2, 2, 900, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    void print() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
