/*
Longest Consecutive Sequence

        Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
        Your algorithm should run in O(n) complexity.
        Example:
        Input: [100, 4, 200, 1, 3, 2]
        Output: 4
        Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
        答案：hashmap中key为数组中数值 对应的value为该数值作为目前一个sequence的boundary是这个sequence的最大长度 这个过程中不断更新最大长度max最后我们就能够得到答案

 */

class Solution {
    public int longestConsecutive(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;

        for(int num:nums){
            if(map.containsKey(num)) continue;

            int left = map.getOrDefault(num-1, 0);
            int right = map.getOrDefault(num+1, 0);
            int sum = left+right+1;
            max = Math.max(max, sum);

            if(left>0) map.put(num-left, sum);
            if(right>0) map.put(num+right, sum); //update the length

            map.put(num, sum);


        }

        return max;




    }
}

