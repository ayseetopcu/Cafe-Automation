package form;

import database.CihazCrud;
import form.giris.FrmMusteriGiris;
import form.personel.FrmCihazEkle;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import property.Cihaz;

/**
 *
 * @author Tugay Demirel
 */
public class BaslangicSinifi {

    public static Socket s;
    public static DataInputStream din;
    public static DataOutputStream dout;
    
    
    ArrayList<Cihaz> cihazList = null;
    StringBuilder sb = new StringBuilder();
    private String masaId = "";

    boolean isExistMacAddress() {
        cihazList = (ArrayList<Cihaz>) new CihazCrud().read("");
        String macAdres = getMacAddress().toString();
        for (Cihaz cihaz : cihazList) {
            if (cihaz.getMacAdres().equals(macAdres)) {
                masaId = cihaz.getMasa_id();
                return true;
            }
        }
        return false;
    }

    public StringBuilder getMacAddress() {
        InetAddress ip;
        StringBuilder sb = new StringBuilder();
        try {

            ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            System.out.print("Current MAC address : ");

            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            System.out.println(sb.toString());
            this.sb = sb;

        } catch (UnknownHostException e) {

            e.printStackTrace();

        } catch (SocketException e) {

            e.printStackTrace();

        }
        return sb;
    }

    String hostName() {
        String hostname = "";
        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
            System.out.println("Hostname: " + hostname);
        } catch (UnknownHostException ex) {
            System.out.println("Hostname can not be resolved");
        }
        return hostname;
    }

    public static void main(String[] args) {
        BaslangicSinifi baslangicSinifi = new BaslangicSinifi();

        if (!baslangicSinifi.isExistMacAddress()) {
            String a = baslangicSinifi.sb.toString();
            System.out.println("a : " + a);
            new FrmCihazEkle(baslangicSinifi.cihazList, (baslangicSinifi.sb).toString(), baslangicSinifi.hostName()).setVisible(true);
        } else {
            new FrmMusteriGiris(baslangicSinifi.masaId).setVisible(true);
        }
        
        try{
            s =new Socket("localhost",1201);// address ip local du client w serveur m7it khdamin f local 
            din=new DataInputStream(s.getInputStream());
            dout=new DataOutputStream(s.getOutputStream());
            String msgin="";
            
            while(!msgin.equals("exit")){
                
                msgin=din.readUTF();
                //Buraya da serverdan gelenleri yazacaz
            }
            
            
        }catch(Exception e){}
    }

}
