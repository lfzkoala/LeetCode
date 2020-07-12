/*
Burst Balloons
        Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

        Find the maximum coins you can collect by bursting the balloons wisely.

        Note:

        You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
        0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
        Example:

        Input: [3,1,5,8]
        Output: 167
        Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
        coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

        答案：The subproblems are overlapped. So we can use divide and conquer + cache.

        Balloons 0, 1, ..., n - 1
        What is the max value if we burst all of them [0, n - 1]?
        Let's first consider bursting [start, end]
        Imagine we burst index i at the end
        [start, ... i - 1, (i), i + 1 ... end]
        Before the end, we already bursted [start, i - 1] and [i + 1, end]
        Before the end, boundaries start - 1, i, end + 1 are always there
        This helps us calculate coins without worrying about details inside [start, i - 1] and [i + 1, end]
        So the range can be divided into
        start - 1, maxCoin(start, i - 1), i, maxCoins(i + 1, end), end + 1
        
 */




class Solution {
    public int maxCoins(int[] nums) {

        if(nums.length == 0) return 0;
        int[][] dp = new int[nums.length][nums.length];

        return helper(nums, 0, nums.length-1, dp);
    }

    private int helper(int[] nums, int start, int end, int[][] dp){

        if(start > end) return 0; //if start > end, no coins earned

        if(dp[start][end] != 0) return dp[start][end]; //

        int max = nums[start];

        for(int i=start; i<=end; i++){
            int val = helper(nums, start, i-1, dp) + helper(nums, i+1, end, dp)+getNum(nums, start-1)*getNum(nums, i)*getNum(nums, end+1);
            max = Math.max(max, val);
        }

        dp[start][end] = max;
        return dp[start][end];
    }

    private int getNum(int[] nums, int i){
        if(i == -1 || i == nums.length) return 1;
        return nums[i];
    }




}
