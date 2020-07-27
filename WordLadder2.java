/*
word ladder II
        Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

        Only one letter can be changed at a time
        Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
        Note:

        Return an empty list if there is no such transformation sequence.
        All words have the same length.
        All words contain only lowercase alphabetic characters.
        You may assume no duplicates in the word list.
        You may assume beginWord and endWord are non-empty and are not the same.
        Example 1:

        Input:
        beginWord = "hit",
        endWord = "cog",
        wordList = ["hot","dot","dog","lot","log","cog"]

        Output:
        [
        ["hit","hot","dot","dog","cog"],
        ["hit","hot","lot","log","cog"]
        ]
        Example 2:

        Input:
        beginWord = "hit"
        endWord = "cog"
        wordList = ["hot","dot","dog","lot","log"]

        Output: []

        Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

        BFS Method

 */
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(endWord)) return result;

        Map<String, List<String>> map = new HashMap<>();
        Set<String> startSet = new HashSet<>();
        startSet.add(beginWord);
        BFS(startSet, endWord, map, dict);


        List<String> list = new ArrayList<>();
        list.add(beginWord);
        DFS(result, list, beginWord, endWord, map);
        return result;
    }

    private void BFS(Set<String> startSet, String endWord, Map<String, List<String>> map, Set<String> dict){
        if(startSet.size() == 0) return;

        Set<String> temp = new HashSet<>();
        boolean finish = false;
        dict.removeAll(startSet);

        for(String s:startSet){
            char[] chars = s.toCharArray();
            for(int i=0; i<chars.length; i++){
                char cur = chars[i];
                for(char c = 'a'; c<='z'; c++){
                    //if(cur == c) continue;
                    chars[i] = c;
                    String word = new String(chars);
                    if(dict.contains(word)){
                        if(endWord.equals(word)){
                            finish = true;
                        }else{
                            temp.add(word);
                        }
                        if(map.get(s) == null) map.put(s, new ArrayList<>());
                        map.get(s).add(word);
                    }
                }
                chars[i] = cur;
            }

        }
        if(!finish) BFS(temp, endWord, map, dict);

    }

    private void DFS(List<List<String>> result, List<String> list, String word, String endWord, Map<String, List<String>> map){

        if(word.equals(endWord)){
            result.add(new ArrayList<>(list));
            return;
        }

        if(map.get(word) == null) return;
        for(String next: map.get(word)){
            list.add(next);
            DFS(result, list, next, endWord, map);
            list.remove(list.size()-1);
        }
    }



}
/*
    Bidirectional Method
    
 */
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(endWord)) return result;

        Map<String, List<String>> map = new HashMap<>();
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        startSet.add(beginWord);
        endSet.add(endWord);
        BFS(startSet, endSet, map, dict, false); //construct the map.


        List<String> list = new ArrayList<>();
        list.add(beginWord);
        DFS(result, list, beginWord, endWord, map);
        return result;
    }

    private void BFS(Set<String> startSet, Set<String> endSet, Map<String, List<String>> map, Set<String> dict, boolean reverse){
        if(startSet.size() == 0) return;

        if(startSet.size() > endSet.size()){
            BFS(endSet, startSet, map, dict, !reverse);
            return;
        }

        Set<String> temp = new HashSet<>();
        boolean finish = false;
        dict.removeAll(startSet); //这一步关键 不然map里什么都没有

        for(String s:startSet){
            char[] chars = s.toCharArray();
            for(int i=0; i<chars.length; i++){
                char cur = chars[i];
                for(char c = 'a'; c<='z'; c++){
                    //if(cur == c) continue;
                    chars[i] = c;
                    String word = new String(chars);
                    if(dict.contains(word)){
                        if(endSet.contains(word)){
                            finish = true;
                        }else{
                            temp.add(word);
                        }
                        String key = reverse? word:s;
                        String val = reverse? s:word;

                        if(map.get(key) == null) map.put(key, new ArrayList<>());
                        map.get(key).add(val);
                    }
                }
                chars[i] = cur;
            }

        }
        if(!finish) BFS(temp, endSet, map, dict, reverse);

    }

    private void DFS(List<List<String>> result, List<String> list, String word, String endWord, Map<String, List<String>> map){

        if(word.equals(endWord)){
            result.add(new ArrayList<>(list));
            return;
        }

        if(map.get(word) == null) return;

        for(String next: map.get(word)){
            list.add(next);
            DFS(result, list, next, endWord, map);
            list.remove(list.size()-1);
        }
    }



}
