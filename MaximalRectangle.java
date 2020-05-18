
/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
        Example:
        Input:
        [
        ["1","0","1","0","0"],
        ["1","0","1","1","1"],
        ["1","1","1","1","1"],
        ["1","0","0","1","0"]
        ]
        Output: 6
        方法1：Maximal Rectangle Histogram: Suppose there is a 2D matrix like

        1 1 0 1 0 1

        0 1 0 0 1 1

        1 1 1 1 0 1

        1 1 1 1 0 1

        First initiate the height array as 1 1 0 1 0 1, which is just a copy of the first row. Then we can easily calculate the max area is 2.

        Then update the array. We scan the second row, when the matrix[1][i] is 0, set the height[i] to 0; else height[i] += 1, which means the height has increased by 1. So the height array again becomes 0 2 0 0 1 2. The max area now is also 2.

        Apply the same method until we scan the whole matrix. the last height arrays is 2 4 2 2 0 4, so the max area has been found as 2 * 4 = 8.

        Then reason we scan the whole matrix is that the maximum value may appear in any row of the height.
        https://www.youtube.com/watch?v=g8bSdXCG-lA

*/


class Solution {
    public int MaximalRectangle(char[][] matrix) {

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int result = 0;

        int[] height = new int[matrix[0].length];

        for(int i=0; i<matrix[0].length; i++){
            if(matrix[0][i]=='1') height[i] = 1;
        }

        result = comRectangle(height); //compute the maximal rectangle until this height

        for(int i=1; i<matrix.length; i++){
            resetHeight(matrix, height, i);
            result = Math.max(result, comRectangle(height));
        }

        return result;
    }


    private void resetHeight(char[][] matrix, int[] height, int row){
        for(int i=0; i<height.length; i++){
            if(matrix[row][i] == '1'){
                height[i] += 1;
            }else{
                height[i] = 0;
            }
        }
    }

    private int comRectangle(int[] height){
        if(height == null || height.length == 0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int maxArea = 0;

        for(int i=0; i<=height.length; i++){
            int h = (i==height.length? 0:height[i]);
            if(stack.isEmpty()||h>=height[stack.peek()]){
                stack.push(i);
            }else{
                int top = stack.pop();
                maxArea = Math.max(maxArea, height[top]*(stack.isEmpty()? i:i-1-stack.peek()));
                i--;
            }

        }

        return maxArea;

    }
}

/*
方法2：简化版方法1并且逻辑更加合理一些
*/
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] height = new int[matrix[0].length];
        int result = 0;

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(matrix[i][j] == '0'){
                    height[j] = 0;
                    continue;
                }

                height[j]++;

                for(int curr = j-1, pre = height[j]; curr>=0; curr--){
                    if(height[curr] == 0) break;
                    pre = Math.min(pre, height[curr]);
                    result = Math.max(result, pre*(j-curr+1));
                }

                result = Math.max(result, height[j]);
            }
        }
        return result;

    }
}

/*
方法3：Dynamic Programming 方法最自然最好记

        The DP solution proceeds row by row, starting from the first row. Let the maximal rectangle area at row i and column j be computed by [right(i,j) - left(i,j)+1]*height(i,j).
        All the 3 variables left, right, and height can be determined by the information from previous row, and also information from the current row. So it can be regarded as a DP solution. The transition equations are:
        left(i,j) = max(left(i-1,j), cur_left), cur_left can be determined from the current row
        right(i,j) = min(right(i-1,j), cur_right), cur_right can be determined from the current row
        height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1';
        height(i,j) = 0, if matrix[i][j]=='0'
*/

class Solution {

    public int maximalRectangle(char[][] matrix) {

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;

        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];

        Arrays.fill(right, n-1);

        int result = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){

                if(matrix[i][j] == '1'){
                    height[j]++;
                }else{
                    height[j]=0;
                }
            }

            int leftBoundary = 0;
            for(int j=0; j<n; j++){
                if(matrix[i][j]=='1'){
                    left[j] = Math.max(left[j], leftBoundary);
                }else{
                    left[j] = 0;
                    leftBoundary = j+1;
                }
            }

            int rightBoundary = n-1;
            for(int j=n-1; j>=0; j--){
                if(matrix[i][j] == '1'){
                    right[j] = Math.min(right[j], rightBoundary);
                }else{
                    right[j] = n-1;
                    rightBoundary = j-1;
                }
            }

            for(int j=0; j<n; j++){
                result = Math.max(result, height[j]*(right[j]-left[j]+1));
            }

        }
        return result;
    }
}
