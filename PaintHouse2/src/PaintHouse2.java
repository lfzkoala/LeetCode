import java.util.*;

public class PaintHouse2 {
    public static void main(String[] args){
        int[][] costs = {{1,5,3}, {2,9,4}};
        PaintHouse2 P = new PaintHouse2();
        int result = P.minCost(costs);
        System.out.println(result);
    }

    public int minCost(int[][] costs){
        if(costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int n = costs.length;
        int k = costs[0].length;

        int min1 = -1;
        int min2 = -1;

        for(int i=0; i<costs.length; i++){
            int lastMin1 = min1, lastMin2 = min2;
            int curMin1Index = -1;
            int curMin2Index = -1;

            for(int j=0; j<k; j++){
                if(j != lastMin1){
                    costs[i][j] += (lastMin1 < 0)? 0:costs[i-1][lastMin1];
                }else{
                    costs[i][j] += (lastMin2 < 0)? 0:costs[i-1][lastMin2];
                }

                if(curMin1Index < 0 || costs[i][j] < costs[i][curMin1Index]){
                    curMin2Index = curMin1Index;
                    curMin1Index = j;
                }else if(curMin2Index < 0 || costs[i][j] < costs[i][curMin2Index]){
                    curMin2Index = j;
                }
            }

            min1 = curMin1Index;
            min2 = curMin2Index;
        }

        return costs[n-1][min1];


    }


}
