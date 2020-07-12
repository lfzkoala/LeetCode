/*
Longest Valid Parentheses
        Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

        Example 1:

        Input: "(()"
        Output: 2
        Explanation: The longest valid parentheses substring is "()"
        Example 2:

        Input: ")()())"
        Output: 4
        Explanation: The longest valid parentheses substring is "()()"






        方法1：stack solution
        The idea is simple, we only update the result (max) when we find a "pair".
        If we find a pair. We throw this pair away and see how big the gap is between current and previous invalid.
        EX: "( )( )"
        stack: -1, 0,
        when we get to index 1 ")", the peek is "(" so we pop it out and see what's before "(".
        In this example it's -1. So the gap is "current_index" - (-1) = 2.

        The idea only update the result (max) when we find a "pair" and push -1 to stack first covered all edge cases.

 */

class Solution {
    public int longestValidParentheses(String s) {

        Stack<Integer> stack = new Stack<>();

        stack.push(-1);
        int result = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '('){
                stack.pop();
                result = Math.max(result, i-stack.peek());
            }else{
                stack.push(i); //注意这里是push i而不是push s.charAt(i)
            }
        }

        return result;


    }
}
/*
方法2：Dynamic Programming
        The idea is go through the string and use DP to store the longest valid parentheses at that point.
        take example "()(())"
        i : [0,1,2,3,4,5]
        s : [( ,) ,( ,( ,) ,) ]
        DP:[0,2,0,0,2,6]

        1, We count all the ‘(‘.
        2, If we find a ‘)’ and ‘(‘ counter is not 0, we have at least a valid result size of 2. “()"
        3, Check the the one before (i - 1). If DP[i - 1] is not 0 means we have something like this “(())” . This will have DP “0024"
        4, We might have something before "(())”. Take "()(())” example, Check the i = 1 because this might be a consecutive valid string.

 */

class Solution {
    public int longestValidParentheses(String s) {

        int[] dp = new int[s.length()];

        int result = 0;

        int count = 0;

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                count++;
            }else if(count>0){
                dp[i] = dp[i-1]+2;
                if(i-dp[i]>=0) dp[i] += dp[i-dp[i]]; //this step is important, it covers cases like '(())' and "()()"
                result = Math.max(result, dp[i]);
                count--;
            }

        }
        return result;



    }
}
