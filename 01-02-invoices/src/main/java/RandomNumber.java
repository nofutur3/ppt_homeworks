import java.util.Random;

/**
 * Trida, pro vytvoreni nahodneho cisla z rozmezi 0 - 1,
 * vyuzivana pro testovani a CLI aplikaci
 */
public class RandomNumber {

    /**
     * @return vraci nahodne cislo v rozmezi 0 - 1
     */
    public int getRandom() {
        int max = 1;
        int min = 0;
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
