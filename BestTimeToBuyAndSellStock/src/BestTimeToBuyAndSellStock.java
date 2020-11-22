import java.util.*;

public class BestTimeToBuyAndSellStock {
    public static void main(String[] args){
        BestTimeToBuyAndSellStock P1 = new BestTimeToBuyAndSellStock();
        int[] prices = {1,3,2,8,4,9};
        int result1 = P1.maxProfit1(prices);
        int result2 = P1.maxProfit2(prices);
        int result3 = P1.maxProfit3(prices);
        int result4 = P1.maxProfit4(prices, 2);
        int result5 = P1.maxProfit5(prices);
        int result6 = P1.maxProfits6(prices, 2);

        System.out.println(result1+" "+result2+" "+result3+" "+result4+" "+result5+" "+result6);

    }

    //only one transaction
    public int maxProfit1(int[] prices){
        //only one transaction
        int buy = Integer.MAX_VALUE;
        int sell = 0;
        for(int i=0; i<prices.length; i++){
            buy = Math.min(buy, prices[i]);
            sell = Math.max(sell, prices[i]-buy);
        }

        return sell;
    }

    //as many transaction as possible
    public int maxProfit2(int[] prices){
        int profit = 0;
        for(int i=1; i<prices.length; i++){
            if(prices[i] > prices[i-1]) profit += prices[i]-prices[i-1];
        }
        return profit;
    }

    //at most 2 transactions
    public int maxProfit3(int[] prices){
        int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
        int sell1 = 0, sell2 = 0;
        for(int i=0; i<prices.length; i++){
            buy1 = Math.min(buy1, prices[i]);
            sell1 = Math.max(sell1, prices[i]-buy1);
            buy2 = Math.min(buy2, prices[i]-sell1);
            sell2 = Math.max(sell2, prices[i]-buy2);
        }

        return sell2;

    }

    //at most k transactions
    public int maxProfit4(int[] prices, int k){
        if(k >= prices.length/2){
            int profit = 0;
            for(int i=1; i<prices.length; i++){
                if(prices[i]-prices[i-1] > 0) profit += prices[i]-prices[i-1];
            }
            return profit;
        }

        int[] buy = new int[k+1];
        int[] sell = new int[k+1];

        Arrays.fill(buy, Integer.MAX_VALUE);
        for(int i=0; i<prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.min(buy[j], prices[i] - sell[j - 1]);
                sell[j] = Math.max(sell[j], prices[i] - buy[j]);
            }
        }
        return sell[k];
    }

    //with cool down, as many Txs as possible.
    //buy[i] the max profit after buy at day i
    //sell[i] the max profit after sell at day i
    //buy[i] = Math.max(buy[i-1], sell[i-2]-prices[i])
    //sell[i] = Math.max(sell[i-1], prices[i]+buy[i-1])
    public int maxProfit5(int[] prices){

        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];

        buy[0] = -prices[0];

        for(int i=1; i<prices.length; i++){
            if(i == 1){
                buy[i] = Math.max(buy[i-1], -prices[i]);
            }else{
                buy[i] = Math.max(buy[i-1], sell[i-2]-prices[i]);
            }

            sell[i] = Math.max(sell[i-1], prices[i]+buy[i-1]);
        }

        return sell[sell.length-1];
    }

    //sell and buy with transaction fee
    public int maxProfits6(int[] prices, int fee){
        int buy = Integer.MAX_VALUE;
        int sell = 0;

        for(int i=0; i<prices.length; i++){
            buy = Math.min(buy, prices[i]-sell);
            sell = Math.max(sell, prices[i]-buy-fee);
        }

        return sell;


    }




}
