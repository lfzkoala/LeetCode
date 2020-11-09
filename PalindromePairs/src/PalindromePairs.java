import java.util.*;
public class PalindromePairs {

    public static void main(String[] args){
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        PalindromePairs P = new PalindromePairs();
        List<List<Integer>> result = P.palindromePairs(words);
        System.out.println(result);
    }

    class TrieNode{
        TrieNode[] next;
        int index;
        List<Integer> list;
        public TrieNode(){
            next = new TrieNode[26];
            index = -1;
            list = new ArrayList<>();
        }
    }

    public List<List<Integer>> palindromePairs(String[] words){
        List<List<Integer>> result = new ArrayList<>();
        TrieNode root = new TrieNode();

        //build tree. Note that each word is added in reverse order.
        for(int i=0; i<words.length; i++) addWords(words[i], i, root);

        //add valid pairs into result
        for(int i=0; i<words.length; i++) addResults(words, i, root, result);
        return result;
    }

    private void addWords(String word, int index, TrieNode root){
        for(int i=word.length()-1; i>=0; i--){
            if(root.next[word.charAt(i)-'a'] == null) root.next[word.charAt(i)-'a'] = new TrieNode();
            if(isPalindrome(word, 0, i)) root.list.add(index);
            root = root.next[word.charAt(i)-'a'];
        }
        root.list.add(index);
        root.index = index;
    }

    private void addResults(String[] words, int index, TrieNode root, List<List<Integer>> result){
        for(int i=0; i<words[index].length(); i++){
            if(root.index >= 0 && root.index != index && isPalindrome(words[index], i, words[index].length()-1)){
                result.add(Arrays.asList(index, root.index));
            }
            root = root.next[words[index].charAt(i)-'a'];
            if(root == null) return;
        }

        for(int j:root.list){
            if(index == j) continue;
            result.add(Arrays.asList(index, j));
        }

    }

    private boolean isPalindrome(String word, int i, int j){
        while(i < j){
            if(word.charAt(i) != word.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }



}
