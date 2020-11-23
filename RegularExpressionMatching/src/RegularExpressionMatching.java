public class RegularExpressionMatching {

    public static void main(String[] args){
        String s = "aab", p = "c*a*b";
        RegularExpressionMatching P = new RegularExpressionMatching();
        boolean result = P.isMatch(s,p);
        System.out.println(result);
    }

    public boolean isMatch(String s, String p){
        //dp[i][j] means s(0...i) matches with p(0...j)
        //if s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.' dp[i][j] = dp[i-1][j-1]
        //if p.charAt(j-1) == '*' dp[i][j] = dp[i][j-2]
        //if s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.' dp[i][j] = dp[i][j] || dp[i-1][j]

        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        //case s = "abc", p = "a*b*c"
        for(int j=1; j<dp[0].length; j++) {
            if(p.charAt(j-1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }else if(p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i][j-2];
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }

        return dp[m][n];

    }

}
