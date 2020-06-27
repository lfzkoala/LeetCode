/*
Random Pick With Weight

        Given an array w of positive integers, where w[i] describes the weight of index i(0-indexed), write a function pickIndex which randomly picks an index in proportion to its weight.
        For example, given an input list of values w = [2, 8], when we pick up a number out of it, the chance is that 8 times out of 10 we should pick the number 1 as the answer since it's the second element of the array (w[1] = 8).

        Example 1:
        Input
        ["Solution","pickIndex"]
        [[[1]],[]]
        Output
        [null,0]

        Explanation
        Solution solution = new Solution([1]);
        solution.pickIndex(); // return 0. Since there is only one single element on the array the only option is to return the first element.

        Example 2:
        Input
        ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
        [[[1,3]],[],[],[],[],[]]
        Output
        [null,1,1,1,1,0]

        Explanation
        Solution solution = new Solution([1, 3]);
        solution.pickIndex(); // return 1. It's returning the second element (index = 1) that has probability of 3/4.
        solution.pickIndex(); // return 1
        solution.pickIndex(); // return 1
        solution.pickIndex(); // return 1
        solution.pickIndex(); // return 0. It's returning the first element (index = 0) that has probability of 1/4.

        Since this is a randomization problem, multiple answers are allowed so the following outputs can be considered correct :
        [null,1,1,1,1,0]
        [null,1,1,1,1,1]
        [null,1,1,1,0,0]
        [null,1,1,1,0,1]
        [null,1,0,1,0,0]
        ......
        and so on.


        Constraints:
        1 <= w.length <= 10000
        1 <= w[i] <= 10^5
        pickIndex will be called at most 10000 times.

        答案：

        Use accumulated freq array to get idx.
        w[] = {2,5,3,4} => wsum[] = {2,7,10,14}
        then get random val random.nextInt(14)+1, idx is in range [1,14]

        idx in [1,2] return 0
        idx in [3,7] return 1
        idx in [8,10] return 2
        idx in [11,14] return 3
        then become LeetCode 35. Search Insert Position
        Time: O(n) to init, O(logn) for one pick
        Space: O(n)


 */

class Solution {

    Random random;
    int[] wSums;

    public Solution(int[] w) {
        this.random = new Random();
        for(int i=1; i<w.length; ++i)
            w[i] += w[i-1];
        this.wSums = w;
    }

    public int pickIndex() {
        int len = wSums.length;
        int idx = random.nextInt(wSums[len-1]) + 1;
        int left = 0, right = len - 1;
        // search position
        while(left <= right){
            int mid = left + (right-left)/2;
            if(wSums[mid] == idx)
                return mid;
            else if(wSums[mid] < idx)
                left = mid + 1;
            else
                right = mid-1;
        }
        return left;
    }
}
