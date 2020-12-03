import java.util.*;
public class Flatten2DArray {

    int[][] vector;
    int row, col;

    public static void main(String[] args){
        int[][] v = {{1,2}, {3}, {4}};
        Flatten2DArray P = new Flatten2DArray(v);
        System.out.println(P.next());
        System.out.println(P.next());
        System.out.println(P.next());
        System.out.println(P.hasNext());
        System.out.println(P.hasNext());
        System.out.println(P.next());
        System.out.println(P.hasNext());
    }

    public Flatten2DArray(int[][] v){
        vector = v;
        row = 0;
        col = 0;
    }

    public int next(){
        if(!hasNext()) return -1;
        int result = vector[row][col];
        col++;
        if(col == vector[row].length){
            row++;
            col = 0;
        }
        return result;
    }

    public boolean hasNext(){
        while(row < vector.length && vector[row].length == 0) row++;
        return row < vector.length;
    }

}
