/*
Word Search II
        Given a 2D board and a list of words from the dictionary, find all words in the board.

        Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



        Example:

        Input:
        board = [
        ['o','a','a','n'],
        ['e','t','a','e'],
        ['i','h','k','r'],
        ['i','f','l','v']
        ]
        words = ["oath","pea","eat","rain"]

        Output: ["eat","oath"]


        Note:

        All inputs are consist of lowercase letters a-z.
        The values of words are distinct.

        方法：using Trie, build a trie for the list of words. 然后对于board像number of islands和word search一样进行DFS recursive search

 */

class Solution {

    class TrieNode{
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {

        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        int row = board.length;
        int col = board[0].length;

        for(int i=0; i<row; i++){
            for(int j = 0; j<col; j++){
                DFS(board, root, result, i, j);
            }
        }

        return result;
    }

    private void DFS(char[][] board, TrieNode cur, List<String> result, int i, int j){


        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;

        char c = board[i][j];
        if(c == '#' || cur.next[c-'a'] == null) return;
        cur = cur.next[c-'a'];

        if(cur.word != null){
            result.add(cur.word);//find one
            cur.word = null; //de-duplicate
        }

        board[i][j] = '#';
        DFS(board, cur, result, i-1, j);
        DFS(board, cur, result, i+1, j);
        DFS(board, cur, result, i, j-1);
        DFS(board, cur, result, i, j+1);
        board[i][j] = c;
    }

    private TrieNode buildTrie(String[] words){

        TrieNode root = new TrieNode();
        for(String w : words){
            char[] chars = w.toCharArray();
            TrieNode p = root;
            for(char c : chars){
                int i = c-'a';
                if(p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w;
        }

        return root;

    }
}
