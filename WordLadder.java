/*
Word Ladder
        Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

        Only one letter can be changed at a time.
        Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
        Note:

        Return 0 if there is no such transformation sequence.
        All words have the same length.
        All words contain only lowercase alphabetic characters.
        You may assume no duplicates in the word list.
        You may assume beginWord and endWord are non-empty and are not the same.
        Example 1:

        Input:
        beginWord = "hit",
        endWord = "cog",
        wordList = ["hot","dot","dog","lot","log","cog"]

        Output: 5

        Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
        return its length 5.
        Example 2:

        Input:
        beginWord = "hit"
        endWord = "cog"
        wordList = ["hot","dot","dog","lot","log"]

        Output: 0

        Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

        方法1：
        思路1：思路主要是BFS 使用一个HashSet来存字典中的每一个word 用一个队列来进行BFS 首先将beginWord放入队列中 对queue中每个word中的character进行广度优先搜索 每次搜索result++ 如果查到某个word等于endWord则输出result+1 如果这个word不等于endWord 则判断word是否在dict中 如果不在则直接进入下一次循环 不加入队列中 如果word在dict中 则在dict中remove这个word并且将这个word加入队列queue中 因为下次需要对它进行广度优先搜索

        Time Complexity: O(n*26^l) (BFS) -> O(n*26^l/2) (Bidirectional BFS), l = len(word), n=|wordList|
        Space Complexity: O(n)


 */



class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>();

        for(String word:wordList){
            set.add(word);
        }

        Queue<String> queue = new LinkedList<>();

        int result = 0;

        if(!set.contains(endWord)) return 0;

        queue.offer(beginWord);

        while(!queue.isEmpty()){
            int size = queue.size();
            result++;
            for(int i=0; i<size; i++){
                String word = queue.poll();
                char[] chars = word.toCharArray();
                for(int j=0; j<chars.length; j++){
                    char cur = chars[j];
                    for(char c='a'; c<='z'; c++){
                        if(c == cur) continue;
                        chars[j] = c;
                        String temp = new String(chars);
                        if(temp.equals(endWord)) return result+1;
                        if(!set.contains(temp)) continue;
                        queue.offer(temp);
                        set.remove(temp);
                    }
                    chars[j] = cur;
                }
            }
        }

        return 0;

    }
}
/*
方法2：
        优化 使用Bidirectional BFS


*/

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> dict = new HashSet<>();

        for(String word:wordList) dict.add(word);

        if(!dict.contains(endWord)) return 0;

        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        set1.add(beginWord);
        set2.add(endWord);

        int result = 0;

        while(!set1.isEmpty() && !set2.isEmpty()){
            result++;
            //swap set1 and set2 if set1.size()>set2.size()
            if(set1.size()>set2.size()){
                Set<String> temp = set1;
                set1 = set2;
                set2 = temp;
            }

            Set<String> set = new HashSet<>();
            for(String word: set1){
                char[] chars = word.toCharArray();
                for(int i=0; i<chars.length; i++){
                    char cur = chars[i];
                    for(char c = 'a'; c<='z'; c++){
                        if(cur ==c) continue;
                        chars[i] = c;
                        String temp = new String(chars);
                        if(set2.contains(temp)) return result+1;
                        if(!dict.contains(temp)) continue;
                        set.add(temp);
                        dict.remove(temp);
                    }
                    chars[i] = cur;
                }
            }
            set1 = set;
        }

        return 0;





    }
}
