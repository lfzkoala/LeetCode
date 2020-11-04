import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;
public class NumberOf1BitsTest {

    @Test
    public void test1(){
        NumberOf1Bits P = new NumberOf1Bits();
        int n = 11;
        int ans = 3;
        assertTrue(P.hammingWeight(n) == ans);
    }



}
