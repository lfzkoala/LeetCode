/*
Fraction To Recurring Decimal
        Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

        If the fractional part is repeating, enclose the repeating part in parentheses.

        Example 1:

        Input: numerator = 1, denominator = 2
        Output: "0.5"
        Example 2:

        Input: numerator = 2, denominator = 1
        Output: "2"
        Example 3:

        Input: numerator = 2, denominator = 3
        Output: "0.(6)"

        答案：

 */

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {

        //很多边界条件要注意
        if(numerator == 0) return "0";

        StringBuilder result = new StringBuilder();

        result.append(((numerator > 0) ^ (denominator > 0)) ? "-":""); //if one of numerator and denominator is negative, append "-". If both have same sign, then set ""

        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator); //类型设为long, 如果不加(long),

        //before decimal
        result.append(num/den);
        num %= den;

        if(num == 0) return result.toString();

        //decimal and after decimal
        result.append(".");

        HashMap<Long, Integer> map = new HashMap<>();
        map.put(num, result.length());

        while(num != 0){
            num *= 10;
            result.append(num/den);
            num %= den;

            //if map has contained num, append parentheses accordingly and break the while loop
            if(map.containsKey(num)){
                int index = map.get(num);
                result.insert(index, "("); //result.insert
                result.append(")");
                break;
            }else{
                map.put(num, result.length());
            }
        }

        return result.toString();



    }
}
