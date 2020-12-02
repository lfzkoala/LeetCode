import java.util.*;
public class SlidingWindowMinAndMax {

    public static void main(String[] args){
        SlidingWindowMinAndMax P = new SlidingWindowMinAndMax();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k=3;
        int[] result1 = P.slidingWindowMin(nums, k);
        for(int i=0; i<result1.length; i++){
            System.out.print(result1[i]+" ");
        }
        System.out.println("\n");
        int[] result2 = P.slidingWindowMax(nums, k);
        for(int i=0; i<result2.length; i++){
            System.out.print(result2[i]+" ");
        }


    }

    public int[] slidingWindowMin(int[] nums, int k){
        int len = nums.length;
        int[] min_left = new int[len];
        int[] min_right = new int[len];

        min_left[0] = nums[0];
        min_right[len-1] = nums[len-1];

        for(int i=1; i<len; i++){
            min_left[i] = (i%k ==0)? nums[i]:Math.min(nums[i], min_left[i-1]);
            int j = len-i-1;
            min_right[j] = (j%k == 0)? nums[j]:Math.min(nums[j], min_right[j+1]);
        }

        int[] result = new int[len-k+1];
        int i=0, j=0;
        while(i+k-1 <= len-1){
            result[i] = Math.min(min_right[i], min_left[i+k-1]);
            i++;
            j++;
        }

        return result;
    }

    public int[] slidingWindowMax(int[] nums, int k){
        int len = nums.length;
        int[] max_left = new int[len];
        int[] max_right = new int[len];

        max_left[0] = nums[0];
        max_right[len-1] = nums[len-1];

        for(int i=1; i<len; i++){
            max_left[i] = (i%k ==0)? nums[i]:Math.max(nums[i], max_left[i-1]);
            int j = len-i-1;
            max_right[j] = (j%k == 0)? nums[j]:Math.max(nums[j], max_right[j+1]);
        }

        int[] result = new int[len-k+1];
        int i=0, j=0;
        while(i+k-1 <= len-1){
            result[i] = Math.max(max_right[i], max_left[i+k-1]);
            i++;
            j++;
        }

        return result;
    }



}
