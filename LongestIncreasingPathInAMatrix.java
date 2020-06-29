/*
Longest Increasing Path in a Matrix
        Given an integer matrix, find the length of the longest increasing path.

        From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

        Example 1:

        Input: nums =
        [
        [9,9,4],
        [6,6,8],
        [2,1,1]
        ]
        Output: 4
        Explanation: The longest increasing path is [1, 2, 6, 9].
        Example 2:

        Input: nums =
        [
        [3,4,5],
        [3,2,6],
        [2,2,1]
        ]
        Output: 4
        Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

        答案：

        To get max length of increasing sequences:

        Do DFS from every cell
        Compare every 4 direction and skip cells that are out of boundary or smaller
        Get matrix max from every cell's max
        Use matrix[x][y] <= matrix[i][j] so we don't need a visited[m][n] array
        The key is to cache the distance because it's highly possible to revisit a cell

        Traverse all points in matrix, use every point as starting point to do dfs traversal. DFS function returns max increasing after comparing four max return distance from four directions.

        cache[i][j] represents longest increasing path starts from point matrix[i][j]

 */

class Solution {

    private int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};

    public int longestIncreasingPath(int[][] matrix) {

        //dfs method

        if(matrix.length == 0) return 0;

        int max = 1;

        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int len = dfs(matrix, i, j, m, n, cache);
                max = Math.max(max, len);
            }
        }

        return max;
    }

    private int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache){
        if(cache[i][j] != 0) return cache[i][j];
        int max = 1;
        for(int[] dir:dirs){
            int row = i+dir[0], col = j+dir[1];
            if(row <0 || row >= m || col < 0 || col >= n || matrix[row][col] <= matrix[i][j]) continue;
            int len = 1+dfs(matrix, row, col, m, n, cache);
            max = Math.max(max, len);
        }

        cache[i][j] = max;
        return max;
    }




}
