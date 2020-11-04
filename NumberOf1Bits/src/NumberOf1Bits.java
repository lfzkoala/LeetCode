
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.*;
public class NumberOf1Bits {


    public static void main(String[] args){
        Result result = JUnitCore.runClasses(NumberOf1BitsTest.class);
        if(result.wasSuccessful()){
            System.out.println("All test cases passed");
        }

        for(Failure failure: result.getFailures()){
            System.out.println(failure.toString());
        }

    }

    public static int hammingWeight(int n){
        int count = 0;

        while(n != 0){
            n &= (n-1);
            count++;
        }
        return count;
    }




}
