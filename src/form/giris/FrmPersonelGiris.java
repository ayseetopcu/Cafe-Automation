package form.giris;

import database.PersonelCrud;
import form.personel.FrmPersonelAnaSayfa;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import property.Personel;

/**
 *
 * @author Java_sabah
 */
public class FrmPersonelGiris extends javax.swing.JFrame {

    public static String ad = "";
    public static String sifre = "";
    private int statu = 0;

    public FrmPersonelGiris(int statu) {
        initComponents();
        if (statu == 1) {
            pnlArka.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Çalışan Girişi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 20)));
        } else if (statu == 2) {
            pnlArka.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Yönetici Girişi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 20)));
        }
        this.statu = statu;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlArka = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtSifre = new javax.swing.JPasswordField();
        btnGiris = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlArka.setBackground(new java.awt.Color(0, 102, 102));
        pnlArka.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personel Girişi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("E-Mail ");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Şifre            ");

        btnGiris.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGiris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/if_sign-in.png"))); // NOI18N
        btnGiris.setText("Giriş");
        btnGiris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGirisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlArkaLayout = new javax.swing.GroupLayout(pnlArka);
        pnlArka.setLayout(pnlArkaLayout);
        pnlArkaLayout.setHorizontalGroup(
            pnlArkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlArkaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlArkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlArkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(txtSifre)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlArkaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGiris, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        pnlArkaLayout.setVerticalGroup(
            pnlArkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlArkaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlArkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnlArkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnGiris, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlArka, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlArka, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGirisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGirisActionPerformed
        if (validation()) {
            ArrayList<Personel> personelList = (ArrayList<Personel>) new PersonelCrud().read(statu);
            boolean kontrol = false;
            for (Personel personel : personelList) {
                if (txtEmail.getText().trim().equals(personel.getEmail()) && txtSifre.getText().equals(personel.getSifre())) {
                    kontrol = true;
                    new FrmPersonelAnaSayfa(personel).setVisible(true);
                    dispose();
                }
            }
            if (!kontrol) {
                JOptionPane.showMessageDialog(this, "Kullanıcı adı ya da şifre hatalı!!!");
            }
        }
    }//GEN-LAST:event_btnGirisActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        new FrmPersonelAnaGiris().setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    boolean validation() {
        if (txtEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir isim giriniz!!!");
            txtEmail.requestFocus();//ilgili texte odaklar
            txtEmail.selectAll();
            return false;
        } else if (txtSifre.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir soyisim giriniz!!!");
            txtSifre.requestFocus();//ilgili texte odaklar
            txtSifre.selectAll();
            return false;
        }
        return true;
    }

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
            java.util.logging.Logger.getLogger(FrmPersonelGiris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPersonelGiris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPersonelGiris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPersonelGiris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPersonelGiris(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGiris;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pnlArka;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtSifre;
    // End of variables declaration//GEN-END:variables
}
