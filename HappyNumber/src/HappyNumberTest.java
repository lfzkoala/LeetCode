import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class HappyNumberTest {
    @Test
    public void Test1(){
        HappyNumber P = new HappyNumber();
        int n = 19;
        assertTrue(P.isHappy(n));
    }

}
