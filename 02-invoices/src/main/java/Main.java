import Model.*;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    private static ArrayList<IFaktura> faktury;


    public static void main(String[] args) {

        SeznamFaktur seznam = new SeznamFaktur();
        RandomNumber r = new RandomNumber();
        Faker faker = new Faker();
        FakturaVydana posledniPridanaVF = null;

        // fake data
        for (int i = 0; i < 5; i++) {
            String number = Integer.toString(faker.number().numberBetween(10000, 20000));
            FakturaPrijata prijataFaktura = new FakturaPrijata(
                    "V" + number,
                    new UdajePlatby(number, Integer.toString(faker.number().numberBetween(1000, 2000)), Integer.toString(faker.number().numberBetween(1000, 2000))),
                    new PolozkyPlatby(faker.number().numberBetween(1000, 10000), r.getRandom() == 1 ? 21 : 15),
                    faker.name().fullName(),
                    faker.date().past(20, TimeUnit.DAYS)
            );
            seznam.addPrijataFaktura(prijataFaktura);

            String vnumber = Integer.toString(faker.number().numberBetween(10000, 20000));
            FakturaVydana fakturaVydana = new FakturaVydana(
                    vnumber,
                    new UdajePlatby(vnumber, Integer.toString(faker.number().numberBetween(1000, 2000)), Integer.toString(faker.number().numberBetween(1000, 2000))),
                    new PolozkyPlatby(faker.number().numberBetween(1000, 20000), r.getRandom() == 1 ? 21 : 15),
                    faker.name().fullName(),
                    faker.date().past(20, TimeUnit.DAYS)
            );
            posledniPridanaVF = fakturaVydana;
            seznam.addVydanaFaktura(fakturaVydana);
        }

        if (!seznam.addVydanaFaktura(posledniPridanaVF)) {
            System.out.println("! Faktura s cislem " + posledniPridanaVF.getCisloFaktury() + " uz v systemu je, takze nebyla pridana.\n\n");
        }

        faktury = seznam.getFaktury();
        System.out.println("V systému je: " + seznam.getFaktury().size() + " faktur\n#####################\n\n");

        for (IFaktura faktura : faktury) {
            System.out.println(faktura);
        }
    }
}
