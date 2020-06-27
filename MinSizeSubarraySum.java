/*
Minimum Size Subarry Sum

        Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
        Example:
        Input: s = 7, nums = [2,3,1,2,4,3]
        Output: 2
        Explanation: the subarray [4,3] has the minimal length under the problem constraint.
        Follow up:
        If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
        答案：滑动窗口


 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        //Sliding window

        int sum=0, from = 0, result = Integer.MAX_VALUE;

        for(int i=0; i<nums.length; i++){
            sum += nums[i];
            while(sum >= s){
                result = Math.min(result, i-from+1);
                sum -= nums[from];
                from++;
            }
        }

        return (result == Integer.MAX_VALUE)? 0:result;

    }
}
