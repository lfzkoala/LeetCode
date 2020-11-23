import java.util.*;
public class DecodeWays {
    public static void main(String[] args){

        String s = "226";
        DecodeWays P = new DecodeWays();
        int result = P.numDecodings(s);
        System.out.println(result);
    }

    public int numDecodings(String s){
        if(s == null || s.length() == 0){
            return 0;
        }

        if(s.charAt(0) == '0') return 0;

        //dp[i] means the number of ways to decode s(0...i)
        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<dp.length; i++){
            int oneDigit = Integer.parseInt(s.substring(i-1, i));
            int twoDigit = Integer.parseInt(s.substring(i-2, i));
            if(oneDigit != 0) dp[i] = dp[i-1];
            if(twoDigit >= 10 && twoDigit <= 26) dp[i] += dp[i-2];
        }
        return dp[s.length()];

    }


}
