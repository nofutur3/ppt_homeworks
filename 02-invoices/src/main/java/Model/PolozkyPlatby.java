package Model;

import java.text.DecimalFormat;

public class PolozkyPlatby {

    private double castkaBezDph;
    private double dph;
    private int sazbaDph;
    private double celkem;

    public PolozkyPlatby(double castkaBezDph, int sazbaDph) {
        this.castkaBezDph = castkaBezDph;
        this.sazbaDph = sazbaDph;
    }

    private double getDph() {
        return Math.round((this.castkaBezDph / 100)  * (100 + this.sazbaDph) - this.castkaBezDph);
    }

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
