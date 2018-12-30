import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class FibonacciTest {

    private int[] result = new int[] {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};
    private Fibonacci fibonacci;

    @BeforeEach
    void init() {
        this.fibonacci = new Fibonacci();
    }

    @Test
    void testFibonacciRecursive() {
        for (int i = 0; i < this.result.length; i++) {
            assertEquals(this.result[i], this.fibonacci.getResultForPosition(i));
        }
    }

    @Test
    void testFibonacciCycle() {
        for (int i = 0; i < this.result.length; i++) {
            assertEquals(this.result[i], this.fibonacci.getResultForPositionCycle(i));
        }
    }
}
