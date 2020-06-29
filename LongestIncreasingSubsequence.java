/*
Longest Increasing Subsequence

        Given an unsorted array of integers, find the length of longest increasing subsequence.
        Example:
        Input: [10,9,2,5,3,7,101,18]
        Output: 4
        Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
        Note:
        There may be more than one LIS combination, it is only necessary for you to return the length.
        Your algorithm should run in O(n2) complexity.
        Follow up: Could you improve it to O(n log n) time complexity?

        答案：方法1 dynamic programming

        time complexity O(n^2)

 */


class Solution {
    public int lengthOfLIS(int[] nums) {

        int[] result = new int[nums.length];

        for(int i=0; i<result.length; i++){
            result[i] = 1;
        }

        for(int i=0; i<nums.length; i++){
            for(int j=0; j<i; j++){
                if(nums[i] > nums[j]){
                    result[i] = Math.max(result[i], result[j]+1);
                }
            }
        }

        int max = 0;
        for(int i=0; i<result.length; i++){
            if(result[i] > max) max = result[i];
        }

        return max;

    }
}

/*
方法2： Improve to O(nlogn) time complexity using binary search



        不是很容易想到

        list is an list storing the smallest tail value of all increasing subsequences with length i+1 in list[i].
        For example, say we have nums = [4,5,6,3], then all the available increasing subsequences are:
        len = 1   :      [4], [5], [6], [3]   => list[0] = 3
        len = 2   :      [4, 5], [5, 6]       => list[1] = 5
        len = 3   :      [4, 5, 6]            => list[2] = 6

        We can easily prove that tails is a increasing array. Therefore it is possible to do a binary search in tails array to find the one needs update.
        Each time we only do one of the two:
        (1) if x is larger than all tail values, append it, increase the size by 1
        (2) if list[i-1] < x <= list[i], update list[i]

        Doing so will maintain the tails invariant. The the final answer is just the list size.


 */
class Solution {
    public int lengthOfLIS(int[] nums) {

        List<Integer> list = new ArrayList<>();

        for(int i=0; i<nums.length; i++){

            int idx = binarySearch(list, nums[i]);
            if(idx == list.size()){
                list.add(nums[i]);
            }else{
                list.set(idx, nums[i]);
            }


        }

        return list.size();
    }

    private int binarySearch(List<Integer> list, int num){
        int left = 0;
        int right = list.size();

        while(left < right){//因为有left=right=0的情况 必须skip这个循环 不能写成left<=right
            int mid = left+(right-left)/2;
            if(list.get(mid) < num){
                left = mid+1;
            }else if(list.get(mid) > num){
                right = mid;
            }else{
                return mid;
            }
        }

        return left;


    }


}
