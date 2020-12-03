/*
Given a maze in the form of an N*M grid, write a program to find a path from the start
(upper-left) to the end (lower-right)
- no obstacle
- has obstacle represented as 0
 */
import java.util.*;
public class MazeTopLeftToBottomRight {

    public static void main(String[] args){

        MazeTopLeftToBottomRight P = new MazeTopLeftToBottomRight();
        int[][] arr = {{1,2,3,4}, {5,0,7,8}, {9,10,11,12}};
        int[] result = new int[arr.length+arr[0].length-1];
        P.printPathsWithObstacle(arr, 0, 0,result, 0);
    }

    private void printPathsWithObstacle(int[][] arr, int row, int col, int result[], int pos){
        if(row >= arr.length || col >= arr[0].length) return;
        if(arr[row][col] == 0) return;
        if(row == arr.length-1 && col == arr[0].length-1){
            result[pos] = arr[row][col];
            System.out.println(Arrays.toString(result));
            return;
        }



        result[pos] = arr[row][col];
        printPathsWithObstacle(arr, row, col+1, result, pos+1);
        printPathsWithObstacle(arr, row+1, col, result, pos+1);

    }
    private void printPaths(int[][] arr, int row, int col, int result[], int pos){
        if(row >= arr.length || col >= arr[0].length) return;
        if(row == arr.length-1 && col == arr[0].length-1){
            result[pos] = arr[row][col];
            System.out.println(Arrays.toString(result));
            return;
        }

        result[pos] = arr[row][col];
        printPaths(arr, row+1, col, result, pos+1);
        printPaths(arr, row, col+1, result, pos+1);
        /*
        if there is no restriction on directions, i.e., we can go 4 directions, then add
        printPaths(arr, row-1, col, result, pos+1);
        printPaths(arr, row, col-1, result, pos+1);
        */


    }




}
