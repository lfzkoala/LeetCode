/*
Spiral Matrix

        Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

        Example 1:

        Input:
        [
        [ 1, 2, 3 ],
        [ 4, 5, 6 ],
        [ 7, 8, 9 ]
        ]
        Output: [1,2,3,6,9,8,7,4,5]
        Example 2:

        Input:
        [
        [1, 2, 3, 4],
        [5, 6, 7, 8],
        [9,10,11,12]
        ]
        Output: [1,2,3,4,8,12,11,10,9,5,6,7]


        方法：
*/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;

        int rowMin=0, colMin=0, rowMax = matrix.length-1, colMax = matrix[0].length-1;

        while(rowMin<=rowMax && colMin<=colMax){

            for(int i=colMin; i<=colMax; i++) result.add(matrix[rowMin][i]);

            rowMin++;

            for(int i=rowMin; i<=rowMax; i++) result.add(matrix[i][colMax]);

            colMax--;

            if(rowMin > rowMax || colMin > colMax) break; //这一步最关键，注意这里要加一个跳出条件，因为在最后一次循环的时候只需要执行一半即可 否则会重复多加一个数，比如[1,2,3,4,8,12,11,10,9,5,6,7]会输出[1,2,3,4,8,12,11,10,9,5,6,7,6]


            for(int i=colMax; i>=colMin; i--) result.add(matrix[rowMax][i]);

            rowMax--;

            for(int i=rowMax; i>=rowMin; i--) result.add(matrix[i][colMin]);

            colMin++;
        }

        return result;
    }
}
