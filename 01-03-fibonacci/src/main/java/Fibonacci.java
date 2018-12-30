import java.security.InvalidParameterException;

/**
 * Trida pro vypocet fibonacciho posloupnosti
 */
public class Fibonacci {

    private int[] cache = new int[1000];

    /**
     *  Vypocet fibonacciho posloupnosti pomoci rekurze s vyuzitim cache
     *
     * @param   position    kolikate cislo fibanocciho posloupnosti ma metoda vratit
     * @return              vraci vysledne cislo fibonacciho posloupnosti
     */
    public int getResultForPosition(int position) {
        if (position < 0 || position > 1000) {
            throw new InvalidParameterException("Position has to be number between 0 and 1000");
        }

        return this.calculateFibonacci(position);
    }

    /**
     *  Vypocet fibonacciho posloupnosti pomoci cyklu
     *
     * @param   position    kolikate cislo fibanocciho posloupnosti ma metoda vratit
     * @return              vraci vysledne cislo fibonacciho posloupnosti
     */
    public int getResultForPositionCycle(int position) {
        if (position < 0 || position > 1000) {
            throw new InvalidParameterException("Position has to be number between 0 and 1000");
        }

        return this.calculateFibonacciWithForCycle(position);
    }

    /**
     * Implementace fibonacciho posloupnosti - algoritmu
     * za pomocí for rekurze a s vyuzitim cache
     *
     * @param   position    kolikate cislo fibanocciho posloupnosti ma metoda vratit
     * @return              vraci vysledne cislo fibonacciho posloupnosti
     */
    private int calculateFibonacci(int position) {
        if (position == 0) return 0;
        if (position == 1) return 1;
        if (this.cache[position] != 0) return this.cache[position];

        int result = this.calculateFibonacci(position - 1) + this.calculateFibonacci(position - 2);

        this.cache[position] = result;

        return result;
    }

    /**
     * Implementace fibonacciho posloupnosti - algoritmu
     * za pomocí for cyklu
     *
     * @param   position    kolikate cislo fibanocciho posloupnosti ma metoda vratit
     * @return              vraci vysledne cislo fibonacciho posloupnosti
     */
    private int calculateFibonacciWithForCycle(int position) {
        if (position == 0) return 0;

        int previous = 0;
        int current = 1;

        for (int i = position; i > 1; i--) {
            int fib = previous + current;
            previous = current;
            current = fib;
        }

        return current;
    }
}
