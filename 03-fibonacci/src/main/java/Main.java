import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean cycle = true;
        Fibonacci fibonacci = new Fibonacci();
        Scanner reader = new Scanner(System.in);

        while (cycle) {
            reader = new Scanner(System.in);
            try {
                System.out.println("Enter a number: ");
                int n = reader.nextInt();

                System.out.println("Result REC: " + fibonacci.getResultForPosition(n));
                System.out.println("Result FOR: " + fibonacci.getResultForPositionCycle(n));
            } catch (InputMismatchException e) {
                System.out.println("Input has to be a number");
                continue;
            }

            System.out.println("Continue? (y/n): ");
            String s = reader.next();
            if (!s.equals("y")) {
                cycle = false;
            }
        }

        reader.close();
        System.out.println("Good bye!");
    }
}
