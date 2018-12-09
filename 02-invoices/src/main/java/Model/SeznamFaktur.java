package Model;


import java.util.ArrayList;

public class SeznamFaktur {

    private ArrayList<FakturaPrijata> pFaktury = new ArrayList<FakturaPrijata>();
    private ArrayList<FakturaVydana> vFaktury = new ArrayList<FakturaVydana>();

    public boolean addPrijataFaktura(FakturaPrijata faktura) {
        for (FakturaPrijata fakturaPrijata : pFaktury) {
            if (faktura.getCisloFaktury().equals(fakturaPrijata.getCisloFaktury())) {
                return false;
            }
        }
        pFaktury.add(faktura);
        return true;
    }

    public boolean addVydanaFaktura(FakturaVydana faktura) {
        for (FakturaVydana fakturaVydana : vFaktury) {
            if (faktura.getCisloFaktury().equals(fakturaVydana.getCisloFaktury())) {
                return false;
            }
        }
        vFaktury.add(faktura);
        return true;
    }

    public ArrayList<IFaktura> getFaktury() {
        ArrayList<IFaktura> result = new ArrayList<IFaktura>();
        result.addAll(vFaktury);
        result.addAll(pFaktury);
        return result;
    }

    public int getPocetFaktur() {
        return this.getFaktury().size();
    }
}
