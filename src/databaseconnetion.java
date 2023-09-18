
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
public class databaseconnetion {

    /**
     *
     * @return
     */
    public static Connection get_Connection() {
        Connection con = null;
        String database_url = "jdbc:mysql://127.0.0.1:3306/pathology_lab_software";
        String user_name = "root";
        String passward = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Exception in Connection              " + ex + "     ", "Database Connection ", JOptionPane.ERROR_MESSAGE);
        }
        try {
            con = (Connection) DriverManager.getConnection(database_url, user_name, passward);
            //JOptionPane.showMessageDialog(null,"           Connecttion Established with Database    !              ","Patholgy Lab",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception in Connection              " + ex + "     ", "Database Connection ", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }

}
