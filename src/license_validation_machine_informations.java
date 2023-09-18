/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ALI HAIDER
 */
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class license_validation_machine_informations {

    public static String get_ip() {
        String ip_address = "";
        try {

            InetAddress ip;
            ip = InetAddress.getLocalHost();
            ip_address = ip.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(license_validation_machine_informations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ip_address;

    }

    public static String get_mac() {

        String mac_address = "";

        try {
            InetAddress ip = null;
            try {
                ip = InetAddress.getLocalHost();
            } catch (UnknownHostException ex) {
                Logger.getLogger(license_validation_machine_informations.class.getName()).log(Level.SEVERE, null, ex);
            }
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            mac_address = sb.toString();

        } catch (SocketException ex) {
            Logger.getLogger(license_validation_machine_informations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mac_address;

    }

    public static void main(String[] args) {

    }
}
