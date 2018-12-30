package Model;

/**
 * Interface pro tridy faktur
 */
public interface IFaktura {
    /**
     * @return cislo faktury
     */
    public String getCisloFaktury();

    /**
     * @return celkova castka faktury
     */
    public double getCelkovaCena();
}
