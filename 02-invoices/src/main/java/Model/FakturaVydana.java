package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Trida pro vydane faktury
 */
public class FakturaVydana extends Faktura {
    public FakturaVydana(String cislo, UdajePlatby udajePlatby, PolozkyPlatby polozkyPlatby, String adresat, Date datumSplatnosti) {
        super(cislo, udajePlatby, polozkyPlatby, adresat, datumSplatnosti);
    }

    @Override
    public String toString() {
        SimpleDateFormat dt = new SimpleDateFormat("dd. mm. yyyy");

        return "Vydana faktura: " + this.getCisloFaktury() + "\n"
                + "   > " + this.udajePlatby + "\n"
                + "   > " + this.polozkyPlatby + "\n"
                + "   > Adresat: " + this.adresat + "\n"
                + "   > Datum splatnosti: " + dt.format(this.datumSplatnosti) + "\n"
                + "   > Celkova castka: " + this.getCelkovaCena() + " CZK \n"
                ;
    }

    @Override
    public double getCelkovaCena() {
        return 0 - this.polozkyPlatby.getCelkem();
    }
}
