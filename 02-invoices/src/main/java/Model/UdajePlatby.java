package Model;

/**
 * Trida pro praci s udaji platby
 */
public class UdajePlatby {

    private String vs;
    private String ss;
    private String ks;

    /**
     *
     * @param vs - variabilni symbol
     * @param ss - specificky symbol
     * @param ks - konstantni symbol
     */
    public UdajePlatby(String vs, String ss, String ks) {
        this.vs = vs;
        this.ss = ss;
        this.ks = ks;
    }

    @Override
    public String toString() {
        return "Udaje platby -> vs: " + this.vs + " ss: " + this.ss + " ks: " + this.ks;
    }
}
