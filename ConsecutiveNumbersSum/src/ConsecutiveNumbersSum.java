import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ConsecutiveNumbersSum {
    public static void main(String[] args){
        Result result = JUnitCore.runClasses(ConsecutiveNumbersSumTest.class);
        if(result.wasSuccessful()) System.out.println("All test cases are passed");
        for(Failure failure: result.getFailures()) {
            System.out.println(failure.toString());
        }
    }

    public static int consecutiveNumbersSum(int N){
        int count = 0;
        //N = k+1+k+2+k+3+...+k+i; = ik+1+2+3+...i = ik+sum(i);
        int sum = 0;
        for(int i=1; sum<N; i++) {
            sum += i;
            if ((N - sum) % i == 0) count++;
        }
        return count;
    }
}
