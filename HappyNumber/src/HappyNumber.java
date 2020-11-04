import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.*;

public class HappyNumber {

    public static void main(String[] args){
        Result result = JUnitCore.runClasses(HappyNumberTest.class);
        if(result.wasSuccessful()) System.out.println("All tests are passed");
        for(Failure failure: result.getFailures()){
            System.out.println(failure.toString());
        }
    }

    public static boolean isHappy(int n){
        Set<Integer> set = new HashSet<>();

        while(set.add(n)){
            int sum = 0;
            while(n != 0) {
                int remain = n % 10;
                sum += remain * remain;
                n /= 10;
            }
            if(sum == 1) return true;
            n = sum;
        }
        return false;
    }



}
