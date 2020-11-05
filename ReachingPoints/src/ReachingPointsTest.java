import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class ReachingPointsTest {

    @Test
    public void Test1(){
        ReachingPoints P = new ReachingPoints();
        int sx = 1, sy=1, tx =3, ty = 5;
        assertTrue(P.reachingPoints(sx, sy, tx, ty));
    }

    @Test
    public void Test2(){
        ReachingPoints P = new ReachingPoints();
        int sx = 1, sy=1, tx =2, ty = 2;
        assertTrue(P.reachingPoints(sx, sy, tx, ty));
    }
    @Test
    public void Test3(){
        ReachingPoints P = new ReachingPoints();
        int sx = 1, sy=1, tx =1, ty = 1;
        assertTrue(P.reachingPoints(sx, sy, tx, ty));
    }

}
