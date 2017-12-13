package database;

import enums.CihazEnum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import property.Cihaz;

/**
 *
 * @author Tugay Demirel
 */
public class CihazCrud extends DB implements CrudProcesses {

    @Override
    public boolean update(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<? extends Object> read(String condition) {
        ArrayList<Cihaz> cihazList = new ArrayList();
        Cihaz cihaz = null;

        try {
            ResultSet rs = null;
            if (condition.equals("")) {
                rs = super.baglan().executeQuery("CALL allCihaz()");
            } else {
                rs = super.baglan().executeQuery("CALL getCihaz('" + condition + "')");
            }
            while (rs.next()) {
                cihaz = new Cihaz();
                cihaz.setComputerName(rs.getString("" + CihazEnum.computer_name));
                cihaz.setMacAdres(rs.getString("" + CihazEnum.mac_adresi));
                cihaz.setMasa_id(rs.getString("" + CihazEnum.masa_id));
                cihazList.add(cihaz);
            }
        } catch (Exception e) {
            System.err.println("allKategori():" + e);
        } finally {
            super.kapat();
        }
        return cihazList;
    }

    @Override
    public boolean create(Object o) {
        Cihaz cihaz = (Cihaz) o;
        try {
            String q = "CALL insertCihaz(?,?,?)";
            PreparedStatement pr = super.preBaglan(q);

            pr.setString(1, cihaz.getMacAdres());
            pr.setString(2, "" + cihaz.getComputerName());
            pr.setString(3, "" + cihaz.getMasa_id());

            int isUpdate = pr.executeUpdate();
            if (isUpdate > 0) {
                System.out.println("create başarılı");
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("insertCihaz" + ex);
        }
        return false;
    }

    @Override
    public boolean delete(String codition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
