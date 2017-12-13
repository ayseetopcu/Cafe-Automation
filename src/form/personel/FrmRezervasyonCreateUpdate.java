/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.personel;

import database.MasaCrud;
import database.RezervasyonCrud;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import property.Masa;
import property.Rezervasyon;

/**
 *
 * @author Tugay Demirel
 */
public class FrmRezervasyonCreateUpdate extends javax.swing.JFrame {

    public static final String TEL_REGEX = "(([\\+]90?)|([0]?))([ ]?)((\\([0-9]{3}\\))|([0-9]{3}))([ ]?)([0-9]{3})(\\s*[\\-]?)([0-9]{2})(\\s*[\\-]?)([0-9]{2})";
    private HashMap<Integer, String> rezervList = new HashMap<>();
    private Rezervasyon rzvr = new Rezervasyon();
    private boolean isCreate = true;
    private String oldMasaId = "";

    public FrmRezervasyonCreateUpdate() {
        initComponents();
        cmbLokasyon.setEnabled(false);
        dateChooser.setMinSelectableDate(new Date());
        timePicker.setEnabled(false);
    }
    
    public FrmRezervasyonCreateUpdate(Rezervasyon rezervasyon) {
        isCreate = false;
        oldMasaId = ""+rezervasyon.getMasaId();
        rzvr = rezervasyon;
        initComponents();
        cmbLokasyon.setEnabled(false);
        dateChooser.setMinSelectableDate(new Date());
        timePicker.setEnabled(false);
        
        txtAd.setText(rezervasyon.getAd());
        txtTelefon.setText(rezervasyon.getTelefon());
        dateChooser.setDate(rezervasyon.getDate());
//        timePicker.setText(rezervasyon.getDate().getHours()+":"+rezervasyon.getDate().getMinutes());
        rezervDate = rezervasyon.getDate();
        rezervasyonGetir();
    }

    void rezervasyonGetir() {
        rezervList.clear();

        ArrayList<Rezervasyon> rezervasyons = (ArrayList<Rezervasyon>) new RezervasyonCrud().read(rezervDate);
        ArrayList<Masa> masaList = (ArrayList<Masa>) new MasaCrud().read("");

        if (!rezervasyons.isEmpty()) {
            masaList.forEach((masa) -> {
                rezervasyons.stream().filter((rezervasyon) -> !(masa.getId() == rezervasyon.getMasaId())).forEachOrdered((_item) -> {
                    rezervList.put(masa.getId(), masa.getMasaAdi());
                });
            });
        } else {
            masaList.forEach((masa) -> {
                rezervList.put(masa.getId(), masa.getMasaAdi());
            });
        }

        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();

        rezervList.entrySet().stream().map((entry) -> {
            Integer key = entry.getKey();
            return entry;
        }).map((entry) -> entry.getValue()).forEachOrdered((value) -> {
            dcbm.addElement(value);
        });

        cmbLokasyon.setModel(dcbm);
        rezervasyons.clear();
        masaList.clear();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtAd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTelefon = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbLokasyon = new javax.swing.JComboBox<>();
        txtMasaEkle = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        timePicker = new com.github.lgooddatepicker.components.TimePicker();
        dateChooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("İsim");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Telefon");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Masa");

        cmbLokasyon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtMasaEkle.setText("Ekle");
        txtMasaEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMasaEkleActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tarih");

        timePicker.setEnabled(false);
        timePicker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timePickerMouseClicked(evt);
            }
        });
        timePicker.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                timePickerPropertyChange(evt);
            }
        });

        dateChooser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateChooserMouseClicked(evt);
            }
        });
        dateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateChooserPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(cmbLokasyon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtMasaEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(20, 20, 20)
                                            .addComponent(txtAd, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(188, 188, 188)
                                .addComponent(timePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbLokasyon)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMasaEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMasaEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMasaEkleActionPerformed
        if (validation()) {
            rzvr.setAd(!isCreate ? txtAd.getText().trim()+"###"+oldMasaId : txtAd.getText().trim());
            rzvr.setTelefon(txtTelefon.getText().trim());

            for (Map.Entry<Integer, String> e : rezervList.entrySet()) {
                Object key = e.getKey();
                Object value = e.getValue();
                if(value.equals(cmbLokasyon.getSelectedItem())){
                    rzvr.setMasaId((int) key);
                    break;
                }
            }
            rzvr.setDate(rezervDate);
            if(isCreate ? new RezervasyonCrud().create(rzvr) : new RezervasyonCrud().update(rzvr)){
                JOptionPane.showMessageDialog(this, "Kayıt Başarılı...");
                FrmPersonelAnaSayfa.refresh();
                dispose();
            }
        }
    }//GEN-LAST:event_txtMasaEkleActionPerformed

    private void timePickerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timePickerMouseClicked
    }//GEN-LAST:event_timePickerMouseClicked

    private void dateChooserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateChooserMouseClicked
    }//GEN-LAST:event_dateChooserMouseClicked

    private Date rezervDate = null;

    private void dateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooserPropertyChange
        rezervDate = dateChooser.getDate();
        if (!timePicker.isEnabled()) {
            timePicker.setEnabled(true);
        } else if (timePicker.getTime() != null) {
            setTimes();
            rezervasyonGetir();
        }
        timePicker.requestFocus();
    }//GEN-LAST:event_dateChooserPropertyChange

    private void timePickerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_timePickerPropertyChange
        if (timePicker.getTime() != null) {
            setTimes();
            cmbLokasyon.setEnabled(true);
            rezervasyonGetir();
        }
    }//GEN-LAST:event_timePickerPropertyChange

    void setTimes() {
        rezervDate.setHours(timePicker.getTime().getHour());
        rezervDate.setMinutes(timePicker.getTime().getMinute());
        rezervDate.setSeconds(timePicker.getTime().getSecond());
    }

    boolean validation() {
        if (txtAd.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir isim giriniz!!!");
            txtAd.requestFocus();
            txtAd.selectAll();
            return false;
        } else if (!isNumeric(txtTelefon.getText().trim()) || !txtTelefon.getText().matches(TEL_REGEX)) {
            JOptionPane.showMessageDialog(this, "Geçersiz telefon numarası!!!");
            txtTelefon.requestFocus();//ilgili texte odaklar
            txtTelefon.selectAll();
            return false;
        } else if (rezervDate == null) {
            JOptionPane.showMessageDialog(this, "Lütfen tarihi ve saati seçiniz!!!");
            return false;
        }

        return true;
    }

    public boolean isNumeric(String data) {
        boolean durum = false;
        char[] dizi = data.toCharArray();
        for (int i = 0; i < dizi.length; i++) {
            if (Character.isDigit(dizi[i])) {
                durum = true;
            } else {
                durum = false;
                break;
            }
        }
        return durum;
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
            java.util.logging.Logger.getLogger(FrmRezervasyonCreateUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRezervasyonCreateUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRezervasyonCreateUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRezervasyonCreateUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRezervasyonCreateUpdate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbLokasyon;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private com.github.lgooddatepicker.components.TimePicker timePicker;
    private javax.swing.JTextField txtAd;
    private javax.swing.JButton txtMasaEkle;
    private javax.swing.JTextField txtTelefon;
    // End of variables declaration//GEN-END:variables
}
