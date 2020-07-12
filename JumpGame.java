/*
Jump Game


        Given an array of non-negative integers, you are initially positioned at the first index of the array.

        Each element in the array represents your maximum jump length at that position.

        Determine if you are able to reach the last index.



        Example 1:

        Input: nums = [2,3,1,1,4]
        Output: true
        Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
        Example 2:

        Input: nums = [3,2,1,0,4]
        Output: false
        Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.


        Constraints:

        1 <= nums.length <= 3 * 10^4
        0 <= nums[i][j] <= 10^5

        方法1：Greedy. Looking from the start and selecting the locally optimum in the hope of reaching global optimum

        Example

        If we have a Greedy Approach here then we will take the path 1+99+1 as we select local optimum from the beginning
        But if we take DP Approach then we start from back and find the cost of reaching end from that specific node. So when we reach the first node we will have two options
        99+1 path
        5+1 path
        Now we simply have to decide between (1+(99+1)) and (20+(5+1)) path


 */

class Solution {
    public boolean canJump(int[] nums) {

        int max = 0;
        for(int i=0; i<nums.length; i++){
            if(i > max) return false;
            max = Math.max(nums[i]+i, max);
        }

        return true;

    }
}

/*
另一种写法：
        Idea is to work backwards from the last index. Keep track of the smallest index that can "jump" to the last index. Check whether the current index can jump to this smallest index.

 */

class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int last = n-1;

        for(int i=n-2; i>=0; i--){
            if(nums[i]+i >= last) last = i;
        }

        return last <= 0;

    }
}
