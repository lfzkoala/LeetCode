/*
Interleaving String

        Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
        Example 1:
        Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
        Output: true

        Example 2:
        Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
        Output: false
        答案：dynamic programming

 */

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {

        int len1 = s1.length();
        int len2 = s2.length();

        if(len1+len2 != s3.length()) return false;

        boolean[][] dp = new boolean[len1+1][len2+1];

        dp[0][0] = true;


        for(int i=1; i<=len1; i++){
            //base case: up to down
            dp[i][0] = dp[i-1][0] && (s1.charAt(i-1) == s3.charAt(i-1));
        }

        for(int i=1; i<=len2; i++){
            //base case: left to right
            dp[0][i] = dp[0][i-1] && (s2.charAt(i-1) == s3.charAt(i-1));

        }

        for(int i=1; i<=len1; i++){
            for(int j=1; j<=len2; j++){
                //special case: up and left has the same character as s3.
                if((s1.charAt(i-1) == s3.charAt(i+j-1)) && (s2.charAt(j-1) == s3.charAt(i+j-1))){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }else if(s1.charAt(i-1) == s3.charAt(i+j-1)){
                    dp[i][j] = dp[i-1][j];
                }else if(s2.charAt(j-1) == s3.charAt(i+j-1)){
                    dp[i][j] = dp[i][j-1];
                }
            }
        }

        return dp[len1][len2];
    }
}
