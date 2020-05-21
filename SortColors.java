/*
Sort Colors
        Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

        Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

        Note: You are not suppose to use the library's sort function for this problem.

        Example:

        Input: [2,0,2,1,1,0]
        Output: [0,0,1,1,2,2]
        Follow up:

        A rather straight forward solution is a two-pass algorithm using counting sort.
        First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
        Could you come up with a one-pass algorithm using only constant space?

        方法1 counting sort： O(n) time complexity, O(n) space complexity, 2-pass algorithm

 */

class Solution {
    public void sortColors(int[] nums) {

        //counting sort O(n) time

        int[] C = new int[3];

        for(int i=0; i<nums.length; i++){
            C[nums[i]]++;
        }

        for(int i=1; i<3; i++){
            C[i] = C[i]+C[i-1];
        }

        int[] B = new int[nums.length];

        for(int i=nums.length-1; i>=0; i--){
            B[C[nums[i]]-1] = nums[i];
            C[nums[i]]--;
        }

        for(int i=0; i<B.length; i++){
            nums[i] = B[i];
        }

    }
}

/*
方法2： 2-pass counting sort 因为k=3很小

 */

class Solution {
    public void sortColors(int[] nums) {

        //2-pass counting sort
        int count0 = 0, count1 = 0, count2 = 0;

        for(int i=0; i<nums.length; i++){
            if(nums[i] == 0) count0++;
            if(nums[i] == 1) count1++;
            if(nums[i] == 2) count2++;
        }

        for(int i=0; i<nums.length; i++){
            if(i < count0){
                nums[i] = 0;
            }else if(i < count0 + count1){
                nums[i] = 1;
            }else{
                nums[i] = 2;
            }
        }
    }
}

/*
方法3：quicksort idea one-pass
        quicksort 3-way partition
        +------+---------+-------------+-------+
        |  <p  |  =p     |  unseen .  |   > p  |
        +------+---------+------------+-------+
        ↑          ↑           ↑
        lt         i            gt
        lt: 1st elem == pivot
        i:  1st unseen elem
        gt: last unseen elem
        
 */

class Solution {
    public void sortColors(int[] nums) {
        // 1-pass
        int p1 = 0, p2 = nums.length - 1, index = 0;
        while (index <= p2) {
            if (nums[index] == 0) {
                //if current is 0, swap with p1 pointer, increment p1
                nums[index] = nums[p1];
                nums[p1] = 0;
                p1++;
            }
            if (nums[index] == 2) {
                //if current is 2, swap with p2 pointer, decrement p2
                nums[index] = nums[p2];
                nums[p2] = 2;
                p2--;
                //may have swapped an extra 0 from the end of array that requires extra processing, so decrement index to account for it
                index--;
            }
            //move to next index to make progress
            index++;
        }
    }
}
