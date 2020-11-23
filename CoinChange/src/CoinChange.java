import java.util.*;
public class CoinChange {

    public static void main(String[] args){
        int[] coins = {1};
        int amount = 2;

        CoinChange P = new CoinChange();
        int result = P.coinChange(coins, amount);
        System.out.println(result);

    }

    public int coinChange(int[] coins, int amount){
        //dp = new dp[amount+1] dp[i] = amount+1 for i>=1;
        //write a function to compute the fewest number of coins
        // dp[i] = Math.min(dp[i], 1+dp[i-coin]) if(i >= coin)

        int[] dp = new int[amount+1];
        for(int i=1; i<dp.length; i++) dp[i] = amount+1;

        for(int i=0; i<dp.length; i++){
            for(int coin: coins){
                if(i-coin >= 0){
                    dp[i] = Math.min(dp[i], 1+dp[i-coin]);
                }
            }
        }

        return dp[amount] == amount+1? -1:dp[amount];


    }


}
