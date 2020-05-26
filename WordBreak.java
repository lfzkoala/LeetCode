/*
Word Break

        Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

        Note:

        The same word in the dictionary may be reused multiple times in the segmentation.
        You may assume the dictionary does not contain duplicate words.
        Example 1:

        Input: s = "leetcode", wordDict = ["leet", "code"]
        Output: true
        Explanation: Return true because "leetcode" can be segmented as "leet code".
        Example 2:

        Input: s = "applepenapple", wordDict = ["apple", "pen"]
        Output: true
        Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
        Note that you are allowed to reuse a dictionary word.
        Example 3:

        Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
        Output: false

 */

class Solution {

    //The idea: using dynamic programming dp[i] to record whether s[0...i] can be combined by words in wordDict, output dp[s.length()].

    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> words = new HashSet<String>(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;

        for(int i=1; i<=s.length(); i++){
            for(int j=i-1; j>=0; j--){
                if(dp[j]==true && words.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];



    }
}


