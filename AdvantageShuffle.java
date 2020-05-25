/*
Advantage Shuffle 田忌赛马:

        Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].

        Return any permutation of A that maximizes its advantage with respect to B.



        Example 1:

        Input: A = [2,7,11,15], B = [1,10,4,11]
        Output: [2,11,7,15]
        Example 2:

        Input: A = [12,24,8,32], B = [13,25,32,11]
        Output: [24,32,8,12]


        Note:

        1 <= A.length = B.length <= 10000
        0 <= A[i] <= 10^9
        0 <= B[i] <= 10^9

        方法：中国人小时候学过的一个故事，叫做田忌赛马，大家应该都知道的，用自己比对方强一点点的马去战胜对方的马，如果对方的马太强了，那么就用自己的最弱的马去参加比赛。这样做的好处就是我们用自己的弱鸡消耗掉对方的精锐，自己获胜的概率就最大。

        使用优先队列priorityqueue解决这个问题。同样是对A,B进行从小到大的排序，排序时需要保存B中的数字原来在数组中的哪个位置。这样我们对A进行一次遍历，每次出动自己的最弱的马，如果这个马能战胜B中最弱的马，那么就这么使用；如果战胜不了的话，那么就用这个最弱的马去和B中最强的马比赛，这样就干掉了对方的精锐。

        时间复杂度是O(nlogn)，空间复杂度是O(n).

 */


class Solution {
    public int[] advantageCount(int[] A, int[] B) {

        Arrays.sort(A);
        int[] result = new int[A.length];
        //max heap on the first element of a and b.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->b[0]-a[0]);

        for(int i=0; i<B.length; i++){
            pq.offer(new int[]{B[i], i});
        }

        int left = 0;
        int right = A.length-1;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int value = cur[0];
            int index = cur[1];
            if(A[right] > value){
                result[index] = A[right];
                right--;
            }else{
                result[index] = A[left];
                left++;
            }

        }

        return result;


    }
}
