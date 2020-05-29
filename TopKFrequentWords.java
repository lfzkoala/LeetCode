/*
Top K Frequent Words

        Given a non-empty list of words, return the k most frequent elements.

        Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

        Example 1:
        Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
        Output: ["i", "love"]
        Explanation: "i" and "love" are the two most frequent words.
        Note that "i" comes before "love" due to a lower alphabetical order.
        Example 2:
        Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
        Output: ["the", "is", "sunny", "day"]
        Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
        with the number of occurrence being 4, 3, 2 and 1 respectively.
        Note:
        You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
        Input words contain only lowercase letters.
        Follow up:
        Try to solve it in O(n log k) time and O(n) extra space.

        方法：定义PriorityQueue按照出现次数排列即可 难点主要是如何定义PriorityQueue.

 */
class Solution {
    public List<String> topKFrequent(String[] words, int k) {

        List<String> result = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<>();

        //counting number of each word
        for(String word : words){
            map.put(word, map.getOrDefault(word,0)+1);
        }

        PriorityQueue<String> pq = new PriorityQueue<String>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2){
                if(map.get(s1) == map.get(s2)){
                    return s1.compareTo(s2);
                }

                return map.get(s2)-map.get(s1);
            }
        });

        pq.addAll(map.keySet());
        for(int i=0; i<k; i++){
            result.add(pq.poll());
        }

        return result;



    }
}
