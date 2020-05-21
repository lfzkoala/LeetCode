/*
Max Area of Island
        Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

        Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

        Example 1:

        [[0,0,1,0,0,0,0,1,0,0,0,0,0],
        [0,0,0,0,0,0,0,1,1,1,0,0,0],
        [0,1,1,0,1,0,0,0,0,0,0,0,0],
        [0,1,0,0,1,1,0,0,1,0,1,0,0],
        [0,1,0,0,1,1,0,0,1,1,1,0,0],
        [0,0,0,0,0,0,0,0,0,0,1,0,0],
        [0,0,0,0,0,0,0,1,1,1,0,0,0],
        [0,0,0,0,0,0,0,1,1,0,0,0,0]]
        Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
        Example 2:

        [[0,0,0,0,0,0,0,0]]
        Given the above grid, return 0.
        Note: The length of each dimension in the given grid does not exceed 50.

        方法1：改进Number of Islands, DFS recursive algorithm

 */

class Solution {
    public int maxAreaOfIsland(int[][] grid) {

        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int row = grid.length;
        int col = grid[0].length;
        int max = 0;

        for(int i=0; i<row; i++){
            for(int j = 0; j<col; j++){
                if(grid[i][j] == 1){

                    int area = gridSearch(grid, row, col, i, j);

                    if(area > max){
                        max = area;
                    }
                }
            }
        }

        return max;
    }

    private int gridSearch(int[][] grid, int row, int col, int x, int y){

        if(x < 0 || y < 0 || x > row-1 || y > col-1 || grid[x][y] == 0) return 0;

        grid[x][y] = 0;

        return 1 + gridSearch(grid, row, col, x-1, y) + gridSearch(grid, row, col, x, y-1)+ gridSearch(grid, row, col, x+1, y) + gridSearch(grid, row, col, x, y+1);

    }
}

/*
方法2： BFS Iterative using Queue

 */

class Solution {

    int[][] dirs = new int[][]{{-1,0}, {0,1}, {1,0}, {0,-1}};

    public int maxAreaOfIsland(int[][] grid) {

        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int row = grid.length;
        int col = grid[0].length;
        int max = 0;


        for(int i=0; i<row; i++){
            for(int j = 0; j<col; j++){
                if(grid[i][j] == 1){
                    max = Math.max(max, gridSearch(grid, i, j));
                }
            }
        }

        return max;
    }

    private int gridSearch(int[][] grid, int x, int y){

        if(grid[x][y] == 0) return 0;
        grid[x][y] = 0;


        int row = grid.length;
        int col = grid[0].length;

        int area = 1;

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{x,y});

        while(!queue.isEmpty()){
            int[] position = queue.poll();
            for(int[] dir : dirs){
                int xx = dir[0]+position[0];
                int yy = dir[1]+position[1];

                if(xx < 0 || yy < 0 || xx > row-1 || yy > col-1 || grid[xx][yy] == 0) continue;

                grid[xx][yy] = 0;
                area++;
                queue.offer(new int[]{xx, yy});

            }
        }

        return area;


    }


}
