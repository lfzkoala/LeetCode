/*
Word Search
        Given a 2D board and a word, find if the word exists in the grid.

        The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

        Example:

        board =
        [
        ['A','B','C','E'],
        ['S','F','C','S'],
        ['A','D','E','E']
        ]

        Given word = "ABCCED", return true.
        Given word = "SEE", return true.
        Given word = "ABCB", return false.

        方法：类似于Number of Islands, DFS recursive
        
 */

class Solution {
    public boolean exist(char[][] board, String word) {

        char[] chars = word.toCharArray();

        int row = board.length;
        int col = board[0].length;

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(wordSearch(i, j, board, chars, 0)) return true;
            }
        }

        return false;
    }

    private boolean wordSearch(int i, int j, char[][] board, char[] chars, int index){

        if(index == chars.length) return true;

        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;

        if(board[i][j] != chars[index]) return false;

        char c = board[i][j];
        board[i][j] = '#';

        boolean exist = wordSearch(i-1, j, board, chars, index+1) ||
                wordSearch(i+1, j, board, chars, index+1)||
                wordSearch(i, j-1, board, chars, index+1)||
                wordSearch(i, j+1, board, chars, index+1);

        board[i][j] = c; //必须回复原来的字符 因为还要进行下一次搜索 这里和number of islands不太一样 number of islands里我们只考虑字符为1的部分
        return exist;
    }
}
