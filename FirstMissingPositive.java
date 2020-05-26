/*
First Missing Positive
        Given an unsorted integer array, find the smallest missing positive integer.

        Example 1:

        Input: [1,2,0]
        Output: 3
        Example 2:

        Input: [3,4,-1,1]
        Output: 2
        Example 3:

        Input: [7,8,9,11,12]
        Output: 1
        Note:

        Your algorithm should run in O(n) time and uses constant extra space.

        方法：
        The basic idea is that we have an array with n cells (n is the length of the array). If an integer is missing it must be in the range [1..n]. This is the crucial observation we use to deduce the algorithm. This means that the range of possible answers is [1..n] if an integer is missing, and if an integer is not missing then the answer is n+1.

        I'll try my best to explain why.

        Let's picture the only two possibilities:

        there is no missing integer in the array
        there is a missing integer in the array.
        If there is no missing integer, this means that the array has all number from 1 to n. This must mean that the array is full. Why, because in the range [1..n] there are exactly n numbers, and if you place n numbers in an array of length n, the array is by definition full. (in this case solution is to return n+1 which is the first smallest integer).

        Once you understand the first case above understanding the second is easy. If there is a missing integer (or more than one), the missing integer(s), let's call it X, must be in the range 1..n. Why, because if the missing integer X is not in the range [1..n] that would imply that all integers [1..n] are in the array, which would mean that the array is full, leaving no space where to place X (since X is not in the range [1..n]).

        Then the algorithm becomes:

        1- Ignore all numbers <=0 and >n since they are outside the range of possible answers (which we proved was [1..n]). We do this by replacing them with the value n+1.
        2- For all other integers <n+1, mark their bucket (cell) to indicate the integer exists. (*see below)
        3- Find the first cell not marked, that is the first missing integer. If you did not find an unmarked cell, there was no missing integer, so return n+1.


 */


class Solution {
    public int firstMissingPositive(int[] nums) {

        int n = nums.length; //the number range should be 1...n;


        //if number <= 0 or num > n, convert them to n+1 uniformly.
        for(int i=0; i<n; i++){
            if(nums[i] <= 0 || nums[i] > n){
                nums[i] = n+1;
            }
        }

        //convert numbers from positive to negative, except for those not in the range (i.e., marked as n+1)
        for(int i=0; i<n; i++){

            int num = Math.abs(nums[i]);
            if(num > n){
                continue;
            }
            num--;
            if(nums[num] > 0){
                nums[num] = -1 * nums[num];
            }
        }

        //find the first remaining positive integer, then i+1 is the smallest integer missing.
        for(int i=0; i<n; i++){
            if(nums[i] > 0) return i+1;
        }

        return n+1;



    }
}
