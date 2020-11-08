import java.util.*;
public class TinyURL {

    public static void main(String[] args){
        TinyURL P = new TinyURL();
        String shortURL = P.idToShortURL(1200);
        System.out.println(shortURL);
        int id = P.shortURLtoID(shortURL);
        System.out.println(id);
    }

    public char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private String idToShortURL(int n){
        StringBuilder URL = new StringBuilder();
        while(n != 0){
            URL.append(map[n%62]);
            n/=62;
        }
        return URL.toString();

    }

    private int shortURLtoID(String shortURL){
        int id = 0;
        for(int i=shortURL.length()-1; i>=0; i--){
            char c = shortURL.charAt(i);
            if(c >= 'a' && c <='z'){
                id = id*62+c-'a';
            }else if(c >= 'A' && c <= 'Z'){
                id = id*62+c-'A'+26;
            }else if(c >= '0' && c <= '9') {
                id = id * 62 + c - '0' + 52;
            }
        }
        return id;

    }




}
