/*
String to Integer(atoi)
        Implement atoi which converts a string to an integer.

        The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

        The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

        If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

        If no valid conversion could be performed, a zero value is returned.

        Note:

        Only the space character ' ' is considered as whitespace character.
        Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
        Example 1:

        Input: "42"
        Output: 42
        Example 2:

        Input: "   -42"
        Output: -42
        Explanation: The first non-whitespace character is '-', which is the minus sign.
        Then take as many numerical digits as possible, which gets 42.
        Example 3:

        Input: "4193 with words"
        Output: 4193
        Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
        Example 4:

        Input: "words and 987"
        Output: 0
        Explanation: The first non-whitespace character is 'w', which is not a numerical
        digit or a +/- sign. Therefore no valid conversion could be performed.
        Example 5:

        Input: "-91283472332"
        Output: -2147483648
        Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
        Thefore INT_MIN (−231) is returned.

        需要按照如下顺序：
        1. 首先需要丢弃字符串前面的空格；

        2. 然后可能有正负号（注意只取一个，如果有多个正负号，那么说这个字符串是无法转换的，返回0。比如测试用例里就有个“+-2”）；

        3. 字符串可以包含0~9以外的字符，如果遇到非数字字符，那么只取该字符之前的部分，如“-00123a66”返回为“-123”；

        4. 如果超出int的范围，返回边界值（2147483647或-2147483648）。
        if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE%10 < digit)

        例如input = -91283472332, Integer.MAX_VALUE = 2147483647, Integer.MAX_VALUE/10 = 214748364 < 9128347233, sign = -1, return Integer.MIN_VALUE

        例如input = -2147483648, Integer.MAX_VALUE/10 = 214748364 = total, Integer.MAX_VALUE % 10 = 7 < digit = 8, sign = -1, return Integer.MIN_VALUE;



 */

class Solution {
    public int myAtoi(String str) {

        int index = 0;
        int total = 0;
        int sign = 1;

        //empty string
        if(str.length() == 0) return 0;

        //remove spaces in the beginning
        while(index < str.length() && str.charAt(index) == ' '){
            index++;
        }

        if(index == str.length()) return 0;

        //get sign
        if(str.charAt(index) == '+' || str.charAt(index) == '-'){
            sign = str.charAt(index)=='+'? 1 : -1;
            index++;
        }

        //compute the number
        while(index < str.length()){

            int digit = str.charAt(index)-'0';
            //if digit is not a number
            if(digit < 0 || digit > 9) break;

            //if the number is overflow
            if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE % 10 < digit){
                return sign == 1? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            total = total*10+digit;
            index++;

        }

        return total*sign;
    }
}
