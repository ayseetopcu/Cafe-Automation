
package property;

/**
 *
 * @author Tugay Demirel
 */
public class Urun {
    
    private int id;
    private String ad;
    private String detay;
    private String fiyat;
    private int kategori;
    private boolean stokDurumu;

    public boolean isStokDurumu() {
        return stokDurumu;
    }

    public void setStokDurumu(boolean stokDurumu) {
        this.stokDurumu = stokDurumu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getDetay() {
        return detay;
    }

    public void setDetay(String detay) {
        this.detay = detay;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public int getKategori() {
        return kategori;
    }

    public void setKategori(int kategori) {
        this.kategori = kategori;
    }

}
