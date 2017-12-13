
package property;

/**
 *
 * @author Tugay Demirel
 */
public class Masa {
    private int id;
    private String masaAdi;
    private int kapasite;
    private int durum;
    private int siparisId;
    private int musteriId;

    public int getSiparisId() {
        return siparisId;
    }

    public void setSiparisId(int siparisId) {
        this.siparisId = siparisId;
    }

    public int getMusteriId() {
        return musteriId;
    }

    public void setMusteriId(int musteriId) {
        this.musteriId = musteriId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMasaAdi() {
        return masaAdi;
    }

    public void setMasaAdi(String masaAdi) {
        this.masaAdi = masaAdi;
    }

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public int getDurum() {
        return durum;
    }

    public void setDurum(int durum) {
        this.durum = durum;
    }

 
    

}
