
import java.util.*;
public class RangeSumQueryMutable {
    //using binary indexed tree to achieve

    public static void main(String[] args){
        RangeSumQueryMutable P = new RangeSumQueryMutable();
        int[] nums = {1,3,5};
        P.NumArray(nums);
        int sum = P.sumRange(0, 2);
        System.out.println(sum);
        P.update(1, 4);
        sum = P.sumRange(0, 2);
        System.out.println(sum);
    }

    int[] BIT;
    int[] record;

    public void NumArray(int[] nums){//O(n) time
        record = new int[nums.length];
        BIT = new int[nums.length+1];

        for(int i=0; i<nums.length; i++){
            update(i, nums[i]);
        }


    }

    public void update(int i, int val){//O(logn) time
        int diff = val-record[i];
        record[i] = val;
        for(int k=i+1; k<BIT.length; k+=k&(-k)) BIT[k] += diff;
    }

    public int sumRange(int i, int j){//O(1) time
        return getSum(j)-getSum(i-1);
    }

    private int getSum(int i){//O(logn) time
        int sum = 0;
        for(int k=i+1; k>0; k-=k&(-k)) sum += BIT[k];

        return sum;
    }




}
