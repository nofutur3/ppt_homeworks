package Model;

public class UdajePlatby {
    // vs, ss, ks

    private String vs;
    private String ss;
    private String ks;

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
