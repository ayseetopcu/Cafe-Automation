package database;

import enums.MasaEnum;
import enums.MusteriEnum;
import enums.SiparisEnum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import property.Masa;

/**
 *
 * @author Tugay Demirel
 */
public class MasaCrud extends DB implements CrudProcesses {

    @Override
    public boolean update(Object o) {
        Masa masa = (Masa) o;
        try {
//             `updateMasa`(IN `eskiid` int ,IN `yeniid` int , IN `yeniSiparis` int , IN `yenimusteri` int)
            String q = "CALL updateMasa(?,?,?,?)";
            PreparedStatement pr = super.preBaglan(q);
            
            pr.setString(1, ""+masa.getId());
            pr.setString(2, masa.getDurum()+"");
            pr.setString(3, ""+masa.getSiparisId());
            pr.setString(4, ""+masa.getMusteriId());
            
            int isCreate = pr.executeUpdate();
            if (isCreate > 0) {
                System.out.println("Create başarılı");
                return true;
            }
            
        } catch (Exception e) {
            System.err.println("masaUpdate():"+e);
        }
        return false;
    }
    
    public boolean update(Masa eskiMasa, Masa yeniMasa){
        
        try {
//             `updateMasa`(IN `eskiid` int ,IN `yeniid` int , IN `yeniSiparis` int , IN `yenimusteri` int)
            String q = "CALL updateMasaForMove(?,?,?,?)";
            PreparedStatement pr = super.preBaglan(q);
            
            pr.setString(1, ""+eskiMasa.getId());
            pr.setString(2, ""+yeniMasa.getId());
            pr.setString(3, ""+eskiMasa.getSiparisId());
            pr.setString(4, ""+eskiMasa.getMusteriId());
            
            int isCreate = pr.executeUpdate();
            if (isCreate > 0) {
                System.out.println("Create başarılı");
                return true;
            }
            
        } catch (Exception e) {
            System.err.println("isCreate:"+e);
        }
        return false;
    }

    @Override
    public boolean create(Object o) {
        Masa masa = (Masa) o;
        System.out.println("masa kapasite : " + masa.getKapasite());
        try{
            String q = "CALL insertMasa(?,?,?)";
            PreparedStatement pr = super.preBaglan(q);

            pr.setString(1, masa.getMasaAdi());
            pr.setString(2,""+ masa.getKapasite());
            pr.setString(3,""+ masa.getDurum());
            
            int isUpdate = pr.executeUpdate();
            if (isUpdate > 0) {
                System.out.println("create başarılı");
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("insertMasa"+ex);
        }
        return false;
    }

    @Override
    public boolean delete(String codition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<? extends Object> read(String condition) {
        ArrayList<Masa> masaList = new ArrayList<>();

        Masa masa = null;
        try {
            ResultSet rs = null;
            if (condition.equals("")) {
                rs = new DB().baglan().executeQuery("CALL allMasa()");
            } else {
                rs = new DB().baglan().executeQuery("CALL getMasa('" + condition + "')");
            }
            while (rs.next()) {
                masa = new Masa();
                masa.setId(rs.getInt("" + MasaEnum.masa_id));
                masa.setMasaAdi(rs.getString("" + MasaEnum.masa_adi));
                masa.setDurum(rs.getInt("" + MasaEnum.masa_durum));
                masa.setKapasite(rs.getInt("" + MasaEnum.masa_kapasite));
                System.out.println("musteriid " + masa.getMasaAdi());

                masaList.add(masa);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MasaCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("allMusteri()");
        } finally {
            super.kapat();
        }
        return masaList;

    }
    
    public ArrayList<? extends Object> read() {
        ArrayList<ArrayList<String>> masaList = new ArrayList<>();
//masaid, masaadi, masakapasite, masadurum, musteri adi, musteri soyadi , musteri tel, siparis icerik, siparis tutar,
        try {
            ResultSet rs = null;
                rs = new DB().baglan().executeQuery("CALL allMasaJoinMusteri()");
            while (rs.next()) {
                ArrayList<String> list = new ArrayList<>();
                list.add(rs.getInt(MasaEnum.masa_id+"")+"");
                list.add(rs.getString(""+MasaEnum.masa_adi));
                list.add(rs.getString(""+MasaEnum.masa_kapasite));
                list.add(rs.getString(""+MasaEnum.masa_durum));
                
                String ad = rs.getString(""+MusteriEnum.musteri_adi);
                list.add(ad == null ? "" : ad);
                String soyAd = rs.getString(""+MusteriEnum.musteri_soyadi);
                list.add(soyAd == null ? "" : soyAd);
                list.add(rs.getString(""+MusteriEnum.musteri_telefon));
                list.add(rs.getString(""+SiparisEnum.siparis_icerik));
                list.add(rs.getString(""+SiparisEnum.siparis_tutar));
       
                System.out.println("aaazzz"+list.get(2));
                masaList.add(list);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MasaCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("allMusteri()");
        } finally {
            super.kapat();
        }
        return masaList;

    }

    //Bu method bize rezervasyon durumuna göre istenen masaları getirir.
    public ArrayList<? extends Object> read(int condition) {
        ArrayList<Masa> masaList = new ArrayList<>();
        Masa masa = null;
        try {
            ResultSet rs = null;
            rs = new DB().baglan().executeQuery("CALL getMasaForDurum("+condition+")");
            while (rs.next()) {
                masa = new Masa();
                masa.setId(rs.getInt("" + MasaEnum.masa_id));
                masa.setMasaAdi(rs.getString("" + MasaEnum.masa_adi));
                masa.setDurum(rs.getInt("" + MasaEnum.masa_durum));
                masa.setKapasite(rs.getInt("" + MasaEnum.masa_kapasite));

                masaList.add(masa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MasaCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("allMusteri()");
        } finally {
            super.kapat();
        }
        return masaList;

    }

}
