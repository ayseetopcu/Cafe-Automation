package database;

import enums.MusteriEnum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import property.Musteri;

/**
 *
 * @author Tugay Demirel
 */
public class MusteriCrud extends DB implements CrudProcesses {

    @Override
    public boolean update(Object o) {
        Musteri musteri = (Musteri) o;
        try {
            System.out.println("update metodundaki musteri bilgileri: " + musteri.getIsim());
            //`updateMusteri`(IN `m_id` int , IN `m_name` varchar(100) , IN `m_surname` varchar(100) , IN `m_email` varchar(100) , IN `m_tel` varchar(100),IN `m_sifre` varchar(100))
            String q = "CALL updateMusteri(?,?,?,?,?,?,?)";
            System.out.println(musteri.getId());
            PreparedStatement pr = super.preBaglan(q);
            pr.setString(1, "" + musteri.getId());
            pr.setString(2, musteri.getIsim());
            pr.setString(3, musteri.getSoyisim());
            pr.setString(4, musteri.getKullanici_adi());
            pr.setString(5, musteri.geteMail());
            pr.setString(6, musteri.getTelefon());
            pr.setString(7, musteri.getSifre());

            int isUpdate = pr.executeUpdate();

            System.out.println("isUpdate değeri : " + isUpdate);
            if (isUpdate > 0) {
                System.out.println("Update  başarılı");
                return true;
            }

        } catch (Exception e) {
            System.err.println("UpdateMusteri() : " + e);
        } finally {
            super.kapat();
        }

        return false;
    }

    @Override
    public ArrayList<? extends Object> read(String condition) {
        ArrayList<Musteri> musteriList = new ArrayList<>();

        Musteri musteri = null;
        try {
            ResultSet rs = null;
            if (condition.equals("")) {
                rs = super.baglan().executeQuery("CALL allMusteri()");
            } else {
                rs = super.baglan().executeQuery("CALL getMusteri('" + condition + "')");
            }
            while (rs.next()) {
                musteri = new Musteri();
                musteri.setId(rs.getInt("" + MusteriEnum.musteri_id));
                musteri.setIsim(rs.getString("" + MusteriEnum.musteri_adi));
                musteri.setSoyisim(rs.getString("" + MusteriEnum.musteri_soyadi));
                musteri.setKullanici_adi(rs.getString("" + MusteriEnum.musteri_kullaniciAdi));
                musteri.seteMail(rs.getString("" + MusteriEnum.musteri_email));
                musteri.setTelefon(rs.getString("" + MusteriEnum.musteri_telefon));
                musteri.setSifre(rs.getString("" + MusteriEnum.musteri_sifre));

                System.out.println("musteriid " + musteri.getIsim());

                musteriList.add(musteri);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MusteriCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("allMusteri()");
        } finally {
            super.kapat();
        }
        return musteriList;

    }

    @Override
    public boolean create(Object o) {
        Musteri musteri = (Musteri) o;
        try {
            String q = "CALL insertMusteri(?,?,?,?,?,?)";
            PreparedStatement pr = super.preBaglan(q);
            pr.setString(1, musteri.getIsim());
            pr.setString(2, musteri.getSoyisim());
            pr.setString(3, musteri.getKullanici_adi());
            pr.setString(4, musteri.geteMail());
            pr.setString(5, musteri.getTelefon());
            pr.setString(6, musteri.getSifre());
            int isCreate = pr.executeUpdate();

            if (isCreate >= 0) {
                System.out.println("Insert başarılı");
                return true;
            }

        } catch (Exception e) {
            System.err.println("InsertMusteri() : " + e);
        } finally {
            super.kapat();
        }

        return false;
    }

    @Override
    public boolean delete(String condition) {
        try {
            String q = "CALL delMusteri(?)";
            PreparedStatement pr = super.preBaglan(q);
            pr.setString(1, condition);
            int isDelete = pr.executeUpdate();

            if (isDelete >= 0) {
                System.out.println("Silme başarılı");
                return true;
            }

        } catch (Exception e) {
            System.err.println("DellMusteri() : " + e);
        } finally {
            super.kapat();
        }

        return false;
    }

}
