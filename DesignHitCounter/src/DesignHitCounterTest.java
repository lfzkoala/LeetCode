import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class DesignHitCounterTest {
    @Test
    public void Test1(){
        DesignHitCounter P = new DesignHitCounter();
        P.HitCounter();
        P.hit(1);
        P.hit(2);
        P.hit(3);
        assertTrue(P.getHits(4) == 3);
        P.hit(300);
        assertTrue(P.getHits(300) == 4);
        assertTrue(P.getHits(301) == 3);

    }


}
