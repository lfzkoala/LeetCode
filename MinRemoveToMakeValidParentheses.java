/*
Minimum Remove to Make Valid Parentheses

        Given a string s of '(' , ')' and lowercase English characters.

        Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

        Formally, a parentheses string is valid if and only if:

        It is the empty string, contains only lowercase characters, or
        It can be written as AB (A concatenated with B), where A and B are valid strings, or
        It can be written as (A), where A is a valid string.


        Example 1:

        Input: s = "lee(t(c)o)de)"
        Output: "lee(t(c)o)de"
        Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
        Example 2:

        Input: s = "a)b(c)d"
        Output: "ab(c)d"
        Example 3:

        Input: s = "))(("
        Output: ""
        Explanation: An empty string is also valid.
        Example 4:

        Input: s = "(a(b(c)d)"
        Output: "a(b(c)d)"


        Constraints:

        1 <= s.length <= 10^5
        s[i] is one of  '(' , ')' and lowercase English letters.


        方法1：
        To make the string valid with minimum removals, we need to get rid of all parentheses that do not have a matching pair.

        Push char index into the stack when we see '('.

        Pop from the stack when we see ')'.

        If the stack is empty, then we have ')' without the pair, and it needs to be removed.
        In the end, the stack will contain indexes of '(' without the pair, if any. We need to remove all of them too.

        Update: check out the new approach 2 that collects indexes of all mismatched parentheses, and removes them right-to-left.

        Approach 1: Stack and Placeholder
        We mark removed parentheses with '*', and erase all of them in the end.

        还有一个方法2 stack with tracking, 但是不如这个好理解

 */

class Solution {
    public String minRemoveToMakeValid(String s) {

        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<sb.length(); i++){
            if(sb.charAt(i) == '(') stack.add(i);
            if(sb.charAt(i) == ')'){
                if(!stack.isEmpty()) stack.pop();
                else sb.setCharAt(i,'*');
            }
        }

        while(!stack.isEmpty()) sb.setCharAt(stack.pop(), '*');

        return sb.toString().replaceAll("\\*", "");

    }
}
