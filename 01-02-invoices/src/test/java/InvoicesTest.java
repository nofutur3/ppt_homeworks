import Model.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoicesTest {

    private Faker faker;

    @BeforeEach
    void init() {
        this.faker = new Faker();
    }

    @Test
    void randomNumber() {
        RandomNumber rn = new RandomNumber();
        for (int i = 0; i < 10; i++ ) {
            int result = rn.getRandom();
            assertTrue(result == 1 || result == 0);
        }
    }

    @Test
    void correctPriceInInvoice() {
        SeznamFaktur seznam = new SeznamFaktur();
        RandomNumber r = new RandomNumber();
        FakturaVydana posledniPridanaVF = null;

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

        assertSame(10, seznam.getPocetFaktur());

        assertFalse(seznam.addVydanaFaktura(posledniPridanaVF));

        assertSame(10, seznam.getPocetFaktur());

        for (IFaktura faktura : seznam.getFaktury()) {
            if (faktura instanceof FakturaPrijata) {
                assertTrue(faktura.getCelkovaCena() >= 0);
            } else {
                assertTrue(faktura.getCelkovaCena() <= 0);
            }
        }
    }
}
