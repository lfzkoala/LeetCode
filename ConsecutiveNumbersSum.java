/*
Consecutive Numbers Sum
        Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?

        Example 1:

        Input: 5
        Output: 2
        Explanation: 5 = 5 = 2 + 3
        Example 2:

        Input: 9
        Output: 3
        Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
        Example 3:

        Input: 15
        Output: 4
        Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
        Note: 1 <= N <= 10 ^ 9.

        答案：let N = k + (k+1) + (k+2) + (k+3) + ... + (k+i-1) = i*k + (1+2+3+... + i-1)
        let sum(i) = (1+2+3+...+i-1), then we have
        N = sum(i) + i*k ==>i*k = N - sum(i)
        Which means: for each i, we can calculate N-sum(i). If N-sum(i) can be divided by i, there is an answer with length i.
        Because, let k = (N - sum(i)) / i, we can add an integer k to each of (0,1, 2, 3, 4, ...., i-1) to become (k, k+1, k+2, k+3,.... k + i-1)
        that is: sum(i) + i * k = N


 */

class Solution {
    public int consecutiveNumbersSum(int N) {
        int count = 0;
        int sum = 0;

        for(int i=1; sum<N; i++){
            sum += i;
            if((N-sum)%i == 0){
                count++;
            }
        }

        return count;
    }
}
