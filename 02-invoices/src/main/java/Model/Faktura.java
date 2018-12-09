package Model;

import java.util.Date;

abstract public class Faktura implements IFaktura {

    protected String cislo;

    protected UdajePlatby udajePlatby;

    protected PolozkyPlatby polozkyPlatby;

    protected String adresat;

    protected Date datumSplatnosti;

    public Faktura(String cislo, UdajePlatby udajePlatby, PolozkyPlatby polozkyPlatby, String adresat, Date datumSplatnosti) {
        this.cislo = cislo;
        this.udajePlatby = udajePlatby;
        this.polozkyPlatby = polozkyPlatby;
        this.adresat = adresat;
        this.datumSplatnosti = datumSplatnosti;
    }

    public abstract double getCelkovaCena();

    public String getCisloFaktury() {
        return this.cislo;
    }
}
