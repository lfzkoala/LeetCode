/*
Concatenated Words

        Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
        A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
        Example:
        Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

        Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

        Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
        "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
        "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

        Note:
        The number of elements of the given array will not exceed 10,000
        The length sum of elements in the given array will not exceed 600,000.
        All the input string will only include lower case letters.
        The returned elements order does not matter.
        答案1：

 */
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        List<String> result = new ArrayList<>();

        HashSet<String> wordSet = new HashSet<>(Arrays.asList(words));

        for(String word:words){
            if(DFS(word, wordSet)){
                result.add(word);
            }
        }

        return result;
    }

    private boolean DFS(String word, HashSet<String> wordSet){

        for(int i=1; i<word.length(); i++){
            if(wordSet.contains(word.substring(0,i))){
                String suffix = word.substring(i);
                if(wordSet.contains(suffix) || DFS(suffix, wordSet)){
                    wordSet.add(suffix);
                    return true;
                }
            }
        }

        return false;
    }
}

/*
方法2：using Trie

 */

class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (current.children[c-'a'] == null) current.children[c-'a'] = new TrieNode();
                current = current.children[c-'a'];
            }
            if (current != root) current.isWord = true;
        }
        List<String> ans = new ArrayList<>();
        for (String word : words) if (dfs(root, word.toCharArray(), 0, word.length() - 1)) ans.add(word);
        return ans;
    }
    boolean dfs(TrieNode root, char[] cstr, int left, int right) {
        TrieNode current = root;
        for (int i = left; i <= right; i++) {
            char c = cstr[i];
            if (current.children[c-'a'] == null) return false;
            current = current.children[c-'a'];
            if (current.isWord) { // prefix
                if (isWord(root, cstr, i + 1, right) || dfs(root, cstr, i + 1, right))
                    return true;
            }
        }
        return false;
    }
    boolean isWord(TrieNode root, char[] cstr, int left, int right) {
        TrieNode current = root;
        for (int i = left; i <= right; i++) {
            char c = cstr[i];
            if (current.children[c-'a'] == null) return false;
            current = current.children[c-'a'];
        }
        return current.isWord;
    }
}
