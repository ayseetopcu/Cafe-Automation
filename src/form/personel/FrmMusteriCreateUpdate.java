package form.personel;

import database.MusteriCrud;
import form.giris.FrmMusteriGiris;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import property.Musteri;

/**
 *
 *
 * @author Tugay Demirel
 */
public class FrmMusteriCreateUpdate extends javax.swing.JFrame {

    public static final String TEL_REGEX = "(([\\+]90?)|([0]?))([ ]?)((\\([0-9]{3}\\))|([0-9]{3}))([ ]?)([0-9]{3})(\\s*[\\-]?)([0-9]{2})(\\s*[\\-]?)([0-9]{2})";
    private String tel = "";

    //isCreate degiskeni create ekranı mı yoksa update ekranı mı açık olacak onun
    //kontrolunu yapar
    private boolean isCreate = false;
    private Musteri musteri = null;
    private String masaId = "";

    public FrmMusteriCreateUpdate(String masaId) throws IOException {
        //Yeni kayıt ekleme
        this.masaId = masaId;
        initComponents();
        setVisible(true);
        isCreate = true;
    }

    public FrmMusteriCreateUpdate(Musteri musteri) throws IOException {
        //Guncelleme
        isCreate = false;
        this.musteri = musteri;

        initComponents();
        txtName.setText(musteri.getIsim());
        txtSurname.setText(musteri.getSoyisim());
        txtEmail.setText(musteri.geteMail());
        txtUserName.setText(musteri.getKullanici_adi());
        txtTelefon.setText(musteri.getTelefon());
        txtSifre.setText(musteri.getSifre());
        setVisible(true);
        btnKaydet.setText("Güncelle");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblSname = new javax.swing.JLabel();
        txtSurname = new javax.swing.JTextField();
        lblTel = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblAdress = new javax.swing.JLabel();
        txtTelefon = new javax.swing.JTextField();
        btnKaydet = new javax.swing.JButton();
        lblConsumerInfo = new javax.swing.JLabel();
        lblAdress1 = new javax.swing.JLabel();
        lblSname1 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtSifre = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Personel Ekle/Güncelle");

        lblName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblName.setText("Adı ");

        lblSname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSname.setText("Soyadı");

        lblTel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTel.setText("E-mail");

        lblAdress.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAdress.setText("Telefon");

        btnKaydet.setText("Ekle");
        btnKaydet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKaydetActionPerformed(evt);
            }
        });

        lblConsumerInfo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblConsumerInfo.setText("Müşteri Bilgileri");

        lblAdress1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAdress1.setText("Şifre");

        lblSname1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSname1.setText("Kullanıcı Adı");

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
                        .addComponent(txtSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTelefon, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblAdress1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSifre))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblSname1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSname1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAdress1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnKaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKaydetActionPerformed
        System.out.println("Güncellenmek istenen müşteri : "  + musteri.getIsim());
        tel = txtTelefon.getText();
        if (validation()) {
//            musteri = new Musteri();
            musteri.setIsim(txtName.getText().trim());
            musteri.setSoyisim(txtSurname.getText().trim());
            musteri.setKullanici_adi(txtUserName.getText().trim());
            musteri.seteMail(txtEmail.getText().trim());
            musteri.setTelefon(txtTelefon.getText().trim());
            musteri.setSifre(txtSifre.getText().trim());

            if (isCreate) {
                if (new MusteriCrud().create(musteri)) {
                    JOptionPane.showMessageDialog(this, "Ekleme başarılı");
                    dispose();
                    new FrmMusteriGiris(masaId).setVisible(true);
                }
            } else if (new MusteriCrud().update(musteri)) {
                JOptionPane.showMessageDialog(this, "Güncelleme başarılı");
                dispose();
                FrmPersonelAnaSayfa.refresh();

            }
            
        }

    }//GEN-LAST:event_btnKaydetActionPerformed

    boolean validation() {

        if (txtName.getText().equals("")) {
            System.out.println("isim hatası");
            JOptionPane.showMessageDialog(this, "Lütfen bir isim giriniz!!!");
            txtName.requestFocus();//ilgili texte odaklar
            txtName.selectAll();
            return false;
        } else if (txtSurname.getText().equals("")) {
            System.out.println("soyisim hatası");
            JOptionPane.showMessageDialog(this, "Lütfen bir soyisim giriniz!!!");
            txtSurname.requestFocus();//ilgili texte odaklar
            txtSurname.selectAll();
            return false;
        } else if (txtUserName.getText().equals("")) {
            System.out.println("soyisim hatası");
            JOptionPane.showMessageDialog(this, "Lütfen bir soyisim giriniz!!!");
            txtUserName.requestFocus();//ilgili texte odaklar
            txtUserName.selectAll();
            return false;
        } else if (!isNumeric(tel) || !tel.matches(TEL_REGEX)) {
            JOptionPane.showMessageDialog(this, "Geçersiz telefon numarası!!!");
            txtTelefon.requestFocus();//ilgili texte odaklar
            txtTelefon.selectAll();
            return false;
        } else if (txtSifre.getText().equals("") || txtSifre.getText().length() < 6) {
            JOptionPane.showMessageDialog(this, "Lütfen en az 6 karakterli geçerli bir şifre giriniz!!!");
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
                    new FrmMusteriCreateUpdate("").setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrmMusteriCreateUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKaydet;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblAdress1;
    private javax.swing.JLabel lblConsumerInfo;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSname;
    private javax.swing.JLabel lblSname1;
    private javax.swing.JLabel lblTel;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtSifre;
    private javax.swing.JTextField txtSurname;
    private javax.swing.JTextField txtTelefon;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
