import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class ConsecutiveNumbersSumTest {
    @Test
    public void Test1(){
        ConsecutiveNumbersSum P = new ConsecutiveNumbersSum();
        int n = 5;
        int ans = 2;
        assertTrue(P.consecutiveNumbersSum(n) == ans);
    }

    @Test
    public void Test2(){
        ConsecutiveNumbersSum P = new ConsecutiveNumbersSum();
        int n = 9;
        int ans = 3;
        assertTrue(P.consecutiveNumbersSum(n) == ans);
    }

    @Test
    public void Test3(){
        ConsecutiveNumbersSum P = new ConsecutiveNumbersSum();
        int n = 15;
        int ans = 4;
        assertTrue(P.consecutiveNumbersSum(n) == ans);
    }

}
