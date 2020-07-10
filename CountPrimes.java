/*
Count Primes

        Count the number of prime numbers less than a non-negative number, n.

        Example:

        Input: 10
        Output: 4
        Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

        答案：We start off with a table of n numbers. Let's look at the first number, 2. We know all multiples of 2 must not be primes, so we mark them off as non-primes. Then we look at the next number, 3. Similarly, all multiples of 3 such as 3 × 2 = 6, 3 × 3 = 9, ... must not be primes, so we mark them off as well. Now we look at the next number, 4, which was already marked off. 4 is not a prime because it is divisible by 2, which means all multiples of 4 must also be divisible by 2 and were already marked off. So we can skip 4 immediately and go to the next number, 5. Now, all multiples of 5 such as 5 × 2 = 10, 5 × 3 = 15, 5 × 4 = 20, 5 × 5 = 25, ... can be marked off.

        based on the idea above, we can get the following code.


 */
class Solution {
    public int countPrimes(int n) {

        boolean[] notPrimes = new boolean[n];
        int count = 0;

        for(int i=2; i<n; i++){
            if(notPrimes[i] == false){
                count++;
                for(int j=2; i*j < n; j++){
                    notPrimes[i*j] = true;
                }
            }
        }

        return count;

    }
}

/*
答案2：Sieve of Eratosthenes time complexity O(nloglogn), space complexity O(n)

        In fact, we can mark off multiples of 5 starting at 5 × 5 = 25, because 5 × 2 = 10 was already marked off by multiple of 2, similarly 5 × 3 = 15 was already marked off by multiple of 3. Therefore, if the current number is p, we can always mark off multiples of p starting at p2, then in increments of p: p2 + p, p2 + 2p, ... Now what should be the terminating loop condition? It is easy to say that the terminating loop condition is p < n, which is certainly correct but not efficient. Yes, the terminating loop condition can be p < √n, as all non-primes ≥ √n must have already been marked off. When the loop terminates, all the numbers in the table that are non-marked are prime.

        The Sieve of Eratosthenes uses an extra O(n) memory and its runtime complexity is O(n log log n).

 */

class Solution {
    public int countPrimes(int n) {

        boolean[] isPrime = new boolean[n];

        for(int i=2; i<n; i++){
            isPrime[i] = true;
        }

        for(int i=2; i*i<n; i++){
            if(!isPrime[i]) continue;
            for(int j=i*i; j<n; j+=i){//p, p^2+p, p^2+2p
                isPrime[j] = false;
            }
        }

        int count=0;
        for(int i=2; i<n; i++){
            if(isPrime[i]) count++;
        }

        return count;


    }
}
