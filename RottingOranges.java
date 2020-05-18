/*
Rotting Oranges
        In a given grid, each cell can have one of three values:
        the value 0 representing an empty cell;
        the value 1 representing a fresh orange;
        the value 2 representing a rotten orange.
        Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
        Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

        Example 1:

        Input: [[2,1,1],[1,1,0],[0,1,1]]
        Output: 4

        Example 2:
        Input: [[2,1,1],[0,1,1],[1,0,1]]
        Output: -1
        Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

        Example 3:
        Input: [[0,2]]
        Output: 0
        Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.


        Note:
        1 <= grid.length <= 10
        1 <= grid[0].length <= 10
        grid[i][j] is only 0, 1, or 2.

        经典BFS 每一次BFS判断是否在持续rotting 如果是就count++ 最后得到的count数量就是最少需要rotting的次数 注意BFS中需要避免重复计算所以先把要rotting的orange从1变成3 最后再统一变成2
*/

class Solution {
    public int orangesRotting(int[][] grid) {
        int count = 0;

        while(isRotting(grid)) count++;
        if(freshOrange(grid)) return -1;

        return count;
    }

    private boolean freshOrange(int[][] grid){

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 1) return true;
            }
        }

        return false;
    }

    private boolean isRotting(int[][] grid){

        boolean rot = false;

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j]==2){

                    if(i>0 && grid[i-1][j] == 1){
                        grid[i-1][j] = 3;
                        rot = true;
                    }

                    if(i<grid.length-1 && grid[i+1][j] == 1){
                        grid[i+1][j] = 3;
                        rot = true;
                    }

                    if(j>0 && grid[i][j-1] == 1){
                        grid[i][j-1]=3;
                        rot = true;
                    }

                    if(j<grid[0].length-1 && grid[i][j+1] == 1){
                        grid[i][j+1] = 3;
                        rot = true;
                    }

                }
            }
        }

        if(rot){
            for(int l=0; l<grid.length; l++){
                for(int k=0; k<grid[0].length; k++){
                    if(grid[l][k] == 3) grid[l][k] = 2;
                }
            }
        }

        return rot;
    }

}
