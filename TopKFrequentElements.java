/*
Top K Frequent Elements
        Given a non-empty array of integers, return the k most frequent elements.

        Example 1:

        Input: nums = [1,1,1,2,2,3], k = 2
        Output: [1,2]
        Example 2:

        Input: nums = [1], k = 1
        Output: [1]
        Note:

        You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
        Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

        方法：bucket sort O(n) time, O(n) space complexity

 */

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        List<Integer>[] bucket = new List[nums.length+1];

        for(int key : map.keySet()){
            int freqNum = map.get(key);
            if(bucket[freqNum] == null) bucket[freqNum] = new ArrayList<>();
            bucket[freqNum].add(key);
        }

        List<Integer> result = new ArrayList<>();

        for(int i=bucket.length-1; i>=0 && result.size() < k; i--){
            if(bucket[i] != null) result.addAll(bucket[i]);
        }

        return result;

    }
}
