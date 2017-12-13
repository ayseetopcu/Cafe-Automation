
package database;

import enums.SiparisEnum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import property.Siparis;

/**
 *
 * @author aylin
 */
public class SiparisCrud extends DB implements CrudProcesses{

    @Override
    public boolean update(Object o) {
        System.out.println("---------Siparis update----------------");
        System.out.println("gelen siparis bilgileri: ");
        Siparis siparis = (Siparis) o;
        System.out.println("siparis içerik" + siparis.getIcerik());
        System.out.println("siparis tutar" + siparis.getTutar());
        try {
            
            
            
            String q = "CALL updateSiparis(?,?,?)";
            PreparedStatement pr = super.preBaglan(q);
            
            
            pr.setString(1, siparis.getIcerik());
            pr.setString(2, siparis.getTutar());
            pr.setInt(3, siparis.getMasaId());
            
       int isUpdate = pr.executeUpdate();
            if (isUpdate > 0) {
                System.out.println("update başarılı");
                return true;
            }
        } catch (Exception e) {
            System.err.println("UpdateSiparis()" + e);
        } finally {
            super.kapat();
            System.out.println("---------Siparis update----------------");

        }

        return false;
    }

    @Override
    public ArrayList<? extends Object> read(String condition) {
        ArrayList<Siparis> siparisList = new ArrayList<>();

        Siparis siparis = null;
        try {
            ResultSet rs = null;
            if (condition.equals("")) {
                rs = super.baglan().executeQuery("CALL allSiparis()");
            } else {
                rs = super.baglan().executeQuery("CALL getSiparis('" + condition + "')");
            }
            while (rs.next()) {
                siparis = new Siparis();
                siparis.setId(rs.getInt("" + SiparisEnum.siparis_id));
                siparis.setIcerik(rs.getString("" + SiparisEnum.siparis_icerik));
                siparis.setTutar(rs.getString("" + SiparisEnum.siparis_tutar));
                siparis.setMasaId(rs.getInt("" + SiparisEnum.masa_id));
                
             
                System.out.println("siparisid " + siparis.getId());

                siparisList.add(siparis);

            }

        } catch (SQLException ex) {//-------------------??????????????????????????-----------------------------
            Logger.getLogger(MusteriCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("allMusteri()");
        } finally {
            super.kapat();
        }
        return siparisList;
    }

    @Override
    public boolean create(Object o) {
        Siparis siparis = (Siparis) o;
        try {
            System.out.println("Siparis create " + siparis.getIcerik());
            String q = "CALL insertSiparis(?,?,?)";
            PreparedStatement pr = super.preBaglan(q);

            pr.setString(1, siparis.getIcerik());
            pr.setString(2, siparis.getTutar());
            pr.setString(3, "" + siparis.getMasaId());
           

            int isUpdate = pr.executeUpdate();
            if (isUpdate > 0) {
                System.out.println("create başarılı");
                return true;
            }
        } catch (Exception e) {
            System.out.println("InsertSiparis()" + e);
        } finally {
            super.kapat();
        }
        return false;
    }

    @Override
    public boolean delete(String condition) {
        try {
            System.out.println("deletedeeee");
            System.out.println(condition);
            String q = "CALL delSiparis(?)";
            PreparedStatement pr = super.preBaglan(q);

            pr.setString(1, condition);
            int isDelete = pr.executeUpdate();
            if (isDelete > 0) {
                System.out.println("delete başarılı");
                return true;
            }
        } catch (Exception e) {
            System.err.println("delSiparis()" + e); //?????????????????
        } finally {
            super.kapat();
        }
        return false;
    }

}