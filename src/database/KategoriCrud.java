package database;

import enums.KategoriEnum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import property.Kategori;

/**
 *
 * @author Tugay Demirel
 */
public class KategoriCrud extends DB implements CrudProcesses {

    @Override
    public boolean update(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<? extends Object> read(String condition) {
        
        ArrayList<Kategori> kategoriList = new ArrayList<>();
        Kategori kategori = null;

        try {
            ResultSet rs = null;
            if (condition.equals("")) {
                rs = super.baglan().executeQuery("CALL allKategori()");
            } else {
                rs = super.baglan().executeQuery("CALL getKategori('" + condition + "')");
            }
            while (rs.next()) {
                kategori = new Kategori();
                kategori.setId(rs.getInt("" + KategoriEnum.kategori_id));
                kategori.setName(rs.getString("" + KategoriEnum.kategori_name));
                kategoriList.add(kategori);
            }
        } catch (Exception e) {
            System.err.println("allKategori():" +e);
        }finally{
            super.kapat();
        }
        return kategoriList;
    }

    @Override
    public boolean create(Object o) {
        Kategori kategori = (Kategori) o;
        try {
            String query = "CALL insertKategori(?)";
            PreparedStatement pr = super.preBaglan(query);
            pr.setString(1, kategori.getName());
            int isInsert = pr.executeUpdate();
            if (isInsert > 0) {
                System.out.println("update başarılı");
                return true;
            }
        } catch (Exception e) {
            System.err.println("updatekategori()" + e);
        } finally {
            super.kapat();
        }
        return false;
    }

    @Override
    public boolean delete(String codition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
