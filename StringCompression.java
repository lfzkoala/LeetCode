/*
String Compression
        Given an array of characters, compress it in-place.
        The length after compression must always be smaller than or equal to the original array.
        Every element of the array should be a character (not int) of length 1.
        After you are done modifying the input array in-place, return the new length of the array.

        Follow up:
        Could you solve it using only O(1) extra space?

        Example 1:
        Input:
        ["a","a","b","b","c","c","c"]

        Output:
        Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

        Explanation:
        "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".


        Example 2:
        Input:
        ["a"]

        Output:
        Return 1, and the first 1 characters of the input array should be: ["a"]

        Explanation:
        Nothing is replaced.


        Example 3:
        Input:
        ["a","b","b","b","b","b","b","b","b","b","b","b","b"]

        Output:
        Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

        Explanation:
        Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
        Notice each digit has it's own entry in the array.


        Note:
        All characters have an ASCII value in [35, 126].
        1 <= len(chars) <= 1000.
        答案：对每个字符的出现次数进行计算即可 注意如果只出现一次则可以不用插入数字 需要引入新的指针更改数组中的字符

 */
class Solution {
    public int compress(char[] chars) {

        int result = 0;
        int idx = 0;

        while(idx < chars.length){
            char currentChar = chars[idx];
            int count = 0;

            while(idx < chars.length && chars[idx] == currentChar){
                idx++;
                count++;
            }

            chars[result] = currentChar;
            result++;
            if(count != 1){//注意要判断count != 1
                for(char c: Integer.toString(count).toCharArray()){
                    chars[result] = c;
                    result++;
                }
            }

        }

        return result;

    }
}
