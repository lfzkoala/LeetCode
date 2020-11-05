import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import java.util.*;
import org.junit.runner.notification.Failure;

public class DesignHitCounter {

    public static void main(String[] args){
        Result result = JUnitCore.runClasses(DesignHitCounterTest.class);
        if(result.wasSuccessful()) System.out.println("All test cases are passed");
        for(Failure failure: result.getFailures()) System.out.println(failure.toString());
    }

    public int[] times;
    public int[] hits;

    public void HitCounter(){
        times = new int[300];
        hits = new int[300];
    }

    public void hit(int timestamp){
        int index = timestamp%300;
        if(times[index] != timestamp){
          times[index] = timestamp;
          hits[index] = 1;
        }else{
            hits[index]++;
        }
    }

    public int getHits(int timestamp){
        int sum = 0;
        for(int i=0; i<times.length; i++){
            if(timestamp-times[i] < 300) sum += hits[i];
        }

        return sum;

    }


}
