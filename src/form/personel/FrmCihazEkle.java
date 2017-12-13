/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.personel;

import database.CihazCrud;
import database.MasaCrud;
import form.giris.FrmMusteriGiris;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import property.Cihaz;
import property.Masa;

/**
 *
 * @author Tugay Demirel
 */
public class FrmCihazEkle extends javax.swing.JFrame {

    ArrayList<Cihaz> cihazList = new ArrayList<>();
    private Cihaz cihaz = new Cihaz();

    public FrmCihazEkle() {
        initComponents();
        masaDoldur();
    }

    public FrmCihazEkle(ArrayList<Cihaz> cihazList,
            String macAdresi,
            String hostName) {
        initComponents();
        txtMacAdresi.setText(macAdresi);
        txtCihazAdi.setText(hostName);

        txtMacAdresi.setEditable(false);
        txtCihazAdi.setEditable(false);
        this.cihazList = (ArrayList<Cihaz>) cihazList.clone();
        cihaz.setComputerName(hostName);
        cihaz.setMacAdres(macAdresi);
        masaDoldur();
    }

    ArrayList<Masa> masaList = null;

    void masaDoldur() {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        dcbm.addElement("Masalar");
        masaList = (ArrayList<Masa>) new MasaCrud().read(1);
        System.out.println("####################################3");
        for (Masa masa : masaList) {
            System.out.println(masa.getMasaAdi());
        }
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        for (Cihaz cihaz : cihazList) {
            System.out.println(cihaz.getMasa_id());
        }

        for (Masa masa : masaList) {
            System.out.println("masa: " + masa.getMasaAdi());
            boolean isMasaExist = false;
            for (Cihaz cihaz : cihazList) {
                if (masa.getId() == Integer.valueOf(cihaz.getMasa_id())) {
                    System.out.println("karşılaştırma: " + masa.getId() + "," + cihaz.getMasa_id());
                    isMasaExist = true;
//                    break;
                }
            }
            System.out.println("masa var mı : " + isMasaExist);
            if (!isMasaExist) {
                dcbm.addElement(masa.getMasaAdi());
            }
        }
        cmbMasa.setModel(dcbm);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtMacAdresi = new javax.swing.JTextField();
        btnCihazEkle = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cmbMasa = new javax.swing.JComboBox<>();
        txtCihazAdi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Masa Ekle");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mac Adresi");

        btnCihazEkle.setText("Ekle");
        btnCihazEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCihazEkleActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Masa");

        cmbMasa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cihaz Adı");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbMasa, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnCihazEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMacAdresi, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCihazAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMacAdresi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCihazAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMasa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCihazEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCihazEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCihazEkleActionPerformed
        System.out.println("selected item :" + cmbMasa.getSelectedIndex());
        if (cmbMasa.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Lütfen bir masa seçiniz!!!");
        } else {
            String selectedValue = (String) cmbMasa.getSelectedItem();
            Masa m = null;
            for (Masa masa : masaList) {
                if(selectedValue.equals(masa.getMasaAdi())){
                    cihaz.setMasa_id(masa.getId()+"");
                    break;
                }
            }
            if (new CihazCrud().create(cihaz)) {
                JOptionPane.showMessageDialog(this, "Cihaz başarıyla eklendi...");
                new FrmMusteriGiris(cihaz.getMasa_id()).setVisible(true);
            }
            dispose();
        }


    }//GEN-LAST:event_btnCihazEkleActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
//        BaslangicSinifi.main(null);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(FrmCihazEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCihazEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCihazEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCihazEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCihazEkle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCihazEkle;
    private javax.swing.JComboBox<String> cmbMasa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtCihazAdi;
    private javax.swing.JTextField txtMacAdresi;
    // End of variables declaration//GEN-END:variables
}
