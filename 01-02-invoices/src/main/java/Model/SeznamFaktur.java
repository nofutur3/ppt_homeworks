package Model;


import java.util.ArrayList;

/**
 * Trida slouzici pro ulozeni vsech faktur,
 * je mozne do vlozit jak fakturu prijatou,
 * tak fakturu vydanou
 */
public class SeznamFaktur {

    private ArrayList<FakturaPrijata> pFaktury = new ArrayList<FakturaPrijata>();
    private ArrayList<FakturaVydana> vFaktury = new ArrayList<FakturaVydana>();

    /**
     * Metoda vklada do seznamu novou prijatou fakturu
     * @param faktura
     * @return bool
     */
    public boolean addPrijataFaktura(FakturaPrijata faktura) {
        for (FakturaPrijata fakturaPrijata : pFaktury) {
            if (faktura.getCisloFaktury().equals(fakturaPrijata.getCisloFaktury())) {
                return false;
            }
        }
        pFaktury.add(faktura);
        return true;
    }

    /**
     * metoda pridava do seznamu novou vydanou fakturu
     * @param faktura
     * @return bool
     */
    public boolean addVydanaFaktura(FakturaVydana faktura) {
        for (FakturaVydana fakturaVydana : vFaktury) {
            if (faktura.getCisloFaktury().equals(fakturaVydana.getCisloFaktury())) {
                return false;
            }
        }
        vFaktury.add(faktura);
        return true;
    }

    /**
     * vraci seznam se vsemi fakturami
     * @return
     */
    public ArrayList<IFaktura> getFaktury() {
        ArrayList<IFaktura> result = new ArrayList<IFaktura>();
        result.addAll(vFaktury);
        result.addAll(pFaktury);
        return result;
    }

    /**
     * vrací aktualni pocet faktur v seznamu
     * @return
     */
    public int getPocetFaktur() {
        return this.getFaktury().size();
    }
}
