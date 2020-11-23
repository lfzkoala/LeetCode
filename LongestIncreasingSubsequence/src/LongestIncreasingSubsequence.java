import java.util.*;
public class LongestIncreasingSubsequence {

    public static void main(String[] args){
        int[] nums = {10,9,2,5,3,7,101,18};
        LongestIncreasingSubsequence P  =new LongestIncreasingSubsequence();
        int result = P.lengthOfLIS(nums);
        System.out.println(result);
    }

    public int lengthOfLIS(int[] nums){
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            int index = searchLeftBound(list, nums[i]);
            if(index == list.size()){
                list.add(nums[i]);
            }else{
                list.set(index, nums[i]);
            }

        }

        return list.size();
    }

    private int searchLeftBound(List<Integer> list, int num){
        int left = 0, right = list.size();
        while(left < right){
            int mid = left+(right-left)/2;
            if(list.get(mid) < num){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return left;
    }


}
