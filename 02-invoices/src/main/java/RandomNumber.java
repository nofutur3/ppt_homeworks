import java.util.Random;


public class RandomNumber {

    public int getRandom() {
        int max = 1;
        int min = 0;
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
