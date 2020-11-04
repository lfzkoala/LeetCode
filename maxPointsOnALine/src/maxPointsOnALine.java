import java.util.*;

public class maxPointsOnALine {
    public static void main(String[] args){
        int[][] points = {{1,1}, {3,2}, {5,3}, {4,1}, {2,3}, {1,4}};
        int result = maxPoints(points);
        System.out.println(result);


    }

    private static int maxPoints(int[][] points){
        if(points.length < 3) return points.length;
        int max = 0;
        HashMap<Long, Integer> map = new HashMap<>();

        for(int i=0; i<points.length; i++){
            int dup = 1;
            map.clear();

            for(int j=i+1; j<points.length; j++){
                int dx = points[j][0]-points[i][0];
                int dy = points[j][1]-points[i][1];

                if(dx == 0 && dy == 0){
                    dup++;
                }else {
                    int gcd = getGCD(dx, dy);
                    long slope = ((long) dx / gcd << 32) + (dy / gcd);
                    map.put(slope, map.getOrDefault(slope, 0) + 1);
                }
            }
            max = Math.max(max, dup);
            for(Map.Entry<Long, Integer> entry: map.entrySet()){
                max = Math.max(max, entry.getValue()+dup);
            }
        }
        return max;

    }

    private static int getGCD(int a, int b){
        return b==0? a:getGCD(b, a%b);
    }

}
