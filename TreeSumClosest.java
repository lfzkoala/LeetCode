/*
3Sum Closest
        Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

        Example 1:
        Input: nums = [-1,2,1,-4], target = 1
        Output: 2
        Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).


        Constraints:
        3 <= nums.length <= 10^3
        -10^3 <= nums[i] <= 10^3
        -10^4 <= target <= 10^4
        答案：O(n^2)算法即可 3SUM问题lower bound is O(n^2)


 */
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int result = nums[0]+nums[1]+nums[nums.length-1];
        Arrays.sort(nums);

        for(int i=0; i<nums.length-2; i++){
            int start = i+1;
            int end = nums.length-1;

            while(start < end){
                int sum = nums[i]+nums[start]+nums[end];

                if(sum > target){
                    end--;
                }else{
                    start++;
                }

                if(Math.abs(sum-target) < Math.abs(result-target)){
                    result = sum;
                }
            }
        }

        return result;

    }
}
