/*

Keep两个list， 一个是buy offers, 一个是sell offers， 例如
buy[10, 20, 15, 9]   sell[11, 15,9, 20]
每当有新的request进来，buy或者sell，找到能match的最好价格。
就是如果是buy 找到最低的sell价格，例如request是 [buy, 12], 匹配到[sell ,9] ， 如果找不到match，就暂且存起来。
sell的话 找超过sell价格的最高的buy offer匹配 ， 匹配不上，就是没有高于sell 价格的buy, 那就存起来
实时更新两个list，并且返回True/False表示能不能match

我用了两个heap， MaxHeap来存Buy Offer， MinHeap来存Sell Offer，
每次request进来，如果是 buy 就看 MinHeap顶端，小于buy的价格就可以匹配，然后移除MinHeap顶端；
若不是， 则将buy offer 加入MaxHeap

Followup: Follow up: 实现会expire的 order，比如说buyer 的一个order valid for 10 min，
然后自动expire 可以直接存queue<Pair<Price, Time>>， 每次要check的时候，先看时间，如果expiration了，就pop出去。

 */

import java.util.*;

public class BuyAndSellCoinbaseWithTime {

    class Node{
        int price;
        int timeStamp;

        public Node(int price, int timeStamp){
            this.price = price;
            this.timeStamp = timeStamp;
        }
    }

    /*
    class BuyNodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node a, Node b){
            return b.price-a.price;
        }
    }

    class SellNodeComparator implements Comparator<Node>{
        @Override
        public int compare(Node a, Node b){
            return a.price-b.price;
        }
    }

     */

    public static List<Integer> matchedBuyOffers;
    public static List<Integer> matchedSellOffers;
    public PriorityQueue<Node> buyPQ;
    public PriorityQueue<Node> sellPQ;
    public static int validTime;

    public static void main(String[] args){
        BuyAndSellCoinbaseWithTime P = new BuyAndSellCoinbaseWithTime();
        boolean result = P.order_book(new String[]{"buy", "12", "0"});
        System.out.println(result);
        result = P.order_book(new String[]{"sell", "9", "11"});
        System.out.println(result);
        result = P.order_book(new String[]{"buy", "10", "13"});
        System.out.println(result);
        System.out.println(matchedBuyOffers);
        System.out.println(matchedSellOffers);
    }


    public BuyAndSellCoinbaseWithTime(){
        matchedBuyOffers = new ArrayList<>();
        matchedSellOffers = new ArrayList<>();
        buyPQ = new PriorityQueue<Node>(new Comparator<Node>(){
            public int compare(Node a, Node b){
                return b.price-a.price;
            }
        });
        sellPQ = new PriorityQueue<Node>(new Comparator<Node>(){
            public int compare(Node a, Node b){
                return a.price-b.price;
            }
        });
        validTime = 5;
    }

    private boolean order_book(String[] request) {//request is of the form [buy/sell, price, timeStamp]
        if (request == null || request.length == 0) return false;
        boolean match = false;
        //requests[i] is of the form ["buy"/"sell", "price"]
        int price = Integer.parseInt(request[1]);
        int timeStamp = Integer.parseInt(request[2]);
        if (request[0].equals("buy")) {
            Node buyNode = new Node(price, timeStamp);

            while(!sellPQ.isEmpty() && sellPQ.peek().timeStamp + validTime < timeStamp){
                sellPQ.poll();
            }

            if (!sellPQ.isEmpty() && sellPQ.peek().price <= buyNode.price) {
                Node sellNode = sellPQ.poll();
                matchedSellOffers.add(sellNode.price);
                match = true;
            }
            //buyPQ.offer(buyNode);
            if (match) {
                matchedBuyOffers.add(buyNode.price);
                return true;
            } else {//not match, add buyNode into buyPQ
                buyPQ.offer(buyNode);
                return false;
            }
        } else {//sell request
            Node sellNode = new Node(price, timeStamp);

            while(!buyPQ.isEmpty() && buyPQ.peek().timeStamp+validTime<timeStamp){
                buyPQ.poll();
            }

            if (!buyPQ.isEmpty() && buyPQ.peek().price >= sellNode.price) {
                Node buyNode = buyPQ.poll();
                matchedBuyOffers.add(buyNode.price);
                match = true;
            }

            if (match) {
                matchedSellOffers.add(sellNode.price);
                return true;
            } else {
                sellPQ.offer(sellNode);
                return false;
            }
        }
    }

}
