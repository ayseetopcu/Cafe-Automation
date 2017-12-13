package form.musteri;

import database.KategoriCrud;
import database.SiparisCrud;
import database.UrunCrud;
import form.panelDeneme;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import property.Kategori;
import property.Musteri;
import property.Siparis;
import property.Urun;

/**
 *
 * @author aylin
 */
public class FrmMusteriSiparis extends javax.swing.JFrame {

    ArrayList<Urun> siparislerList = new ArrayList<>();
    private String masaId;
    private Musteri musteri = null;

    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

    public FrmMusteriSiparis(Musteri musteri,
            String masaId) {
        initComponents();
        this.masaId = masaId;
        this.musteri = musteri;
        varsaSiparisGetir();
        kategoriGetir();

        System.out.println("sipariş formuna gelen masaId : " + masaId);
    }

    void kategoriGetir() {
        ArrayList<Kategori> kategoriliList = (ArrayList<Kategori>) new KategoriCrud().read("");

        for (Kategori kategori : kategoriliList) {
            ArrayList<Urun> urunList = (ArrayList<Urun>) new UrunCrud().read(kategori);
            JScrollPane scrollPane = new JScrollPane();
            jTabbedPane6.addTab(kategori.getName(), scrollPane);
            panelDeneme pnDeneme = new panelDeneme(urunList);
            scrollPane.setViewportView(pnDeneme);
        }
    }

    void varsaSiparisGetir() {
        ArrayList<Siparis> siparises = (ArrayList<Siparis>) new SiparisCrud().read(masaId);
        Siparis s = null;
        if (siparises.size() > 0) {
            s = siparises.get(0);
        }
        if (s != null) {
            String[] urunIdleri = s.getIcerik().split("###");
            for (String string : urunIdleri) {
                System.out.println("urunnnnn: " + string);
            }
            for (String string : urunIdleri) {
                System.out.println("gönderilen urun id : " + string);
                if (!string.equals("")) {
                    siparislerList.add((Urun) new UrunCrud().read(string).get(0));
                    tabloyuDoldur();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSiparisler = new javax.swing.JTable();
        btnSiparisSil = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        btnSiparisTamamla = new javax.swing.JButton();
        btnSiparisIptal = new javax.swing.JButton();
        txtToplamFiyat = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnEkle = new javax.swing.JButton();
        txtUrunAdi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtUrunDetay = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUrunFiyat = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sipariş Ver");
        setBackground(new java.awt.Color(153, 153, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane6.setBackground(new java.awt.Color(204, 153, 255));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Siparişlerim"));

        tblSiparisler.setBackground(new java.awt.Color(204, 204, 255));
        tblSiparisler.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSiparisler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSiparislerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSiparisler);

        btnSiparisSil.setBackground(new java.awt.Color(102, 102, 255));
        btnSiparisSil.setText("Seçili Siparişi Sil");
        btnSiparisSil.setPreferredSize(new java.awt.Dimension(120, 50));
        btnSiparisSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiparisSilActionPerformed(evt);
            }
        });

        jLabel41.setText("Toplam Fiyat :");

        btnSiparisTamamla.setBackground(new java.awt.Color(102, 102, 255));
        btnSiparisTamamla.setText("Siparişi Tamamla");
        btnSiparisTamamla.setPreferredSize(new java.awt.Dimension(120, 50));
        btnSiparisTamamla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiparisTamamlaActionPerformed(evt);
            }
        });

        btnSiparisIptal.setBackground(new java.awt.Color(102, 102, 255));
        btnSiparisIptal.setText("Siparişi İptal Et");
        btnSiparisIptal.setEnabled(false);
        btnSiparisIptal.setPreferredSize(new java.awt.Dimension(120, 50));
        btnSiparisIptal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiparisIptalActionPerformed(evt);
            }
        });

        txtToplamFiyat.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(btnSiparisTamamla, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSiparisSil, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSiparisIptal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtToplamFiyat)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSiparisSil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(btnSiparisTamamla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSiparisIptal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtToplamFiyat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ürün Bilgileri"));

        jLabel1.setText("Adı");

        btnEkle.setText("Ekle");
        btnEkle.setEnabled(false);
        btnEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEkleActionPerformed(evt);
            }
        });

        txtUrunAdi.setEditable(false);

        jLabel2.setText("Detay");

        txtUrunDetay.setEditable(false);

        jLabel3.setText("Fiyat");

        txtUrunFiyat.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUrunAdi))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUrunFiyat)
                            .addComponent(txtUrunDetay, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)))
                    .addComponent(btnEkle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtUrunAdi)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUrunDetay, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUrunFiyat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static Urun urun = null;
    private int siparisTutar = 0;
    private void btnEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEkleActionPerformed
        siparislerList.add(urun);
        siparisTutar += Integer.valueOf(urun.getFiyat());
        tabloyuDoldur();
        txtToplamFiyat.setText(siparisTutar + "");
    }//GEN-LAST:event_btnEkleActionPerformed

    private void btnSiparisTamamlaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiparisTamamlaActionPerformed
        String siparisIcerik = "";

        for (Urun urun1 : siparislerList) {
            System.out.println("qewrl");
            siparisIcerik += "###" + urun1.getId();
            System.out.println("urun adı: " + urun1.getAd());
        }
        System.out.println("sipariş içerik : " + siparisIcerik);
        System.out.println("sipariş tutar : " + siparisTutar);

        Siparis siparis = new Siparis();
        siparis.setIcerik(siparisIcerik);
        FrmMusteriAnaSayfa.masaninSiparisi.setIcerik(siparisIcerik);
        FrmMusteriAnaSayfa.masaninSiparisi.setTutar("" + siparisTutar);
        siparis.setTutar(siparisTutar + "");
        System.out.println("siparis masa id : " + siparis.getMasaId());
        siparis.setMasaId(Integer.valueOf(masaId));

        System.out.println("siparis masa id : " + siparis.getMasaId());
        ArrayList<Siparis> siparises = (ArrayList<Siparis>) new SiparisCrud().read(masaId);
        Siparis s = null;
        if (siparises.size() > 0) {
            s = siparises.get(0);
        }

        if (s == null) {
            if (new SiparisCrud().create(siparis)) {
                JOptionPane.showMessageDialog(this, "Sipariş alındı...");
                System.out.println("masaaaa" + siparis.getMasaId());
            }
        } else {
            if (new SiparisCrud().update(siparis)) {
                JOptionPane.showMessageDialog(this, "Sipariş güncellendi...");
                System.out.println("masaaaa" + siparis.getMasaId());
            }
        }
        btnSiparisIptal.setEnabled(true);
    }//GEN-LAST:event_btnSiparisTamamlaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        new FrmMusteriAnaSayfa(musteri, masaId).setVisible(true);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    String seciliSiparisAdi = "";
    private void tblSiparislerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSiparislerMouseClicked
        seciliSiparisAdi = tblSiparisler.getValueAt(tblSiparisler.getSelectedRow(), 0).toString();
        System.out.println("secili siparis : " + seciliSiparisAdi);
    }//GEN-LAST:event_tblSiparislerMouseClicked

    private void btnSiparisSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiparisSilActionPerformed
        // TODO add your handling code here:
        if (seciliSiparisAdi.equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir sipariş seçiniz!!!");
        } else {
            String siparisId = "";
            for (Urun urun1 : siparislerList) {
                siparislerList.remove(urun1);
                tabloyuDoldur();
                seciliSiparisAdi = "";
                System.out.println("seciiiiili: " + seciliSiparisAdi);
                break;
            }
        }
    }//GEN-LAST:event_btnSiparisSilActionPerformed

    private void btnSiparisIptalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiparisIptalActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Siparişi iptal etmek istediğinize emin misiniz?") == JOptionPane.YES_OPTION) {
            if (new SiparisCrud().delete(masaId)) {
                JOptionPane.showMessageDialog(this, "Siparişler iptal edildi...");
                siparislerList.clear();
                FrmMusteriAnaSayfa.masaninSiparisi.setIcerik("");
                FrmMusteriAnaSayfa.masaninSiparisi.setTutar("");
                tabloyuDoldur();
            }
        }
    }//GEN-LAST:event_btnSiparisIptalActionPerformed

    void tabloyuDoldur() {
        siparisTutar = 0;
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Ürün Adı");
        dtm.addColumn("Ürün Detayı");
        dtm.addColumn("Ürün Fiyatı");

        for (Urun urun : siparislerList) {
            dtm.addRow(new String[]{urun.getAd(), urun.getDetay(), urun.getFiyat()});
            siparisTutar += Integer.valueOf(urun.getFiyat());
        }
        tblSiparisler.setModel(dtm);
        txtToplamFiyat.setText(siparisTutar + "");
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
            java.util.logging.Logger.getLogger(FrmMusteriSiparis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMusteriSiparis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMusteriSiparis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMusteriSiparis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMusteriSiparis(null, "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnEkle;
    private javax.swing.JButton btnSiparisIptal;
    private javax.swing.JButton btnSiparisSil;
    private javax.swing.JButton btnSiparisTamamla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTable tblSiparisler;
    public static javax.swing.JTextField txtToplamFiyat;
    public static javax.swing.JTextField txtUrunAdi;
    public static javax.swing.JTextField txtUrunDetay;
    public static javax.swing.JTextField txtUrunFiyat;
    // End of variables declaration//GEN-END:variables
}
