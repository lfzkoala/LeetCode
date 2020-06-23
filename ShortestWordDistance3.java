/*
Shortest Word Distance III
        Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

        word1 and word2 may be the same and they represent two individual words in the list.

        Example:
        Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

        Input: word1 = “makes”, word2 = “coding”
        Output: 1
        Input: word1 = "makes", word2 = "makes"
        Output: 3
        Note:
        You may assume word1 and word2 are both in the list.

        类似于shortest word distance I. 多考虑一个word1 = word2的情况 这种情况下用两个指针p1, p2记录每段相同字符串之间的距离 记录的方法如下图
        *-------*---*------*
        另外需要注意的是p1, p2不能像之前一样的-1 因为这种情况下p1-p2=0, 所以需要将p1, p2分别设置为minDistance和-minDistance. 于是minDistance, p1, p2需要设置为long整形 最后返回需要转换为int类型

        方法1：

 */

class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {

        long minDistance = Integer.MAX_VALUE, p1 = minDistance, p2 = -minDistance;

        for(int i=0; i<words.length; i++){

            if(words[i].equals(word1)){
                if(word1.equals(word2)){
                    p1 = p2;
                    p2 = i;
                }else{
                    p1 = i;
                }
            }else if(words[i].equals(word2)){
                p2 = i;
            }

            minDistance = Math.min(minDistance, Math.abs(p1-p2));

        }

        return (int) minDistance;

    }
}

/*
方法2：优化方法1代码

 */

class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {

        int p1 = -1, p2 = -1;
        int minDistance = Integer.MAX_VALUE;

        for(int i=0; i<words.length; i++){
            if(words[i].equals(word1)){
                if(word1.equals(word2)){
                    if(p1 != -1){
                        minDistance = Math.min(minDistance, i-p1);
                    }
                    p1 = i;
                }else{
                    p1 = i;
                    if(p2 != -1){
                        minDistance = Math.min(minDistance, i-p2);
                    }
                }
            }else if(words[i].equals(word2)){
                p2 = i;
                if(p1 != -1){
                    minDistance = Math.min(minDistance, i-p1);
                }
            }
        }

        return minDistance;



    }
}
