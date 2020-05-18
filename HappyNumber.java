/*
Happy Number

        Write an algorithm to determine if a number n is "happy".
        A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
        Return True if n is a happy number, and False if not.
        Example:
        Input: 19
        Output: true
        Explanation:
        12 + 92 = 82
        82 + 22 = 68
        62 + 82 = 100
        12 + 02 + 02 = 1
        检验squareSum的步骤（while循环）比较简单，关键是需要使用怎样的数据结构才能判断出loops endlessly in a cycle, 这里需要使用HashSet，并且用add函数判断是否有重复 因为如果有set中已经存在这样的函数则会返回false 所以当不能add的时候则说明进入了循环 直接return false

*/

class Solution {
    public boolean isHappy(int n) {

        Set<Integer> set = new HashSet<Integer>();

        while(set.add(n)){

            int squareSum = 0;
            while(n > 0){
                int remain = n%10;
                squareSum += remain*remain;
                n = n/10;
            }

            if(squareSum == 1){
                return true;
            }else{
                n = squareSum;
            }
        }


        return false;
    }
}
