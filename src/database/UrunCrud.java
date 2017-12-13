package database;

import enums.UrunEnum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import property.Kategori;
import property.Urun;

/**
 *
 * @author Tugay Demirel
 */
public class UrunCrud extends DB implements CrudProcesses {

    @Override
    public boolean update(Object o) {
        Urun menu = (Urun) o;
        try {
            String q = "CALL updateUrun(?,?,?,?,?,?)";
            PreparedStatement pr = super.preBaglan(q);

            pr.setString(1, "" + menu.getId());
            pr.setString(2, menu.getAd());
            pr.setString(3, menu.getDetay());
            pr.setString(4, menu.getFiyat());
            pr.setString(5, "" + menu.getKategori());
            pr.setBoolean(6, menu.isStokDurumu());

            int isUpdate = pr.executeUpdate();
            if (isUpdate > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("UpdateMenu()" + e);
        } finally {
            super.kapat();

        }

        return false;
    }

    @Override
    public ArrayList<? extends Object> read(String condition) {
        ArrayList<Urun> menuList = new ArrayList<>();
        Urun menu = null;

        try {
            ResultSet rs = null;
            if (condition.equals("")) {
                rs = super.baglan().executeQuery("CALL allUrun()");
            } else {
                rs = super.baglan().executeQuery("CALL getUrun('" + condition + "')");
            }
            while (rs.next()) {
                menu = new Urun();
                menu.setId(rs.getInt("" + UrunEnum.urun_id));
                menu.setAd(rs.getString("" + UrunEnum.urun_adi));
                menu.setDetay(rs.getString("" + UrunEnum.urun_detay));
                menu.setFiyat(rs.getString("" + UrunEnum.urun_fiyat));
                menu.setKategori(rs.getInt("" + UrunEnum.kategori_id));
                menu.setStokDurumu(rs.getBoolean("" + UrunEnum.urun_stok));
                menuList.add(menu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusteriCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("allMusteri()");
        } finally {
            super.kapat();
        }
        return menuList;
    }

    public ArrayList<? extends Object> read(Kategori kategori) {
        ArrayList<Urun> urunList = new ArrayList<>();
        Urun urun = null;

        try {
            ResultSet rs = null;

            rs = super.baglan().executeQuery("CALL getUrunForKategori('"+kategori.getId()+"')");

            while (rs.next()) {
                urun = new Urun();
                urun.setId(rs.getInt("" + UrunEnum.urun_id));
                urun.setAd(rs.getString("" + UrunEnum.urun_adi));
                urun.setDetay(rs.getString("" + UrunEnum.urun_detay));
                urun.setFiyat(rs.getString("" + UrunEnum.urun_fiyat));
                urun.setKategori(rs.getInt("" + UrunEnum.kategori_id));
                urun.setStokDurumu(rs.getBoolean("" + UrunEnum.urun_stok));
                urunList.add(urun);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusteriCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("getUrunForKategori()");
        } finally {
            super.kapat();
        }
        return urunList;
    }

    @Override
    public boolean create(Object o) {
        Urun menu = (Urun) o;
        try {
            String q = "CALL insertUrun(?,?,?,?,?)";
            PreparedStatement pr = super.preBaglan(q);

            pr.setString(1, menu.getAd());
            pr.setString(2, menu.getDetay());
            pr.setString(3, menu.getFiyat());
            pr.setString(4, "" + menu.getKategori());
            pr.setBoolean(5, menu.isStokDurumu());

            int isUpdate = pr.executeUpdate();
            if (isUpdate > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("InsertMenu()" + e);
        } finally {
            super.kapat();
        }
        return false;
    }

    @Override
    public boolean delete(String condition) {
        try {
            String q = "CALL delUrun(?)";
            PreparedStatement pr = super.preBaglan(q);

            pr.setString(1, condition);
            int isDelete = pr.executeUpdate();
            if (isDelete > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("DeleteMenu()" + e);
        } finally {
            super.kapat();
        }
        return false;
    }

}
