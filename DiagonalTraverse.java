/*
Diagonal Traverse
        Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

        Example:
        Input:
        [
        [ 1, 2, 3 ],
        [ 4, 5, 6 ],
        [ 7, 8, 9 ]
        ]

        Output:  [1,2,4,7,5,3,6,8,9]

        Explanation:



        Note:
        The total number of elements of the given matrix will not exceed 10,000.
        答案：只要摸清楚坐标变换的套路即可，见下图


 */

class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {

        if(matrix.length == 0) return new int[0];

        int m = matrix.length, n = matrix[0].length;

        int[][] dirs = {{-1,1}, {1,-1}};
        int[] result = new int[m*n];

        int row = 0, col = 0, d = 0;

        for(int i=0; i<m*n; i++){
            result[i] = matrix[row][col];
            row += dirs[d][0];
            col += dirs[d][1];

            if(row >=m){
                //example row = 2, col = 1, d=1 -> row = 3, col = 0 -> row = 2, col = 2, d = 0
                row = m-1;
                col += 2;
                d = 1-d;
            }

            if(col >= n){
                //example, row = 0, col = 2, d = 0 -> row = -1, col = 3 -> col = 2, row = 1, d = 1
                col = n-1;
                row += 2;
                d = 1-d;
            }

            if(row < 0){
                row = 0;
                d = 1-d;
            }

            if(col < 0){
                col = 0;
                d = 1-d;
            }
        }

        return result;

    }
}
