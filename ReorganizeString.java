/*
Reorganize String

        Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
        If possible, output any possible result.  If not possible, return the empty string.
        Example 1:
        Input: S = "aab"
        Output: "aba"

        Example 2:
        Input: S = "aaab"
        Output: ""

        Note:
        S will consist of lowercase letters and have length in range [1, 500].
        答案：
        No Sort O(N):
        count letter appearance and store in hash[i]
        find the letter with largest occurence.
        put the letter into even index numbe (0, 2, 4 ...) char array
        put the rest into the array


 */
class Solution {
    public String reorganizeString(String S) {

        int max = 0, idx = 0;

        int[] count = new int[26];


        //count the number of letter's appearance in string S
        for(int i=0; i<S.length(); i++){
            count[S.charAt(i)-'a']++;
        }

        char[] result = new char[S.length()];
        int letter = 0;


        //count the maximum value appeared.
        for(int i=0; i<count.length; i++){
            if(count[i] > max){
                max = count[i];
                letter = i;
            }
        }

        if(max > (S.length()+1)/2) return ""; //if so, it is impossible to reorganize


        //fill in the letters at index 0,2,4.... in the result array.

        while(count[letter] > 0){

            result[idx] = (char)(letter+'a');
            idx += 2;
            count[letter]--;

        }

        //fill in the letters at other indices in the result array.
        for(int i=0; i<count.length; i++){
            while(count[i] > 0){
                if(idx >= result.length){
                    idx = 1;
                }

                result[idx] = (char)(i+'a');
                idx += 2;
                count[i]--;
            }
        }

        return String.valueOf(result);

    }
}
