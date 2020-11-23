import java.util.*;
public class OddEvenJump {

    public static void main(String[] args){
        int[] A = {10,13,12,14,15};
        OddEvenJump P = new OddEvenJump();
        System.out.println(P.oddEvenJump(A));
    }

    public int oddEvenJump(int[] A){
        TreeMap<Integer, Integer> map = new TreeMap<>();

        boolean[] higher = new boolean[A.length];
        boolean[] lower = new boolean[A.length];
        int n = A.length;

        map.put(A[n-1], n-1);
        higher[n-1] = true;
        lower[n-1] = true;

        int result = 1;
        for(int i=n-2; i>=0; i--){
            Map.Entry high = map.ceilingEntry(A[i]);
            Map.Entry low = map.floorEntry(A[i]);

            if(high != null){
                higher[i] = lower[(int)high.getValue()];
            }

            if(low != null){
                lower[i] = higher[(int)low.getValue()];
            }

            if(higher[i]) result++;
            map.put(A[i], i);
        }

        return result;



    }


}
