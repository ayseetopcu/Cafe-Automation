
package form.baslangic;

import form.giris.FrmPersonelAnaGiris;
import form.personel.FrmPersonelAnaSayfa;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tugay Demirel
 */
public class PersonelBaslangic {
    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPersonelAnaGiris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPersonelAnaGiris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPersonelAnaGiris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPersonelAnaGiris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPersonelAnaGiris().setVisible(true);
            }
        });

        String msgin = "";
        try {
            ss = new ServerSocket(1201);
            s = ss.accept();

            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            while (!msgin.equals("exit")) {
                msgin = din.readUTF();
                System.out.println(msgin);
                String[] mesajBilgileri = msgin.split(",");

                JOptionPane.showMessageDialog(null, mesajBilgileri[2],mesajBilgileri[1]+"-Müşteri Talebi",JOptionPane.WARNING_MESSAGE);

                String masaId = mesajBilgileri[0];

                DefaultTableModel dtm = (DefaultTableModel) FrmPersonelAnaSayfa.tblMasa.getModel();
                for (int i = 0; i < dtm.getRowCount(); i++) {
                    String columnValue = (String) dtm.getValueAt(i, 0);
                    if (masaId.equals(columnValue)) {
                        FrmPersonelAnaSayfa.kontrol++;
                        FrmPersonelAnaSayfa.tblMasa.setRowSelectionInterval(i, i);
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

}
