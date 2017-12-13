package form.personel;

import database.PersonelCrud;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import property.Personel;

/**
 *
 *
 * @author Tugay Demirel
 */
public class FrmPersonelCreateUpdate extends javax.swing.JFrame {
    
    public static final String TEL_REGEX = "(([\\+]90?)|([0]?))([ ]?)((\\([0-9]{3}\\))|([0-9]{3}))([ ]?)([0-9]{3})(\\s*[\\-]?)([0-9]{2})(\\s*[\\-]?)([0-9]{2})";
    private String tel = "";

    //isCreate degiskeni create ekranı mı yoksa update ekranı mı açık olacak onun
    //kontrolunu yapar
    private boolean isCreate = true;
    private Personel personel = null;
    
    public FrmPersonelCreateUpdate() throws IOException {
        //Yeni kayıt ekleme
        initComponents();
        setVisible(true);
        rdBtnBay.setSelected(true);
        personel = new Personel();
    }
    
    public FrmPersonelCreateUpdate(Personel personel) throws IOException {
        //Guncelleme
        isCreate = false;
        this.personel = personel;
        initComponents();
        txtName.setText(personel.getAd());
        txtSurname.setText(personel.getSoyad());
        txtEmail.setText(personel.getEmail());
        txtTelefon.setText(personel.getTelefon());
        txtSifre.setText(personel.getSifre());
        txtMaas.setText(personel.getMaas());
        if (personel.getCinsiyet() == 1) {
            rdbtnBayan.setSelected(true);
        } else {
            rdBtnBay.setSelected(true);
        }
        cmboxStatu.setSelectedIndex(personel.getStatu()-1);
        setVisible(true);
        
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        lblAdress2 = new javax.swing.JLabel();
        txtMaas = new javax.swing.JTextField();
        lblAdress3 = new javax.swing.JLabel();
        rdBtnBay = new javax.swing.JRadioButton();
        rdbtnBayan = new javax.swing.JRadioButton();
        lblAdress4 = new javax.swing.JLabel();
        cmboxStatu = new javax.swing.JComboBox<>();
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
        lblConsumerInfo.setText("Personel Bilgileri");

        lblAdress1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAdress1.setText("Şifre");

        lblAdress2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAdress2.setText("Maaş");

        lblAdress3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAdress3.setText("Cinsiyet");

        buttonGroup1.add(rdBtnBay);
        rdBtnBay.setText("Bay");

        buttonGroup1.add(rdbtnBayan);
        rdbtnBayan.setText("Bayan");

        lblAdress4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAdress4.setText("Statü");

        cmboxStatu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Personel", "Yönetici" }));

        txtSifre.setText("jPasswordField1");

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAdress2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaas, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAdress4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmboxStatu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAdress3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdBtnBay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdbtnBayan)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAdress1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSifre)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAdress2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAdress3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdBtnBay)
                    .addComponent(rdbtnBayan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAdress4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(cmboxStatu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnKaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKaydetActionPerformed
        tel = txtTelefon.getText();
        if (validation()) {
            personel.setAd(txtName.getText().trim());
            personel.setSoyad(txtSurname.getText().trim());
            personel.setEmail(txtEmail.getText().trim());
            personel.setTelefon(txtTelefon.getText().trim());
            personel.setSifre(txtSifre.getText().trim());
            personel.setMaas(txtMaas.getText().trim());
            System.out.println(new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            System.out.println("sdf: "+ sdf);
            
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
            personel.setDate(sqlDate);
            if(rdBtnBay.isSelected()){
                personel.setCinsiyet(2);
            }else{
                personel.setCinsiyet(1);
            }
            personel.setStatu(cmboxStatu.getSelectedIndex()+1);
            if (isCreate ? new PersonelCrud().create(personel) : new PersonelCrud().update(personel)) {
                JOptionPane.showMessageDialog(this, "Kayıt başarılı");
            }else{
                JOptionPane.showMessageDialog(this, "Personel oluşturma hatası");
            }
        }
        FrmPersonelAnaSayfa.refresh();
        dispose();
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
        } else if (!isNumeric(tel) || !tel.matches(TEL_REGEX)) {
            JOptionPane.showMessageDialog(this, "Geçersiz telefon numarası!!!");
            txtEmail.requestFocus();//ilgili texte odaklar
            txtEmail.selectAll();
            return false;
        } else if (!isNumeric(txtMaas.getText()) || txtMaas.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen geçerli maaş giriniz!!!");
            txtEmail.requestFocus();//ilgili texte odaklar
            txtEmail.selectAll();
            return false;
        } else if (txtSifre.getText().equals("") || txtSifre.getText().length() < 6) {
            JOptionPane.showMessageDialog(this, "Lütfen en az 6 karakterli geçerli bir şifre giriniz!!!");
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
                    new FrmPersonelCreateUpdate().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrmPersonelCreateUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKaydet;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmboxStatu;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblAdress1;
    private javax.swing.JLabel lblAdress2;
    private javax.swing.JLabel lblAdress3;
    private javax.swing.JLabel lblAdress4;
    private javax.swing.JLabel lblConsumerInfo;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSname;
    private javax.swing.JLabel lblTel;
    private javax.swing.JRadioButton rdBtnBay;
    private javax.swing.JRadioButton rdbtnBayan;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaas;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtSifre;
    private javax.swing.JTextField txtSurname;
    private javax.swing.JTextField txtTelefon;
    // End of variables declaration//GEN-END:variables
}
