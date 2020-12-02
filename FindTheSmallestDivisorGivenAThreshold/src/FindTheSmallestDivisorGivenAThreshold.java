import java.util.*;
public class FindTheSmallestDivisorGivenAThreshold {

    public static void main(String[] args){
        FindTheSmallestDivisorGivenAThreshold P = new FindTheSmallestDivisorGivenAThreshold();
        int[] nums = {2,3,5,7,11};
        System.out.println(P.smallestDivisor(nums, 11));
    }

    public int smallestDivisor(int[] nums, int threshold){
        int left = 1, right = (int)1e6;
        while(left < right){
            int divisor = left+(right-left)/2;
            int sum = 0;
            for(int num: nums) sum += (num+divisor-1)/divisor;
            if(sum > threshold){
                left = divisor+1;
            }else{
                right = divisor;
            }
        }

        return left;


    }



}
