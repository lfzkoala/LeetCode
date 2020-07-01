/*
Basic Calculator III


        Implement a basic calculator to evaluate a simple expression string.
        The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
        The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
        You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].
        Some examples:
        "1 + 1" = 2
        " 6-4 / 2 " = 4
        "2*(5+5*2)/3+(6/2+8)" = 21
        "(2+6* 3+5- (3*14/7+2)*5)+3"=-12


        Note: Do not use the eval built-in library function.
        答案：包括加减乘除非负整数 带括号 而且包含空格
        
 */

        class Solution {
        public int calculate(String s) {
        int num = 0;
        int result = 0;
        int i = 0;
        char sign = '+';

        Stack<Integer> stack = new Stack<>();

        while(i <= s.length()){//calculating each block of number operations, a block can be a single number or the result inside of a pair of brackets.
        //note that we use <= instead of <, we dont skip the last character
        char ch = i == s.length()? '+':s.charAt(i);
        i++;
        if(ch == ' ') continue;

        if(ch <= '9' && ch >= '0'){
        num = num*10+(ch-'0');
        }else{
        if(ch == '('){
        int left = 1;
        int end = i;
        while(left != 0){
        if(s.charAt(end) == '(') left++;
        if(s.charAt(end) == ')') left--;
        end++;
        }
        num = calculate(s.substring(i,end-1));
        i = end;
        }else{//ch is not '('
        if(sign == '+'){
        stack.push(num);
        }

        if(sign == '-'){
        stack.push(-num);
        }

        if(sign == '*'){
        stack.push(stack.pop()*num);
        }

        if(sign == '/'){
        stack.push(stack.pop()/num);
        }

        num = 0;
        sign = ch;
        }
        }
        }


        while(!stack.isEmpty()) {//adding all numbers in the stack as the result
        result += stack.pop();
        }
        return result;
        }
        }
