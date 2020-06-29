/*
Rotate Image

        You are given an n x n 2D matrix representing an image.
        Rotate the image by 90 degrees (clockwise).
        Note:
        You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
        Example 1:
        Given input matrix =
        [
        [1,2,3],
        [4,5,6],
        [7,8,9]
        ],

        rotate the input matrix in-place such that it becomes:
        [
        [7,4,1],
        [8,5,2],
        [9,6,3]
        ]

        Example 2:
        Given input matrix =
        [
        [ 5, 1, 9,11],
        [ 2, 4, 8,10],
        [13, 3, 6, 7],
        [15,14,12,16]
        ],

        rotate the input matrix in-place such that it becomes:
        [
        [15,13, 2, 5],
        [14, 3, 4, 1],
        [12, 6, 8, 9],
        [16, 7,10,11]
        ]
        答案：应该只有n*n的矩阵可以这么做

        先对称交换矩阵的每一行 之后再斜角度交换每一个元素 对角线上的元素不变

 */


class Solution {
    public void rotate(int[][] matrix) {

        if(matrix.length == 0) return;

        //switch rows symmetrically
        for(int i = 0, j=matrix.length-1; i<j; i++, j--){
            int[] temp = matrix[i];
            matrix[i] = matrix[j];
            matrix[j] = temp;
        }

        //switch each element orthogonially.
        for(int i=0; i<matrix.length; i++){
            for(int j=i+1; j<matrix[0].length; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}

/*
逆时针旋转的方法

 */

/* Counter-clockwise Rotate */
public void antiRotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for(int first=0, last=cols-1; first<last; first++,last--) {
        for(int i=0; i<matrix.length; i++) {
        int tmp = matrix[i][first];
        matrix[i][first] = matrix[i][last];
        matrix[i][last] = tmp;
        }
        }
        for(int i = 0; i < rows; i++){
        for(int j = i+1; j < cols; j++){
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = tmp;
        }
        }
        }
