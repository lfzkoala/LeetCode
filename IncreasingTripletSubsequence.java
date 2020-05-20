/*
Increasing Triplet Subsequence
        Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
        Formally the function should:
        Return true if there exists i, j, k
        such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
        Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
        Example 1:
        Input: [1,2,3,4,5]
        Output: true

        Example 2:
        Input: [5,4,3,2,1]
        Output: false
        方法：
        
 */
class Solution {
    public boolean increasingTriplet(int[] nums) {

        //[2,1,3,4,1]
        /*

        small = Integer.MAX_VALUE
        largest = Integer.MAX_VALUE

        2 < small small = 2, largest = Integer.MAX_VALUE;
        1 < small small = 1, largest = Integer.MAX_VALUE;
        3 > small, 3 < largest, small = 1, largest = 3
        4 > small, 4 > largest, return true
        */

        int smallest = Integer.MAX_VALUE, largest = Integer.MAX_VALUE;

        for(int i=0; i<nums.length; i++){
            if(nums[i] <= smallest){
                smallest = nums[i];
            }else if(nums[i] <= largest){
                largest = nums[i];
            }else{
                return true;
            }
        }
        return false;
    }
}
