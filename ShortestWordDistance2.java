/*
Shortest Word Distance II
        Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters.

        Example:
        Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

        Input: word1 = “coding”, word2 = “practice”
        Output: 3
        Input: word1 = "makes", word2 = "coding"
        Output: 1
        Note:
        You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

        方法1：
        通过的答案 用HashMap存储每个String以及对应的list之间的关系 list存储这个String出现在words数组中的index 计算最小值时取出对应的两个list像上题中一样计算


 */
class WordDistance {

    //initiate a HashMap
    Map<String, List<Integer>> map;


    public WordDistance(String[] words) {

        map = new HashMap<String, List<Integer>>();

        for(int i=0; i<words.length; i++){

            String cur = words[i];
            if(map.containsKey(cur)){
                map.get(cur).add(i);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(cur, list);
            }
        }
    }

    public int shortest(String word1, String word2) {

        int p1 = 0, p2 = 0;
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int minDistance = Integer.MAX_VALUE;

        while(p1 < list1.size() && p2 < list2.size()){
            minDistance = Math.min(minDistance, Math.abs(list1.get(p1)-list2.get(p2)));
            if(list1.get(p1) < list2.get(p2)){
                p1++;
            }else{
                p2++;
            }
        }

        return minDistance;

    }
}
