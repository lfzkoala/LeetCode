/*
Shortest Word Distance
        Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

        Example:
        Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

        Input: word1 = “coding”, word2 = “practice”
        Output: 3
        Input: word1 = "makes", word2 = "coding"
        Output: 1
        Note:
        You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.


        方法1：
        brute force 复杂度O(n^2)

 */

class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {

        int minDistance = Integer.MAX_VALUE;
        for(int i=0; i<words.length; i++){
            for(int j=0; j<words.length; j++){
                if(words[i].equals(word1) && words[j].equals(word2)){
                    if(Math.abs(i-j) < minDistance){
                        minDistance = Math.abs(i-j);
                    }
                }
            }
        }

        return minDistance;

    }
}

/*
方法2：
        complexity O(n)


 */
class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {

        int p1 = -1, p2 = -1;
        int minDistance = Integer.MAX_VALUE;

        for(int i=0; i<words.length; i++){

            if(words[i].equals(word1)){
                p1 = i;
            }

            if(words[i].equals(word2)){
                p2 = i;
            }

            if(p1 != -1 && p2 != -1){
                minDistance = Math.min(minDistance, Math.abs(p1-p2));
            }
        }

        return minDistance;
    }
}

/*
方法3：
        using two sorted list

 */

class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;

        for(int i=0; i<words.length; i++){

            if(words[i].equals(word1)){
                list1.add(i);
            }

            if(words[i].equals(word2)){
                list2.add(i);
            }
        }

        int p1=0, p2=0;

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
