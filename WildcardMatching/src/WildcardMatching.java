public class WildcardMatching {
    public static void main(String[] args){
        String s = "adceb", p = "*a*b";
        WildcardMatching P = new WildcardMatching();
        boolean result = P.isMatch(s,p);
        System.out.println(result);

    }

    public boolean isMatch(String s, String p){
        //p contains ? and *.
        //? matches any single character
        //* mathces any sequence of characters
        //example: s = adceb, p = *a*b
        int i=0, j=0, indexStar = -1, match = -1;

        while(i < s.length()){
            if(j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')){
                i++;
                j++;
            }else if(j < p.length() && p.charAt(j) == '*'){
                indexStar = j;
                j++;
                match = i;
            }else if(indexStar != -1) {
                j = indexStar + 1;
                match++;
                i = match;
            }else{
                return false;
            }
        }

        while(j < p.length() && p.charAt(j) == '*') j++;
        return j == p.length();

    }



}
