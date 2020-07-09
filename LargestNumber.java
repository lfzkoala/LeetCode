/*
Largest Number
        Given a list of non negative integers, arrange them such that they form the largest number.

        Example 1:

        Input: [10,2]
        Output: "210"
        Example 2:

        Input: [3,30,34,5,9]
        Output: "9534330"
        Note: The result may be very large, so you need to return a string instead of an integer.

        答案：

 */

class Solution {
    public String largestNumber(int[] nums) {

        String[] numString = new String[nums.length];

        for(int i=0; i<nums.length; i++){
            numString[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numString, (s1,s2) -> (s2+s1).compareTo(s1+s2)); //sort the array with larger number in front of smaller number.

        if(numString[0].charAt(0) == '0') return "0";

        StringBuilder sb = new StringBuilder();

        for(String s:numString){
            sb.append(s);
        }

        return sb.toString();

    }
}
