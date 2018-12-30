package Model;

import java.util.Date;

/**
 * Abstraktni trid pro vsechny faktury
 */
abstract public class Faktura implements IFaktura {

    /**
     * Cislo faktury
     */
    protected String cislo;

    /**
     * udaje platby
     */
    protected UdajePlatby udajePlatby;

    /**
     * polozky platby
     */
    protected PolozkyPlatby polozkyPlatby;

    /**
     * adresat
     */
    protected String adresat;

    /**
     * datum splatnosti
     */
    protected Date datumSplatnosti;

    public Faktura(String cislo, UdajePlatby udajePlatby, PolozkyPlatby polozkyPlatby, String adresat, Date datumSplatnosti) {
        this.cislo = cislo;
        this.udajePlatby = udajePlatby;
        this.polozkyPlatby = polozkyPlatby;
        this.adresat = adresat;
        this.datumSplatnosti = datumSplatnosti;
    }

    /**
     * @return celkova cena faktury
     */
    public abstract double getCelkovaCena();

    /**
     * @return cislo faktury
     */
    public String getCisloFaktury() {
        return this.cislo;
    }
}
