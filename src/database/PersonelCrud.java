package database;

import enums.PersonelEnum;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import property.Personel;

/**
 *
 * @author Tugay Demirel
 */
public class PersonelCrud extends DB implements CrudProcesses {

    @Override
    public boolean update(Object o) {
        Personel personel = (Personel) o;
        try {
            String q = "CALL updatePersonel(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pr = super.preBaglan(q);

            pr.setInt(1, personel.getId());
            pr.setString(2, personel.getAd());
            pr.setString(3, personel.getSoyad());
            pr.setInt(4, personel.getCinsiyet());
            pr.setString(5, personel.getEmail());
            pr.setString(6, personel.getTelefon());
            pr.setString(7, personel.getSifre());
            pr.setString(8, personel.getMaas());
            pr.setInt(9, personel.getStatu());
            pr.setDate(10, (Date) personel.getDate());
            int isCreate = pr.executeUpdate();

            if (isCreate >= 0) {
                System.out.println("update başarılı");
                return true;
            }

        } catch (Exception e) {
            System.err.println("UpdatePersonel() : " + e);
        } finally {
            super.kapat();
        }
        return false;
    }

    @Override
    public ArrayList<? extends Object> read(String condition) {
        System.out.println("buraya gelen şart : " + condition);
        ArrayList<Personel> personelList = new ArrayList<>();
        Personel personel = null;

        try {
            ResultSet rs = null;

            if (condition.equals("")) {
                rs = super.baglan().executeQuery("CALL allPersonel()");
            } else {
                rs = super.baglan().executeQuery("CALL getPersonel('" + condition + "')");
            }
            while (rs.next()) {
                personel = new Personel();

                personel.setId(rs.getInt("" + PersonelEnum.personel_id));
                personel.setAd(rs.getString("" + PersonelEnum.personel_adi));
                personel.setSoyad(rs.getString("" + PersonelEnum.personel_soyadi));
                personel.setCinsiyet(rs.getInt("" + PersonelEnum.personel_cinsiyet));
                personel.setEmail(rs.getString("" + PersonelEnum.personel_email));
                personel.setTelefon(rs.getString("" + PersonelEnum.personel_telefon));
                personel.setSifre(rs.getString("" + PersonelEnum.personel_sifre));
                personel.setMaas(rs.getString("" + PersonelEnum.personel_maas));
                personel.setStatu(rs.getInt("" + PersonelEnum.personel_status));
                personel.setDate(rs.getDate("" + PersonelEnum.personel_tarih));

                personelList.add(personel);

            }

        } catch (Exception e) {
            System.err.println("AllPersonel: " + e);
        } finally {
            super.kapat();
        }
        return personelList;
    }
    
     public ArrayList<? extends Object> read(int condition) {
        System.out.println("buraya gelen şart : " + condition);
        ArrayList<Personel> personelList = new ArrayList<>();
        Personel personel = null;

        try {
            ResultSet rs = null;

                rs = super.baglan().executeQuery("CALL getPersonelForStatus("+condition+")");
            while (rs.next()) {
                personel = new Personel();

                personel.setId(rs.getInt("" + PersonelEnum.personel_id));
                personel.setAd(rs.getString("" + PersonelEnum.personel_adi));
                personel.setSoyad(rs.getString("" + PersonelEnum.personel_soyadi));
                personel.setCinsiyet(rs.getInt("" + PersonelEnum.personel_cinsiyet));
                personel.setEmail(rs.getString("" + PersonelEnum.personel_email));
                personel.setTelefon(rs.getString("" + PersonelEnum.personel_telefon));
                personel.setSifre(rs.getString("" + PersonelEnum.personel_sifre));
                personel.setMaas(rs.getString("" + PersonelEnum.personel_maas));
                personel.setStatu(rs.getInt("" + PersonelEnum.personel_status));
                personel.setDate(rs.getDate("" + PersonelEnum.personel_tarih));

                personelList.add(personel);

            }

        } catch (Exception e) {
            System.err.println("AllPersonelForStatus: " + e);
        } finally {
            super.kapat();
        }
        return personelList;
    }

    @Override
    public boolean create(Object o) {
        Personel personel = (Personel) o;
        try {
            String q = "CALL insertPersonel(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pr = super.preBaglan(q);
            pr.setString(1, personel.getAd());
            pr.setString(2, personel.getSoyad());
            pr.setInt(3, personel.getCinsiyet());
            pr.setString(4, personel.getEmail());
            pr.setString(5, personel.getTelefon());
            pr.setString(6, personel.getSifre());
            pr.setString(7, personel.getMaas());
            pr.setInt(8, personel.getStatu());
            pr.setDate(9, (Date) personel.getDate());
            int isCreate = pr.executeUpdate();

            if (isCreate >= 0) {
                System.out.println("Insert başarılı");
                return true;
            }

        } catch (Exception e) {
            System.err.println("InsertPersonel() : " + e);
        } finally {
            super.kapat();
        }
        return false;
    }

    @Override
    public boolean delete(String condition) {
        try {
            String q = "CALL delPersonel(?)";
            PreparedStatement pr = super.preBaglan(q);
            pr.setString(1, condition);
            int isDelete = pr.executeUpdate();

            if (isDelete >= 0) {
                System.out.println("Silme başarılı");
                return true;
            }

        } catch (Exception e) {
            System.err.println("DellPersonel() : " + e);
        } finally {
            super.kapat();
        }

        return false;
    }

}
