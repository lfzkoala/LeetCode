public class RangeSumQuery2DImmutable {
    public static void main(String[] args){
        int[][] matrix = {{3,0,1,4,2}, {5,6,3,2,1}, {1,2,0,1,5}, {4,1,0,1,7}, {1,0,3,0,5}};
        RangeSumQuery2DImmutable P = new RangeSumQuery2DImmutable();
        P.NumMatrix(matrix);
        int result = P.sumRegion(2,1,4,3);
        System.out.println(result);
    }

    int[][] dp;
    public void NumMatrix(int[][] matrix){
        dp = new int[matrix.length+1][matrix[0].length+1];
        //dp[i][j] records the sum of the square from matrix[0][0] to matrix[i][j]
        for(int i=1; i<dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2){
        int minRow = Math.min(row1, row2);
        int maxRow = Math.max(row1, row2);
        int minCol = Math.min(col1, col2);
        int maxCol = Math.max(col1, col2);

        return dp[maxRow+1][maxCol+1]-dp[minRow][maxCol+1]-dp[maxRow+1][minCol]+dp[minRow][minCol];

    }


}
