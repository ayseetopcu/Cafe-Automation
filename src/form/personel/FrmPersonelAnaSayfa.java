package form.personel;

import database.KategoriCrud;
import database.MasaCrud;
import database.MusteriCrud;
import database.PersonelCrud;
import database.RezervasyonCrud;
import database.SiparisCrud;
import database.UrunCrud;
import form.giris.FrmPersonelAnaGiris;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import property.Kategori;
import property.Masa;
import property.Musteri;
import property.Personel;
import property.Rezervasyon;
import property.Urun;

/**
 *
 * @author Tugay Demirel
 */
public class FrmPersonelAnaSayfa extends javax.swing.JFrame {

    private Personel personel = null;

    public FrmPersonelAnaSayfa(Personel personel) {
        initComponents();
        musterileriGetir();
        menuGetir();
        personelGetir();
        rezervasyonGetir();
        masalariGetir();

        this.personel = personel;

        if (personel.getStatu() == 1) {
            //Eğer yonetici girişi yapılmadıysa personel paneli silinecek
            tbpanePersonel.remove(pnlPersonel);
        }
        setTitle(personel.getAd() + "-" + personel.getSoyad());

//        ListSelectionModel model = tblMasa.getSelectionModel();
//        model.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//
//                if (kontrol > 0) {
//                    seciliMasaId = "" + model.getMinSelectionIndex();
//                    btnHesapKesActionPerformed(null);
//                }
//                kontrol = 0;
//            }
//        });
    }

    static void refresh() {
        musterileriGetir();
        menuGetir();
        personelGetir();
        rezervasyonGetir();
        masalariGetir();

    }

    static void musterileriGetir() {
        ArrayList<Musteri> musteriler = (ArrayList<Musteri>) new MusteriCrud().read("");
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Musteri Numarası");
        dtm.addColumn("Musteri Adı");
        dtm.addColumn("Musteri Soyadı");
        dtm.addColumn("Musteri Email");
        dtm.addColumn("Musteri Telefon");
        for (Musteri musteri : musteriler) {
            dtm.addRow(new String[]{"" + musteri.getId(), musteri.getIsim(), musteri.getSoyisim(), musteri.geteMail(), musteri.getTelefon()});
        }
        tblMusteriler.setModel(dtm);
    }

    static void masalariGetir() {
        ArrayList<ArrayList<String>> masalar = (ArrayList<ArrayList<String>>) new MasaCrud().read();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Masa Id");
        dtm.addColumn("Masa Adı");
        dtm.addColumn("Kapasite");
        dtm.addColumn("Masa Durumu");
        dtm.addColumn("Müşteri Adı Soyadı");
        dtm.addColumn("Müşteri Telefon");
        dtm.addColumn("Siparişler");
        dtm.addColumn("Tutar");

        for (ArrayList<String> l : masalar) {
            System.out.println("tutututu" + l.get(7));
            String durum = "";
            if (l.get(3).equals("1")) {
                durum = "BOŞ";
            } else if (l.get(3).equals("2")) {
                durum = "DOLU";
            } else if (l.get(3).equals("3")) {
                durum = "REZERVE";
            }
            dtm.addRow(new String[]{"" + l.get(0), l.get(1), l.get(2), l.get(3), l.get(4) + " " + l.get(5), l.get(6), varsaSiparisGetir(l.get(7)), l.get(8)});
        }
        tblMasa.setModel(dtm);
    }

    static void menuGetir() {
        ArrayList<Urun> uruns = (ArrayList<Urun>) new UrunCrud().read("");
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Id");
        dtm.addColumn("Yiyecek Adı");
        dtm.addColumn("Detay");
        dtm.addColumn("Fiyat");
        dtm.addColumn("Kategori");
        dtm.addColumn("Stok Durumu");

        for (Urun menu : uruns) {
            String kategori = "";
            String stokDurumu = "";

            ArrayList<Kategori> kategoris = (ArrayList<Kategori>) new KategoriCrud().read("");
            for (Kategori kategori1 : kategoris) {
                if (menu.getKategori() == kategori1.getId()) {
                    kategori = kategori1.getName();
                }
            }
            if (menu.isStokDurumu()) {
                stokDurumu = "VAR";
            } else {
                stokDurumu = "YOK";
            }
            dtm.addRow(new String[]{"" + menu.getId(), menu.getAd(), menu.getDetay(), menu.getFiyat(), "" + kategori, "" + stokDurumu});
        }
        tblMenu.setModel(dtm);
    }

    void kategoriGoster() {
        //kategorileri database den çekeceğim
        //ondan sonra bunları cmbx e ekleyeceğim
        ArrayList<Kategori> kategoris = (ArrayList<Kategori>) new KategoriCrud().read("");

    }

    static void personelGetir() {
        ArrayList<Personel> personels = (ArrayList<Personel>) new PersonelCrud().read("");
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Id");
        dtm.addColumn("İsim Soyisim");
        dtm.addColumn("Email");
        dtm.addColumn("Telefon");
        dtm.addColumn("Cinsiyet");
        dtm.addColumn("Maas");
        dtm.addColumn("Statü");
        dtm.addColumn("Tarih");
        System.out.println("kolonlar eklendi");

        System.out.println("personeller: " + personels.get(0).getAd());
        System.out.println(personels.size());
        for (Personel personel : personels) {
            System.out.println("tugay: " + personel.getAd());
            String cinsiyet = personel.getCinsiyet() == 1 ? "Bayan" : "Bay";
            String status = personel.getStatu() == 1 ? "Personel" : "Yönetici";
            dtm.addRow(new String[]{"" + personel.getId(), personel.getAd() + " " + personel.getSoyad(), personel.getEmail(), personel.getTelefon(), cinsiyet, personel.getMaas(), status, "" + personel.getDate()});
        }
        System.out.println("fordan çıktı");
        tblPersonel.setModel(dtm);
    }

    static void rezervasyonGetir() {
        ArrayList<Rezervasyon> rezervasyons = (ArrayList<Rezervasyon>) new RezervasyonCrud().read("");
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Id");
        dtm.addColumn("Rezervasyon Adı");
        dtm.addColumn("Telefon");
        dtm.addColumn("Masa Bilgileri");
        dtm.addColumn("Rezervasyon Tarihi");

        for (Rezervasyon rezervasyon : rezervasyons) {
            dtm.addRow(new String[]{rezervasyon.getId() + "", rezervasyon.getAd(), rezervasyon.getTelefon(), rezervasyon.getMasaId() + "", rezervasyon.getDate().toString()});
        }
        tblRezervasyon.setModel(dtm);
    }

    static String varsaSiparisGetir(String siparisText) {
        String siparisler = "";
        if (siparisText != null && !siparisText.equals("")) {
            String[] urunIdleri = siparisText.split("###");
            ArrayList<Urun> list = (ArrayList<Urun>) new UrunCrud().read("");
            for (String string : urunIdleri) {
                if (!string.equals("")) {
                    for (Urun urun : list) {
                        if (string.equals(urun.getId() + "")) {
                            if (siparisler.equals("")) {
                                siparisler += urun.getAd();
                            } else {
                                siparisler += "," + urun.getAd();
                            }
                        }
                    }
                }
            }
        }
        return siparisler;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbpanePersonel = new javax.swing.JTabbedPane();
        pnlMasalar = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMasa = new javax.swing.JTable();
        pnlMasaIcerigi = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtSiparisler = new javax.swing.JTextArea();
        btnSiparisHazir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtMasaAdi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMusteriAdi = new javax.swing.JTextField();
        btnMasaEkle = new javax.swing.JButton();
        btnMusteriTasi = new javax.swing.JButton();
        btnHesapKes = new javax.swing.JButton();
        btnCikis5 = new javax.swing.JButton();
        pnlMusteriler = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMusteriler = new javax.swing.JTable();
        btnMusteriGuncelle = new javax.swing.JButton();
        btnMusteriSil = new javax.swing.JButton();
        btnCikis4 = new javax.swing.JButton();
        pnlMonu = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMenu = new javax.swing.JTable();
        btnMenuGuncelle = new javax.swing.JButton();
        btnMenuEkle = new javax.swing.JButton();
        btnMenuSil = new javax.swing.JButton();
        btnStok = new javax.swing.JButton();
        btnKategori = new javax.swing.JButton();
        btnCikis3 = new javax.swing.JButton();
        pnlRezervasyonlar = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblRezervasyon = new javax.swing.JTable();
        btnRezervEkle = new javax.swing.JButton();
        btnRezervGuncelle = new javax.swing.JButton();
        btnRezervSil = new javax.swing.JButton();
        btnCikis = new javax.swing.JButton();
        pnlPersonel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPersonel = new javax.swing.JTable();
        btnPersonelEkle = new javax.swing.JButton();
        btnPersonelGuncelle = new javax.swing.JButton();
        btnPersonelSil = new javax.swing.JButton();
        btnCikis2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PERSONEL İŞLEMLERİ");
        setSize(new java.awt.Dimension(0, 0));

        tblMasa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Masa Numarası", "Masa Adı", "Kapasite", "Müşteri Adı", "Müşteri Telefonu", "Masa Durumu", "Siparişler", "Tutar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMasa.setColumnSelectionAllowed(true);
        tblMasa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblMasaFocusGained(evt);
            }
        });
        tblMasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMasaMouseClicked(evt);
            }
        });
        tblMasa.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tblMasaComponentShown(evt);
            }
        });
        tblMasa.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblMasaPropertyChange(evt);
            }
        });
        jScrollPane4.setViewportView(tblMasa);
        tblMasa.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblMasa.getColumnModel().getColumnCount() > 0) {
            tblMasa.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblMasa.getColumnModel().getColumn(1).setPreferredWidth(10);
            tblMasa.getColumnModel().getColumn(2).setPreferredWidth(20);
            tblMasa.getColumnModel().getColumn(3).setPreferredWidth(20);
            tblMasa.getColumnModel().getColumn(4).setPreferredWidth(20);
            tblMasa.getColumnModel().getColumn(6).setPreferredWidth(15);
        }

        pnlMasaIcerigi.setBorder(javax.swing.BorderFactory.createTitledBorder("Masa İçeriği"));

        jLabel1.setText("Masa Adı");

        txtSiparisler.setEditable(false);
        txtSiparisler.setColumns(20);
        txtSiparisler.setRows(5);
        jScrollPane5.setViewportView(txtSiparisler);

        btnSiparisHazir.setText("Sipariş Hazır");
        btnSiparisHazir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiparisHazirActionPerformed(evt);
            }
        });

        jLabel2.setText("Siparişler");

        txtMasaAdi.setEditable(false);

        jLabel3.setText("Müşteri Adı");

        txtMusteriAdi.setEditable(false);
        txtMusteriAdi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMusteriAdiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMasaIcerigiLayout = new javax.swing.GroupLayout(pnlMasaIcerigi);
        pnlMasaIcerigi.setLayout(pnlMasaIcerigiLayout);
        pnlMasaIcerigiLayout.setHorizontalGroup(
            pnlMasaIcerigiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
            .addGroup(pnlMasaIcerigiLayout.createSequentialGroup()
                .addComponent(btnSiparisHazir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtMasaAdi)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtMusteriAdi)
        );
        pnlMasaIcerigiLayout.setVerticalGroup(
            pnlMasaIcerigiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMasaIcerigiLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMasaAdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMusteriAdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSiparisHazir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnMasaEkle.setText("Masa Ekle");
        btnMasaEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasaEkleActionPerformed(evt);
            }
        });

        btnMusteriTasi.setText("Müşteriyi Taşı");
        btnMusteriTasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMusteriTasiActionPerformed(evt);
            }
        });

        btnHesapKes.setText("Hesap Kes");
        btnHesapKes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHesapKesActionPerformed(evt);
            }
        });

        btnCikis5.setText("Çıkış");
        btnCikis5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCikis5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMasalarLayout = new javax.swing.GroupLayout(pnlMasalar);
        pnlMasalar.setLayout(pnlMasalarLayout);
        pnlMasalarLayout.setHorizontalGroup(
            pnlMasalarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMasalarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMasalarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMasalarLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlMasaIcerigi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMasalarLayout.createSequentialGroup()
                        .addComponent(btnMasaEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMusteriTasi)
                        .addGap(18, 18, 18)
                        .addComponent(btnHesapKes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCikis5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlMasalarLayout.setVerticalGroup(
            pnlMasalarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMasalarLayout.createSequentialGroup()
                .addGroup(pnlMasalarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMasalarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlMasaIcerigi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMasalarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMasaEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHesapKes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMusteriTasi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCikis5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbpanePersonel.addTab("Masalar", pnlMasalar);

        tblMusteriler.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMusteriler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMusterilerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMusteriler);

        btnMusteriGuncelle.setText("Guncelle");
        btnMusteriGuncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMusteriGuncelleActionPerformed(evt);
            }
        });

        btnMusteriSil.setText("Sil");
        btnMusteriSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMusteriSilActionPerformed(evt);
            }
        });

        btnCikis4.setText("Çıkış");
        btnCikis4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCikis4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMusterilerLayout = new javax.swing.GroupLayout(pnlMusteriler);
        pnlMusteriler.setLayout(pnlMusterilerLayout);
        pnlMusterilerLayout.setHorizontalGroup(
            pnlMusterilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMusterilerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMusterilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                    .addGroup(pnlMusterilerLayout.createSequentialGroup()
                        .addComponent(btnMusteriGuncelle)
                        .addGap(18, 18, 18)
                        .addComponent(btnMusteriSil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCikis4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlMusterilerLayout.setVerticalGroup(
            pnlMusterilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMusterilerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMusterilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMusteriGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMusteriSil, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCikis4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbpanePersonel.addTab("Kayıtlı Müşteriler", pnlMusteriler);

        tblMenu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMenuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMenu);

        btnMenuGuncelle.setText("Guncelle");
        btnMenuGuncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuGuncelleActionPerformed(evt);
            }
        });

        btnMenuEkle.setText("Ekle");
        btnMenuEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuEkleActionPerformed(evt);
            }
        });

        btnMenuSil.setText("Sil");
        btnMenuSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuSilActionPerformed(evt);
            }
        });

        btnStok.setText("Stokta Var/Yok");
        btnStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStokActionPerformed(evt);
            }
        });

        btnKategori.setText("Kategori Ekle");
        btnKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKategoriActionPerformed(evt);
            }
        });

        btnCikis3.setText("Çıkış");
        btnCikis3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCikis3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMonuLayout = new javax.swing.GroupLayout(pnlMonu);
        pnlMonu.setLayout(pnlMonuLayout);
        pnlMonuLayout.setHorizontalGroup(
            pnlMonuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMonuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                    .addGroup(pnlMonuLayout.createSequentialGroup()
                        .addComponent(btnMenuEkle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMenuGuncelle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMenuSil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnStok)
                        .addGap(18, 18, 18)
                        .addComponent(btnKategori)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCikis3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlMonuLayout.setVerticalGroup(
            pnlMonuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMonuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMonuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnMenuEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMenuGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMenuSil, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMonuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnCikis3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlMonuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnStok, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        tbpanePersonel.addTab("Genel Mönü", pnlMonu);

        tblRezervasyon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Rezervasyon Adı", "Müşteri Telefon", "Masa Id", "Rezervasyon Tarihi"
            }
        ));
        tblRezervasyon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRezervasyonMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblRezervasyon);

        btnRezervEkle.setText("Ekle");
        btnRezervEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRezervEkleActionPerformed(evt);
            }
        });

        btnRezervGuncelle.setText("Guncelle");
        btnRezervGuncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRezervGuncelleActionPerformed(evt);
            }
        });

        btnRezervSil.setText("Sil");
        btnRezervSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRezervSilActionPerformed(evt);
            }
        });

        btnCikis.setText("ÇIKIŞ");

        javax.swing.GroupLayout pnlRezervasyonlarLayout = new javax.swing.GroupLayout(pnlRezervasyonlar);
        pnlRezervasyonlar.setLayout(pnlRezervasyonlarLayout);
        pnlRezervasyonlarLayout.setHorizontalGroup(
            pnlRezervasyonlarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRezervasyonlarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRezervEkle)
                .addGap(18, 18, 18)
                .addComponent(btnRezervGuncelle)
                .addGap(18, 18, 18)
                .addComponent(btnRezervSil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 456, Short.MAX_VALUE)
                .addComponent(btnCikis, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlRezervasyonlarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlRezervasyonlarLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6)
                    .addContainerGap()))
        );
        pnlRezervasyonlarLayout.setVerticalGroup(
            pnlRezervasyonlarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRezervasyonlarLayout.createSequentialGroup()
                .addContainerGap(489, Short.MAX_VALUE)
                .addGroup(pnlRezervasyonlarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlRezervasyonlarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRezervEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRezervGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRezervSil, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCikis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(pnlRezervasyonlarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlRezervasyonlarLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                    .addGap(67, 67, 67)))
        );

        tbpanePersonel.addTab("Rezervasyonlar", pnlRezervasyonlar);

        tblPersonel.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPersonel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPersonelMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblPersonel);

        btnPersonelEkle.setText("Ekle");
        btnPersonelEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonelEkleActionPerformed(evt);
            }
        });

        btnPersonelGuncelle.setText("Güncelle");
        btnPersonelGuncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonelGuncelleActionPerformed(evt);
            }
        });

        btnPersonelSil.setText("Sil");
        btnPersonelSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonelSilActionPerformed(evt);
            }
        });

        btnCikis2.setText("ÇIKIŞ");
        btnCikis2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCikis2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPersonelLayout = new javax.swing.GroupLayout(pnlPersonel);
        pnlPersonel.setLayout(pnlPersonelLayout);
        pnlPersonelLayout.setHorizontalGroup(
            pnlPersonelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPersonelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPersonelEkle)
                .addGap(18, 18, 18)
                .addComponent(btnPersonelGuncelle)
                .addGap(18, 18, 18)
                .addComponent(btnPersonelSil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 456, Short.MAX_VALUE)
                .addComponent(btnCikis2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlPersonelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlPersonelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3)
                    .addContainerGap()))
        );
        pnlPersonelLayout.setVerticalGroup(
            pnlPersonelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPersonelLayout.createSequentialGroup()
                .addContainerGap(489, Short.MAX_VALUE)
                .addGroup(pnlPersonelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCikis2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPersonelLayout.createSequentialGroup()
                        .addGroup(pnlPersonelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPersonelEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPersonelGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPersonelSil, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(pnlPersonelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlPersonelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                    .addGap(67, 67, 67)))
        );

        tbpanePersonel.addTab("Personel Bilgileri", pnlPersonel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbpanePersonel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbpanePersonel)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static int kontrol = 0;
    private void btnHesapKesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHesapKesActionPerformed
        if (seciliMasaId.equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir masa seçiniz!!!");
        } else {
            System.out.println("ghghgh:" + seciliMasaSiparisleri);
            if (seciliMasaSiparisleri.trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Seçili olan masanın siparişi bulunmamaktadır!!!");
            } else if (JOptionPane.showConfirmDialog(this, "Tutar : " + seciliMasaSiparisleri + " Hesap kapatılsın mı ") == JOptionPane.YES_OPTION) {
                if (new SiparisCrud().delete(seciliMasaId)) {
                    JOptionPane.showMessageDialog(this, "Ödeme tamamlandı...");
                    refresh();
                    textleriTemizle();
                }
            }
        }
        kontrol = 0;
        seciliMasaId = "";
    }//GEN-LAST:event_btnHesapKesActionPerformed

    void textleriTemizle() {
        txtMasaAdi.setText("");
        txtMusteriAdi.setText("");
        txtSiparisler.setText("");
    }

    String seciliMasaId = "";
    private void btnMusteriTasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMusteriTasiActionPerformed
        System.out.println("secilimasadurum : " + seciliMasaDurum);
        if (seciliMasaId.equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir masa seçiniz!!!");
        } else if (seciliMasaDurum.equals("DOLU")) {
            Masa masa = (Masa) new MasaCrud().read(seciliMasaId).get(0);
            new FrmMusteriTasi(masa).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Masa halihazırda dolu değil!!!");
        }
        seciliMasaId = "";
    }//GEN-LAST:event_btnMusteriTasiActionPerformed

    private void btnMasaEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasaEkleActionPerformed
        new FrmMasaEkle().setVisible(true);
    }//GEN-LAST:event_btnMasaEkleActionPerformed

    private void btnPersonelGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonelGuncelleActionPerformed
        if (seciliPersonelId.equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir personel seçiniz!!!");
        } else {
            Personel personel = new Personel();
            personel = (Personel) new PersonelCrud().read(seciliPersonelId).get(0);
            try {
                new FrmPersonelCreateUpdate(personel).setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(FrmPersonelAnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        seciliPersonelId = "";
    }//GEN-LAST:event_btnPersonelGuncelleActionPerformed

    private void btnPersonelEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonelEkleActionPerformed
        try {
            // TODO add your handling code here:
            new FrmPersonelCreateUpdate();
        } catch (IOException ex) {
            Logger.getLogger(FrmPersonelAnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPersonelEkleActionPerformed

    private void btnRezervEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRezervEkleActionPerformed
        new FrmRezervasyonCreateUpdate().setVisible(true);
    }//GEN-LAST:event_btnRezervEkleActionPerformed

    private void btnMusteriSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMusteriSilActionPerformed
        if (seciliMusteriId.equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir müşteri seçiniz !!!");
        } else {
            if (new MusteriCrud().delete(seciliMusteriId)) {
                JOptionPane.showMessageDialog(this, "Silme başarılı");
                musterileriGetir();
            }
        }
        seciliMusteriId = "";
    }//GEN-LAST:event_btnMusteriSilActionPerformed

    private String seciliMusteriId = "";
    private void tblMusterilerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMusterilerMouseClicked
        seciliMusteriId = tblMusteriler.getValueAt(tblMusteriler.getSelectedRow(), 0).toString();
        System.out.println("seçilen satır: " + seciliMusteriId);
    }//GEN-LAST:event_tblMusterilerMouseClicked

    private void btnMusteriGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMusteriGuncelleActionPerformed
        if (seciliMusteriId.equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir müşteri seçiniz !!!");
        } else {
            MusteriCrud mCrud = new MusteriCrud();
            Musteri musteri = (Musteri) mCrud.read(seciliMusteriId).get(0);
            try {
                new FrmMusteriCreateUpdate(musteri).setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(FrmPersonelAnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnMusteriGuncelleActionPerformed

    private void btnMenuEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuEkleActionPerformed
        try {
            new FrmMenuCreateUpdate();
        } catch (IOException ex) {
            Logger.getLogger(FrmPersonelAnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMenuEkleActionPerformed

    private void btnMenuGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuGuncelleActionPerformed
        if (seciliUrunId.equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir menu elemanı seçiniz !!!");
        } else {
            UrunCrud mCrud = new UrunCrud();
            Urun urun = (Urun) mCrud.read(seciliUrunId).get(0);
            try {
                new FrmMenuCreateUpdate(urun).setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(FrmPersonelAnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        seciliUrunId = "";
    }//GEN-LAST:event_btnMenuGuncelleActionPerformed

    private void btnKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKategoriActionPerformed
        String yeniKategori = JOptionPane.showInputDialog("Kategori ismi=?");
        Kategori kategori = new Kategori();
        kategori.setName(yeniKategori);
        try {
            if (new KategoriCrud().create(kategori)) {
                JOptionPane.showMessageDialog(this, "Kategori eklendi");
            }
        } catch (Exception e) {
            System.err.println("kategori buton action: " + e);
        }
    }//GEN-LAST:event_btnKategoriActionPerformed
    private String seciliUrunId = "";
    private void tblMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMenuMouseClicked
        seciliUrunId = tblMenu.getValueAt(tblMenu.getSelectedRow(), 0).toString();
    }//GEN-LAST:event_tblMenuMouseClicked

    private void btnMenuSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuSilActionPerformed
        if (seciliUrunId.equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir menu elemanı seçiniz !!!");
        } else {
            if (new UrunCrud().delete(seciliUrunId)) {
                JOptionPane.showMessageDialog(this, "Silme başarılı");
                menuGetir();
                seciliUrunId = "";
            }
        }
    }//GEN-LAST:event_btnMenuSilActionPerformed

    private void btnStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStokActionPerformed
        if (seciliUrunId.equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir menu elemanı seçiniz !!!");
        } else {
            UrunCrud mCrud = new UrunCrud();
            Urun menu = (Urun) mCrud.read(seciliUrunId).get(0);
            if (menu.isStokDurumu()) {
                menu.setStokDurumu(false);
            } else {
                menu.setStokDurumu(true);
            }
            new UrunCrud().update(menu);
            menuGetir();
        }
        seciliUrunId = "";
    }//GEN-LAST:event_btnStokActionPerformed
    private String seciliPersonelId = "";
    private void tblPersonelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPersonelMouseClicked
        seciliPersonelId = tblPersonel.getValueAt(tblPersonel.getSelectedRow(), 0).toString();
    }//GEN-LAST:event_tblPersonelMouseClicked

    private void btnPersonelSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonelSilActionPerformed
        if (seciliPersonelId.equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir personel seçiniz!!!");
        } else {
            if (new PersonelCrud().delete(seciliPersonelId)) {
                JOptionPane.showMessageDialog(this, "Silme Başarılı...");
                personelGetir();
            }
        }
        seciliPersonelId = "";
    }//GEN-LAST:event_btnPersonelSilActionPerformed

    private void btnRezervSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRezervSilActionPerformed
        if (seciliRezervasyonId.equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir rezervasyon seçiniz!!!");
        } else {
            String condition = seciliRezervasyonId + "###" + seciliRezervasyonTableId;
            if (new RezervasyonCrud().delete(condition)) {
                JOptionPane.showMessageDialog(this, "Silme Başarılı...");
                rezervasyonGetir();
            }
        }
        seciliRezervasyonId = "";
    }//GEN-LAST:event_btnRezervSilActionPerformed
    private String seciliRezervasyonId = "";
    private String seciliRezervasyonTableId = "";
    private void tblRezervasyonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRezervasyonMouseClicked
        seciliRezervasyonId = tblRezervasyon.getValueAt(tblRezervasyon.getSelectedRow(), 0).toString();
        seciliRezervasyonTableId = tblRezervasyon.getValueAt(tblRezervasyon.getSelectedRow(), 3).toString();
    }//GEN-LAST:event_tblRezervasyonMouseClicked

    private void btnRezervGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRezervGuncelleActionPerformed
        if (seciliRezervasyonId.equals("")) {
            JOptionPane.showMessageDialog(this, "Lütfen bir rezervasyon seçiniz!!!");
        } else {
            RezervasyonCrud rCrud = new RezervasyonCrud();
            Rezervasyon rezervasyon = (Rezervasyon) rCrud.read(seciliRezervasyonId).get(0);
            new FrmRezervasyonCreateUpdate(rezervasyon).setVisible(true);
        }
        seciliRezervasyonId = "";
    }//GEN-LAST:event_btnRezervGuncelleActionPerformed
    String seciliMasaMusteriName = "";
    String seciliMasaHesap = "";
    String seciliMasaSiparisleri = "";
    String seciliMasaDurum = "";
    private void tblMasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMasaMouseClicked

        seciliMasaId = tblMasa.getValueAt(tblMasa.getSelectedRow(), 0).toString();

        seciliMasaDurum = tblMasa.getValueAt(tblMasa.getSelectedRow(), 3) == null ? "" : tblMasa.getValueAt(tblMasa.getSelectedRow(), 3).toString();
        if (seciliMasaDurum.equals("1")) {
            seciliMasaDurum = "BOŞ";
        } else if (seciliMasaDurum.equals("2")) {
            seciliMasaDurum = "DOLU";
        } else if (seciliMasaDurum.equals("3")) {
            seciliMasaDurum = "REZERVE";
        }

        txtMasaAdi.setText(tblMasa.getValueAt(tblMasa.getSelectedRow(), 1).toString());
        txtMusteriAdi.setText(tblMasa.getValueAt(tblMasa.getSelectedRow(), 4) == null ? "" : tblMasa.getValueAt(tblMasa.getSelectedRow(), 4).toString());
        seciliMasaMusteriName = txtMusteriAdi.getText();
        seciliMasaHesap = tblMasa.getValueAt(tblMasa.getSelectedRow(), 7) == null ? "" : tblMasa.getValueAt(tblMasa.getSelectedRow(), 7).toString();

        seciliMasaSiparisleri = (tblMasa.getValueAt(tblMasa.getSelectedRow(), 6) == null ? "" : tblMasa.getValueAt(tblMasa.getSelectedRow(), 6).toString());

        txtSiparisler.setText("");
        String[] a = seciliMasaSiparisleri.split(",");
        seciliMasaSiparisleri = "";
        for (String string : a) {
            seciliMasaSiparisleri += "\n\r" + string;
        }
        txtSiparisler.setText(seciliMasaSiparisleri);
        if (kontrol > 0) {
            btnHesapKesActionPerformed(null);
        }
    }//GEN-LAST:event_tblMasaMouseClicked

    private void txtMusteriAdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMusteriAdiActionPerformed
    }//GEN-LAST:event_txtMusteriAdiActionPerformed

    private void tblMasaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tblMasaComponentShown
    }//GEN-LAST:event_tblMasaComponentShown

    private void tblMasaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblMasaPropertyChange
        System.out.println("property changed");
    }//GEN-LAST:event_tblMasaPropertyChange

    private void tblMasaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblMasaFocusGained
        // TODO add your handling code here:
        System.out.println("focus gained");

    }//GEN-LAST:event_tblMasaFocusGained

    private void btnSiparisHazirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiparisHazirActionPerformed
        // TODO add your handling code here:
//        tblMasa.setRowSelectionInterval(6, 6);
    }//GEN-LAST:event_btnSiparisHazirActionPerformed

    private void btnCikis2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCikis2ActionPerformed
        btnCikis5ActionPerformed(null);
    }//GEN-LAST:event_btnCikis2ActionPerformed

    private void btnCikis3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCikis3ActionPerformed
        btnCikis5ActionPerformed(null);
    }//GEN-LAST:event_btnCikis3ActionPerformed

    private void btnCikis4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCikis4ActionPerformed
        btnCikis5ActionPerformed(null);
    }//GEN-LAST:event_btnCikis4ActionPerformed

    private void btnCikis5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCikis5ActionPerformed
        new FrmPersonelAnaGiris().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCikis5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPersonelAnaSayfa(new Personel()).setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCikis;
    private javax.swing.JButton btnCikis2;
    private javax.swing.JButton btnCikis3;
    private javax.swing.JButton btnCikis4;
    public static javax.swing.JButton btnCikis5;
    public static javax.swing.JButton btnHesapKes;
    private javax.swing.JButton btnKategori;
    private javax.swing.JButton btnMasaEkle;
    private javax.swing.JButton btnMenuEkle;
    private javax.swing.JButton btnMenuGuncelle;
    private javax.swing.JButton btnMenuSil;
    private javax.swing.JButton btnMusteriGuncelle;
    private javax.swing.JButton btnMusteriSil;
    private javax.swing.JButton btnMusteriTasi;
    private javax.swing.JButton btnPersonelEkle;
    private javax.swing.JButton btnPersonelGuncelle;
    private javax.swing.JButton btnPersonelSil;
    private javax.swing.JButton btnRezervEkle;
    private javax.swing.JButton btnRezervGuncelle;
    private javax.swing.JButton btnRezervSil;
    private javax.swing.JButton btnSiparisHazir;
    private javax.swing.JButton btnStok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel pnlMasaIcerigi;
    private javax.swing.JPanel pnlMasalar;
    private javax.swing.JPanel pnlMonu;
    private javax.swing.JPanel pnlMusteriler;
    private javax.swing.JPanel pnlPersonel;
    private javax.swing.JPanel pnlRezervasyonlar;
    public static javax.swing.JTable tblMasa;
    private static javax.swing.JTable tblMenu;
    private static javax.swing.JTable tblMusteriler;
    private static javax.swing.JTable tblPersonel;
    private static javax.swing.JTable tblRezervasyon;
    private javax.swing.JTabbedPane tbpanePersonel;
    private javax.swing.JTextField txtMasaAdi;
    private javax.swing.JTextField txtMusteriAdi;
    private static javax.swing.JTextArea txtSiparisler;
    // End of variables declaration//GEN-END:variables
}
