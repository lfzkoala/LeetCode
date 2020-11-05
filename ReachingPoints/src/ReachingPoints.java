import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class ReachingPoints {
    public static void main(String[] args){
        Result result = JUnitCore.runClasses(ReachingPointsTest.class);
        if(result.wasSuccessful()) System.out.println("All test cases are passed");
        for(Failure failure: result.getFailures()){
            System.out.println(failure.toString());
        }
    }

    public static boolean reachingPoints(int sx, int sy, int tx, int ty){
        while(sx<tx && sy<ty){
            if(ty>tx){
                ty %= tx;
            }else{
                tx %= ty;
            }
        }

        if(sx == sy && tx <= ty && (ty-tx)%sx==0) return true;
        if(tx == ty && sx <= sy && (sy-sx)%tx == 0) return true;
        return false;
    }



}
