package form.personel;

import database.KategoriCrud;
import database.UrunCrud;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import property.Kategori;
import property.Urun;

/**
 *
 * @author Tugay Demirel
 */
public class FrmMenuCreateUpdate extends javax.swing.JFrame {

    private Urun urun = null;

    //isCreate degiskeni create ekranı mı yoksa update ekranı mı açık olacak onun
    //kontrolunu yapar
    private boolean isCreate = true;

    public FrmMenuCreateUpdate() throws IOException {
        initComponents();
        setVisible(true);
        urun = new Urun();
        kategoriGoster();

    }

    public FrmMenuCreateUpdate(Urun menu) throws IOException {
        this.urun = menu;

        initComponents();
        setVisible(true);
        kategoriGoster();
        isCreate = false;
        txtName.setText(menu.getAd());
        txtFiyat.setText(menu.getFiyat());
        txtDetay.setText(menu.getDetay());
        cmbxKategori.setSelectedIndex(menu.getKategori());
        if (menu.isStokDurumu()) {
            radioButonStokVar.setSelected(true);
        } else {
            radioButonStokYok.setSelected(true);
        }

    }

    void kategoriGoster() {
        //kategorileri database den çekeceğim
        //ondan sonra bunları cmbx e ekleyeceğim
        ArrayList<Kategori> kategoris = (ArrayList<Kategori>) new KategoriCrud().read("");

        //verileri aldım şimdi combobox model ile ekleyeceğim
        DefaultComboBoxModel cmBoxModel = new DefaultComboBoxModel();
        cmBoxModel.addElement("Kategori");
        for (Kategori kategori : kategoris) {
            cmBoxModel.addElement(kategori.getName());
        }
        cmbxKategori.setModel(cmBoxModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrpStok = new javax.swing.ButtonGroup();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblSname = new javax.swing.JLabel();
        lblTel = new javax.swing.JLabel();
        txtFiyat = new javax.swing.JTextField();
        lblAdress = new javax.swing.JLabel();
        txtDetay = new javax.swing.JTextField();
        btnKaydet = new javax.swing.JButton();
        lblConsumerInfo = new javax.swing.JLabel();
        cmbxKategori = new javax.swing.JComboBox<>();
        lblAdress1 = new javax.swing.JLabel();
        radioButonStokVar = new javax.swing.JRadioButton();
        radioButonStokYok = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Yeni Kullanıcı Oluştur");

        lblName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblName.setText("Adı ");

        lblSname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSname.setText("Kategori");

        lblTel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTel.setText("Fiyat");

        lblAdress.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAdress.setText("Detay");

        btnKaydet.setText("Ekle");
        btnKaydet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKaydetActionPerformed(evt);
            }
        });

        lblConsumerInfo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblConsumerInfo.setText("Yiyecek/İçecek Bilgileri");

        cmbxKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbxKategori.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbxKategoriİtemStateChanged(evt);
            }
        });

        lblAdress1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAdress1.setText("Stok Durumu");

        btnGrpStok.add(radioButonStokVar);
        radioButonStokVar.setText("var");

        btnGrpStok.add(radioButonStokYok);
        radioButonStokYok.setText("yok");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblConsumerInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtName))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblSname, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbxKategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFiyat, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDetay, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblAdress1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radioButonStokVar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButonStokYok, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblConsumerInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(cmbxKategori)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiyat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDetay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAdress1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioButonStokVar)
                    .addComponent(radioButonStokYok))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKaydetActionPerformed
        if (validation()) {

            urun.setAd(txtName.getText().trim());
            urun.setDetay(txtDetay.getText().trim());
            urun.setKategori(cmbxKategori.getSelectedIndex());
            urun.setFiyat(txtFiyat.getText().trim());
            if (radioButonStokVar.isSelected()) {
                urun.setStokDurumu( true );
            }else{
                urun.setStokDurumu( false );
            }

            System.out.println(isCreate);
            if (isCreate ? new UrunCrud().create(urun) : new UrunCrud().update(urun)) {
                JOptionPane.showMessageDialog(this, "Kayıt başarılı...");
            }
            FrmPersonelAnaSayfa.refresh();
            dispose();
        }
    }//GEN-LAST:event_btnKaydetActionPerformed

    private String selectedCmbxItem = "";
    private void cmbxKategoriİtemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbxKategoriİtemStateChanged
        selectedCmbxItem = "" + cmbxKategori.getSelectedItem();
        System.out.println("index:" + cmbxKategori.getSelectedIndex());
        System.out.println("item:" + cmbxKategori.getSelectedItem());
    }//GEN-LAST:event_cmbxKategoriİtemStateChanged

    boolean validation() {

        if (txtName.getText().equals("")) {
            System.out.println("isim hatası");
            JOptionPane.showMessageDialog(this, "Lütfen bir isim giriniz!!!");
            txtName.requestFocus();//ilgili texte odaklar
            txtName.selectAll();
            return false;
        } else if (cmbxKategori.getSelectedIndex() < 1) {
            JOptionPane.showMessageDialog(this, "Lütfen bir kategori seçiniz!!!");
            cmbxKategori.requestFocus();
            return false;
        } else if (txtFiyat.getText().equals("") || !isNumeric(txtFiyat.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Geçerli bir fiyat giriniz!!!");
            txtFiyat.requestFocus();
            txtFiyat.selectAll();
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrmMenuCreateUpdate().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrmMenuCreateUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGrpStok;
    private javax.swing.JButton btnKaydet;
    private javax.swing.JComboBox<String> cmbxKategori;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblAdress1;
    private javax.swing.JLabel lblConsumerInfo;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSname;
    private javax.swing.JLabel lblTel;
    private javax.swing.JRadioButton radioButonStokVar;
    private javax.swing.JRadioButton radioButonStokYok;
    private javax.swing.JTextField txtDetay;
    private javax.swing.JTextField txtFiyat;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
