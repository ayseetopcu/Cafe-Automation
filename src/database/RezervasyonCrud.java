package database;

import enums.RezervasyonEnum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import property.Rezervasyon;

/**
 *
 * @author Tugay Demirel
 */
public class RezervasyonCrud extends DB implements CrudProcesses {

    @Override
    public boolean update(Object o) {
        Rezervasyon rezervasyon = (Rezervasyon) o;
        System.out.println(rezervasyon.getAd());
        String[] dizi = rezervasyon.getAd().split("###");
        String ad = dizi[0];
        String oldmasaId = dizi[1];
        rezervasyon.setAd(ad);
        java.sql.Date sqlDate = new java.sql.Date(rezervasyon.getDate().getTime());
        try {
            String q = "CALL updateRezervasyon(?,?,?,?,?,?)";
            PreparedStatement pr = super.preBaglan(q);

            pr.setInt(1, rezervasyon.getId());
            pr.setString(2, rezervasyon.getAd());
            pr.setString(3, rezervasyon.getTelefon());
            pr.setInt(4, Integer.valueOf(oldmasaId));
            pr.setInt(5, rezervasyon.getMasaId());
            pr.setDate(6, sqlDate);

            int isCreate = pr.executeUpdate();

            if (isCreate >= 0) {
                System.out.println("create başarılı");
                return true;
            }

        } catch (Exception e) {
            System.err.println("updateRezervasyon() : " + e);
        } finally {
            super.kapat();
        }
        return false;
    }

    @Override
    public ArrayList<? extends Object> read(String condition) {
        ArrayList<Rezervasyon> rezervasyonList = new ArrayList<>();
        Rezervasyon rezervasyon = null;

        try {
            ResultSet rs = null;

            if (condition.equals("")) {
                rs = super.baglan().executeQuery("CALL allRezervasyon()");
            } else {
                rs = super.baglan().executeQuery("CALL getRezervasyon('" + condition + "')");
            }
            while (rs.next()) {
                rezervasyon = new Rezervasyon();
                rezervasyon.setId(rs.getInt("" + RezervasyonEnum.rezervasyon_id));
                rezervasyon.setAd(rs.getString("" + RezervasyonEnum.rezervasyon_name));
                rezervasyon.setTelefon(rs.getString("" + RezervasyonEnum.rezervasyon_telefon));
                rezervasyon.setMasaId(rs.getInt("" + RezervasyonEnum.masa_id));
                rezervasyon.setDate(rs.getDate("" + RezervasyonEnum.rezervasyon_tarihi));
                rezervasyonList.add(rezervasyon);
            }
        } catch (Exception e) {
            System.err.println("allRezervasyon()" + e);
        } finally {
            super.kapat();
        }
        return rezervasyonList;
    }

    //Overload rezervasyon getir metodu, tarih ile getirilecek veriler için tanımlandı
    //Tetiklendiği sınıf, frmRezervasyonCreateUpdate sınıfıdır. 
    public ArrayList<? extends Object> read(Date date) {
        ArrayList<Rezervasyon> rezervasyonList = new ArrayList<>();
        Rezervasyon rezervasyon = null;

        try {
            ResultSet rs = null;

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            Date date2 = new Date();
            date2.setTime(date.getTime());
            date2.setHours(0);
            date2.setMinutes(00);
            date2.setSeconds(00);

            rs = super.baglan().executeQuery("CALL getRezervasyonForDate('" + dateFormat.format(date2) + "','" + dateFormat.format(date) + "')");
            while (rs.next()) {
                rezervasyon = new Rezervasyon();
                rezervasyon.setId(rs.getInt("" + RezervasyonEnum.rezervasyon_id));
                rezervasyon.setAd(rs.getString("" + RezervasyonEnum.rezervasyon_name));
                rezervasyon.setTelefon(rs.getString("" + RezervasyonEnum.rezervasyon_telefon));
                rezervasyon.setMasaId(rs.getInt("" + RezervasyonEnum.masa_id));
                rezervasyon.setDate(rs.getDate("" + RezervasyonEnum.rezervasyon_tarihi));
                rezervasyonList.add(rezervasyon);
                System.out.println("Rezervasyon Cruda gelen veri: " + rezervasyon.getDate());
            }
        } catch (Exception e) {
            System.err.println("getRezervasyonForDate()" + e);
        } finally {
            super.kapat();
        }
        return rezervasyonList;
    }

    @Override
    public boolean create(Object o) {
        Rezervasyon rezervasyon = (Rezervasyon) o;
        java.sql.Date sqlDate = new java.sql.Date(rezervasyon.getDate().getTime());

        try {
            String query = "CALL insertRezervasyon(?,?,?,?)";
            PreparedStatement pr = super.preBaglan(query);
            pr.setString(1, rezervasyon.getAd());
            pr.setString(2, rezervasyon.getTelefon());
            pr.setInt(3, rezervasyon.getMasaId());
            pr.setDate(4, sqlDate);
            int isInsert = pr.executeUpdate();
            if (isInsert > 0) {
                System.out.println("update başarılı");
                return true;
            }
        } catch (Exception e) {
            System.err.println("insertRezervasyon()" + e);
        } finally {
            super.kapat();
        }
        return false;
    }

    @Override
    public boolean delete(String condition) {
        try {
            String[] conditions = condition.split("###");
            String q = "CALL delRezervasyon(?,?)";
            PreparedStatement pr = super.preBaglan(q);

            pr.setString(1, conditions[0]);
            pr.setString(2, conditions[1]);

            int isDelete = pr.executeUpdate();
            if (isDelete > 0) {
                System.out.println("delete başarılı");
                return true;
            }
        } catch (Exception e) {
            System.out.println("DeleteRezervasyon()" + e);
        } finally {
            super.kapat();
        }
        return false;
    }

}
