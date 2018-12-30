package Model;

import java.text.DecimalFormat;

/**
 * Trida reprezentujici polozky platby
 */
public class PolozkyPlatby {

    /**
     * castka bez dph
     */
    private double castkaBezDph;

    /**
     * dph - tato hodnota se dopocitava
     */
    private double dph;

    /**
     * sazba dph  - 15 nebo 21
     */
    private int sazbaDph;

    /**
     * celkova cena, tato hodnota se dopocitava
     */
    private double celkem;

    public PolozkyPlatby(double castkaBezDph, int sazbaDph) {
        this.castkaBezDph = castkaBezDph;
        this.sazbaDph = sazbaDph;
    }

    /**
     * @return vypocitanou hodnotu dph
     */
    private double getDph() {
        return Math.round((this.castkaBezDph / 100)  * (100 + this.sazbaDph) - this.castkaBezDph);
    }

    /**
     * @return celkovou cenu faktury
     */
    public double getCelkem() {
        return Math.round(getDph() + this.castkaBezDph);
    }

    @Override
    public String toString() {
        return "Polozky -> " +
                " castka bez DPH: " + this.castkaBezDph +
                " sazba dph: " + this.sazbaDph +
                " dph: " +  this.getDph() +
                " celkem: " + this.getCelkem()
                ;
    }
}
